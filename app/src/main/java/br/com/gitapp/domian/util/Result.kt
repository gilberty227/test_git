package br.com.gitapp.domian.util

sealed  class Result<out R> {
    data class Success<out T>(val dado: T?) : Result<T?>()
    data class Error(val exception: Exception) : Result<Nothing>()
}