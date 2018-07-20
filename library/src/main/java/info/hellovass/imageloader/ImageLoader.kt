package info.hellovass.imageloader

import android.content.Context
import android.graphics.Bitmap
import io.reactivex.Observable

interface ImageLoader {

    fun loadImage(context: Context, imageUrl: String): Observable<Bitmap>
}