package at.team30.setroute

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.infrastructure.RoutesRepository
import at.team30.setroute.ui.routes.RouteListViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RoutesListViewModelTest {

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getRoutes_providesThreeItems() {
        val sut = given_viewModel_with_repository(RoutesRepository())
        assertEquals(7, sut.getRoutes().value?.size)
    }

    private fun given_viewModel_with_repository(repository: IRoutesRepository): RouteListViewModel {
        return RouteListViewModel(repository)
    }
}