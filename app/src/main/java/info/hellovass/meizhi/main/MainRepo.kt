package info.hellovass.meizhi.main

import info.hellovass.meizhi.dto.MeiZhiDTO
import info.hellovass.meizhi.lib.network.*
import io.reactivex.Single

class MainRepo {

    fun getMeiZhis(count: Int, page: Int): Single<List<MeiZhiDTO>> {

        return ApiClient.sINSTANCE
                .provideApi(Api::class.java)
                .getMeiZhis(count, page)
                .compose(RxResultHandler.handleResult())
    }
}
