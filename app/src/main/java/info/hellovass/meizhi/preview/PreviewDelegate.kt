package info.hellovass.meizhi.preview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.meizhi.R

class PreviewDelegate(activity: AppCompatActivity) : ActivityDelegate(activity) {

    override fun getLayoutResId(): Int = R.layout.activity_preview

    fun loadData(extras: Bundle?) {

        Glide.with(activity)
                .load(extras?.getString("url"))
                .into(find(R.id.ivPreview))
    }
}