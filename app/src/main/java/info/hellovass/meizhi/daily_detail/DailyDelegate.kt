package info.hellovass.meizhi.daily_detail

import android.support.v7.widget.Toolbar
import android.view.View
import info.hellovass.architecture.mvp.special.v.IDelegate
import info.hellovass.meizhi.R

class DailyDelegate : IDelegate {

    override fun getLayoutResId(): Int {
        return R.layout.activity_daily_detail
    }

    override fun setTitle(title: String?) {

    }

    override fun setupNavigation(naviIcon: Int, onBackPressedListener: View.OnClickListener?) {
    }

    override fun setupMenu(id: Int, listener: Toolbar.OnMenuItemClickListener) {
    }

    override fun hideOrShowToolbar() {
    }

    override fun bindToolbarClickListener(listener: View.OnClickListener) {
    }

    override fun showProgressbar() {
    }

    override fun hideProgressbar() {
    }

}
