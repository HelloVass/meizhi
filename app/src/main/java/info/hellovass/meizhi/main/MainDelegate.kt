package info.hellovass.meizhi.main

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.paginate.Paginate
import info.hellovass.library.mvp.v.IDelegate
import info.hellovass.meizhi.R
import info.hellovass.meizhi.dto.MeiZhiDTO
import org.jetbrains.anko.toast


class MainDelegate : IDelegate {

    private val viewAdapter by lazy { MeiZhisAdapter() }

    lateinit var rcvList: RecyclerView

    lateinit var refreshLayout: SwipeRefreshLayout

    lateinit var paginate: Paginate

    private var isLoading: Boolean = false

    override fun getLayoutResId(): Int {

        return R.layout.activity_main
    }

    fun setupRefreshLayout(activity: AppCompatActivity) {

        refreshLayout = activity.findViewById(R.id.refreshLayout)
        refreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
                R.color.refresh_progress_2, R.color.refresh_progress_3)
    }

    fun setupRcvList(activity: AppCompatActivity) {

        rcvList = activity.findViewById(R.id.rcvList)
        rcvList.layoutManager = GridLayoutManager(activity, 2)
        rcvList.adapter = viewAdapter
    }

    fun setupLoadMore(onLoadMoreListener: () -> Unit) {

        paginate = Paginate.with(rcvList, object : Paginate.Callbacks {

            override fun onLoadMore() = onLoadMoreListener.invoke()

            override fun isLoading(): Boolean = this@MainDelegate.isLoading

            override fun hasLoadedAllItems(): Boolean = false

        }).setLoadingTriggerThreshold(0).build()
        paginate.setHasMoreDataToLoad(false)
    }

    fun refreshData(data: List<MeiZhiDTO>) {

        viewAdapter.refresh(data)
    }

    fun insertAll(data: List<MeiZhiDTO>) {

        viewAdapter.insert(data)
    }

    fun showRefreshing() {

        refreshLayout.isRefreshing = true
    }

    fun hideRefreshing() {

        refreshLayout.isRefreshing = false
    }

    fun showPaginateLoading() {

        isLoading = true
    }

    fun hidePaginateLoading() {

        isLoading = false
    }

    fun toast(activity: AppCompatActivity, error: String?) {

        activity.toast(error ?: "未知错误")
    }
}