package info.hellovass.meizhi.main

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import info.hellovass.library.mvp.v.IDelegate
import info.hellovass.meizhi.R
import info.hellovass.meizhi.dto.MeiZhiDTO
import org.jetbrains.anko.find


class MainDelegate : IDelegate {

    private val viewAdapter by lazy { MeiZhisAdapter() }

    override fun getLayoutResId(): Int {

        return R.layout.activity_main
    }

    fun setupRcvList(activity: Activity) {

        val rcvList = activity.find<RecyclerView>(R.id.rcvList)

        rcvList.layoutManager = GridLayoutManager(activity, 2)
        rcvList.adapter = viewAdapter
    }

    fun insertItems(data: List<MeiZhiDTO>) {

        viewAdapter.insert(data)
    }

    fun failed() {

    }

    fun loading() {

    }
}