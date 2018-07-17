package info.hellovass.meizhi.main

import info.hellovass.architecture.mvp.special.m.IRepo
import info.hellovass.dto.MeiZhi
import info.hellovass.network.Api
import info.hellovass.network.ApiClient
import info.hellovass.network.RxResultHelper
import io.reactivex.Observable

class MainRepo : IRepo {

    fun getMeiZhiData(count: Int, page: Int): Observable<List<MeiZhi>> {

        return ApiClient.sINSTANCE
                .provideApi(Api::class.java)
                .getMeiZhis(count, page)
                .compose(RxResultHelper.handleResult())
    }
}
