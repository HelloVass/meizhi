package info.hellovass.meizhi.preview

import android.os.Bundle
import info.hellovass.architecture.mvp.special.p.ActivityPresenter

class PreviewActivity : ActivityPresenter<PreviewDelegate, PreviewRepo>() {

    override fun createViewDelegate(): PreviewDelegate? = PreviewDelegate(this)

    override fun createRepo(): PreviewRepo? = PreviewRepo()

    override fun initWidgets() {

    }

    override fun bindEvent() {

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        viewDelegate?.loadData(intent.extras)
    }
}