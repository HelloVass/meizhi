package info.hellovass.widgets.loadmore

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager

interface ILayoutManager {

    fun isOnBottom(): Boolean
}

class LinearLayoutManagerImpl(private val linearLayoutManager: LinearLayoutManager) : ILayoutManager {

    override fun isOnBottom(): Boolean {

        val lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = linearLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - 1
    }
}

class GridLayoutManagerImpl(private val gridLayoutManager: GridLayoutManager) : ILayoutManager {

    override fun isOnBottom(): Boolean {

        val lastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = gridLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - 1
    }
}

class StaggeredLayoutManagerImpl(private val staggeredGridLayoutManager: StaggeredGridLayoutManager) : ILayoutManager {

    override fun isOnBottom(): Boolean {

        val lastCompletelyVisibleItemPosition = staggeredGridLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = staggeredGridLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - 1
    }
}


