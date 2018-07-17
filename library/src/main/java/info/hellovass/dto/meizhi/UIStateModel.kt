package info.hellovass.dto.meizhi

data class UIStateModel(private val inProgress: Boolean = false,
                        private val error: String? = null,
                        private val data: MeiZhiData? = null) {

    companion object {

        fun loading(): UIStateModel {

            return UIStateModel(inProgress = true)
        }

        fun succeed(data: MeiZhiData): UIStateModel {

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

        return data?.error ?: true || error != null
    }

    fun isSucceed(): Boolean {

        return data?.results?.isNotEmpty() ?: false && !isFailed()
    }

    fun getData(): MeiZhiData {

        return data ?: throw IllegalStateException("data can't be null")
    }

    fun getError(): String {

        return error ?: throw IllegalStateException("error can't be null")
    }
}