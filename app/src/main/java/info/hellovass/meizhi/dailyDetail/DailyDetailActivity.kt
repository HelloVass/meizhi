package info.hellovass.meizhi.dailyDetail

import android.os.Bundle
import android.view.View
import info.hellovass.architecture.mvp.special.p.ActivityPresenter
import info.hellovass.dto.UIStateDTO
import info.hellovass.ext.`formatAs`
import info.hellovass.imageloader.GlideLoader
import info.hellovass.meizhi.R
import info.hellovass.network.RxSchedulersHelper

class DailyDetailActivity : ActivityPresenter<DailyDeatilDelegate, DailyDetailRepo>() {

    override fun createViewDelegate(): DailyDeatilDelegate? {
        return DailyDeatilDelegate(this)
    }

    override fun createRepo(): DailyDetailRepo? {
        return DailyDetailRepo(GlideLoader())
    }

    override fun initWidgets() {

        viewDelegate?.setupNavigation(R.drawable.ic_navi_back, View.OnClickListener {
            onBackPressed()
        })

        viewDelegate?.setTitle(publishedAt.formatAs("yyyy-MM-dd"))
    }

    override fun bindEvent() {

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        repo?.let { dailyDetailRepo ->
            dailyDetailRepo.loadImage(this, wap720)
                    .map { result -> UIStateDTO.success(result) }
                    .onErrorReturn { result -> UIStateDTO.error(result.message) }
                    .startWith(UIStateDTO.loading())
                    .compose(RxSchedulersHelper.io2main())
                    .subscribe { result -> dispatchBitmap(result) }
        }

        repo?.let { dailyDetailRepo ->
            dailyDetailRepo.loadData(publishedAt.formatAs("yyyy/MM/dd"))
                    .map { result -> UIStateDTO.success(result) }
                    .onErrorReturn { result -> UIStateDTO.error(result.message) }
                    .startWith(UIStateDTO.loading())
                    .compose(RxSchedulersHelper.io2main())
                    .subscribe { result -> dispatchDailyDetail(result) }
        }
    }
}