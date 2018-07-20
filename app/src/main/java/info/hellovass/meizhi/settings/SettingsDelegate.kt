package info.hellovass.meizhi.settings

import android.support.v7.app.AppCompatActivity
import info.hellovass.architecture.mvp.special.v.ActivityDelegate
import info.hellovass.meizhi.R

class SettingsDelegate(activity: AppCompatActivity) : ActivityDelegate(activity) {

    override fun getLayoutResId(): Int {
        return R.layout.activity_settings
    }
}
