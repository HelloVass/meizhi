package info.hellovass.android.mvvmlite

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity

fun <ViewModel : BaseViewModel<S>, S : IState> activityViewModel(
    activity: FragmentActivity,
    viewModelClass: Class<ViewModel>
): ViewModel {
    return ViewModelProviders.of(
        activity,
        ViewModelFactory.create(viewModelClass = viewModelClass)
    ).get(viewModelClass)
}
