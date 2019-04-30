package info.hellovass.android.uikit.loadmore

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.hellovass.android.uikit.R

class LoadMoreAdapter(
    private val bindMap: BindMap = BindMap(
        layoutResId = R.layout.uikit_layout_loadmore,
        holderClass = DefaultLoadMoreVH::class.java
    ),
    private val realAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_LOADMORE = Int.MAX_VALUE
    }

    private val differ = AsyncListDiffer<ILoadMore.State>(this, DiffCallback())

    private val items: MutableList<ILoadMore.State>
        get() = differ.currentList

    class BindMap(
        val layoutResId: Int,
        val holderClass: Class<out VH>
    )

    override fun getItemViewType(position: Int): Int {
        return when (position >= realAdapter.itemCount) {
            true ->
                TYPE_LOADMORE
            else ->
                realAdapter.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType == TYPE_LOADMORE) {
            true -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    bindMap.layoutResId,
                    parent,
                    false
                )
                bindMap.holderClass.getDeclaredConstructor(View::class.java)
                    .newInstance(view)
            }
            else -> {
                realAdapter.onCreateViewHolder(parent, viewType)
            }
        }
    }

    override fun getItemCount(): Int {
        return realAdapter.itemCount + items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_LOADMORE -> {
                (holder as VH).bindData(data = items[0])
            }
            else -> {
                realAdapter.onBindViewHolder(holder, position)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when (payloads.isNullOrEmpty()) {
            true ->
                onBindViewHolder(holder, position)
            else ->
                realAdapter.onBindViewHolder(holder, position, payloads)
        }
    }

    fun update(newItems: List<ILoadMore.State>) {
        differ.submitList(newItems)
    }

    abstract class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindData(data: ILoadMore.State)
    }
}