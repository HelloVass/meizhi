package info.hellovass.architecture.mvp.standard.v

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import info.hellovass.architecture.mvp.standard.p.IPresenter

abstract class BaseActivity<P : IPresenter> : AppCompatActivity() {

    private var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 创建 Presenter
        presenter = createPresenter()

        // 设置 ContentView
        setContentView(provideLayoutResId())

        // 初始化
        initData(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.onDestroy()
    }

    abstract fun createPresenter(): P?

    abstract fun provideLayoutResId(): Int

    abstract fun initData(savedInstanceState: Bundle?)
}