package at.team30.setroute.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
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
        val languageButton = view.findViewById<Button>(R.id.language_button)
        languageButton.setOnClickListener { languageDialog() }
    }

    private fun languageDialog() {
        val languages = arrayOf("EN", "DE", "RU")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.select_a_language))
        builder.setSingleChoiceItems(languages, 0) { dialog, i ->
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }


}