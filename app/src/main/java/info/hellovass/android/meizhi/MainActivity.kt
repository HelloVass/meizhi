package info.hellovass.android.meizhi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import info.hellovass.android.uikit.loadmore.ILoadMore
import info.hellovass.android.uikit.loadmore.LoadMore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var iLoadMore:ILoadMore?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iLoadMore = LoadMore.Builder(recyclerView = rcvList)
            .setLoadMore {

            }
            .build()
    }
}
