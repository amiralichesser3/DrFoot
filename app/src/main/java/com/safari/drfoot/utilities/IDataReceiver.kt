package com.hafezie.barname.utility

interface IDataReceiver<T> {
    fun onDataReceived(data: T?)
    fun onFailure(message: String)
}