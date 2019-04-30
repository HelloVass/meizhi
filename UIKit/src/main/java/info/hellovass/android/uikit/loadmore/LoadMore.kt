package info.hellovass.android.uikit.loadmore

import android.support.v7.widget.RecyclerView

class LoadMore private constructor(
    private val recyclerView: RecyclerView,
    private val loadMore: (() -> Unit)?
) : ILoadMore {

    private val realAdapter = recyclerView.adapter!!

    private val loadMoreAdapter: LoadMoreAdapter = LoadMoreAdapter(
        realAdapter = realAdapter
    )

    private val onReachBottomListener = OnReachBottomListener(onReachBottom = {
        if (state.error != null)
            return@OnReachBottomListener

        if (state.loading)
            return@OnReachBottomListener

        if (!state.hasMore)
            return@OnReachBottomListener

        loadMore?.invoke()
    })

    private var state: ILoadMore.State = ILoadMore.State(
        loading = false,
        hasMore = true,
        error = null,
        title = ""
    )

    constructor(builder: Builder) : this(
        recyclerView = builder.recyclerView,
        loadMore = builder.loadMore
    )

    init {
        recyclerView.addOnScrollListener(onReachBottomListener)
        recyclerView.adapter = loadMoreAdapter
    }

    override fun setState(state: ILoadMore.State) {
        this.state = state
    }

    class Builder(val recyclerView: RecyclerView) {

        var loadMore: (() -> Unit)? = null
            private set

        fun setLoadMore(listener: () -> Unit) = apply {
            this.loadMore = listener
        }

        fun build() = LoadMore(this)
    }
}