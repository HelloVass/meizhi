package info.hellovass.meizhi.main

import info.hellovass.library.mvp.m.IRepo
import info.hellovass.meizhi.dto.MeiZhiDTO
import info.hellovass.meizhi.lib.network.Api
import info.hellovass.meizhi.lib.network.ApiClient
import info.hellovass.meizhi.lib.network.Result
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MainRepo :IRepo{

    fun getMeiZhis(count: Int, page: Int): Observable<Result<List<MeiZhiDTO>>> {

        return ApiClient.sINSTANCE
                .provideApi(Api::class.java)
                .getMeiZhis(count, page)
                .subscribeOn(Schedulers.io())
    }
}
