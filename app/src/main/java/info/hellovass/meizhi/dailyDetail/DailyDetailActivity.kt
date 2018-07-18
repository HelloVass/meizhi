package info.hellovass.meizhi.dailyDetail

import info.hellovass.architecture.mvp.special.p.ActivityPresenter

class DailyDetailActivity : ActivityPresenter<DailyDelegate, DailyRepo>() {

    override fun createViewDelegate(): DailyDelegate? {
        return DailyDelegate()
    }

    override fun createRepo(): DailyRepo? {
        return DailyRepo()
    }

    override fun initWidgets() {
    }

    override fun bindEvent() {
    }

}