package info.hellovass.architecture.mvp.special.v

import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.contentView

fun ActivityDelegate.showSnackbar(text: String) {

    val view: View = activity.contentView
            ?: throw IllegalArgumentException("view can't be null")
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
}

fun ActivityDelegate.showToast(text: String) {

    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
}