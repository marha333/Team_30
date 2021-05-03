package at.team30.setroute.ui.route_detail

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import at.team30.setroute.BuildConfig
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class RouteDetailViewModel @Inject constructor(
        @ApplicationContext private val context : Context,
        private val routesRepository : IRoutesRepository
) : ViewModel() {


    fun getRoute(id: Int): Route? {
        return routesRepository.getRoutesById(id)
    }

    fun requestImage(route: Route?,
                     responseListener: Response.Listener<android.graphics.Bitmap>,
                     errorListener: Response.ErrorListener) {
        if (route == null) {
            return;
        }
        val queue = Volley.newRequestQueue(context)
        //TODO test without api_key
        val apiKey = BuildConfig.MAPS_API_KEY; //
        var url = "https://maps.googleapis.com/maps/api/staticmap?size=600x400&key=" + apiKey + "&style=feature:all%7Cvisibility=off"
        var counter = 0;
        if (route.positions != null) {
            route.positions.forEach {
                url = url + "&markers=color:red%7Clabel:" + (++counter) + "%7C" + it.latitude + "," + it.longitude
            }
        }


        val imageRequest = ImageRequest(
                url,
                responseListener,
                0, // max width
                0, // max height
                ImageView.ScaleType.CENTER_CROP, // image scale type
                Bitmap.Config.ARGB_8888, // decode config
                errorListener
        )

        // Add the request to the RequestQueue.
        queue.add(imageRequest)

    }
}