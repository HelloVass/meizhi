package info.hellovass.meizhi.lib.network

import info.hellovass.meizhi.dto.MeiZhiDTO
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("data/福利/{count}/{page}")
    fun getMeiZhis(@Path("count") count: Int, @Path("page") page: Int): Observable<Result<List<MeiZhiDTO>>>
}