package info.hellovass.meizhi.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.share

class ShareBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        // 链接
        val link = intent?.dataString ?: ""
        context?.share(text = link, subject = "链接")
    }
}