package com.rafaelguimas.domain

import com.rafaelguimas.domain.exception.Failure

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val failure: Failure) : Result<Nothing>()
}