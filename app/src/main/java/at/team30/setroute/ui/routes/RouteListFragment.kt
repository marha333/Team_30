package at.team30.setroute.ui.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
        return inflater.inflate(R.layout.fragment_route_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.list) as ListView
        viewModel.getRoutes().observe(viewLifecycleOwner, { routeList ->
            val adapter: ArrayAdapter<String> = ArrayAdapter(
                requireActivity().applicationContext,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                routeList.map { it.name }
            );
            listView.adapter = adapter;
        });
    }
}