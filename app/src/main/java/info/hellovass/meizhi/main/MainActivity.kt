package info.hellovass.meizhi.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.dto.MeiZhi
import info.hellovass.dto.UIStateDTO
import info.hellovass.meizhi.R
import info.hellovass.network.RxSchedulersHelper

class MainActivity : ActivityPresenter<MainDelegate, MainRepo>() {

    var pageNum: Int = 1

    override fun createRepo(): MainRepo? {

        return MainRepo()
    }

    override fun createViewDelegate(): MainDelegate {

        return MainDelegate(this)
    }

    override fun initWidgets() {

        // 标题
        viewDelegate?.setTitle("MeiZhi")

        // 菜单
        viewDelegate?.setupMenu(R.menu.menu_main, Toolbar.OnMenuItemClickListener { it ->

            when (it.itemId) {

                R.id.action_more -> {
                    redirectToSettings()
                    true
                }
                else -> {
                    false
                }
            }
        })

        // 下拉刷新控件初始化
        viewDelegate?.setupRefreshLayout()

        // 列表控件初始化
        viewDelegate?.setupRcvList(object : MeiZhisAdapter.OnTouchListener {

            override fun onImageTouch(imageArea: ImageView, view: View, meizhi: MeiZhi) {

                redirectToPreview(meizhi, imageArea)
            }

            override fun onBlankTouch(imageArea: ImageView, view: View, meizhi: MeiZhi) {

                redirectToDailyDetail(meizhi, imageArea)
            }
        })

        // 加载更多控件初始化
        viewDelegate?.setupLoadMore {
            loadData(false)
        }
    }

    override fun bindEvent() {

        // Toolbar 点击事件
        viewDelegate?.bindToolbarClickListener(View.OnClickListener {
            viewDelegate?.smoothScrollToPosition()
        })

        // 绑定下拉刷新事件
        viewDelegate?.bindOnRefreshListener {
            loadData(true)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        loadData(true)
    }

    private fun loadData(pullToRefresh: Boolean) {

        if (pullToRefresh) {
            pageNum = 1
        }

        repo?.let { myRepo ->
            myRepo.getMeiZhiData(count = 10, page = pageNum)
                    .map { result -> UIStateDTO.success(result) }
                    .onErrorReturn { result -> UIStateDTO.error(result.message) }
                    .startWith(UIStateDTO.loading())
                    .compose(RxSchedulersHelper.io2main())
                    .subscribe { uiStateDTO -> dispatchResult(pullToRefresh, uiStateDTO) }
        }
    }
}




