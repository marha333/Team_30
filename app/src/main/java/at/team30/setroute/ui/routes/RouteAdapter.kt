package at.team30.setroute.ui.routes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import at.team30.setroute.R
import at.team30.setroute.models.Route

class RouteAdapter(context:Context, var items:List<Route>) : ArrayAdapter<Route>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val currentItemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.route_item, parent, false)

            val currentRoute = getItem(position)

            val nameView : TextView = currentItemView.findViewById(R.id.name)
            nameView.setText(currentRoute?.name ?: "-")

            return currentItemView
    }

}