package info.hellovass.meizhi.preview


val PreviewActivity.imageUrl: String
    get() {
        return intent.extras.getString("url")
    }

val PreviewActivity.fileName: String
    get() {
        val name: String = intent.extras.getString("desc")
        return "$name.jpg"
    }