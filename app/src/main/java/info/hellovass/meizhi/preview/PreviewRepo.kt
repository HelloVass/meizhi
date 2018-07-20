package info.hellovass.meizhi.preview

import info.hellovass.architecture.mvp.special.m.IRepo
import info.hellovass.disk.DiskHelper
import info.hellovass.imageloader.ImageLoader

class PreviewRepo(private val imageLoader: ImageLoader, private val diskHelper: DiskHelper) : IRepo,
        ImageLoader by imageLoader,
        DiskHelper by diskHelper



