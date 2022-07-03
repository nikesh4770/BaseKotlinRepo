package com.app.recipe.repositories

import com.app.recipe.network.SearchComplexRecipeResponse
import com.app.recipe.network.api.RecipeApiInterface
import com.app.recipe.other.AppUtils.parseError
import com.app.recipe.other.Resource
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeApiInterface: RecipeApiInterface
) : RecipeRepositoryInterface {

    override suspend fun complexSearchRecipe(searchTerm: String): Resource<SearchComplexRecipeResponse> {
        return try {
            val response = recipeApiInterface.searchRecipes(searchTerm)
            return when (response.isSuccessful) {
                true -> Resource.success(response.body())
                false -> Resource.error(
                    message = response.message(),
                    data = parseError(response, SearchComplexRecipeResponse::class.java)
                )
            }
        } catch (e: Exception) {
            Resource.error("Something went wrong", null)
        }
    }
}