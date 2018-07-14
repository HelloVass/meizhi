package info.hellovass.meizhi.main

import info.hellovass.architecture.mvp.special.m.IRepo
import info.hellovass.dto.MeiZhiDTO
import info.hellovass.network.Api
import info.hellovass.network.ApiClient
import info.hellovass.network.Result
import io.reactivex.Observable

class MainRepo : IRepo {

    fun getMeiZhis(count: Int, page: Int): Observable<Result<List<MeiZhiDTO>>> {

        return ApiClient.sINSTANCE
                .provideApi(Api::class.java)
                .getMeiZhis(count, page)
    }
}
