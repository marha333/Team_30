package at.team30.setroute

import at.team30.setroute.infrastructure.RoutesRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class RoutesRepositoryTest {

    val sut : RoutesRepository = RoutesRepository()

    @Test
    fun getRoutes_returnsThreeElements() {
        assertEquals(3, sut.getRoutes().size)
    }
}