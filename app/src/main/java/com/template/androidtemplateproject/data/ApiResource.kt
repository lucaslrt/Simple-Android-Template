package com.template.androidtemplateproject.data

data class ApiResource<out T>(
    val status: ApiStatus,
    val data: T?,
    val messageWithoutStatusCode: String?,
    val message: String?,
    val exception: Throwable?,
    val statusCode: Int = -1
) {

    companion object {
        enum class ApiStatus {
            SUCCESS, ERROR, LOADING
        }

        fun <T> success(data: T?): ApiResource<T> {
            return ApiResource(
                ApiStatus.SUCCESS,
                data,
                null,
                null,
                null
            )
        }

        fun <T> error(
            messageWithoutStatusCode: String = "",
            msgWithStatusCode: String,
            exception: Throwable?,
            data: T?,
            statusCode: Int = -1
        ): ApiResource<T> {
            return ApiResource(
                ApiStatus.ERROR,
                data,
                messageWithoutStatusCode,
                msgWithStatusCode,
                exception,
                statusCode
            )
        }

        fun <T> loading(data: T?): ApiResource<T> {
            return ApiResource(
                ApiStatus.LOADING,
                data,
                null,
                null,
                null
            )
        }
    }

}
