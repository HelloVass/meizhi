package info.hellovass.android.mvvmlite

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun <VM : BaseViewModel<S>, S : IState> activityViewModel(
    activity: FragmentActivity,
    viewModelClass: Class<VM>
): VM {
    return ViewModelProviders.of(
        activity,
        ViewModelProviderFactoryImpl()
    ).get(viewModelClass)
}

fun <VM : BaseViewModel<S>, S : IState> fragmentViewModel(
    fragment: Fragment,
    viewModelClass: Class<VM>
): VM {
    return ViewModelProviders.of(
        fragment,
        ViewModelProviderFactoryImpl()
    ).get(viewModelClass)
}

