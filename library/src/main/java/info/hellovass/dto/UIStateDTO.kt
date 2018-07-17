package info.hellovass.dto

enum class Status {
    Loading,
    Succeed,
    Failed,
}

class UIStateDTO<T>(val status: Status, private val error: String? = null, private val data: T? = null) {

    companion object {

        fun <T> success(data: T): UIStateDTO<T> {
            return UIStateDTO(Status.Succeed, error = null, data = data)
        }

        fun <T> error(error: String?): UIStateDTO<T> {
            return UIStateDTO(Status.Failed, error, data = null)
        }

        fun <T> loading(): UIStateDTO<T> {
            return UIStateDTO(Status.Loading, error = null, data = null)
        }
    }

    fun getData(): T {
        return data ?: throw IllegalStateException()
    }

    fun getError(): String {
        return error ?: throw IllegalStateException()
    }
}

