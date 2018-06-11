package info.hellovass.meizhi.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import info.hellovass.meizhi.VMFactory

fun <T : ViewModel> FragmentActivity.obtainViewModel(modelClass: Class<T>): T {

    return ViewModelProviders.of(this, VMFactory.getInstance(application)).get(modelClass)
}