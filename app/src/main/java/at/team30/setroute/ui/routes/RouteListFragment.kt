package at.team30.setroute.ui.routes

import android.os.Bundle
import android.view.*
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import at.team30.setroute.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RouteListFragment : Fragment() {
    private val viewModel: RouteListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_route_list, container, false)
        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.list) as ListView
        viewModel.getRoutes().observe(viewLifecycleOwner, { routeList ->
            val adapter = RouteAdapter(
                requireActivity().applicationContext,
                routeList
            );
            listView.adapter = adapter;
        });
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}