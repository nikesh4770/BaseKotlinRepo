package com.app.recipe.repositories

import com.app.recipe.network.SearchComplexRecipeResponse
import com.app.recipe.other.Resource

interface RecipeRepositoryInterface {

    suspend fun complexSearchRecipe(searchTerm: String): Resource<SearchComplexRecipeResponse>
}