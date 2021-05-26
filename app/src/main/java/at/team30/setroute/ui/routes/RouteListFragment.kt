package at.team30.setroute.ui.routes

import android.os.Bundle
import android.view.*
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import at.team30.setroute.R
import at.team30.setroute.models.Field
import at.team30.setroute.models.Interest
import at.team30.setroute.models.Order
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.RangeSlider
import com.zeugmasolutions.localehelper.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


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

        viewModel.language = LocaleHelper.getLocale(requireContext()).language
        val view = inflater.inflate(R.layout.fragment_route_list, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.list) as ListView
        viewModel.getRoutes().observe(viewLifecycleOwner, { routeList ->
            val adapter = RouteAdapter(
                requireActivity(),
                routeList
            )
            listView.adapter = adapter
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.sort -> {
                sortingDialog()
                return true
            }
            R.id.filter -> {
                filteringDialog()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun sortingDialog() {


        val dialogView: View = this.layoutInflater.inflate(R.layout.sort_dialog, null)
        val orderRadioGroup = dialogView.findViewById<RadioGroup>(R.id.sort_order_group)
        val fieldSpinner = dialogView.findViewById<Spinner>(R.id.sort_field)

        val options = viewModel.getSortingOptions()
        orderRadioGroup.check(when(options.order) {
            Order.ASCENDING -> R.id.sort_ascending
            Order.DESCENDING -> R.id.sort_descending
        })
        fieldSpinner.setSelection(when(options.field) {
            Field.TITLE -> 0
            Field.DURATION -> 1
            Field.DISTANCE -> 2
        })

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.sort_order)
            .setView(dialogView)
            .setPositiveButton(getString(R.string.apply)) { dialog, _ ->
                dialog.dismiss()
                viewModel.applySorting(
                    orderRadioGroup.checkedRadioButtonId,
                    resources.getStringArray(R.array.sort_options_identifier)[fieldSpinner.selectedItemPosition])
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create().show()
    }

    private fun filteringDialog() {
        val dialogView: View = this.layoutInflater.inflate(R.layout.filter_dialog, null)

        val durationSlider: RangeSlider = dialogView.findViewById(R.id.duration_slider)
        val lengthSlider: RangeSlider = dialogView.findViewById(R.id.length_slider)
        durationSlider.setValues(0f, 120f)
        lengthSlider.setValues(0f, 20f)

        val interests = resources.getStringArray(R.array.filter_options)

        val fieldSpinner = dialogView.findViewById<Spinner>(R.id.filter_field)

        val listInterests: ArrayList<Interest> = ArrayList()

        for (i in interests) {
            val interest = Interest()
            interest.setTitle(i)
            interest.setSelected(false)
            listInterests.add(interest)
        }

        val myAdapter = FilterAdapter(this.requireContext(), 0, listInterests)

        fieldSpinner.adapter = myAdapter

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.filter_options))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.apply)) { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create().show()
    }
}