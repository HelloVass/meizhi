package info.hellovass.meizhi.dailyDetail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import info.hellovass.dto.Category
import info.hellovass.meizhi.R
import kotlinx.android.synthetic.main.listitem_daily_detail.view.*
import kotlinx.android.synthetic.main.section_daily_detail.view.*

interface Action {
    fun setItems(data: List<Category>)
}

class DailyDetailAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action {

    val catrgorys: MutableList<Category> = mutableListOf()

    private var onItemClickListener: OnItemClickListener? = null

    companion object {

        // 分组
        const val TYPE_SECTION = 1

        // 普通项目
        const val TYPE_ITEM = TYPE_SECTION + 1
    }

    override fun getItemViewType(position: Int): Int {

        return when {
            isFirstItem(position) -> {
                TYPE_SECTION
            }
            isSameType(position - 1, position) -> {
                TYPE_ITEM
            }
            else -> {
                TYPE_SECTION
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {

        TYPE_ITEM -> {
            ItemVH(inflate(R.layout.listitem_daily_detail, parent, false))
        }
        else -> {
            SectionVH(inflate(R.layout.section_daily_detail, parent, false))
        }
    }

    override fun getItemCount(): Int = catrgorys.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (getItemViewType(position)) {

        TYPE_SECTION -> {
            (holder as SectionVH).onBindViewHolder(catrgorys[position], position)
        }
        else -> {
            (holder as ItemVH).onBindViewHolder(catrgorys[position], position)
        }
    }

    override fun setItems(data: List<Category>) {
        catrgorys.clear()
        catrgorys += data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class SectionVH(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun onBindViewHolder(category: Category, position: Int) {

            with(category) {

                itemView.tvSectionTitle.text = category.type
                itemView.tvSectionContent.text = category.desc

                itemView.tvSectionContent.setOnClickListener { it ->
                    onItemClickListener?.onItemClick(it, category, position)
                }
            }
        }
    }

    inner class ItemVH(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun onBindViewHolder(category: Category, position: Int) {

            with(category) {

                itemView.tvItemContent.text = category.desc

                itemView.tvItemContent.setOnClickListener { it ->
                    onItemClickListener?.onItemClick(it, category, position)
                }
            }
        }
    }

    interface OnItemClickListener {

        fun onItemClick(view: View, category: Category, position: Int)
    }
}

