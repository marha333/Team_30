package at.team30.setroute

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import at.team30.setroute.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun routes_displayed() {
        onView(withText("Coffee")).check(matches(isDisplayed()))
        onView(withText("Coffee2")).check(matches(isDisplayed()))
        onView(withText("Parks")).check(matches(isDisplayed()))
        onView(withText("Parks2")).check(matches(isDisplayed()))

        //Other routes not displayed on the emulator display, you need to scroll to reach it
    }
}
