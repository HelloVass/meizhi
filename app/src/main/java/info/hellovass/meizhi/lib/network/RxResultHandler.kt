package info.hellovass.meizhi.lib.network

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer

object RxResultHandler {

    fun <T> handleResult(): ObservableTransformer<Result<T>, Resource<T>> {

        return ObservableTransformer { upstream ->

            upstream.flatMap { (error, results) ->

                if (!error) {
                    Observable.just(Resource.success(results))
                } else {
                    Observable.error(Throwable("loadMoreError..."))
                }
            }
        }
    }
}