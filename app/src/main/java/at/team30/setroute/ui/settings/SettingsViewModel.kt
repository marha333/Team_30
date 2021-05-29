package at.team30.setroute.ui.settings

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.team30.setroute.Helper.EmailHelper
import at.team30.setroute.models.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {

    var emailResult: MutableLiveData<EmailHelper.EmailResult> = MutableLiveData(EmailHelper.EmailResult.UNKNOWN)

    fun getLanguages() : Array<String> = Language.values().map { it.code }.toTypedArray()
    fun getIndexForLocale(locale: Language): Int = getLanguages().indexOf(locale.code)

    fun sendFeedback(text: String)  {
        viewModelScope.launch (Dispatchers.IO){
            emailResult.postValue(EmailHelper.sendEmail(text, "setroute30@gmail.com"))
        }
    }
}