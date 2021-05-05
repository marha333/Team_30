package at.team30.setroute

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import at.team30.setroute.ui.route_detail.RouteDetailViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RouteDetailViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository: IRoutesRepository = mockk()

    @Test
    fun route_get_returns_correct_route() {
        // Arrange
        val routes = Fixtures.routes_many()
        given_routes_in_repository(routes)
        val sut = given_viewModel()

        // Act
        val result = sut.getRoute(1)

        // Assert
        Assert.assertNotNull(result)
        Assert.assertEquals(routes[0], result)
    }

    private fun given_viewModel(): RouteDetailViewModel {
        return RouteDetailViewModel(mockRepository)
    }

    private fun given_routes_in_repository(routes: List<Route>) {
        every { mockRepository.getRoutes() } returns routes
        for (route in routes) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }
}