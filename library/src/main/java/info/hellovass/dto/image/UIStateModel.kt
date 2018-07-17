package info.hellovass.dto.image

import android.net.Uri


data class UIStateModel(private val inProgress: Boolean = false,
                        private val error: String? = null,
                        private val data: Uri? = null) {
    companion object {

        fun loading(): UIStateModel {

            return UIStateModel(inProgress = true)
        }

        fun succeed(data: Uri): UIStateModel {

            return UIStateModel(data = data)
        }

        fun failed(error: String?): UIStateModel {

            return UIStateModel(error = error)
        }
    }

    fun isLoading(): Boolean {

        return inProgress
    }

    fun isFailed(): Boolean {

        return error != null
    }

    fun isSucceed(): Boolean {

        return data != null && !isFailed()
    }

    fun getData(): Uri {

        return data ?: throw IllegalStateException("data can't be null")
    }

    fun getError(): String {

        return error ?: throw IllegalStateException("error can't be null")
    }
}