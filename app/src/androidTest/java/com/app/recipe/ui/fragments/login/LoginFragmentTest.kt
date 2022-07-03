package com.app.recipe.ui.fragments.login

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import com.app.recipe.MainActivity
import com.app.recipe.R
import com.app.recipe.other.DataBindingIdlingResourceRule
import com.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.not
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

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

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

        onView(withId(R.id.txtLoginButton)).perform(click())

        verify(navController).navigate(
            R.id.action_loginFragment_to_homeFragment
        )
    }

    @Test
    fun launchLoginScreen_WelcomeLabelShouldDisplay() {
        launchFragmentInHiltContainer<LoginFragment> {
        }

        onView(withId(R.id.txtWelcomeLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun launchLoginScreen_forgotPasswordShouldDisplay() {
        launchFragmentInHiltContainer<LoginFragment> {
        }

        onView(withId(R.id.txtForgotPasswordButton)).check(matches(isDisplayed()))
    }

    @Test
    fun launchLoginScreen_emailClearButtonShouldNotDisplay() {
        launchFragmentInHiltContainer<LoginFragment> {
        }

        onView(withId(R.id.imgLoginClearEmail)).check(matches(not(isDisplayed())))
    }

    @Test
    fun launchLoginScreen_passwordClearButtonShouldNotDisplay() {
        launchFragmentInHiltContainer<LoginFragment> {
        }

        onView(withId(R.id.imgLoginClearEmail)).check(matches(not(isDisplayed())))
    }
}