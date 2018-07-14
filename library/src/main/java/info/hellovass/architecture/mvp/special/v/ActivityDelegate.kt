package info.hellovass.architecture.mvp.special.v

import android.app.Activity
import android.view.View

abstract class ActivityDelegate(val activity: Activity) : IDelegate {

    fun <V : View> find(id: Int): V {

        return activity.findViewById(id) as V
    }
}