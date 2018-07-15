package info.hellovass.meizhi.preview

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.chrisbanes.photoview.OnViewTapListener
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.meizhi.R

class PreviewActivity : ActivityPresenter<PreviewDelegate, PreviewRepo>() {

    override fun createViewDelegate(): PreviewDelegate? = PreviewDelegate(this)

    override fun createRepo(): PreviewRepo? = PreviewRepo()

    override fun initWidgets() {}

    override fun bindEvent() {

        viewDelegate?.setupNavigation("预览", View.OnClickListener { finish() })

        viewDelegate?.setupMenu(R.menu.menu_preview, Toolbar.OnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.action_share -> {
                    true
                }
                R.id.action_save -> {
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
}