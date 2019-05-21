package com.safari.drfoot.utilities

class Resource<T> private constructor(private val status: Status, val data: T?, val message: String?) {
    fun isSuccessful(): Boolean {
        return status === Status.SUCCESS && data != null
    }

    fun isLoading(): Boolean {
        return status === Status.LOADING
    }

    fun isError(): Boolean {
        return status === Status.ERROR
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}