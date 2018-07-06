package info.hellovass.meizhi.lib.network

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer

object RxResultHandler {

    fun <T> handleResult(): ObservableTransformer<Result<T>, T> {

        return ObservableTransformer { upstream ->

            upstream.flatMap { (error, results) ->

                if (!error)
                    Observable.just(results)
                else
                    Observable.error(Throwable("服务器发生错误"))
            }
        }
    }
}