package info.hellovass.meizhi.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val isLoading: LiveData<Boolean> = MutableLiveData<Boolean>().apply {

        value = false
    }

    val error: LiveData<Boolean> = MutableLiveData<Boolean>().apply {

        value = false
    }
}