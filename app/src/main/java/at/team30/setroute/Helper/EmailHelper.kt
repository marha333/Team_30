package at.team30.setroute.Helper

import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class EmailHelper {
    companion object {
        suspend fun sendEmail(text: String, receiver: String) {
            val userName =  "setroute30@gmail.com"
            val password =  "team30setroute"
            val emailFrom = "setroute30@gmail.com"
            val subject = "Feedback SetRoute"



            val props = Properties()
            props.setProperty("mail.smtp.host", "smtp.googlemail.com")
            props.setProperty("mail.smtp.port", "587")
            props.setProperty("mail.smtp.auth", "true")
            props.setProperty("mail.smtp.starttls.enable", "true")

            val session = Session.getDefaultInstance(props, object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(userName, password)
                }
            })

            session.debug = true

            try {
                val mimeMessage = MimeMessage(session)
                mimeMessage.setFrom(InternetAddress(emailFrom))
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver, false))
                //mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailCC, false))
                mimeMessage.setText(text)
                mimeMessage.subject = subject
                mimeMessage.sentDate = Date()

                val smtpTransport = session.getTransport("smtp")
                smtpTransport.connect()
                smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
                smtpTransport.close()
            } catch (messagingException: MessagingException) {
                messagingException.printStackTrace()
            }
        }
    }
}