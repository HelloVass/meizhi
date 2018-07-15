package info.hellovass.architecture.mvp.special.v

import android.support.v7.widget.Toolbar
import android.view.View

interface IDelegate {

    fun getLayoutResId(): Int

    fun setupNavigation(title: String?, onBackPressedListener: View.OnClickListener?)

    fun setupMenu(id: Int, listener: Toolbar.OnMenuItemClickListener)

    fun hideOrShowToolbar()
}