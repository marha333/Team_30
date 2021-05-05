package at.team30.setroute

import android.graphics.Bitmap
import at.team30.setroute.infrastructure.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ImageRepositoryInstrumentedTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private var imageRepository = ImageRepository(RoutesRepository())

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

        val map_member = imageRepository.javaClass.getDeclaredField("map")
        map_member.isAccessible = true
        val member_val =  map_member.get(imageRepository) as MutableMap<Int, Bitmap>
        member_val[0] = bmp

        val img = runBlocking { imageRepository.getImage(0) }
        Assert.assertEquals(bmp, img)
    }
}