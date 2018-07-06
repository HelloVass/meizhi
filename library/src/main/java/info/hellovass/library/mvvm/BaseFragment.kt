package info.hellovass.planner.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.Serializable

abstract class BaseFragment : Fragment() {

    private var mArgs: Bundle? = null

    private var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mArgs = arguments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

    protected abstract fun initData(savedInstanceState: Bundle?)

    protected abstract fun obtainVM()

    protected abstract fun observeData()
}