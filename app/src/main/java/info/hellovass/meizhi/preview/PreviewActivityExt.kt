package info.hellovass.meizhi.preview

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.architecture.mvp.special.v.showToast
import info.hellovass.dto.Status
import info.hellovass.dto.UIStateDTO

val PreviewActivity.large: String
    get() {
        return extras.getString("large", "")
    }

val PreviewActivity.desc: String
    get() {
        return extras.getString("desc", "")
    }

val PreviewActivity.wap720: String
    get() {
        return large.replace("large", "wap720")
    }

val PreviewActivity.extras: Bundle
    get() {
        return intent.extras
    }

@Suppress("unused")
val PreviewActivity.permission: String
    get() {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE
    }

fun PreviewActivity.dispatchBitmap(uiStateDTO: UIStateDTO<Bitmap>) {

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


fun PreviewActivity.dispatchUri(uiStateDTO: UIStateDTO<Uri>) {

    when (uiStateDTO.status) {
        Status.Loading -> {
            viewDelegate?.showToast("下载中...")
        }
        Status.Succeed -> {
            viewDelegate?.showSnackbar("{图片已保存至${PreviewRepo.saveDir}目录下}")
            viewDelegate?.notifyGallery(uiStateDTO.getData())
        }
        Status.Failed -> {
            viewDelegate?.showSnackbar(uiStateDTO.getError())
        }
    }
}