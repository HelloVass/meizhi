package info.hellovass.architecture.mvp.special.v

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.DecelerateInterpolator
import info.hellovass.library.R


abstract class ActivityDelegate(val activity: AppCompatActivity) : IDelegate {

    protected lateinit var toolbar: Toolbar

    private val Toolbar.isHidden: Boolean
        get() {
            return y < 0
        }

    fun <V : View> find(id: Int): V {

        return activity.findViewById(id) as V
    }

    override fun setTitle(title: String?) {

        toolbar = find(R.id.toolbar)
        toolbar.title = title
    }

    override fun setupNavigation(naviIcon: Int, onBackPressedListener: View.OnClickListener?) {

        toolbar = find(R.id.toolbar)
        toolbar.setNavigationIcon(naviIcon)
        toolbar.setNavigationOnClickListener(onBackPressedListener)
    }

    override fun setupMenu(id: Int, listener: Toolbar.OnMenuItemClickListener) {

        toolbar = find(R.id.toolbar)
        toolbar.inflateMenu(id)
        toolbar.setOnMenuItemClickListener(listener)
    }

    override fun hideOrShowToolbar() {

        toolbar = find(R.id.toolbar)
        toolbar.animate()
                .translationY(if (toolbar.isHidden) 0.0F else -toolbar.height.toFloat())
                .setInterpolator(DecelerateInterpolator(2.0F))
                .start()
    }
}