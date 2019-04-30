package info.hellovass.android.mvvmlite

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations

class QuReactiveLiveData<T : Any?>(
    private val source: LiveData<T>
) {
    companion object {

        fun <T, R> map(source: LiveData<T>, func: (T) -> R): LiveData<R> =
            Transformations.map(source, func)

        fun <T> distinctUntilChanged(source: LiveData<T>): LiveData<T> {
            return distinctUntilChanged(source = source, func = { it })
        }

        fun <T, R> distinctUntilChanged(source: LiveData<T>, func: (T) -> R): LiveData<T> {
            var prev: Any? = null
            return filter(source = source, func = {
                when (val key = func(it)) {
                    prev ->
                        false
                    else -> {
                        prev = key
                        true
                    }
                }
            })
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> filter(source: LiveData<T>, func: (T) -> Boolean): LiveData<T> {
            val result = MediatorLiveData<T>()
            result.addSource(source) { if (func(it as T)) result.value = it }
            return result
        }
    }
}