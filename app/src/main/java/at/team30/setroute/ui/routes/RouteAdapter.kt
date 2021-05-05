package at.team30.setroute.ui.routes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import at.team30.setroute.R
import at.team30.setroute.models.Route
import com.zeugmasolutions.localehelper.LocaleHelper

class RouteAdapter(context: Context, private var items: List<Route>) : ArrayAdapter<Route>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val currentItemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.route_item, parent, false)
        val currentRoute = getItem(position)

        currentItemView.setOnClickListener {
            val action = RouteListFragmentDirections.actionRoutesFragmentToRouteDetailFragment(currentRoute?.id ?: 1)
            findNavController(parent).navigate(action)
        }

        val nameView : TextView = currentItemView.findViewById(R.id.name)
        val descriptionView : TextView = currentItemView.findViewById(R.id.description)
        val durationView : TextView = currentItemView.findViewById(R.id.duration)
        val lengthView : TextView = currentItemView.findViewById(R.id.length)

        val locale = LocaleHelper.getLocale(context)
        nameView.text = currentRoute?.getLocalizedName(locale.language) ?: "-"
        descriptionView.text = currentRoute?.getLocalizedDescription(locale.language) ?: "-"
        durationView.text =
            "${currentRoute?.duration.toString()} ${parent.resources.getString(R.string.unit_min)}"
        lengthView.text =
            "${currentRoute?.length.toString()} ${parent.resources.getString(R.string.unit_km)}"

        return currentItemView
    }
}