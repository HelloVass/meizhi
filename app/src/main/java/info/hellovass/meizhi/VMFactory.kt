package info.hellovass.meizhi

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import info.hellovass.meizhi.main.MainRepo
import info.hellovass.meizhi.main.MainVM

class VMFactory private constructor() : ViewModelProvider.Factory {

    companion object {

        private var sINSTANCE: VMFactory? = null

        fun getInstance(): VMFactory {

            return sINSTANCE ?: synchronized(VMFactory::class.java) {
                sINSTANCE ?: VMFactory()
                        .also { sINSTANCE = it }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {

                when {
                    isAssignableFrom(MainVM::class.java) ->
                        MainVM(MainRepo())
                    else ->
                        throw IllegalArgumentException("未知 ViewModel")
                }
            } as T
}