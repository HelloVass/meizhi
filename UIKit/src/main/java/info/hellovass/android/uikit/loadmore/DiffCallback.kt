package info.hellovass.android.uikit.loadmore

import android.support.v7.util.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<ILoadMore.State>() {

    override fun areItemsTheSame(
        oldItem: ILoadMore.State,
        newItem: ILoadMore.State
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ILoadMore.State,
        newItem: ILoadMore.State
    ): Boolean {
        return oldItem == newItem
    }
}