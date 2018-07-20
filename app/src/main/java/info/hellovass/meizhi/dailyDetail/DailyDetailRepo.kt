package info.hellovass.meizhi.dailyDetail

import info.hellovass.architecture.mvp.special.m.IRepo
import info.hellovass.imageloader.ImageLoader

class DailyDetailRepo(private val imageLoader: ImageLoader) : IRepo, ImageLoader by imageLoader {

}
