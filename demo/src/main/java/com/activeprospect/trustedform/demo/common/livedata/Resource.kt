package com.activeprospect.trustedform.demo.common.livedata

sealed class Resource<T>(val data: T? = null) {

    var wasHandled = false
        protected set

    class Success<T>(data: T) : Resource<T>(data) {

        val dataEvent: T?
            get() = if (wasHandled) {
                null
            } else {
                data
            }.also { wasHandled = true }
    }

    class Error<T>(val error: Throwable) : Resource<T>() {

        val errorEvent: Throwable?
            get() = if (wasHandled) {
                null
            } else {
                error
            }.also { wasHandled = true }
    }
}