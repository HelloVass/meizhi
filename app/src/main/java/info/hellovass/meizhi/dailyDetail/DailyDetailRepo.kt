package info.hellovass.meizhi.dailyDetail

import info.hellovass.architecture.mvp.special.m.IRepo
import info.hellovass.dto.Category
import info.hellovass.imageloader.ImageLoader
import info.hellovass.network.Api
import info.hellovass.network.ApiClient
import io.reactivex.Observable

class DailyDetailRepo(private val imageLoader: ImageLoader) : IRepo,
        ImageLoader by imageLoader {

    fun loadData(date: String): Observable<List<Category>> {

        return ApiClient.sINSTANCE
                .provideApi(Api::class.java)
                .getDaily(date)
                .map { dailyDetail ->
                    dailyDetail.results.flatMap { it.value }.toList()
                }
    }
}
