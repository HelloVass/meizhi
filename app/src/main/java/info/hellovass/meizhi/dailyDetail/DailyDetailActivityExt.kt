package info.hellovass.meizhi.dailyDetail

import android.graphics.Bitmap
import android.os.Bundle
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.Status
import info.hellovass.dto.UIStateDTO

val DailyDetailActivity.desc: String
    get() {
        return extras.getString("desc", "")
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

        }
        Status.Succeed -> {
            viewDelegate?.setBitmap(uiStateDTO.getData())
        }
        Status.Failed -> {
            viewDelegate?.showSnackbar(uiStateDTO.getError())
        }
    }
}
