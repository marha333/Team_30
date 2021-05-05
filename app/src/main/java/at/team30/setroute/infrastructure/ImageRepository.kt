package at.team30.setroute.infrastructure

import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import at.team30.setroute.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class ImageRepository @Inject constructor( private val routesRepository : IRoutesRepository) : IImageRepository{
    private var map = mutableMapOf<Int, Bitmap>()
    private val client = OkHttpClient()
    private val apiKey = BuildConfig.MAPS_API_KEY

    override suspend fun getImage(route_id : Int): Bitmap? {
        var image = map.get(route_id)
        val route = routesRepository.getRoutesById(route_id)
        if(route != null && image == null)
        {
            fun run() {
                var url = "https://maps.googleapis.com/maps/api/staticmap?size=600x400&key=" + apiKey + "&style=feature:all%7Cvisibility=off"
                var counter = 0
                if (route.positions != null) {
                    route.positions.forEach {
                        url = url + "&markers=color:red%7Clabel:" + (++counter) + "%7C" + it.latitude + "," + it.longitude
                    }
                }

                val request = Request.Builder()
                    .url(url)
                        .build()

                            client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                                /*
                        for ((name, value) in response.headers) {
                            println("$name: $value")
                        }
                                 */
                         image = BitmapFactory.decodeStream(response.body!!.byteStream())
                    }
            }
            map[route_id] = image!!
            return image
        }
        else
            return image
    }
}