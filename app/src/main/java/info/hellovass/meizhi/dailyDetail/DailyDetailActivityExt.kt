package info.hellovass.meizhi.dailyDetail

import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.Category
import info.hellovass.dto.Status
import info.hellovass.dto.UIStateDTO
import java.util.*

val DailyDetailActivity.publishedAt: Date
    get() {
        return extras.getSerializable("publishedAt") as Date
    }

val DailyDetailActivity.wap720: String
    get() {
        return extras.getString("url", "").replace("large", "wap720")
    }

val DailyDetailActivity.extras: Bundle
    get() {
        return intent.extras
    }


fun DailyDetailActivity.dispatchBitmap(uiStateDTO: UIStateDTO<Bitmap>) {

    when (uiStateDTO.status) {

        Status.Loading -> {
            viewDelegate?.showProgressbar()
        }
        Status.Succeed -> {
            viewDelegate?.hideProgressbar()
            viewDelegate?.setBitmap(uiStateDTO.getData())
        }
        Status.Failed -> {
            viewDelegate?.hideProgressbar()
            viewDelegate?.showSnackbar(uiStateDTO.getError())
        }
    }
}

fun DailyDetailActivity.dispatchDailyDetail(uiStateDTO: UIStateDTO<List<Category>>) {

    when (uiStateDTO.status) {

        Status.Loading -> {

        }
        Status.Succeed -> {

            // 寻找视频数据
            videoCategory = uiStateDTO.getData().find { it.type == "休息视频" }

            viewDelegate?.setItems(uiStateDTO.getData())

        }
        Status.Failed -> {
            viewDelegate?.showSnackbar(uiStateDTO.getError())
        }
    }
}

fun DailyDetailActivity.redirectToBrowser(category: Category?) {

    val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setToolbarColor(Color.parseColor("#795548"))
            .build()

    val url = Uri.parse(category?.url)

    customTabsIntent.launchUrl(this, url)
}
