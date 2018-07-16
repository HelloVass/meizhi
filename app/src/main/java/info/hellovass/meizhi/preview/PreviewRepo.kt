package info.hellovass.meizhi.preview

import android.app.Activity
import android.content.Context
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
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PreviewRepo : IRepo {

    val saveDir: String = File(Environment.getExternalStorageDirectory(), "MeiZhi").absolutePath

    fun saveImageToDisk(activity: Activity, imageUrl: String, fileName: String): Maybe<Uri> {

        return maybe1(activity, imageUrl)
                .flatMap { maybe2(fileName, it) }
    }

    private fun maybe1(activity: Activity, imageUrl: String): Maybe<Bitmap> {

        return Maybe.create { emitter ->

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
}



