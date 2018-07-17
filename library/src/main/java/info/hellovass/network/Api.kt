package info.hellovass.network

import info.hellovass.dto.MeiZhi
import info.hellovass.dto.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("data/福利/{count}/{page}")
    fun getMeiZhis(@Path("count") count: Int, @Path("page") page: Int): Observable<Result<List<MeiZhi>>>
}