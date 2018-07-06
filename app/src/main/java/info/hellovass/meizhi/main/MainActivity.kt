package info.hellovass.meizhi.main

import info.hellovass.library.mvp.p.ActivityPresenter
import info.hellovass.meizhi.lib.network.Resource
import info.hellovass.meizhi.lib.network.Result
import info.hellovass.meizhi.lib.network.RxSchedulerHelper
import info.hellovass.meizhi.lib.network.Status
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_main.*
import ru.alexbykov.nopaginate.paginate.NoPaginate

class MainActivity : ActivityPresenter<MainDelegate, MainRepo>() {

    private var pageNum = 1

    private lateinit var noPaginate: NoPaginate

    override fun createRepo(): MainRepo? {

        return MainRepo()
    }

    override fun createViewDelegate(): MainDelegate {

        return MainDelegate()
    }

    override fun initWidgets() {

        mViewDelegate?.setupRcvList(this@MainActivity)
    }

    override fun bindEvent() {

        noPaginate = NoPaginate.with(rcvList)
                .setLoadingTriggerThreshold(0)
                .setOnLoadMoreListener {

                    loadData(false)
                }
                .build()
    }

    private fun loadData(pullToRefresh: Boolean) {

        if (pullToRefresh) {
            pageNum = 1
        }

        mRepo?.let {

            it.getMeiZhis(count = 10, page = pageNum)
                    .compose(translate())
                    .startWith(Resource.loading())
                    .compose(RxSchedulerHelper.io2main())
                    .subscribe({
                        when (it?.status) {
                            Status.SUCCESS -> {

                                mViewDelegate?.insertItems(it.data!!)
                                noPaginate.showLoading(false)
                                pageNum++
                            }
                            Status.ERROR -> {

                                mViewDelegate?.failed()
                                noPaginate.showLoading(false)
                                noPaginate.showError(true)
                            }
                            Status.LOADING -> {

                                mViewDelegate?.loading()
                                noPaginate.showLoading(true)
                            }
                        }
                    })
        }
    }

    private fun <T> translate(): ObservableTransformer<Result<T>, Resource<T>> {

        return ObservableTransformer { upstream ->

            upstream.flatMap { (error, results) ->

                if (!error) {
                    Observable.just(Resource.success(results))
                } else {
                    Observable.error(Throwable("error..."))
                }
            }
        }
    }
}




