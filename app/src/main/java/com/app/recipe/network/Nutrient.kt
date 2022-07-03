package com.app.recipe.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Nutrient(
    var name: String? = null,
    var amount: Double? = null,
    var unit: String? = null
) : Parcelable