package info.hellovass.meizhi.lib.network

import io.reactivex.Single
import io.reactivex.SingleTransformer

object RxResultHandler {

    fun <T> handleResult(): SingleTransformer<Result<T>, T> {

        return SingleTransformer { upstream ->

            upstream.flatMap { (error, results) ->

                if (!error)
                    Single.just(results)
                else
                    Single.error(Throwable("服务器发生错误"))
            }
        }
    }
}