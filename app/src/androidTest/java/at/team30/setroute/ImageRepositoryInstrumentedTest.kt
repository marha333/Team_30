package at.team30.setroute

import android.graphics.Bitmap
import at.team30.setroute.infrastructure.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@UninstallModules(MockDependencyInjection::class)
@HiltAndroidTest
class ImageRepositoryInstrumentedTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    var ImageRepository = at.team30.setroute.infrastructure.ImageRepository(RoutesRepository())

    @Before
    fun init() {
        hiltRule.inject()
    }


    @Test
    fun imageIsPresent()
    {
        val w: Int = 100
        val h: Int = 100

        val conf = Bitmap.Config.ARGB_8888 // see other conf types

        val bmp = Bitmap.createBitmap(w, h, conf) // this creates a MUTABLE bitmap

        val map_member = ImageRepository.javaClass.getDeclaredField("map")
        map_member.isAccessible = true
        val member_val =  map_member.get(ImageRepository) as MutableMap<Int, Bitmap>
        member_val[0] = bmp

        val img = runBlocking { ImageRepository.getImage(0) }
        Assert.assertEquals(bmp, img)
    }
}