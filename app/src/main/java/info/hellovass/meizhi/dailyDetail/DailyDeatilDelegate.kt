package info.hellovass.meizhi.dailyDetail

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.meizhi.R

class DailyDeatilDelegate(activity: AppCompatActivity) : ActivityDelegate(activity) {

    override fun getLayoutResId(): Int {
        return R.layout.activity_daily_detail
    }

    fun setBitmap(bitmap: Bitmap) {

        find<ImageView>(R.id.ivHeader)
                .setImageBitmap(bitmap)
    }
}
