package info.hellovass.widgets.loadmore

import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * 对 StaggeredGridLayoutManager 的扩展
 */
fun StaggeredGridLayoutManager.findLastCompletelyVisibleItemPosition(): Int {

    val lastPositions = IntArray(spanCount)
    this.findLastVisibleItemPositions(lastPositions)
    return findMax(lastPositions)
}

private fun findMax(lastPositions: IntArray): Int {

    var max = lastPositions[0]

    for (value in lastPositions) {
        if (value > max) {
            max = value
        }
    }
    return max
}
