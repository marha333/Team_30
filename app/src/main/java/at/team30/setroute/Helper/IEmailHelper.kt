package at.team30.setroute.Helper

interface IEmailHelper {
    enum class EmailResult { ERROR, SUCCESS, UNKNOWN }

    suspend fun sendEmail(text: String, receiver: String, subject: String): EmailResult
}