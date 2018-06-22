package info.hellovass.planner.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.io.Serializable

abstract class BaseActivity : AppCompatActivity() {

    private var mArgs: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // 获取 ViewModel
        obtainVM()

        // 加载 UI 和初始化数据
        initData(savedInstanceState)

        // 观察数据变化
        observeData()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : Serializable> parseArgs(args: Bundle?, key: String): T {

        return args?.getSerializable(key) as T
    }

    protected abstract fun getLayoutResId(): Int

    protected abstract fun obtainVM()

    protected abstract fun initData(savedInstanceState: Bundle?)

    protected abstract fun observeData()
}