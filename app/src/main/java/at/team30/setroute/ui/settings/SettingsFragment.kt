package at.team30.setroute.ui.settings

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import at.team30.setroute.R
import at.team30.setroute.ui.MainActivity

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val languages = arrayListOf("EN", "DE", "RUS")
        val language_view = view.findViewById<Spinner>(R.id.spinner_languages)
        val language_adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, languages)
        language_view.adapter = language_adapter

        val sharedPreference = activity?.getSharedPreferences("test_preferences", Context.MODE_PRIVATE)
        val DMSwitch = view.findViewById<Switch>(R.id.switch_dark_mode)
        if (sharedPreference != null)
            DMSwitch.isChecked = sharedPreference.getBoolean("DMSwitchState", false)

        val darkModeSwitch = view.findViewById(R.id.switch_dark_mode) as Switch
        darkModeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            var editor = sharedPreference?.edit()
            if (isChecked) {
                activity?.setTheme(R.style.Theme_SetRoute_Dark)
                editor?.putBoolean("DMSwitchState", true)
                editor?.putString("CurrentTheme","Dark")
                editor?.commit()
                recreate(requireActivity() as Activity)
            } else {
                activity?.setTheme(R.style.Theme_SetRoute)
                editor?.putBoolean("DMSwitchState", false)
                editor?.putString("CurrentTheme","Light")
                editor?.commit()
                recreate(requireActivity() as Activity)
            }
        }
    }


}