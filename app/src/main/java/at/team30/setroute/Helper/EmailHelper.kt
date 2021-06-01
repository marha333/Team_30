package at.team30.setroute.Helper

import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class EmailHelper(
    val smtpHost: String = "smtp.googlemail.com",
    val smtpPort: Int = 587,
    val authenticate: Boolean = true,
    val enableStartTtls: Boolean = true,
    val userName: String? = "setroute30@gmail.com",
    val password: String? = "team30setroute",
    val sender: String = "setroute30@gmail.com"
) : IEmailHelper {
    init {
        if(authenticate && (userName == null || password == null)){
            throw IllegalArgumentException("If authentication is enabled a username and password have to be provided")
        }
    }
    override suspend fun sendEmail(text: String, receiver: String, subject: String): IEmailHelper.EmailResult {
        // https://gist.github.com/BlackthornYugen/1b3e1ff4426294e7054c9a7190e8f2cd






        val props = Properties()

        props.setProperty("mail.smtp.host", smtpHost)
        props.setProperty("mail.smtp.port", smtpPort.toString())
        props.setProperty("mail.smtp.auth", authenticate.toString())
        props.setProperty("mail.smtp.starttls.enable", enableStartTtls.toString())



        val session = Session.getDefaultInstance(props, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(userName, password)
            }
        })

        session.debug = true

        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(sender))
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver, false))
            mimeMessage.setText(text)
            mimeMessage.subject = subject
            mimeMessage.sentDate = Date()

            val smtpTransport = session.getTransport("smtp")
            smtpTransport.connect()
            smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
            smtpTransport.close()
        } catch (messagingException: MessagingException) {
            messagingException.printStackTrace()
            return IEmailHelper.EmailResult.ERROR;
        }
        return IEmailHelper.EmailResult.SUCCESS;
    }
}