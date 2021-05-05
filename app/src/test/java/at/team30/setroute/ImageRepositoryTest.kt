package at.team30.setroute

import android.graphics.Bitmap
import at.team30.setroute.infrastructure.ImageRepository
import at.team30.setroute.infrastructure.RoutesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImageRepositoryTest {
    private val ImgRepository : ImageRepository = ImageRepository(RoutesRepository())


    @Test
    fun imageIsNotPresent()
    {
        val img = runBlocking { ImgRepository.getImage(-1) }
        Assert.assertEquals(null, img)
    }


}