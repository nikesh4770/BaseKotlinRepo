package com.app.recipe.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchComplexRecipeResponse(
    var results: List<Result>? = null,
    var offset: Int? = null,
    var number: Int? = null,
    var totalResults: Long? = null
) : Parcelable