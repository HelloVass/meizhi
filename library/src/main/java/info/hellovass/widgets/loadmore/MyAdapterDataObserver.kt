package info.hellovass.widgets.loadmore

import android.support.v7.widget.RecyclerView
import info.hellovass.widgets.loadmore.LoadMoreWrapper

class MyAdapterDataObserver(private val loadMoreWrapper: LoadMoreWrapper) : RecyclerView.AdapterDataObserver() {

    override fun onChanged() {

        loadMoreWrapper.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {

        loadMoreWrapper.notifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {

        loadMoreWrapper.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {

        loadMoreWrapper.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {

        loadMoreWrapper.notifyItemRangeChanged(positionStart, itemCount)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {

        loadMoreWrapper.notifyItemRangeChanged(positionStart, itemCount, payload)
    }
}