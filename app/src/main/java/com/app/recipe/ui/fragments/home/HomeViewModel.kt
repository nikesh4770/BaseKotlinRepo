package com.app.recipe.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.recipe.network.SearchComplexRecipeResponse
import com.app.recipe.other.Resource
import com.app.recipe.repositories.RecipeRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRepositoryInterface: RecipeRepositoryInterface
) : ViewModel() {

    private val _complexRecipeSearch = MutableLiveData<Resource<SearchComplexRecipeResponse>>()
    val complexRecipeResponse: LiveData<Resource<SearchComplexRecipeResponse>> =
        _complexRecipeSearch

    private val _searchTerm = MutableLiveData<String>()
    val searchTerm: LiveData<String> = _searchTerm

    fun performComplexRecipeSearch(query: String) {
        if (query.isEmpty()) {
            _complexRecipeSearch.value = Resource.error(null, null)
            return
        }
        _searchTerm.value = query
        _complexRecipeSearch.value = Resource.loading(null)
        viewModelScope.launch {
            val resource = recipeRepositoryInterface.complexSearchRecipe(query)
            _complexRecipeSearch.value = resource
        }
    }

}