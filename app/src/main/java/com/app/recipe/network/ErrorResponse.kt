package com.app.recipe.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(val status: String?, val code: Int, val message: String) : Parcelable