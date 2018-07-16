package info.hellovass.meizhi.preview

import android.net.Uri
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.network.Resource
import info.hellovass.network.Status
import io.reactivex.Observable
import io.reactivex.ObservableTransformer


fun <T> transform(): ObservableTransformer<T, Resource<T>> {

    return ObservableTransformer { upstream ->

        upstream.flatMap {

            Observable.just(Resource.success(it))
        }
    }
}

val PreviewActivity.imageUrl: String
    get() {
        return intent.extras.getString("url")
    }

val PreviewActivity.fileName: String
    get() {
        return intent.extras.getString("desc")
    }

fun PreviewActivity.dispatchResult(resource: Resource<Uri>) {

    when (resource.status) {
        Status.LOADING -> {
            viewDelegate?.showSnackbar("保存中...")
        }
        Status.SUCCESS -> {
            viewDelegate?.showSnackbar("图片保存至${repo?.saveDir}目录下")
            viewDelegate?.notifyGallery(resource.data!!)
        }
        Status.ERROR -> {
            viewDelegate?.showSnackbar("保存失败${resource.message}")
        }
    }
}