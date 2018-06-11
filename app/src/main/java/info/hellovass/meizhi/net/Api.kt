package info.hellovass.meizhi.net

import info.hellovass.meizhi.data.MeiZhi
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("data/福利/{count}/{page}")
    fun getMeizhis(@Path("count") count: Int, @Path("page") page: Int): Single<Result<List<MeiZhi>>>
}