package info.hellovass.meizhi.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 对 MeiZhis Adapter 的扩展
 */
fun MeiZhisAdapter.inflate(layoutId: Int, parent: ViewGroup, attachToRoot: Boolean): View {

    return LayoutInflater.from(this.context).inflate(layoutId, parent, attachToRoot)
}