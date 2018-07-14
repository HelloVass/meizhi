package info.hellovass.widgets.loadmore

interface ILoadMore {

    fun onLoadMoreBegin()

    fun onLoadMoreSucceed(hasMoreItems: Boolean)

    fun onLoadMoreFailed()

    fun resetLoadMore()
}