package com.app.recipe.ui.fragments.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.recipe.MainCoroutineRule
import com.app.recipe.getOrAwaitValueTest
import com.app.recipe.other.Status
import com.app.recipe.repositories.FakeRecipeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(FakeRecipeRepository())
    }


//    @Test
//    fun `complexRecipeSearch_returnsErrorResponse`() {
//
//        val response = homeViewModel.performComplexRecipeSearch("pasta")
//    }

    @Test
    fun `complexRecipeSearch_returnsResponse`() {
        homeViewModel.performComplexRecipeSearch("pasta")
        val response = homeViewModel.complexRecipeResponse.getOrAwaitValueTest()

        assertThat(response.status).isEqualTo(Status.SUCCESS)
    }
}