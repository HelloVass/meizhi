package info.hellovass.dto

data class MeiZhi(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val publishedAt: String,
        val source: String,
        val type: String,
        var url: String,
        val used: Boolean,
        val who: String
)