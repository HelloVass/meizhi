package info.hellovass.meizhi.main

import info.hellovass.architecture.mvp.special.m.IRepo
import info.hellovass.dto.MeiZhiData
import info.hellovass.network.Api
import info.hellovass.network.ApiClient
import io.reactivex.Observable

class MainRepo : IRepo {

    fun getMeiZhiData(count: Int, page: Int): Observable<MeiZhiData> {

        return ApiClient.sINSTANCE
                .provideApi(Api::class.java)
                .getMeiZhis(count, page)
    }
}
