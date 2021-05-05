package at.team30.setroute.ui.route_detail

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.Visibility
import at.team30.setroute.R
import at.team30.setroute.models.Language
import com.zeugmasolutions.localehelper.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteDetailFragment : Fragment() {
    private val viewModel: RouteDetailViewModel by viewModels()
    private val args: RouteDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setId(args.routeId);
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_route_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locale = LocaleHelper.getLocale(requireContext())
        val route = viewModel.getRoute(args.routeId)

        val imageView = view.findViewById<ImageView>(R.id.static_map_view);
        val headerView = view.findViewById<ImageView>(R.id.header)
        val errorView = view.findViewById<ImageView>(R.id.connectionError)
        val name = view.findViewById<TextView>(R.id.title)
        name.text = route?.getLocalizedName(locale.language)
        val information = view.findViewById<TextView>(R.id.information)
        information.text =
            "${route?.length?.toString() ?: "-"} ${getString(R.string.unit_km)} / ${route?.duration?.toString() ?: "-"} ${getString(R.string.unit_min)}"
        val description = view.findViewById<TextView>(R.id.description)
        description.text = route?.getLocalizedDescription(locale.language)


        val imageObserver = Observer<Bitmap?> { image ->


            if(image != null){
                imageView.setImageBitmap(image)
                imageView.visibility = View.VISIBLE;
                headerView.visibility = View.INVISIBLE
            }else{
                headerView.visibility = View.INVISIBLE;
                errorView.visibility = View.VISIBLE;
            }

        }
        viewModel.getImageLiveData().observe(viewLifecycleOwner,imageObserver);
    }
}