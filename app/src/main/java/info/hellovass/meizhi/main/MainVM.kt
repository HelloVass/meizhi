package info.hellovass.meizhi.main

import android.arch.lifecycle.MutableLiveData
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import info.hellovass.meizhi.base.BaseVM
import info.hellovass.meizhi.dto.MeiZhiDTO
import info.hellovass.meizhi.lib.network.Resource
import info.hellovass.meizhi.lib.network.RxSchedulerHelper

class MainVM(private val mainRepo: MainRepo) : BaseVM() {

    val meiZhisObservable = MutableLiveData<Resource<List<MeiZhiDTO>>>()

    val meiZhisDataSet = mutableListOf<MeiZhiDTO>()

    fun provideOnRefreshListener(): SwipeRefreshLayout.OnRefreshListener? {

        return SwipeRefreshLayout.OnRefreshListener {

            loadData(1)
        }
    }

    fun provideOnScrollListener(): RecyclerView.OnScrollListener? {

        return object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {

                val viewLayoutManager = (recyclerView?.layoutManager as LinearLayoutManager)

                if (viewLayoutManager.findFirstCompletelyVisibleItemPosition()
                        >= viewLayoutManager.itemCount - 1) {

                    loadData(1)
                }
            }
        }
    }

    fun loadData(page: Int) {

        mainRepo.getMeiZhis(count = 10, page = page)
                .compose(RxSchedulerHelper.io2main())
                .doOnSubscribe({ meiZhisObservable.postValue(Resource.loading()) })
                .subscribe({ data ->

                    if (page == 1) {

                        meiZhisDataSet.clear()
                    }

                    data?.let {

                        meiZhisDataSet.addAll(data)
                        meiZhisObservable.postValue(Resource.success(data))
                    }
                }, { error -> meiZhisObservable.postValue(Resource.error(error.message)) })
    }
}

