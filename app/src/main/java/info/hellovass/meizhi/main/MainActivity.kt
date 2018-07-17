package info.hellovass.meizhi.main

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.Toolbar
import android.view.View
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.architecture.mvp.special.v.showSnackbar
import info.hellovass.delegates.SpfDelegate
import info.hellovass.dto.MeiZhi
import info.hellovass.dto.UIStateDTO
import info.hellovass.meizhi.App
import info.hellovass.meizhi.R
import info.hellovass.meizhi.preview.PreviewActivity
import info.hellovass.network.Action
import info.hellovass.network.RxSchedulersHelper
import org.jetbrains.anko.intentFor

class MainActivity : ActivityPresenter<MainDelegate, MainRepo>() {

    var pageNum: Int by SpfDelegate(App.getInstance(), "meizhi", "pageNum", 1)

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
                    viewDelegate?.showSnackbar("更多操作")
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
        viewDelegate?.setupRcvList(object : MeiZhisAdapter.OnMeiZhiTouchListener() {

            override fun onTouch(view: View, imageView: View, meizhi: MeiZhi) {

                val intent = intentFor<PreviewActivity>("url" to meizhi.url, "desc" to meizhi.desc)

                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,
                        imageView, "picture")

                ActivityCompat.startActivity(this@MainActivity, intent, optionsCompat.toBundle())

            }
        })

        // 加载更多控件初始化
        viewDelegate?.setupLoadMore {
            loadData(Action.LoadMore)
        }
    }

    override fun bindEvent() {

        // 绑定下拉刷新事件
        viewDelegate?.bindOnRefreshListener {
            loadData(Action.Refresh)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        loadData(Action.Refresh)
    }

    private fun loadData(action: Action) {

        if (action == Action.Refresh) {
            pageNum = 1
        }

        repo?.let { repo ->
            repo.getMeiZhiData(count = 10, page = pageNum)
                    .map { result -> UIStateDTO.success(result) }
                    .onErrorReturn { result -> UIStateDTO.error(result.message) }
                    .startWith(UIStateDTO.loading())
                    .compose(RxSchedulersHelper.io2main())
                    .subscribe { uiStateDTO -> dispatchResult(action, uiStateDTO) }
        }
    }
}




