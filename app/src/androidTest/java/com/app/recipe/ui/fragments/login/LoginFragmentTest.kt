package com.app.recipe.ui.fragments.login

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.app.recipe.R
import com.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class LoginFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun clickLogin_shouldNavigateToHomeFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.loginClick)).perform(click())

        verify(navController).navigate(
            R.id.action_loginFragment_to_homeFragment
        )
    }
}