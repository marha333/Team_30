package at.team30.setroute.ui.routes

import android.os.Bundle
import android.view.*
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import at.team30.setroute.R
import at.team30.setroute.models.Field
import at.team30.setroute.models.Order
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


        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireContext(),
                R.style.AlertDialogCustom
            )
        )

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

        builder.setTitle(getString(R.string.sort_order))

        builder.setView(dialogView)
        builder.setPositiveButton(getString(R.string.apply)) { dialog, _ ->
            dialog.dismiss()
            viewModel.applySorting(
                orderRadioGroup.checkedRadioButtonId,
                resources.getStringArray(R.array.sort_options_identifier)[fieldSpinner.selectedItemPosition])
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }

    private fun filteringDialog() {


        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireContext(),
                R.style.AlertDialogCustom
            )
        )
        builder.setTitle(getString(R.string.filter_options))


        //val dialogView: View = this.layoutInflater.inflate(R.layout.sort_dialog, null)

        //builder.setView(dialogView)
        builder.setPositiveButton(getString(R.string.apply)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}