package info.hellovass.android.uikit.loadmore

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import info.hellovass.android.uikit.dp2px
import kotlinx.android.synthetic.main.uikit_layout_loadmore.view.*

class DefaultLoadMoreVH(itemView: View) : LoadMoreAdapter.VH(itemView) {

    init {
        itemView.apply {
            background = GradientDrawable().apply {
                setColor(Color.parseColor("#F1F1F2"))
                cornerRadius = context.dp2px(value = 4).toFloat()
            }
        }
    }

    override fun bindData(data: ILoadMore.State) {
        itemView.apply {
            tvTitle.text = data.title
        }
    }
}