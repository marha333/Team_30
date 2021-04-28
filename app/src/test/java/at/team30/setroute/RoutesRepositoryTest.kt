package at.team30.setroute

import at.team30.setroute.infrastructure.RoutesRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class RoutesRepositoryTest {

    val sut: RoutesRepository = RoutesRepository()

    @Test
    fun getRoutes_returnsThreeElements() {
        assertEquals(7, sut.getRoutes().size)
    }

    @Test
    fun getRoutes_testDuration() {
        assertEquals(10, sut.getRoutes().elementAt(0).duration)
        assertEquals(15, sut.getRoutes().elementAt(1).duration)
        assertEquals(20, sut.getRoutes().elementAt(2).duration)
        assertEquals(30, sut.getRoutes().elementAt(3).duration)
        assertEquals(20, sut.getRoutes().elementAt(4).duration)
        assertEquals(30, sut.getRoutes().elementAt(5).duration)
        assertEquals(40, sut.getRoutes().elementAt(6).duration)
    }

    @Test
    fun getRoutes_testLength() {
        assertEquals(0.5, sut.getRoutes().elementAt(0).length, 0.01)
        assertEquals(0.7, sut.getRoutes().elementAt(1).length, 0.01)
        assertEquals(3.7, sut.getRoutes().elementAt(2).length, 0.01)
        assertEquals(5.9, sut.getRoutes().elementAt(3).length, 0.01)
        assertEquals(6.9, sut.getRoutes().elementAt(4).length, 0.01)
        assertEquals(10.6, sut.getRoutes().elementAt(5).length, 0.01)
        assertEquals(15.2, sut.getRoutes().elementAt(6).length, 0.01)
    }
}