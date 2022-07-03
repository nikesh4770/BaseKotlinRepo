package com.app.recipe.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.app.recipe.network.Nutrient
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutrition(
    var nutrients: List<Nutrient>? = null
) : Parcelable