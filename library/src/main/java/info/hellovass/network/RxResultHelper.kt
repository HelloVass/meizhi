package info.hellovass.network

import info.hellovass.dto.Result
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

object RxResultHelper {

    fun <T> handleResult(): ObservableTransformer<Result<T>, T> {

        return ObservableTransformer { upstream ->

            upstream.flatMap { result ->
                if (!result.error) {
                    Observable.just(result.results)
                } else {
                    Observable.error(Throwable("服务器内部错误"))
                }
            }
        }
    }
}