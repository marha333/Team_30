package at.team30.setroute.ui.settings

import androidx.lifecycle.ViewModel
import at.team30.setroute.models.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {
    fun getLanguages() : Array<String> = Language.values().map { it.code }.toTypedArray()
    fun getIndexForLocale(locale: Language): Int = getLanguages().indexOf(locale.code)
}