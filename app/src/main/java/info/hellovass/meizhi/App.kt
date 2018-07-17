package info.hellovass.meizhi

import android.app.Application

class App : Application() {

    companion object {

        private lateinit var sINSTANCE: App

        fun getInstance(): App {

            return sINSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()

        sINSTANCE = this
    }
}