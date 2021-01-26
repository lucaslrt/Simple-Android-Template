package com.template.androidtemplateproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.template.androidtemplateproject.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkVisibleFragmentIsHomeFragment() {
        onView(withId(R.id.fragment_home)).check(matches(isDisplayed()))
    }

    @Test
    fun movingToAllBottomMenuPages() {
        onView(withId(R.id.fragment_home)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_dashboard)).perform(click())
        onView(withId(R.id.fragment_dashboard)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_notifications)).perform(click())
        onView(withId(R.id.fragment_notifications)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.fragment_home)).check(matches(isDisplayed()))
    }
}