package info.hellovass.android.uikit.loadmore

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class LayoutManagerAdapter(
    private val manager: RecyclerView.LayoutManager?
) : ILayoutManager {

    override fun isOnBottom(): Boolean {
        return when (manager) {

            is LinearLayoutManager ->
                LinearManagerAdapter(manager = manager).isOnBottom()

            else ->
                throw IllegalStateException("unknown manager: $manager")
        }
    }
}