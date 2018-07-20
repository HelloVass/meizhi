package info.hellovass.dto

data class DailyDetail(val error: Boolean,
                       val category: List<String>,
                       val results: Map<String, List<Category>>)

data class Category(val _id: String,
                    val createdAt: String,
                    val desc: String,
                    val images: List<String>,
                    val publishedAt: String,
                    val source: String,
                    val type: String,
                    val url: String,
                    val used: Boolean,
                    val who: String)