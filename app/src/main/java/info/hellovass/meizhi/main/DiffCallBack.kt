package info.hellovass.meizhi.main

import android.support.v7.util.DiffUtil
import info.hellovass.meizhi.dto.MeiZhiDTO

class DiffCallBack(private val oldDataSet: List<MeiZhiDTO>, private val newDataSet: List<MeiZhiDTO>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldDataSet.size

    override fun getNewListSize(): Int = newDataSet.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldDataSet[oldItemPosition]._id == newDataSet[newItemPosition]._id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldDataSet[oldItemPosition].url == newDataSet[newItemPosition].url
    }
}
