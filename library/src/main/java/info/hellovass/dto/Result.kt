package info.hellovass.dto

data class Result<T>(val error: Boolean, val results: T)