package info.hellovass.meizhi.dailyDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun DailyDetailAdapter.isSameType(previous: Int, current: Int): Boolean {

    return catrgorys[previous].type == catrgorys[current].type
}

fun DailyDetailAdapter.inflate(layoutId: Int, parent: ViewGroup, attachToRoot: Boolean): View {

    return LayoutInflater.from(context).inflate(layoutId, parent, attachToRoot)
}

fun isFirstItem(position: Int) = position == 0
