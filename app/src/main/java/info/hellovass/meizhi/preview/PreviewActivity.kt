package info.hellovass.meizhi.preview

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.chrisbanes.photoview.OnViewTapListener
import com.tbruyelle.rxpermissions2.RxPermissions
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.architecture.mvp.special.v.showToast
import info.hellovass.dto.image.UIStateModel
import info.hellovass.meizhi.R
import info.hellovass.network.ObservableHelper
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class PreviewActivity : ActivityPresenter<PreviewDelegate, PreviewRepo>() {

    override fun createViewDelegate(): PreviewDelegate? = PreviewDelegate(this)

    override fun createRepo(): PreviewRepo? = PreviewRepo()

    override fun initWidgets() {

        RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .subscribe { granted ->

                    if (!granted) {
                        viewDelegate?.showToast("授权失败")
                    }else{
                        viewDelegate?.showToast("授权成功")
                    }
                }
    }

    override fun bindEvent() {

        viewDelegate?.setTitle("预览")

        viewDelegate?.setupNavigation(R.drawable.ic_navi_back, View.OnClickListener { onBackPressed() })

        viewDelegate?.setupMenu(R.menu.menu_preview, Toolbar.OnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.action_save -> {
                    saveImageToDisk()
                    true
                }
                R.id.action_share -> {

                    true
                }
                else -> {
                    false
                }
            }
        })

        viewDelegate?.bindOnViewTapListener(OnViewTapListener { _, _, _ ->
            viewDelegate?.hideOrShowToolbar()
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        viewDelegate?.loadImage(intent.extras)
    }

    private fun saveImageToDisk() {

        repo?.let { repo ->

            val o1 = repo.requestPermission(this, permission)
            val o2 = repo.saveToDisk(this, imageUrl, fileName)

            Observable.zip<Boolean, Uri, UIStateModel>(o1, o2, BiFunction { granted, uri ->
                if (!granted)
                    UIStateModel.failed("授权失败")
                else
                    UIStateModel.succeed(uri)
            })
                    .onErrorReturn { UIStateModel.failed(it.message) }
                    .startWith(UIStateModel.loading())
                    .compose(ObservableHelper.io2main())
                    .subscribe { dispatchResult(it) }
        }
    }
}





