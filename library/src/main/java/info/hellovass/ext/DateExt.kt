package info.hellovass.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatAs(style: String): String {
    return SimpleDateFormat(style, Locale.getDefault())
            .format(this)
}