package info.hellovass.android.mvvmlite

import android.arch.lifecycle.LiveData

fun <T, R> LiveData<T>.map(func: (T) -> (R)): LiveData<R> =
    QuReactiveLiveData.map(this, func)


fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> =
    QuReactiveLiveData.distinctUntilChanged(this)

