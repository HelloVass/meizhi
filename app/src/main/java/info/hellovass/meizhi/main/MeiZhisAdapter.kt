package info.hellovass.meizhi.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import info.hellovass.dto.meizhi.MeiZhi
import info.hellovass.meizhi.R
import kotlinx.android.synthetic.main.listitem_meizhis.view.*

class MeiZhisAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val meizhis: MutableList<MeiZhi> = mutableListOf()

    var onMeiZhiTouchListener: OnMeiZhiTouchListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView: View = inflate(R.layout.listitem_meizhis, parent, false)
        return MeiZhiVH(itemView, onMeiZhiTouchListener)
    }

    override fun getItemCount(): Int {

        return meizhis.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MeiZhiVH).onBindViewHolder(meizhis[position], position)
    }

    fun setItems(data: List<MeiZhi>) {

        meizhis.clear()
        meizhis.addAll(data)
        notifyDataSetChanged()
    }

    fun addItems(data: List<MeiZhi>) {

        val startIndex: Int = meizhis.size
        val itemCount: Int = data.count()
        meizhis.addAll(data)
        notifyItemRangeInserted(startIndex, itemCount)
    }

    class MeiZhiVH(itemView: View, private val onMeiZhiTouchListener: OnMeiZhiTouchListener?) : RecyclerView.ViewHolder(itemView) {

        fun onBindViewHolder(meiZhi: MeiZhi, position: Int) {

            itemView.apply {

                Glide.with(context)
                        .load(meiZhi.url)
                        .into(ivCover)

                tvTitle.text = meiZhi.desc
            }

            itemView.setOnClickListener { itemView ->

                onMeiZhiTouchListener?.onTouch(itemView, itemView.ivCover, meiZhi)
            }
        }
    }

    abstract class OnMeiZhiTouchListener {

        abstract fun onTouch(view: View, imageView: View, meiZhi: MeiZhi)
    }
}