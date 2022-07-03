package com.app.recipe.repositories

import com.app.recipe.network.Result
import com.app.recipe.network.SearchComplexRecipeResponse
import com.app.recipe.other.Resource

class FakeRecipeRepository : RecipeRepositoryInterface {

    private val recipeList: List<Result> = mutableListOf()

    private var isError = false

    fun setNetworkError(value: Boolean) {
        isError = value
    }

    override suspend fun complexSearchRecipe(searchTerm: String): Resource<SearchComplexRecipeResponse> {
        return when (isError) {
            true -> Resource.error("Test Error.", null)
            false -> Resource.success(SearchComplexRecipeResponse(recipeList, 10, 50, 1800))
        }
    }
}