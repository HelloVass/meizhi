package info.hellovass.disk

import android.app.Activity
import android.net.Uri
import io.reactivex.Observable

interface DiskHelper {

    fun saveToDisk(activity: Activity,
                   imageUrl: String,
                   fileName: String,
                   saveDirPath: String): Observable<Uri>
}