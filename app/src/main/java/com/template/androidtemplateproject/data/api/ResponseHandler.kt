package com.template.androidtemplateproject.data.api

import com.template.androidtemplateproject.util.LogUtil
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ResponseHandler {

    fun <T : Any> handleSuccess(data: T?): ApiResource<T> {
        if (data is Response<*>) {
            val statusCode = data.code()
            val errorBody = data.errorBody()
            if (errorBody != null && statusCode in 400..499) {
                val message = fetchErrorMessageByResponseBody(errorBody) ?: ""
                return ApiResource.error(message, "$message ($statusCode)", null, data, statusCode)
            }
        }

        return ApiResource.success(data)
    }

    fun <T : Any> handleException(exception: Exception): ApiResource<T> {
        exception.printStackTrace()
        return when (exception) {
            is HttpException -> {
                LogUtil.print("handleException() -> HttpException")
                val responseBody = exception.response()?.errorBody()
                val statusCode = exception.code()
                if (responseBody != null) {
                    val errorByResponseBody = fetchErrorMessageByResponseBody(responseBody)
                    if (!errorByResponseBody.isNullOrEmpty())
                        return ApiResource.error(
                            errorByResponseBody,
                            "$errorByResponseBody ($statusCode)",
                            exception,
                            null,
                            statusCode
                        )
                }
                val message = "Ocorreu um problema. Tente novamente mais tarde."
                return ApiResource.error(
                    message, "$message ($statusCode)",
                    exception, null, statusCode
                )
            }
            is SocketTimeoutException -> {
                LogUtil.print("handleException() -> SocketTimeoutException")
                val message = "Ocorreu um problema. Tente novamente mais tarde."
                ApiResource.error(
                    message, "$message (Timeout)",
                    exception, null
                )
            }
            is UnknownHostException -> {
                LogUtil.print("handleException() -> UnknownHostException")
                val message = "Não foi possível concluir a solicitação, tente novamente."
                ApiResource.error(
                    message,
                    message,
                    exception,
                    null
                )
            }
            else -> {
                LogUtil.print("handleException() -> $exception")
                val message = "Ocorreu um problema. Tente novamente mais tarde. (Desconhecido)"
                ApiResource.error(
                    message,
                    message,
                    exception,
                    null
                )
            }
        }
    }

    private fun fetchErrorMessageByResponseBody(responseBody: ResponseBody?): String? {
        try {
            val jsonObject: JSONObject
            val bodyMessage = responseBody?.string()?.trim { it <= ' ' } ?: ""
            if (bodyMessage.isNotEmpty()) {
                if (bodyMessage.startsWith("{")) {

                    jsonObject = JSONObject(bodyMessage)
                    if (!jsonObject.isNull("message")) {

                        return jsonObject.getString("message")

                    } else if (!jsonObject.isNull("erros")) {

                        if (jsonObject["erros"] is JSONArray) {

                            val errorArray = jsonObject.getJSONArray("erros")
                            if (errorArray.length() > 0) return errorArray.getString(0)

                        } else if (jsonObject["erros"] is JSONObject) {
                            return jsonObject.getJSONObject("erros").toString()
                        }
                    }
                } else if (bodyMessage.startsWith("[")) {

                    val jsonArray = JSONArray(bodyMessage)
                    return if (jsonArray.length() > 0) jsonArray.getString(0) else bodyMessage

                } else {
                    return bodyMessage
                }
            } else {
                return null
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return e.message
        }
        return null
    }

}