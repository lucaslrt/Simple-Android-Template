package com.template.androidtemplateproject

import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.template.androidtemplateproject.util.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    /*@get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)*/

    @get:Rule
    val rule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        /*val notAnimatedDrawable = ContextCompat.getDrawable(rule.activity, R.drawable.ic_error)
        (rule.activity.findViewById(R.id.progressBar) as ProgressBar).indeterminateDrawable =
            notAnimatedDrawable*/
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkVisibleFragmentIsHomeFragment(){

        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
//        val scenario = launch(MainActivity::class.java).use { scenario ->
//            scenario.moveToState(Lifecycle.State.CREATED) // Moves the activity state to State.CREATED.
//        }
//
//
//        scenario.onActivity {

//        }
    }
}