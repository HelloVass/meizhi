package info.hellovass.meizhi.browser

import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.meizhi.R

class BrowserDelegate(activity: AppCompatActivity) : ActivityDelegate(activity) {

    override fun getLayoutResId(): Int = R.layout.activity_browser

    fun setupWebView(onReceivedTitle: (String?) -> Unit) {

        val webview = find<WebView>(R.id.wvContent)

        webview.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

                view?.loadUrl(request?.url.toString())
                return true
            }
        }

        webview.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)

                onReceivedTitle(title)
            }
        }
    }

    fun loadUrl(url: String) {

        find<WebView>(R.id.wvContent)
                .loadUrl(url)
    }

    fun canGoBack(): Boolean {

        return find<WebView>(R.id.wvContent)
                .canGoBack()
    }

    fun goBack() {

        find<WebView>(R.id.wvContent)
                .goBack()
    }
}