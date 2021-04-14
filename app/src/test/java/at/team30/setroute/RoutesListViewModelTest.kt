package at.team30.setroute

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import at.team30.setroute.infrastructure.RoutesRepository
import at.team30.setroute.ui.routes.RouteListViewModel
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RoutesListViewModelTest {

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val sut: RouteListViewModel = RouteListViewModel(RoutesRepository())

    @Test
    fun getRoutes_providesThreeItems() {
        assertEquals(3, sut.getRoutes().value?.size)
    }
}