package info.hellovass.android.mvvmlite

fun <T> List<T>.update(reducer: (T) -> T, predicate: (T) -> Boolean) =
    indexOfFirst { predicate(it) }.let { index ->
        when (index >= 0) {
            true ->
                copy(index = index, value = reducer(get(index)))
            else ->
                this
        }
    }

fun <T> List<T>.copy(index: Int, value: T): List<T> =
    toMutableList().apply { set(index, value) }