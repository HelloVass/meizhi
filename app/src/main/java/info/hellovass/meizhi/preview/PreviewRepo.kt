package info.hellovass.meizhi.preview

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import info.hellovass.architecture.mvp.special.m.IRepo
import io.reactivex.Maybe
import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream

class PreviewRepo : IRepo {

    companion object {
        val saveDir: String = File(Environment.getExternalStorageDirectory(), "MeiZhi").absolutePath
    }

    fun saveToDisk(activity: Activity, imageUrl: String, fileName: String): Observable<Uri> {

        return maybe1(activity, imageUrl)
                .flatMap { bitmap -> maybe2(fileName, bitmap) }
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

    private fun maybe2(fileName: String, bitmap: Bitmap): Maybe<Uri> {

        return Maybe.create<Uri> {

            try {
                val saveDir = File(saveDir)
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

    fun loadImage(context: Context, imageUrl: String): Observable<Bitmap> {

        return Maybe.create<Bitmap> { emitter ->

            try {
                val target = Glide.with(context)
                        .asBitmap()
                        .load(imageUrl)
                        .submit()
                emitter.onSuccess(target.get())
            } catch (e: Throwable) {
                emitter.onError(e)
            }
        }.toObservable()
    }
}



