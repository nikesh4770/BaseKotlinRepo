package com.app.recipe.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    var id: Long? = null,
    var title: String? = null,
    var image: String? = null,
    var imageType: String? = null,
    var nutrition: Nutrition? = null
) : Parcelable