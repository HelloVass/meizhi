package info.hellovass.meizhi.preview

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import info.hellovass.architecture.mvp.special.m.IRepo
import io.reactivex.Maybe
import io.reactivex.MaybeOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PreviewRepo : IRepo {

    val saveDir: String = File(Environment.getExternalStorageDirectory(), "MeiZhi").absolutePath

    fun saveImageToDisk(activity: Activity, extras: Bundle?): Maybe<Uri>? {

        return Maybe.create(MaybeOnSubscribe<Bitmap> { emitter ->

            try {
                val target: FutureTarget<Bitmap> = Glide.with(activity)
                        .asBitmap()
                        .load(extras?.get("url"))
                        .submit()
                val bitmap: Bitmap = target.get()
                emitter.onSuccess(bitmap)
            } catch (e: Throwable) {
                emitter.onError(e)
            }
        }).flatMap { bitmap ->

            val saveDir = File(saveDir)
            val file = File(saveDir, generateFileName(extras))
            val uri = Uri.fromFile(file)

            if (!saveDir.exists()) {
                saveDir.mkdir()
            }

            try {
                saveImage2Disk(file, bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            notifyGallery(activity, uri)

            Maybe.just(uri)
        }.subscribeOn(Schedulers.io())
    }

    private fun saveImage2Disk(file: File, bitmap: Bitmap) {
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
    }

    private fun notifyGallery(activity: Activity, uri: Uri?) {
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri)
        activity.sendBroadcast(intent)
    }

    private fun generateFileName(extras: Bundle?): String? {

        val des = extras?.getString("desc")
        return "$des.jpg"
    }
}



