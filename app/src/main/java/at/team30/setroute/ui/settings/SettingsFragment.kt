package at.team30.setroute.ui.settings

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import at.team30.setroute.R
import at.team30.setroute.models.Language
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import com.zeugmasolutions.localehelper.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private val viewModel: SettingsViewModel by viewModels()

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
                editor?.putString("CurrentTheme", "Dark")
                editor?.commit()
                recreate(requireActivity() as Activity)
            } else {
                activity?.setTheme(R.style.Theme_SetRoute)
                editor?.putBoolean("DMSwitchState", false)
                editor?.putString("CurrentTheme", "Light")
                editor?.commit()
                recreate(requireActivity() as Activity)
            }
        }

        val distanceUnitSwitch = view.findViewById(R.id.switch_units) as Switch
        if (sharedPreference != null)
            distanceUnitSwitch.isChecked = sharedPreference.getBoolean("MilesEnabled", false)
        distanceUnitSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            var editor = sharedPreference?.edit()

            editor?.putBoolean("MilesEnabled", isChecked)

            editor?.commit()
        }


        val sendFeedbackButton = view.findViewById(R.id.submit_feedback_button) as Button
        val feedbackTextView = view.findViewById(R.id.feedback_text_field) as EditText

        sendFeedbackButton.setOnClickListener() {
            val textFeedback = feedbackTextView.text.toString()
            viewModel.sendFeedback(textFeedback)
            feedbackTextView.text.clear()
        }


    }

    private fun languageDialog() {
        val currentIndex = viewModel.getIndexForLocale(Language.forCode(LocaleHelper.getLocale(requireContext()).language))

        val builder = AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.AlertDialogCustom))
        val titleView: View = this.layoutInflater.inflate(R.layout.alert_title, null)
        titleView.findViewById<TextView>(R.id.title_text).text = resources.getString(R.string.select_a_language)
        builder.setCustomTitle(titleView)
        builder.setSingleChoiceItems(viewModel.getLanguages(), currentIndex) { dialog, i ->
            val language = viewModel.getLanguages()[i].toLowerCase(Locale.ROOT)
            (activity as LocaleAwareCompatActivity).updateLocale(Locale(language))
            updateSelectedLanguage()
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun updateSelectedLanguage() {
        val languageButton = requireView().findViewById<Button>(R.id.language_button)
        languageButton.text = Language.forCode(LocaleHelper.getLocale(requireContext()).language).code
    }
}

