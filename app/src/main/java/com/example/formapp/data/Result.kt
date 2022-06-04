package com.example.formapp.data

import com.example.formapp.util.UiText

sealed class Result<T>(
    val message: UiText? = null,
    val data: T? = null
) {
    class Success<T>(data: T? = null, message: UiText? = null): Result<T>(data = data, message = message)
    class Error<T>(message: UiText? = null): Result<T>(message = message)
}
