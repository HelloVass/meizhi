package info.hellovass.meizhi.main

import android.os.Bundle
import info.hellovass.library.mvp.p.ActivityPresenter
import info.hellovass.meizhi.lib.network.Resource
import info.hellovass.meizhi.lib.network.RxResultHandler
import info.hellovass.meizhi.lib.network.RxSchedulerHelper
import info.hellovass.meizhi.lib.network.Status

class MainActivity : ActivityPresenter<MainDelegate, MainRepo>() {

    private var pageNum = 1

    override fun createRepo(): MainRepo? {

        return MainRepo()
    }

    override fun createViewDelegate(): MainDelegate {

        return MainDelegate()
    }

    override fun initWidgets() {

        // 下拉刷新控件初始化
        mViewDelegate?.setupRefreshLayout(this)

        // 列表控件初始化
        mViewDelegate?.setupRcvList(this)
    }

    override fun bindEvent() {

        mViewDelegate?.refreshLayout?.setOnRefreshListener {
            loadData(true)
        }

        mViewDelegate?.setupLoadMore {
            loadData(false)
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

        mRepo?.let {

            it.getMeiZhis(count = 10, page = pageNum)
                    .compose(RxResultHandler.handleResult())
                    .startWith(Resource.loading())
                    .compose(RxSchedulerHelper.io2main())
                    .subscribe({

                        when (it?.status) {
                            Status.SUCCESS -> {
                                if (pullToRefresh) {
                                    mViewDelegate?.refreshData(it.data!!)
                                    mViewDelegate?.hideRefreshing()
                                } else {
                                    mViewDelegate?.insertAll(it.data!!)
                                    mViewDelegate?.hidePaginateLoading()
                                }
                                pageNum++
                            }
                            Status.LOADING -> {
                                if (pullToRefresh) {
                                    mViewDelegate?.showRefreshing()
                                } else {
                                    mViewDelegate?.showPaginateLoading()
                                }
                            }
                            Status.ERROR -> {
                                if (pullToRefresh) {
                                    mViewDelegate?.hideRefreshing()
                                } else {
                                    mViewDelegate?.hidePaginateLoading()
                                }
                            }
                        }
                    }, {
                        mViewDelegate?.toast(this, it.message)
                    })
        }
    }
}




