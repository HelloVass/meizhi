package info.hellovass.meizhi.lib

import android.support.v7.widget.LinearLayoutManager

class LinearLayoutManagerDelegate(private val linearLayoutManager: LinearLayoutManager) : ILayoutManager {

    override fun getItemCount(): Int {

        return linearLayoutManager.itemCount
    }

    override fun findLastCompletelyVisibleItemPosition(): Int {

        return linearLayoutManager.findLastCompletelyVisibleItemPosition()
    }
}
