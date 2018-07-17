package info.hellovass.meizhi.main

import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.MeiZhi
import info.hellovass.dto.Status
import info.hellovass.dto.UIStateDTO
import info.hellovass.network.Action

fun MainActivity.dispatchResult(action: Action, uiStateDTO: UIStateDTO<List<MeiZhi>>) {

    when (action) {
        Action.Refresh -> {
            handleRefresh(uiStateDTO)
        }
        Action.LoadMore -> {
            handleLoadMore(uiStateDTO)
        }
    }
}

fun MainActivity.handleRefresh(uiStateDTO: UIStateDTO<List<MeiZhi>>) {

    when (uiStateDTO.status) {
        Status.Loading -> {
            viewDelegate?.showRefreshing(true)
        }
        Status.Failed -> {
            viewDelegate?.showRefreshing(false)
            viewDelegate?.showSnackbar(uiStateDTO.getError())
        }
        Status.Succeed -> {
            viewDelegate?.setItems(uiStateDTO.getData())
            viewDelegate?.showRefreshing(false)
            viewDelegate?.resetLoadMore()
            pageNum++
        }
    }
}

fun MainActivity.handleLoadMore(uiStateDTO: UIStateDTO<List<MeiZhi>>) {

    when (uiStateDTO.status) {
        Status.Loading -> {
            viewDelegate?.onLoadMoreBegin()
        }
        Status.Succeed -> {
            viewDelegate?.addItems(uiStateDTO.getData())
            viewDelegate?.onLoadMoreSucceed(true)
            pageNum++
        }
        Status.Failed -> {
            viewDelegate?.onLoadMoreFailed()
            viewDelegate?.showSnackbar(uiStateDTO.getError())
        }
    }
}
