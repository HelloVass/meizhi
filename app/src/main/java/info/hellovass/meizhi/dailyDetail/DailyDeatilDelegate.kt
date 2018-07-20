package info.hellovass.meizhi.dailyDetail

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.meizhi.R

class DailyDeatilDelegate(activity: AppCompatActivity, private val viewAdapter: DailyDetailAdapter) : ActivityDelegate(activity), Action by viewAdapter {

    private lateinit var rcvList: RecyclerView

    override fun getLayoutResId(): Int = R.layout.activity_daily_detail

    fun setBitmap(bitmap: Bitmap) {

        find<ImageView>(R.id.ivHeader)
                .setImageBitmap(bitmap)
    }

    fun setupRcvList(onItemClickListener: OnItemClickListener) {

        rcvList = find(R.id.rcvList)
        rcvList.layoutManager = LinearLayoutManager(activity)
        rcvList.adapter = viewAdapter
        viewAdapter.onItemClickListener = onItemClickListener
    }
}
