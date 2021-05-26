package at.team30.setroute.ui.routes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import at.team30.setroute.R
import at.team30.setroute.models.Interest


class FilterAdapter(context: Context, resource: Int, objects: List<Interest>) :
    ArrayAdapter<Interest?>(context, resource, objects) {
    private var mContext: Context? = null
    private var listState: ArrayList<Interest>? = null
    private var myAdapter: FilterAdapter? = null

    fun FilterAdapter(context: Context?, resource: Int, objects: List<Interest>?) {
        mContext = context
        listState = objects as ArrayList<Interest>?
        myAdapter = this
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position : Int, convertView : View?,
                      parent: ViewGroup) : View {
        val currentItemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.filter_spinner_item, parent, false)
        val currentInterest = getItem(position)

        val text : TextView = currentItemView.findViewById(R.id.f_spinner_item)
        if (currentInterest != null) {
            text.text = currentInterest.getTitle()
        }

        return currentItemView
    }
}