package at.team30.setroute

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.infrastructure.ISettingRepository
import at.team30.setroute.infrastructure.SettingRepository
import at.team30.setroute.models.Field
import at.team30.setroute.models.Order
import at.team30.setroute.models.Route
import at.team30.setroute.models.SortingOptions
import at.team30.setroute.ui.routes.RouteListViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RoutesListViewModelTest {

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository: IRoutesRepository = mockk()
    private val settingRepository : ISettingRepository = SettingRepository()

    @Before
    fun init() {
        settingRepository.storeSortingOptions(SortingOptions(Order.ASCENDING, Field.TITLE))
    }

    @Test
    fun routes_get_provides_all_routes() {
        // Arrange
        val routes = Fixtures.routes_many()
        given_routes_in_repository(routes)
        val sut = given_viewModel()

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertNotNull(result)
        assertEquals(routes.size, result?.size)
        assertEquals(routes, result)
    }

    private fun given_viewModel(): RouteListViewModel {
        return RouteListViewModel(mockRepository, settingRepository)
    }

    private fun given_routes_in_repository(routes: List<Route>) {
        every { mockRepository.getRoutes() } returns routes
        for (route in routes) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }
}