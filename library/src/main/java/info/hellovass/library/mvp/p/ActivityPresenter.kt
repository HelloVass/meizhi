package info.hellovass.library.mvp.p

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import info.hellovass.library.mvp.m.IRepo

import info.hellovass.library.mvp.v.IDelegate

abstract class ActivityPresenter<T : IDelegate, M : IRepo> : AppCompatActivity() {

    protected var mViewDelegate: T? = null

    protected var mRepo: M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRepo = createRepo()
        mViewDelegate = createViewDelegate()

        setContentView(mViewDelegate!!.getLayoutResId())

        initWidgets()

        bindEvent()
    }

    override fun onDestroy() {
        super.onDestroy()

        mViewDelegate = null
        mRepo = null
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        mRepo ?: createRepo()
        mViewDelegate ?: createViewDelegate()
    }

    protected abstract fun createRepo(): M?

    protected abstract fun createViewDelegate(): T?

    protected abstract fun initWidgets()

    protected abstract fun bindEvent()
}
