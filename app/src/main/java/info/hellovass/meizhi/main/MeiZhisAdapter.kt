package info.hellovass.meizhi.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import info.hellovass.meizhi.R
import info.hellovass.meizhi.dto.MeiZhiDTO
import info.hellovass.meizhi.ext.inflate
import kotlinx.android.synthetic.main.listitem_meizhis.view.*


class MeiZhisAdapter(val datas: List<MeiZhiDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private const val LOADMORE: Int = 1

        private const val ITEM: Int = 2
    }

    override fun getItemCount(): Int = datas.size + 1

    override fun getItemViewType(position: Int): Int {

        return if (position >= itemCount) {

            LOADMORE
        } else {

            ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {

        LOADMORE -> {

            LoadMore(inflate(R.layout.listitem_load_more, parent, false))
        }
        else -> {

            Item(inflate(R.layout.listitem_meizhis, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (getItemViewType(position)) {

        LOADMORE -> {

        }
        else -> {

            (holder as Item).bind(datas[position], position)
        }
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(meiZhiDTO: MeiZhiDTO, position: Int) {

            // 加载图片
            itemView.ivCover.apply {

                Glide.with(context)
                        .load(meiZhiDTO.url)
                        .into(this)
            }

            // 名称
            itemView.tvTitle.apply {

                text = meiZhiDTO.who
            }
        }
    }

    class LoadMore(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
