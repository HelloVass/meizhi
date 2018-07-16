package info.hellovass.meizhi.preview

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.chrisbanes.photoview.OnViewTapListener
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.meizhi.R
import io.reactivex.android.schedulers.AndroidSchedulers

class PreviewActivity : ActivityPresenter<PreviewDelegate, PreviewRepo>() {

    override fun createViewDelegate(): PreviewDelegate? = PreviewDelegate(this)

    override fun createRepo(): PreviewRepo? = PreviewRepo()

    override fun initWidgets() {}

    override fun bindEvent() {

        viewDelegate?.setTitle("预览")

        viewDelegate?.setupNavigation(R.drawable.ic_navi_back, View.OnClickListener { onBackPressed() })

        viewDelegate?.setupMenu(R.menu.menu_preview, Toolbar.OnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.action_save -> {
                    saveImageToDisk(intent.extras)
                    true
                }
                R.id.action_share -> {
                    viewDelegate?.showSnackbar("开发中")
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

    private fun saveImageToDisk(extras: Bundle?) {

        repo?.saveImageToDisk(this, extras)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { viewDelegate?.showSnackbar("图片保存至${repo?.saveDir}目录下") }
    }
}



