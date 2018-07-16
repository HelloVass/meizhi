package info.hellovass.dto

data class MeiZhiData(val error: Boolean, val results: List<MeiZhi>)

data class MeiZhi(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String
)