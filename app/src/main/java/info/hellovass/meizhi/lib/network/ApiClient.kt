package info.hellovass.meizhi.lib.network

import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient private constructor() {

    companion object {

        val sINSTANCE: ApiClient by lazy {

            ApiClient()
        }
    }

    private val mRetrofit: Retrofit

    init {

        mRetrofit = Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(provideOkHttpClient())
                .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(provideLogInterceptor())
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .build()
    }

    private fun provideLogInterceptor(): Interceptor {

        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun <T> provideApi(service: Class<T>): T {

        return mRetrofit.create(service)
    }
}