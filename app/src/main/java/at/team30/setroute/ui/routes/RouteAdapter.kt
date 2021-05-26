package at.team30.setroute.ui.routes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import at.team30.setroute.Helper.RouteIconHelper
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

        val locale = LocaleHelper.getLocale(context)
        val nameView : TextView = currentItemView.findViewById(R.id.name)
        val descriptionView : TextView = currentItemView.findViewById(R.id.description)
        val durationView : TextView = currentItemView.findViewById(R.id.duration)
        val lengthView : TextView = currentItemView.findViewById(R.id.length)
        val iconView : ImageView = currentItemView.findViewById(R.id.image)

        val sharedPreference =  context.getSharedPreferences("test_preferences", Context.MODE_PRIVATE)
        val currentTheme = sharedPreference.getString("CurrentTheme","Light") as String


        //nameView.text = currentRoute?.name ?: "-"

        if (currentTheme == "Dark")
            nameView.setTextColor(ContextCompat.getColor(context, R.color.white))
        else
            nameView.setTextColor(ContextCompat.getColor(context, R.color.black))

        nameView.text = currentRoute?.getLocalizedName(locale.language) ?: "-"
        descriptionView.text = currentRoute?.getLocalizedDescription(locale.language) ?: "-"



        // shared Preference

        var milesEnabled = sharedPreference?.getBoolean("MilesEnabled", false) ?: false
        var distanceUnit = ""
        if(milesEnabled) {
            distanceUnit = context.getString(R.string.unit_miles)
        }
        else {
            distanceUnit = context.getString(R.string.unit_km)
        }


        val length = String.format("%.2f", currentRoute?.getLength(milesEnabled)).toString()



        durationView.text =
                "${currentRoute?.duration.toString()} ${parent.resources.getString(R.string.unit_min)}"
        lengthView.text =
                "${length} ${distanceUnit}"

        if (currentTheme == "Dark")
            iconView.setImageResource(RouteIconHelper.getRouteTypeIconIdentifier(currentRoute?.type, true))
        else
            iconView.setImageResource(RouteIconHelper.getRouteTypeIconIdentifier(currentRoute?.type, false))

        return currentItemView
    }
}