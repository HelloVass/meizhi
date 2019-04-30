package info.hellovass.android.mvvmlite

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelFactory<S : IState>(
    private val viewModelClass: Class<VM>
) : ViewModelProvider.Factory {

    companion object {
        fun <VM> create(viewModelClass: Class<VM>): ViewModelProvider.Factory? {
            return ViewModelFactory(viewModelClass = viewModelClass)
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.newInstance() as T
    }
}