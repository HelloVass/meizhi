package info.hellovass.meizhi.main

import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.UIStateModel
import info.hellovass.network.Action
import info.hellovass.network.Status

fun MainActivity.dispatchResult(action: Action, uiState: UIStateModel) {

    when (action) {
        Action.Refresh -> {
            handleRefresh(uiState)
        }
        Action.LoadMore -> {
            handleLoadMore(uiState)
        }
    }
}

fun MainActivity.handleRefresh(uiState: UIStateModel) {

    when {
        uiState.isLoading() -> {
            viewDelegate?.showRefreshing(true)
        }
        uiState.isFailed() -> {
            viewDelegate?.showRefreshing(false)
            viewDelegate?.showSnackbar(uiState.getError())
        }
        uiState.isSucceed() -> {
            viewDelegate?.setItems(uiState.getData().results)
            viewDelegate?.showRefreshing(false)
            viewDelegate?.resetLoadMore()
            pageNum++
        }
    }
}

fun MainActivity.handleLoadMore(uiState: UIStateModel) {

    when {
        uiState.isLoading() -> {
            viewDelegate?.onLoadMoreBegin()
        }
        uiState.isSucceed() -> {
            viewDelegate?.addItems(uiState.getData().results)
            viewDelegate?.onLoadMoreSucceed(true)
            pageNum++
        }
        uiState.isFailed() -> {
            viewDelegate?.onLoadMoreFailed()
            viewDelegate?.showSnackbar(uiState.getError())
        }
    }
}
