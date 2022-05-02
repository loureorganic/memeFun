package com.example.bookappkotlin.screens.login.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookappkotlin.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest  {

    @Test
    fun verifyActivityLaunched() {

        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.login)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyInputsIsDisplayedAndWithAccessibility(){

        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.emailEt)).check(matches(isDisplayed()))
        onView(withId(R.id.emailEt)).check(matches(hasContentDescription()))

        onView(withId(R.id.passwordEt)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordEt)).check(matches(hasContentDescription()))
    }
}
