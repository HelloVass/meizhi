package info.hellovass.meizhi.preview

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.OnViewTapListener
import com.github.chrisbanes.photoview.PhotoView
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.meizhi.R

class PreviewDelegate(activity: AppCompatActivity) : ActivityDelegate(activity) {

    override fun getLayoutResId(): Int = R.layout.activity_preview

    fun setBitmap(bitmap: Bitmap) {

       find<ImageView>(R.id.ivPreview)
               .setImageBitmap(bitmap)
    }

    fun bindOnViewTapListener(onViewTapListener: OnViewTapListener) {

        find<PhotoView>(R.id.ivPreview)
                .setOnViewTapListener(onViewTapListener)
    }

    fun notifyGallery(uri: Uri) {

        activity.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
    }
}