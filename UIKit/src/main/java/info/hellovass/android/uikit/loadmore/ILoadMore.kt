package info.hellovass.android.uikit.loadmore

interface ILoadMore {

    fun setState(state: State)

    data class State(
        val loading: Boolean,
        val hasMore: Boolean,
        val error: Throwable?,
        val title: String
    )
}