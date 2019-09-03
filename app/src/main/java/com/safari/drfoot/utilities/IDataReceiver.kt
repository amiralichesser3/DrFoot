package com.safari.drfoot.utility

interface IDataReceiver<T> {
    fun onDataReceived(data: T?)
    fun onFailure(message: String)
}