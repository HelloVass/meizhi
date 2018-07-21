package info.hellovass.meizhi.browser

import android.os.Bundle
import android.view.View
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.meizhi.R

class BrowserActivity : ActivityPresenter<BrowserDelegate, BrowserRepo>() {

    override fun createViewDelegate(): BrowserDelegate? {
        return BrowserDelegate(this)
    }

    override fun createRepo(): BrowserRepo? {
        return BrowserRepo()
    }

    override fun initWidgets() {

        viewDelegate?.setupNavigation(R.drawable.ic_navi_back, onBackPressedListener = View.OnClickListener { finish() })

        viewDelegate?.setupWebView(onReceivedTitle = { title ->
            viewDelegate?.setTitle(title)
        })
    }

    override fun bindEvent() {

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        viewDelegate?.loadUrl(url)
    }

    override fun onBackPressed() {

        // 是否允许回到上一页
        val canGoBack = viewDelegate?.canGoBack()
                ?: false

        when {
            canGoBack -> {
                viewDelegate?.goBack()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}