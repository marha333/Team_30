package at.team30.setroute

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import at.team30.setroute.infrastructure.DependencyInjection
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(DependencyInjection::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var routesRepository: IRoutesRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun given_many_routes_in_repository_expect_all_routes_displayed_in_list() {
        // Assert
        for(route in routesRepository.getRoutes()) {
            onView(withText(route.name)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun given_many_routes_enter_detail_of_first_route() {
        // Arrange
        val selectedRoute = routesRepository.getRoutes()[0]

        // Act
        onView(withText(selectedRoute.name)).perform(click())

        // Assert
        onView(withText(selectedRoute.name)).check(matches(isDisplayed()))
        onView(withText(selectedRoute.description)).check(matches(isDisplayed()))
    }

    @Test
    fun given_many_routes_enter_detail_press_back_displays_list_view() {
        // Arrange
        val selectedRoute = routesRepository.getRoutes()[0]

        // Act
        onView(withText(selectedRoute.name)).perform(click())

        // Assert
        onView(withText(selectedRoute.name)).check(matches(isDisplayed()))
        onView(withText(selectedRoute.description)).check(matches(isDisplayed()))

        // Act
        onView(isRoot()).perform(pressBack())

        // Assert
        for(route in routesRepository.getRoutes()) {
            onView(withText(route.name)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun given_many_routes_enter_detail_press_routes_button_displays_list_view() {
        // Arrange
        val selectedRoute = routesRepository.getRoutes()[0]

        // Act
        onView(withText(selectedRoute.name)).perform(click())

        // Assert
        onView(withText(selectedRoute.name)).check(matches(isDisplayed()))
        onView(withText(selectedRoute.description)).check(matches(isDisplayed()))

        // Act
        onView(withId(R.id.routesFragment)).perform(click())

        // Assert
        for(route in routesRepository.getRoutes()) {
            onView(withText(route.name)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun starting_activity_navigating_to_setting_view() {
        //Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext;

        //Act
        onView(withId(R.id.settingsFragment)).perform(click())

        //Assert
        onView(withText(context.resources.getString(R.string.select_a_language))).check(matches((isDisplayed())))
        onView(withText(context.resources.getString(R.string.switch_to_miles))).check(matches((isDisplayed())))
        onView(withText(context.resources.getString(R.string.switch_to_dark_mode))).check(matches((isDisplayed())))
    }
}
