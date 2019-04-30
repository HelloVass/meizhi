package info.hellovass.android.uikit.loadmore

import android.support.v7.widget.LinearLayoutManager

class LinearManagerAdapter(
    private val manager: LinearLayoutManager
) : ILayoutManager {

    override fun isOnBottom(): Boolean {
        val lastCompletelyVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition()
        val totalCount = manager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - 1
    }
}