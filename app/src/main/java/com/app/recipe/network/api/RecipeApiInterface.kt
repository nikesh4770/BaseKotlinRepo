package com.app.recipe.network.api

import com.app.recipe.network.SearchComplexRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiInterface {

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") searchTerm: String
    ): Response<SearchComplexRecipeResponse>

}