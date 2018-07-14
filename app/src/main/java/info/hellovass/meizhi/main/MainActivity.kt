package info.hellovass.meizhi.main

import android.os.Bundle
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.dto.MeiZhiDTO
import info.hellovass.network.Resource
import info.hellovass.network.RxResultHandler
import info.hellovass.network.RxSchedulerHelper
import info.hellovass.network.Status

class MainActivity : ActivityPresenter<MainDelegate, MainRepo>() {

    private var pageNum = 1

    override fun createRepo(): MainRepo? {

        return MainRepo()
    }

    override fun createViewDelegate(): MainDelegate {

        return MainDelegate(this)
    }

    override fun initWidgets() {

        // 下拉刷新控件初始化
        viewDelegate?.setupRefreshLayout()

        // 列表控件初始化
        viewDelegate?.setupRcvList()

        // 加载更多控件初始化
        viewDelegate?.setupLoadMore {

            loadData(false)
        }
    }

    override fun bindEvent() {

        viewDelegate?.bindOnRefreshListener {

            loadData(true)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        loadData(true)
    }

    private fun loadData(pullToRefresh: Boolean) {

        if (pullToRefresh) {
            pageNum = 1
        }

        repo?.let {

            it.getMeiZhis(count = 10, page = pageNum)
                    .compose(RxResultHandler.handleResult())
                    .onErrorReturn { Resource.error(it.message) }
                    .compose(RxSchedulerHelper.io2main())
                    .startWith(Resource.loading())
                    .subscribe { handleResource(pullToRefresh, it) }
        }
    }

    private fun handleResource(pullToRefresh: Boolean, resource: Resource<List<MeiZhiDTO>>?) {

        when (resource?.status) {

            Status.LOADING -> {
                if (pullToRefresh) {
                    viewDelegate?.showRefreshing(true)
                } else {
                    viewDelegate?.onLoadMoreBegin()
                }
            }
            Status.SUCCESS -> {
                if (pullToRefresh) {
                    viewDelegate?.setItems(resource.data!!)
                    viewDelegate?.showRefreshing(false)
                    viewDelegate?.resetLoadMore()
                } else {
                    viewDelegate?.addItems(resource.data!!)
                    viewDelegate?.onLoadMoreSucceed(true)
                }
                pageNum++
            }
            Status.ERROR -> {
                if (pullToRefresh) {
                    viewDelegate?.showRefreshing(false)
                } else {
                    viewDelegate?.onLoadMoreFailed()
                }
            }
        }
    }
}




