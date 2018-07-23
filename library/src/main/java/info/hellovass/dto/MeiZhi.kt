package info.hellovass.dto

import java.util.*


data class MeiZhi(val _id: String,
                  val createdAt: Date,
                  val desc: String,
                  val publishedAt: Date,
                  val source: String,
                  val type: String,
                  val url: String,
                  val used: Boolean,
                  val who: String)

val MeiZhi.wap720: String
    get() {
        return url.replace("large", "wap720")
    }