package info.hellovass.network

import io.reactivex.Observable
import io.reactivex.ObservableTransformer

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