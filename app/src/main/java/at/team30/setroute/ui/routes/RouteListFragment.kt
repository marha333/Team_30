package at.team30.setroute.ui.routes

import android.os.Bundle
import android.util.Log
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

        val interests = resources.getStringArray(R.array.filter_options)

        val fieldSpinner = dialogView.findViewById<Spinner>(R.id.filter_field)

        val listInterests: ArrayList<Interest> = ArrayList()
        val spinnerTitle = Interest() // i couldn't think of anything else
        spinnerTitle.setTitle("Interests")
        listInterests.add(spinnerTitle)

        val options = viewModel.getFilteringOptions()
        val checkedBoxes = options.interests

        for ((counter, i) in interests.withIndex()) {
            val interest = Interest()
            interest.setTitle(i)
            if (checkedBoxes.contains(counter + 1))
                interest.setSelected(true)
            listInterests.add(interest)
        }

        durationSlider.setValues(options.minDuration, options.maxDuration)

        lengthSlider.setValues(options.minDistance, options.maxDistance)

        val myAdapter = FilterAdapter(this.requireContext(), 0, listInterests)

        fieldSpinner.adapter = myAdapter

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.filter_options))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.apply)) { dialog, _ ->
                val minDuration = durationSlider.values[0]
                val maxDuration = durationSlider.values[1]
                val minDistance = lengthSlider.values[0]
                val maxDistance = lengthSlider.values[1]
                dialog.dismiss()

                val checkedInterests : ArrayList<Int> = ArrayList()

                for ((counter, k) in myAdapter.getListState().withIndex()) {
                    if (k.isSelected()) {
                        checkedInterests.add(counter) // because the interest number 0 is a spinner title
                    }
                }

                viewModel.applyFiltering(checkedInterests, minDistance, maxDistance, minDuration, maxDuration)
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create().show()
    }
}