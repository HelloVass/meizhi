package info.hellovass.imageloader

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import io.reactivex.Maybe
import io.reactivex.Observable

class GlideLoader : ImageLoader {

    override
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