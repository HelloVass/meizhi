package info.hellovass.meizhi.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import info.hellovass.meizhi.R
import info.hellovass.meizhi.data.MeiZhi
import info.hellovass.meizhi.ext.inflate

class MeiZhisAdapter : RecyclerView.Adapter<MeiZhisVH>() {

    private val mDatas: MutableList<MeiZhi> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeiZhisVH {

        return MeiZhisVH(inflate(R.layout.listitem_meizhis, parent, false))
    }

    override fun getItemCount(): Int {

        return mDatas.size
    }

    override fun onBindViewHolder(holder: MeiZhisVH, position: Int) {

    }

    fun setDatas(datas: List<MeiZhi>?) {

        datas?.let {

            mDatas += datas
            notifyDataSetChanged()
        }
    }
}


class MeiZhisVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

}
