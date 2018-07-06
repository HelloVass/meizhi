package info.hellovass.meizhi.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import info.hellovass.meizhi.R
import info.hellovass.meizhi.dto.MeiZhiDTO
import info.hellovass.meizhi.ext.inflate
import kotlinx.android.synthetic.main.listitem_meizhis.view.*

class MeiZhisAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val meizhis: MutableList<MeiZhiDTO> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MeiZhiVH(inflate(R.layout.listitem_meizhis, parent, false))
    }

    override fun getItemCount(): Int {

        return meizhis.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MeiZhiVH).bind(meizhis[position], position)
    }

    fun insert(data: List<MeiZhiDTO>) {

        val startIndex: Int = meizhis.size
        val itemCount: Int = data.count()
        meizhis.addAll(data)
        notifyItemRangeInserted(startIndex, itemCount)
    }

    class MeiZhiVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(meiZhiDTO: MeiZhiDTO, position: Int) {

            itemView.apply {

                Glide.with(context).load(meiZhiDTO.url).into(ivCover)

                tvTitle.text = meiZhiDTO.desc
            }
        }
    }
}