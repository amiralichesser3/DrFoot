package com.safari.drfoot.utilities.contracts

interface MyCallback<T> {
    fun onSuccess(param: T)
    fun onError(param: T)
}