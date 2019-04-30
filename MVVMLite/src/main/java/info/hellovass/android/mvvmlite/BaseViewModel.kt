package info.hellovass.android.mvvmlite

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.ViewModel
import kotlin.reflect.KProperty1

abstract class BaseViewModel<T : IState>(initialState: T) : ViewModel() {

    private val state = MutableLiveData<T>().apply { value = initialState }

    fun setState(reducer: T.() -> T) {
        state.value = reducer(state.value!!)
    }

    fun withState(block: (T) -> Unit) {
        block(state.value!!)
    }

    fun <A> selectSubscribe(prop1: KProperty1<T, A>) = map(state) {
        Tuple1(prop1.get(it))
    }.distinctUntilChanged()

    fun <A, B> selectSubscribe(prop1: KProperty1<T, A>, prop2: KProperty1<T, B>) = map(state) {
        Tuple2(prop1.get(it), prop2.get(it))
    }.distinctUntilChanged()

    fun <A, B, C> selectSubscribe(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>
    ) = map(state) {
        Tuple3(prop1.get(it), prop2.get(it), prop3.get(it))
    }.distinctUntilChanged()

    fun <A, B, C, D> selectSubscribe(
        prop1: KProperty1<T, A>,
        prop2: KProperty1<T, B>,
        prop3: KProperty1<T, C>,
        prop4: KProperty1<T, D>
    ) = map(state) {
        Tuple4(prop1.get(it), prop2.get(it), prop3.get(it), prop4.get(it))
    }.distinctUntilChanged()
}