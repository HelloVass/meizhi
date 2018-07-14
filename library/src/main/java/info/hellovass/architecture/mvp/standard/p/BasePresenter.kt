package info.hellovass.kmvp.p

import info.hellovass.kmvp.m.IRepo
import info.hellovass.kmvp.v.IView

abstract class BasePresenter<V : IView, M : IRepo>(protected var view: V, private val repo: M) : IPresenter