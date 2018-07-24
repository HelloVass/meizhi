package info.hellovass.meizhi.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import info.hellovass.dto.MeiZhi
import info.hellovass.dto.wap720
import info.hellovass.meizhi.R
import kotlinx.android.synthetic.main.listitem_meizhis.view.*

class MeiZhisAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val meizhis: MutableList<MeiZhi> = mutableListOf()

    private var onTouchListener: OnTouchListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView: View = inflate(R.layout.listitem_meizhis, parent, false)
        return MeiZhiVH(itemView)
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

    fun setOnTouchListener(onTouchListener: OnTouchListener) {

        this.onTouchListener = onTouchListener
    }

    inner class MeiZhiVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.ivCover.setOriginalSize(280, 360)
        }

        @Suppress("UNUSED_PARAMETER")
        fun onBindViewHolder(meiZhi: MeiZhi, position: Int) {

            with(meiZhi) {

                Glide.with(itemView.context)
                        .load(meiZhi.wap720)
                        .into(itemView.ivCover)

                itemView.ivCover.setOnClickListener { it ->
                    onTouchListener?.onImageTouch(it as ImageView, itemView, meiZhi)
                }

                itemView.llBlank.setOnClickListener { _ ->
                    onTouchListener?.onBlankTouch(itemView.ivCover, itemView, meiZhi)
                }

                itemView.tvTitle.text = meiZhi.desc
            }
        }
    }

    interface OnTouchListener {

        fun onImageTouch(imageArea: ImageView, view: View, meizhi: MeiZhi)

        fun onBlankTouch(imageArea: ImageView, view: View, meizhi: MeiZhi)
    }
}






