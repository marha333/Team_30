package at.team30.setroute.ui.settings

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import at.team30.setroute.Helper.EmailHelper
import at.team30.setroute.Helper.IEmailHelper
import at.team30.setroute.R
import at.team30.setroute.models.Language
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.switchmaterial.SwitchMaterial
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import com.zeugmasolutions.localehelper.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*



@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private val viewModel: SettingsViewModel by viewModels()

    companion object {
        const val SHARED_PREF_KEY = "SettingsPreferences"
        const val DM_PREF_KEY = "DMSwitchState"
        const val MILES_PREF_KEY = "MilesEnabled"
    }

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
        updateSelectedLanguage()

        val sharedPreference = activity?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val dmSwitch = view.findViewById<SwitchMaterial>(R.id.switch_dark_mode)
        dmSwitch.isChecked = sharedPreference?.getBoolean(DM_PREF_KEY, false) ?: false
        dmSwitch.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPreference?.edit()
            if (isChecked) {
                editor?.putBoolean(DM_PREF_KEY, true)
                editor?.commit()
                recreate(requireActivity() as Activity)
            } else {
                editor?.putBoolean(DM_PREF_KEY, false)
                editor?.commit()
                recreate(requireActivity() as Activity)
            }
        }

        val distanceUnitSwitch = view.findViewById<SwitchMaterial>(R.id.switch_units)
        distanceUnitSwitch.isChecked = sharedPreference?.getBoolean(MILES_PREF_KEY, false) ?: false
        distanceUnitSwitch.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPreference?.edit()
            editor?.putBoolean(MILES_PREF_KEY, isChecked)
            editor?.commit()
        }


        val sendFeedbackButton = view.findViewById(R.id.submit_feedback_button) as Button
        val feedbackTextView = view.findViewById(R.id.feedback_text_field) as EditText


        sendFeedbackButton.setOnClickListener() {
            val textFeedback = feedbackTextView.text.toString()
            viewModel.sendFeedback(textFeedback)
            viewModel.emailResult.observe(viewLifecycleOwner, {
                if(it == IEmailHelper.EmailResult.SUCCESS){
                    Toast.makeText(context, "Thanks for the feedback!", Toast.LENGTH_LONG).show()
                    feedbackTextView.text.clear()
                }else if(it == IEmailHelper.EmailResult.ERROR){
                    Toast.makeText(context, "Error sending feedback", Toast.LENGTH_LONG).show();
                }
            })


        }


    }

    private fun languageDialog() {
        val currentIndex = viewModel.getIndexForLocale(Language.forCode(LocaleHelper.getLocale(requireContext()).language))

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.select_a_language)
            .setSingleChoiceItems(viewModel.getLanguages(), currentIndex) { dialog, i ->
                val language = viewModel.getLanguages()[i].toLowerCase(Locale.ROOT)
                (activity as LocaleAwareCompatActivity).updateLocale(Locale(language))
                updateSelectedLanguage()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create().show()
    }

    private fun updateSelectedLanguage() {
        val languageButton = requireView().findViewById<Button>(R.id.language_button)
        languageButton.text = Language.forCode(LocaleHelper.getLocale(requireContext()).language).code
    }
}

