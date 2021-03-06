package com.app.recipe.other

/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A JUnit rule that registers an idling resource for all fragment views that use data binding.
 */
class DataBindingIdlingResourceRule(
    activityTestRule: ActivityScenarioRule<*>
) : TestWatcher() {
    private val idlingResource = DataBindingIdlingResource(activityTestRule)

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        description?.let { super.finished(it) }
    }

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        if (description != null) {
            super.starting(description)
        }
    }

}