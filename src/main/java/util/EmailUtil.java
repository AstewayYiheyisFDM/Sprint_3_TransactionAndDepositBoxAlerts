package util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailUtil {
    private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());

    private EmailUtil(){
    }

    // Send email to the specified email ID
    public static void sendEmail(String to, String subject, String body) throws MessagingException {
        // Set up properties for the email
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        properties.put("mail.smtp.host", "smtp.gmail.com");   // Google's SMTP host
        properties.put("mail.smtp.port", "587");              // Port for TLS
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Trust Gmail's SSL certificates

        // Authenticator for the session
        final String username = "testmailforproject1234@gmail.com"; // Replace with your email
        final String password = System.getenv("SMTP_PASSWORD"); // Replace with your email password or app-specific password

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Compose the email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);
            logger.info("Email sent successfully!");

        } catch (MessagingException e) {
            logger.log(Level.SEVERE, e, () -> String.format("Failed to send email to %s", to));
            throw new MessagingException("Failed to send email to " + to);
        }
    }
}
