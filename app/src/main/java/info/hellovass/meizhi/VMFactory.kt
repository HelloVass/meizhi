package info.hellovass.meizhi

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import info.hellovass.meizhi.main.MainRepo
import info.hellovass.meizhi.main.MainVM

class VMFactory private constructor(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    companion object {

        private var sINSTANCE: VMFactory? = null

        fun getInstance(application: Application): VMFactory {

            return sINSTANCE ?: synchronized(VMFactory::class.java) {
                sINSTANCE ?: VMFactory(application)
                        .also { sINSTANCE = it }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {

                when {
                    isAssignableFrom(MainVM::class.java) ->
                        MainVM(application, MainRepo())
                    else ->
                        throw IllegalArgumentException("未知 ViewModel")
                }
            } as T
}