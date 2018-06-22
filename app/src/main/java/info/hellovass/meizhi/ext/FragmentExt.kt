package info.hellovass.meizhi.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import info.hellovass.meizhi.VMFactory

fun <T : ViewModel> Fragment.createVM(modelClass: Class<T>): T {

    return ViewModelProviders.of(this, VMFactory.getInstance()).get(modelClass)
}