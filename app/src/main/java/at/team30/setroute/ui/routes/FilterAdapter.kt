package at.team30.setroute.ui.routes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import at.team30.setroute.R
import at.team30.setroute.models.Interest


class FilterAdapter(context: Context, resource: Int, objects: ArrayList<Interest>) :
    ArrayAdapter<Interest?>(context, resource, objects as List<Interest?>) {
    private var listState: ArrayList<Interest> = objects
    private var isFromView = false

    fun getListState(): ArrayList<Interest> {
        return listState
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position : Int, convertView : View?, parent: ViewGroup) : View {
        var currentItemView = convertView
        val holder : ViewHolder

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(context).inflate(R.layout.filter_spinner_item, parent, false)
            holder = ViewHolder()

            holder.mTextView = currentItemView.findViewById(R.id.f_spinner_item)
            holder.mCheckBox = currentItemView.findViewById(R.id.f_spinner_checkbox)

            currentItemView!!.tag = holder
        } else {
            holder = currentItemView.tag as ViewHolder
        }

        holder.mTextView.text = listState[position].getTitle()

        isFromView = true
        holder.mCheckBox.isChecked = listState[position].isSelected()
        isFromView = false

        if (position == 0) {
            holder.mCheckBox.visibility = View.INVISIBLE
        } else {
            holder.mCheckBox.visibility = View.VISIBLE
        }

        holder.mCheckBox.tag = position

        holder.mCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (!isFromView) {
                listState[position].setSelected(isChecked)
            }
        }

        return currentItemView
    }

    private class ViewHolder {
        lateinit var mTextView: TextView
        lateinit var mCheckBox: CheckBox
    }

}