package info.hellovass.meizhi.preview

import android.Manifest
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.architecture.mvp.special.v.showToast
import info.hellovass.dto.image.UIStateModel


val PreviewActivity.imageUrl: String
    get() {
        return intent.extras.getString("url")
    }

val PreviewActivity.fileName: String
    get() {
        val name: String = intent.extras.getString("desc")
        return "$name.jpg"
    }

@Suppress("unused")
val PreviewActivity.permission: String
    get() {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE
    }


fun PreviewActivity.dispatchResult(uiStateModel: UIStateModel) {

    when {
        uiStateModel.isLoading() -> {
            viewDelegate?.showToast("下载中...")
        }
        uiStateModel.isFailed() -> {
            viewDelegate?.showSnackbar(uiStateModel.getError())

        }
        uiStateModel.isSucceed() -> {
            viewDelegate?.showSnackbar("{图片已保存至${PreviewRepo.saveDir}目录下}")
            viewDelegate?.notifyGallery(uiStateModel.getData())
        }
    }
}