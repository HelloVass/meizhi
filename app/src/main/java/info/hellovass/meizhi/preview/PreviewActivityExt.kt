package info.hellovass.meizhi.preview

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.Status
import info.hellovass.dto.UIStateDTO
import java.io.File

val PreviewActivity.url: String
    get() {
        return extras.getString("url", "")
    }

val PreviewActivity.desc: String
    get() {
        return extras.getString("desc", "")
    }

val PreviewActivity.wap720: String
    get() {
        return url.replace("large", "wap720")
    }

@Suppress("unused")
val PreviewActivity.permission: String
    get() {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE
    }

@Suppress("unused")
val PreviewActivity.saveDir: String
    get() {
        return File(Environment.getExternalStorageDirectory(), "MeiZhi").absolutePath
    }

val PreviewActivity.extras: Bundle
    get() {
        return intent.extras
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
            // do nothing
        }
        Status.Succeed -> {
            viewDelegate?.showSnackbar("{图片已保存至${saveDir}目录下}")
            viewDelegate?.notifyGallery(uiStateDTO.getData())
        }
        Status.Failed -> {
            viewDelegate?.showSnackbar(uiStateDTO.getError())
        }
    }
}