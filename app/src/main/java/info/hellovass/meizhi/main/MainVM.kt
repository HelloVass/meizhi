package info.hellovass.meizhi.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import info.hellovass.meizhi.data.MeiZhi
import info.hellovass.meizhi.net.Resource
import info.hellovass.meizhi.net.RxSchedulerHelper

class MainVM(private val context: Application, private val mainRepo: MainRepo) : AndroidViewModel(context) {

    val meizhis: MutableLiveData<Resource<List<MeiZhi>>> by lazy {

        MutableLiveData<Resource<List<MeiZhi>>>()
    }

    /**
     * 获取小姐姐列表
     */
    fun getMeiZhis(count: Int = 10, page: Int = 1) {

        mainRepo.getMeiZhis(count, page)
                .compose(RxSchedulerHelper.io2main())
                .subscribe({ response -> meizhis.value = response }, { throwable -> meizhis.value = Resource.error(throwable?.message) })
    }
}