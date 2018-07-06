package info.hellovass.meizhi.lib.network

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function

object A {

    fun <T> handle(): ObservableTransformer<Result<T>, Resource<T>> {

        return ObservableTransformer { upstream ->

            upstream.flatMap { (error, results) ->
                if (!error) {
                    Observable.just(Resource.success(results))
                } else {
                    Observable.error(Throwable("服务器错误"))
                }
            }
        }
    }
}
