package info.hellovass.network

import info.hellovass.dto.MeiZhiData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("data/福利/{count}/{page}")
    fun getMeiZhis(@Path("count") count: Int, @Path("page") page: Int): Observable<MeiZhiData>
}