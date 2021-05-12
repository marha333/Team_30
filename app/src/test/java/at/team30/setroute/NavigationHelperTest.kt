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
        Assert.assertEquals("https://www.google.com/maps/dir/?api=1&origin=47.068291509777374,15.4505505&destination=47.06846198659862,15.434199801525855&travelmode=walking&waypoints=", result)
    }

    @Test
    fun getNavLink_one_waypoint() {
        val routes = Fixtures.routes_navigation_test()
        given_routes_in_repository(routes)

        val result = NavigationHelper.getNavLink(routes[1])

        Assert.assertNotNull(result)
        Assert.assertEquals("https://www.google.com/maps/dir/?api=1&origin=47.068291509777374,15.4505505&destination=47.06846198659862,15.434199801525855&travelmode=walking&waypoints=47.066792160267376,15.441534570967487%7C", result)
    }

    @Test
    fun getNavLink_many_waypoints() {
        val routes = Fixtures.routes_navigation_test()
        given_routes_in_repository(routes)

        val result = NavigationHelper.getNavLink(routes[2])

        Assert.assertNotNull(result)
        Assert.assertEquals("https://www.google.com/maps/dir/?api=1&origin=47.068291509777374,15.4505505&destination=47.06846198659862,15.434199801525855&travelmode=walking&waypoints=47.066792160267376,15.441534570967487%7C47.06322620536392,15.436719303092854%7C", result)
    }

    private fun given_routes_in_repository(routes: List<Route>) {
        every { mockRepository.getRoutes() } returns routes
        for (route in routes) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }
}