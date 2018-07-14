package info.hellovass.architecture.mvp.standard.p

import info.hellovass.architecture.mvp.standard.m.IRepo
import info.hellovass.architecture.mvp.standard.v.IView

abstract class BasePresenter<V : IView, M : IRepo>(protected var view: V, private val repo: M) : IPresenter