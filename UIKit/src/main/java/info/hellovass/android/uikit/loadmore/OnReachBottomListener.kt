package info.hellovass.android.uikit.loadmore

import android.support.v7.widget.RecyclerView

class OnReachBottomListener(
    private val onReachBottom: () -> Unit
) : RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (LayoutManagerAdapter(manager = recyclerView.layoutManager).isOnBottom()) {
            onReachBottom()
        }
    }
}