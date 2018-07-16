package info.hellovass.meizhi.preview

import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.chrisbanes.photoview.OnViewTapListener
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.meizhi.R
import info.hellovass.network.Resource
import info.hellovass.network.RxSchedulerHelper
import io.reactivex.functions.Consumer

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
                    saveImageToDisk()
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

    @Suppress("UNUSED_LAMBDA_EXPRESSION")
    private fun saveImageToDisk() {

        repo?.let {

            it.saveImageToDisk(this, imageUrl, fileName)
                    .toObservable()
                    .compose(transform())
                    .onErrorReturn { Resource.error(it.message) }
                    .compose(RxSchedulerHelper.io2main())
                    .startWith { Resource.loading<Uri>() }
                    .subscribe({ dispatchResult(it) }, { dispatchResult(Resource.error(it.message)) })
        }
    }
}





