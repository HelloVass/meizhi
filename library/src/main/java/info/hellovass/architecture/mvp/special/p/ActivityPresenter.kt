package info.hellovass.architecture.mvp.special.p

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import info.hellovass.architecture.mvp.special.m.IRepo
import info.hellovass.architecture.mvp.special.v.IDelegate

abstract class ActivityPresenter<V : IDelegate, M : IRepo> : AppCompatActivity() {

    protected var viewDelegate: V? = null

    protected var repo: M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDelegate = createViewDelegate()
        repo = createRepo()

        // 安全取出 layoutResId
        val layoutResId = viewDelegate?.getLayoutResId() ?: -1

        if (layoutResId < 0) {
            throw IllegalArgumentException("layoutResId can't be -1")
        }
        setContentView(layoutResId)

        initWidgets()

        bindEvent()
    }

    override fun onDestroy() {
        super.onDestroy()

        viewDelegate = null
        repo = null
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        repo ?: createRepo()
        viewDelegate ?: createViewDelegate()
    }

    protected abstract fun createViewDelegate(): V?

    protected abstract fun createRepo(): M?

    protected abstract fun initWidgets()

    protected abstract fun bindEvent()
}
