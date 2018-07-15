package info.hellovass.meizhi.preview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.OnViewTapListener
import com.github.chrisbanes.photoview.PhotoView
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.meizhi.R

class PreviewDelegate(activity: AppCompatActivity) : ActivityDelegate(activity) {

    override fun getLayoutResId(): Int = R.layout.activity_preview

    fun loadImage(extras: Bundle?) {

        Glide.with(activity)
                .load(extras?.getString("url"))
                .into(find(R.id.ivPreview))
    }

    fun bindOnViewTapListener(onViewTapListener: OnViewTapListener) {

        find<PhotoView>(R.id.ivPreview)
                .setOnViewTapListener(onViewTapListener)
    }
}