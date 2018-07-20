package info.hellovass.disk

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import io.reactivex.Maybe
import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream

class DiskHelperDelegate : DiskHelper {

    override
    fun saveToDisk(activity: Activity,
                   imageUrl: String,
                   fileName: String,
                   saveDirPath: String): Observable<Uri> {

        return maybe1(activity, imageUrl)
                .flatMap { bitmap -> maybe2(fileName, bitmap, saveDirPath) }
                .toObservable()
    }

    private fun maybe1(activity: Activity, imageUrl: String): Maybe<Bitmap> {

        return Maybe.create<Bitmap> { emitter ->

            try {
                val target: FutureTarget<Bitmap> = Glide.with(activity)
                        .asBitmap()
                        .load(imageUrl)
                        .submit()
                val bitmap: Bitmap = target.get()
                emitter.onSuccess(bitmap)
            } catch (e: Throwable) {
                emitter.onError(e)
            }
        }
    }

    private fun maybe2(fileName: String, bitmap: Bitmap, saveDirPath: String): Maybe<Uri> {

        return Maybe.create<Uri> {

            try {
                val saveDir = File(saveDirPath)
                val file = File(saveDir, fileName)

                if (!saveDir.exists()) {
                    saveDir.mkdir()
                }

                // 写入到输出流
                val outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                outputStream.close()

                // 成功写入
                it.onSuccess(Uri.fromFile(file))
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}