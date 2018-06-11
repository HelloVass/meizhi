package info.hellovass.meizhi.ext

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.inflate(layoutId: Int, parent: ViewGroup, attachToRoot: Boolean): View {

    return LayoutInflater.from(parent.context).inflate(layoutId, parent, attachToRoot)
}