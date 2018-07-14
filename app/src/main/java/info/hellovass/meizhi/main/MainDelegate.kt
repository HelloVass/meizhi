package info.hellovass.meizhi.main

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.dto.MeiZhiDTO
import info.hellovass.widgets.loadmore.ILoadMore
import info.hellovass.widgets.loadmore.LoadMore
import info.hellovass.meizhi.R
import org.jetbrains.anko.toast


class MainDelegate(activity: AppCompatActivity) : ActivityDelegate(activity), ILoadMore {

    private val viewAdapter by lazy { MeiZhisAdapter(activity) }

    private lateinit var rcvList: RecyclerView

    private lateinit var refreshLayout: SwipeRefreshLayout

    private lateinit var loadMore: ILoadMore

    override fun getLayoutResId(): Int {

        return R.layout.activity_main
    }

    fun setupRefreshLayout() {
        refreshLayout = find(R.id.refreshLayout)
        refreshLayout.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2,
                R.color.refresh_progress_3)
    }

    fun setupRcvList() {
        rcvList = find(R.id.rcvList)
        rcvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rcvList.adapter = viewAdapter
    }

    fun setupLoadMore(onLoadMoreListener: () -> Unit) {

        loadMore = LoadMore.Builder(activity)
                .setRecyclerView(rcvList)
                .setLoadMoreListener {

                    if (!refreshLayout.isRefreshing) {
                        onLoadMoreListener()
                    }
                }
                .build()
    }

    fun bindOnRefreshListener(onRefreshListener: () -> Unit) {
        refreshLayout.setOnRefreshListener(onRefreshListener)
    }

    fun setItems(data: List<MeiZhiDTO>) {
        viewAdapter.setItems(data)
    }

    fun addItems(data: List<MeiZhiDTO>) {
        viewAdapter.addItems(data)
    }

    fun showRefreshing(isRefreshing: Boolean) {
        refreshLayout.isRefreshing = isRefreshing
    }

    override fun onLoadMoreBegin() {
        loadMore.onLoadMoreBegin()
    }

    override fun onLoadMoreSucceed(hasMoreItems: Boolean) {
        loadMore.onLoadMoreSucceed(hasMoreItems)
    }

    override fun onLoadMoreFailed() {
        loadMore.onLoadMoreFailed()
    }

    override fun resetLoadMore() {
        loadMore.resetLoadMore()
    }

    fun toast(message: String?) {
        activity.toast(message ?: "")
    }
}