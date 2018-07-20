package info.hellovass.network

import info.hellovass.dto.DailyDetail
import info.hellovass.dto.MeiZhi
import info.hellovass.dto.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("data/福利/{count}/{page}")
    fun getMeiZhis(@Path("count") count: Int,
                   @Path("page") page: Int): Observable<Result<List<MeiZhi>>>

    @GET("day/{date}")
    fun getDaily(@Path("date") date: String): Observable<DailyDetail>
}