package info.hellovass.architecture.mvp.special.v

import android.support.v7.widget.Toolbar
import android.view.View

interface IDelegate {

    fun getLayoutResId(): Int

    fun setTitle(title: String?)

    fun setupNavigation(naviIcon: Int, onBackPressedListener: View.OnClickListener?)

    fun setupMenu(id: Int, listener: Toolbar.OnMenuItemClickListener)

    fun hideOrShowToolbar()

    fun bindToolbarClickListener(listener: View.OnClickListener)

    fun showProgressbar()

    fun hideProgressbar()
}