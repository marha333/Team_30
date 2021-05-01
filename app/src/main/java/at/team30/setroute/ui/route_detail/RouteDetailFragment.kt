package at.team30.setroute.ui.route_detail

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import at.team30.setroute.BuildConfig
import at.team30.setroute.R
import at.team30.setroute.models.Route
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteDetailFragment : Fragment() {
    private val viewModel: RouteDetailViewModel by viewModels()
    private val args: RouteDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_route_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val route = viewModel.getRoute(args.routeId)


        val name = view.findViewById<TextView>(R.id.title)
        name.text = route?.name
        val information = view.findViewById<TextView>(R.id.information)
        information.text = (route?.length?.toString() ?: "-") + " km  / " + (route?.duration?.toString() ?: "-") + " min"
        val description = view.findViewById<TextView>(R.id.description)
        description.text = route?.description

        requestImage(route, view.findViewById(R.id.header))
    }

    private fun requestImage(route: Route?, imageView: ImageView){
        if(route == null){
            return;
        }
        val queue = Volley.newRequestQueue(context)

        val apiKey = ""; //
        var url = "https://maps.googleapis.com/maps/api/staticmap?size=600x400&key=" + apiKey + "&style=feature:all%7Cvisibility=off"
        var counter = 0;
        if(route.positions != null){
            route.positions.forEach {
                url = url + "&markers=color:red%7Clabel:"+ (++counter) + "%7C" + it.latitude + "," + it.longitude
            }
        }

        // Request a string response from the provided URL.
        val imageRequest = ImageRequest(
                url,
                {bitmap -> // response listener
                    Log.d("Image Request:","Image loaded")
                    imageView.setImageBitmap(bitmap)
                    route.image = bitmap;
                },
                0, // max width
                0, // max height
                ImageView.ScaleType.CENTER_CROP, // image scale type
                Bitmap.Config.ARGB_8888, // decode config
                {error-> // error listener
                    Log.e("Image Request", error.message)
                }
        )

        // Add the request to the RequestQueue.
        if(route.image == null){
            queue.add(imageRequest)
        }else{
            imageView.setImageBitmap(route.image)
        }




    }
}