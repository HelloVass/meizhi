package info.hellovass.widgets.loadmore

enum class Status(val title: String?) {

    Idle(null),
    Error("加载失败，请重试"),
    Loading("加载中..."),
    NoMore("没有更多数据啦"),
}

