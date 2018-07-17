package info.hellovass.delegates

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
class SpfDelegate<T>(context: Application, private val spfName: String, private val key: String, private val value: T) {

    private val spf: SharedPreferences  by lazy { context.getSharedPreferences(spfName, Context.MODE_PRIVATE) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = with(spf) {

        when (value) {
            is Long ->
                getLong(key, value)
            is String ->
                getString(key, value)
            is Int ->
                getInt(key, value)
            is Boolean ->
                getBoolean(key, value)
            is Float ->
                getFloat(key, value)
            else ->
                throw IllegalArgumentException()
        } as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = with(spf.edit()) {

        when (value) {
            is Long ->
                putLong(key, value)
            is String ->
                putString(key, value)
            is Int ->
                putInt(key, value)
            is Boolean ->
                putBoolean(key, value)
            is Float ->
                putFloat(key, value)
            else ->
                throw IllegalArgumentException()
        }.apply()
    }
}