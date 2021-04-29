package at.team30.setroute

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import at.team30.setroute.infrastructure.DependencyInjection
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import at.team30.setroute.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(DependencyInjection::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockRepository: IRoutesRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun given_one_route_in_repository_expect_one_route_displayed_in_list() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_one())

        // Act
        ActivityScenario.launch(MainActivity::class.java)

        // Assert
        for(route in FixturesAndroid.routes_one()) {
            onView(withText(route.name)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun given_many_routes_in_repository_expect_all_routes_displayed_in_list() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_many())

        // Act
        ActivityScenario.launch(MainActivity::class.java)

        // Assert
        for(route in FixturesAndroid.routes_many()) {
            onView(withText(route.name)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun given_many_routes_enter_detail_of_first_route() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_many())
        val selectedRoute = FixturesAndroid.routes_many()[0]

        // Act
        ActivityScenario.launch(MainActivity::class.java)
        onView(withText(selectedRoute.name)).perform(click())

        // Assert
        onView(withText(selectedRoute.name)).check(matches(isDisplayed()))
        onView(withText(selectedRoute.description)).check(matches(isDisplayed()))
    }

    @Test
    fun given_many_routes_enter_detail_press_back_displays_list_view() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_many())
        val selectedRoute = FixturesAndroid.routes_many()[0]

        // Act
        ActivityScenario.launch(MainActivity::class.java)
        onView(withText(selectedRoute.name)).perform(click())

        // Assert
        onView(withText(selectedRoute.name)).check(matches(isDisplayed()))
        onView(withText(selectedRoute.description)).check(matches(isDisplayed()))

        // Act
        onView(isRoot()).perform(pressBack())

        // Assert
        for(route in FixturesAndroid.routes_many()) {
            onView(withText(route.name)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun given_many_routes_enter_detail_press_routes_button_displays_list_view() {
        // Arrange
        given_routes_in_repository(FixturesAndroid.routes_many())
        val selectedRoute = FixturesAndroid.routes_many()[0]

        // Act
        ActivityScenario.launch(MainActivity::class.java)
        onView(withText(selectedRoute.name)).perform(click())

        // Assert
        onView(withText(selectedRoute.name)).check(matches(isDisplayed()))
        onView(withText(selectedRoute.description)).check(matches(isDisplayed()))

        // Act
        onView(withId(R.id.routesFragment)).perform(click())

        // Assert
        for(route in FixturesAndroid.routes_many()) {
            onView(withText(route.name)).check(matches(isDisplayed()))
        }
    }

    private fun given_routes_in_repository(routes: List<Route>) {
        every { mockRepository.getRoutes() } returns routes
        for (route in routes) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }
}
