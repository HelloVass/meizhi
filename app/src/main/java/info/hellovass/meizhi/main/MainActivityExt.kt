package info.hellovass.meizhi.main

import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.MeiZhiDTO
import info.hellovass.network.Action
import info.hellovass.network.Resource
import info.hellovass.network.Status

fun MainActivity.dispatchResult(action: Action, resource: Resource<List<MeiZhiDTO>>) {

    when (action) {
        Action.Refresh -> {
            handleRefresh(resource)
        }
        Action.LoadMore -> {
            handleLoadMore(resource)
        }
    }
}

fun MainActivity.handleRefresh(resource: Resource<List<MeiZhiDTO>>) {

    when (resource.status) {
        Status.LOADING -> {
            viewDelegate?.showRefreshing(true)
        }
        Status.SUCCESS -> {
            viewDelegate?.setItems(resource.data!!)
            viewDelegate?.showRefreshing(false)
            viewDelegate?.resetLoadMore()
            pageNum++
        }
        Status.ERROR -> {
            viewDelegate?.showRefreshing(false)
            viewDelegate?.showSnackbar(resource.message ?: "未知错误")
        }
    }
}

fun MainActivity.handleLoadMore(resource: Resource<List<MeiZhiDTO>>) {

    when (resource.status) {
        Status.LOADING -> {
            viewDelegate?.onLoadMoreBegin()
        }
        Status.SUCCESS -> {
            viewDelegate?.addItems(resource.data!!)
            viewDelegate?.onLoadMoreSucceed(true)
            pageNum++
        }
        Status.ERROR -> {
            viewDelegate?.onLoadMoreFailed()
            viewDelegate?.showSnackbar(resource.message ?: "未知错误")
        }
    }
}
