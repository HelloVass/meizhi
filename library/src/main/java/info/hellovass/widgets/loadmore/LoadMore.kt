package info.hellovass.widgets.loadmore

import android.content.Context
import android.support.v7.widget.RecyclerView

class LoadMore private constructor(builder: Builder) : ILoadMore {

    private var isFailed: Boolean = false

    private var isLoading: Boolean = false

    private var hasMore: Boolean = true

    private var context: Context

    private var recyclerView: RecyclerView

    private var loadMoreListener: () -> Unit

    private var userAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    private var loadMoreWrapper: LoadMoreWrapper

    private var myAdapterDataObserver: RecyclerView.AdapterDataObserver

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

            if (isOnBottom(recyclerView, dx, dy)) {
                onReachBottom()
            }
        }
    }

    init {

        context = builder.context

        recyclerView = builder.recyclerView

        loadMoreListener = builder.loadMoreListener

        userAdapter = recyclerView.adapter

        loadMoreWrapper = LoadMoreWrapper(context = context, adapter = userAdapter, status = Status.Idle)

        myAdapterDataObserver = MyAdapterDataObserver(loadMoreWrapper = loadMoreWrapper)

        setupLoadMoreWrapper()

        setupScrollListener()
    }

    private fun setupLoadMoreWrapper() {

        userAdapter.registerAdapterDataObserver(myAdapterDataObserver)
        recyclerView.adapter = loadMoreWrapper
    }

    private fun setupScrollListener() {

        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun onReachBottom() {

        if (isFailed) {
            return
        }

        if (isLoading) {
            return
        }

        if (!hasMore) {
            return
        }
        loadMoreListener()
    }

    override fun onLoadMoreBegin() {

        isFailed = false
        isLoading = true
        loadMoreWrapper.notifyStatusChanged(Status.Loading)
    }

    override fun onLoadMoreSucceed(hasMoreItems: Boolean) {

        isFailed = false
        isLoading = false
        hasMore = hasMoreItems

        if (!hasMoreItems) {
            loadMoreWrapper.notifyStatusChanged(Status.NoMore)
        }
    }

    override fun onLoadMoreFailed() {

        isFailed = true
        isLoading = false
        loadMoreWrapper.notifyStatusChanged(Status.Error)
    }

    override fun resetLoadMore() {

        isFailed = false
        isLoading = false
        hasMore = true
        loadMoreWrapper.notifyStatusChanged(Status.Idle)
    }

    class Builder(internal val context: Context) {

        internal lateinit var recyclerView: RecyclerView

        internal lateinit var loadMoreListener: () -> Unit

        fun setRecyclerView(recyclerView: RecyclerView): Builder {

            this.recyclerView = recyclerView
            return this
        }

        fun setLoadMoreListener(loadMoreListener: () -> Unit): Builder {

            this.loadMoreListener = loadMoreListener
            return this
        }

        fun build(): ILoadMore {

            return LoadMore(this)
        }
    }
}