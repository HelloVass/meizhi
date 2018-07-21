package info.hellovass.meizhi.browser

val BrowserActivity.url: String
    get() {
        return intent.extras.getString("url")
    }