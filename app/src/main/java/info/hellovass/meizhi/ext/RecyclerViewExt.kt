package info.hellovass.meizhi.ext

import android.support.v7.widget.RecyclerView
import info.hellovass.meizhi.lib.ILayoutManager

fun RecyclerView.bindPuller(iLayoutManager: ILayoutManager, loadMoreHandler: () -> Unit) {

    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {

            if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                    iLayoutManager.findLastCompletelyVisibleItemPosition() >= iLayoutManager.getItemCount() - 1) {

                loadMoreHandler()
            }
        }
    })
}
