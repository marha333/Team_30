package at.team30.setroute.ui.route_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import at.team30.setroute.R
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
    }
}