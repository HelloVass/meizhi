package info.hellovass.meizhi.lib


interface ILayoutManager {

    fun findLastCompletelyVisibleItemPosition(): Int

    fun getItemCount(): Int
}