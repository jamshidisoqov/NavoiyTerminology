package io.jamshid.navoiyterminology.domain.models

sealed class Response<T>(
    var isLoading:Boolean = false,
    var data: T?=null,
    var message: String?=null
) {
    class Loading<T>:Response<T>(isLoading = true)
    class Success<T>(data: T?):Response<T>(data = data)
    class Error<T>(message: String?):Response<T>(message = message)
}
