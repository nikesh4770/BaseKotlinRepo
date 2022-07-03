package com.app.recipe.other

import com.google.gson.GsonBuilder
import retrofit2.Response

object AppUtils {

    /**
     * Function to parse retrofit error response.
     */
    fun <T> parseError(response: Response<T>, clazz: Class<T>?): T? =
        response.errorBody()?.let {
            GsonBuilder().create().fromJson(it.charStream(), clazz)
        }

}