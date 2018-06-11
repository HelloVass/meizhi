package info.hellovass.meizhi.net

import io.reactivex.Single
import io.reactivex.SingleTransformer

object RxHandleResult {

    fun <T> handleResult(): SingleTransformer<Result<T>, Resource<T>> {

        return SingleTransformer { upstream ->

            upstream.flatMap { (error, results) ->

                if (!error)
                    Single.just(Resource.success(results))
                else
                    Single.error(Throwable("服务器异常"))
            }
        }
    }
}
