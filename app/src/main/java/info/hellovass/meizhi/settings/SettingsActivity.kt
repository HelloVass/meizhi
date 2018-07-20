package info.hellovass.meizhi.settings

import info.hellovass.architecture.mvp.special.p.ActivityPresenter

class SettingsActivity : ActivityPresenter<SettingsDelegate, SettingsRepo>() {

    override fun createViewDelegate(): SettingsDelegate? {
        return SettingsDelegate(this)
    }

    override fun createRepo(): SettingsRepo? {
        return SettingsRepo()
    }

    override fun initWidgets() {
    }

    override fun bindEvent() {
    }

}