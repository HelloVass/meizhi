package info.hellovass.meizhi.main

import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.widget.ImageView
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.MeiZhi
import info.hellovass.dto.Status
import info.hellovass.dto.UIStateDTO
import info.hellovass.meizhi.dailyDetail.DailyDetailActivity
import info.hellovass.meizhi.preview.PreviewActivity
import org.jetbrains.anko.intentFor

fun MainActivity.dispatchResult(pullToRefresh: Boolean, uiStateDTO: UIStateDTO<List<MeiZhi>>) {

    when {
        pullToRefresh -> {
            handleRefresh(uiStateDTO)
        }
        else -> {
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

fun MainActivity.redirectToPreview(meizhi: MeiZhi, imageArea: ImageView) {

    val intent = intentFor<PreviewActivity>("url" to meizhi.url,
            "desc" to meizhi.desc)

    val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            imageArea, "picture")

    ActivityCompat.startActivity(this,
            intent, optionsCompat.toBundle())
}

fun MainActivity.redirectToDailyDetail(meizhi: MeiZhi, imageArea: ImageView) {

    val intent = intentFor<DailyDetailActivity>("url" to meizhi.url,
            "desc" to meizhi.desc)

    val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            imageArea, "picture")

    ActivityCompat.startActivity(this,
            intent, optionsCompat.toBundle())
}
