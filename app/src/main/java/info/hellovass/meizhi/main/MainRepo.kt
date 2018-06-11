package info.hellovass.meizhi.main

import info.hellovass.meizhi.data.MeiZhi
import info.hellovass.meizhi.net.*
import io.reactivex.Single

class MainRepo {

    fun getMeiZhis(count: Int, page: Int): Single<Resource<List<MeiZhi>>> {

        return ApiClient.sINSTANCE
                .provideApi(Api::class.java)
                .getMeizhis(count, page)
                .compose(RxHandleResult.handleResult())
    }
}
