package at.team30.setroute.ui.routes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import at.team30.setroute.Helper.RouteIconHelper
import at.team30.setroute.R
import at.team30.setroute.models.Route
import at.team30.setroute.ui.settings.SettingsFragment
import com.zeugmasolutions.localehelper.LocaleHelper

class RouteAdapter(context: Context, private var items: List<Route>) : ArrayAdapter<Route>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val currentItemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.route_item, parent, false)
        val currentRoute = getItem(position)

        currentItemView.setOnClickListener {
            val action = RouteListFragmentDirections.actionRoutesFragmentToRouteDetailFragment(currentRoute?.id ?: 1)
            findNavController(parent).navigate(action)
        }

        val locale = LocaleHelper.getLocale(context)
        val iconView : ImageView = currentItemView.findViewById(R.id.image)
        val nameView : TextView = currentItemView.findViewById(R.id.name)
        val descriptionView : TextView = currentItemView.findViewById(R.id.description)
        val durationView : TextView = currentItemView.findViewById(R.id.duration)
        val lengthView : TextView = currentItemView.findViewById(R.id.length)
        val isWalked : TextView = currentItemView.findViewById(R.id.is_walked)

        val sharedPreference =  context.getSharedPreferences(SettingsFragment.SHARED_PREF_KEY, Context.MODE_PRIVATE)

        if(sharedPreference?.getBoolean("route_walked_${currentRoute?.id}", false) == false)
            isWalked.visibility = View.GONE
        else
            isWalked.visibility = View.VISIBLE

        nameView.text = currentRoute?.getLocalizedName(locale.language) ?: "-"
        descriptionView.text = currentRoute?.getLocalizedDescription(locale.language) ?: "-"

        val milesEnabled = sharedPreference?.getBoolean(SettingsFragment.MILES_PREF_KEY, false) ?: false
        val distanceUnit = context.getString(if(milesEnabled) R.string.unit_miles else R.string.unit_km)
        val length = String.format("%.2f", currentRoute?.getLength(milesEnabled)).toString()

        durationView.text =
                "${currentRoute?.duration.toString()} ${parent.resources.getString(R.string.unit_min)}"
        lengthView.text =
                "${length} ${distanceUnit}"

        iconView.setImageResource(RouteIconHelper.getRouteTypeIconIdentifier(currentRoute?.type))



        return currentItemView
    }
}