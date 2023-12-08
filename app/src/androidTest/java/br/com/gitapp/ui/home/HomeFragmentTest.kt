package br.com.gitapp.ui.home

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import br.com.gitapp.MainActivity
import br.com.gitapp.domian.model.FakeUserData
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import br.com.gitapp.R

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val USER_IN_TESTE = FakeUserData.listUser

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("br.com.gitapp", appContext.packageName)
    }

    @Test
    fun test_isListFragmentVisible_onAppLaunch(){
        onView(withId(R.id.editTextSearch)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun test_isListClickItemFragmentVisible_onAppLaunch(){
        onView(withId(R.id.editTextSearch)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.recyclerViewUser)).check(matches(isCompletelyDisplayed()))
        onView(withText("Home")).check(matches(isCompletelyDisplayed()))
    }
}