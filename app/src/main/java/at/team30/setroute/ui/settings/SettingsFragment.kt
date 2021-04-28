package at.team30.setroute.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import at.team30.setroute.R

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
    }


}