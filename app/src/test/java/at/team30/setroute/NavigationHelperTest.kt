package at.team30.setroute

import at.team30.setroute.Helper.NavigationHelper
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class NavigationHelperTest {

    private val mockRepository: IRoutesRepository = mockk()

    @Test
    fun getNavLink_no_waypoints() {
        val routes = Fixtures.routes_navigation_test()
        given_routes_in_repository(routes)

        val result = NavigationHelper.getNavLink(routes[0])

        Assert.assertNotNull(result)
        Assert.assertEquals("https://www.google.com/maps/dir/?api=1&origin=47.068291509777374,15.4505505&destination=47.06846198659862,15.434199801525855&travelmode=walking&dir_action=navigate&waypoints=", result)
    }

    @Test
    fun getNavLink_one_waypoint() {
        val routes = Fixtures.routes_navigation_test()
        given_routes_in_repository(routes)

        val result = NavigationHelper.getNavLink(routes[1])

        Assert.assertNotNull(result)
        Assert.assertEquals("https://www.google.com/maps/dir/?api=1&origin=47.068291509777374,15.4505505&destination=47.06846198659862,15.434199801525855&travelmode=walking&dir_action=navigate&waypoints=47.066792160267376,15.441534570967487%7C", result)
    }

    @Test
    fun getNavLink_many_waypoints() {
        val routes = Fixtures.routes_navigation_test()
        given_routes_in_repository(routes)

        val result = NavigationHelper.getNavLink(routes[2])

        Assert.assertNotNull(result)
        Assert.assertEquals("https://www.google.com/maps/dir/?api=1&origin=47.068291509777374,15.4505505&destination=47.06846198659862,15.434199801525855&travelmode=walking&dir_action=navigate&waypoints=47.066792160267376,15.441534570967487%7C47.06322620536392,15.436719303092854%7C", result)
    }

    @Test
    fun getNavLink_too_many_waypoints() {
        val routes = Fixtures.routes_navigation_test()
        given_routes_in_repository(routes)

        val result = NavigationHelper.getNavLink(routes[3])

        Assert.assertNotNull(result)
        Assert.assertEquals("https://www.google.com/maps/dir/?api=1&origin=47.068291509777374,15.4505505&destination=47.06846198659862,15.434199801525855&travelmode=walking&dir_action=navigate&waypoints=47.066792160267376,15.441534570967487%7C47.06322620536392,15.436719303092854%7C47.066879741064554,15.440527566412388%7C47.06766105481461,15.438427157230246%7C47.06815432749808,15.438588089763373%7C47.06820548144104,15.437107510458626%7C47.066484489688676,15.436217017108664%7C47.06936373974392,15.43755275714318%7C47.07202700318936,15.438219176788994%7C", result)
    }

    private fun given_routes_in_repository(routes: List<Route>) {
        every { mockRepository.getRoutes() } returns routes
        for (route in routes) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }
}