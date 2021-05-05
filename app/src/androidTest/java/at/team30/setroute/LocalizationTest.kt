package at.team30.setroute

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import at.team30.setroute.infrastructure.DependencyInjection
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import at.team30.setroute.ui.MainActivity
import com.zeugmasolutions.localehelper.LocaleHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(DependencyInjection::class)
@HiltAndroidTest
class LocalizationTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockRepository: IRoutesRepository

    @Before
    fun init() {
        hiltRule.inject()
        val context = InstrumentationRegistry.getInstrumentation().targetContext;
        LocaleHelper.setLocale(context, LocaleHelper.systemLocale)
    }

    @After
    fun cleanup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext;
        LocaleHelper.setLocale(context, LocaleHelper.systemLocale)
    }

    @Test
    fun language_select_dialog_is_displayed() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_many())

        // Act
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.settingsFragment)).perform(click()) // Go to settings
        onView(withId(R.id.language_button)).perform(click()) // Click Language button

        // Assert
        onView(withText(R.string.select_a_language)).check(matches(isDisplayed()))
        onView(withText(R.string.cancel)).perform(click())
    }

    @Test
    fun select_german_changes_string_resources() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_many())

        // Act
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.settingsFragment)).perform(click()) // Go to settings
        onView(withId(R.id.language_button)).perform(click()) // Click Language button
        onView(withText("DE")).perform(click()) // Change language to German
        onView(withId(R.id.routesFragment)).perform(click()) // Go to routes list

        // Assert
        for(route in FixturesAndroid.routes_many()) {
            onView(withText(route.name_de)).check(matches(isDisplayed()))
        }
        scenario.close()
    }

    @Test
    fun select_russian_changes_string_resources() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_many())

        // Act
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.settingsFragment)).perform(click()) // Go to settings
        onView(withId(R.id.language_button)).perform(click()) // Click Language button
        onView(withText("RU")).perform(click()) // Change language to Russian
        onView(withId(R.id.routesFragment)).perform(click()) // Go to routes list

        // Assert
        for(route in FixturesAndroid.routes_many()) {
            onView(withText(route.name_ru)).check(matches(isDisplayed()))
        }
        scenario.close()
    }

    private fun given_routes_in_repository(routes: List<Route>) {
        every { mockRepository.getRoutes() } returns routes
        for (route in routes) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }
}