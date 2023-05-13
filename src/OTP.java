import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * The OTP class is a class that represents a one-time password (OTP).The class provides a method sendOTPEmail(String toEmail, String otp) that takes an email address and an OTP as inputs, and sends an email to the specified email address containing the OTP
 */
public class OTP {
    /**
     *this function sends an OTP to the users email
     * @param toEmail
     * @param otp
     */
    public void sendOTPEmail(String toEmail, String otp) {
        final String fromEmail = "senderotp1@gmail.com";
        final String appPassword = "hajzcfmqtltyqdio";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, appPassword);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your Registering OTP Code");
            message.setText("Your OTP Code is: " + otp);
            Transport.send(message);
            System.out.println("OTP email sent to " + toEmail);
        } catch (MessagingException e) {
            System.out.println("Error sending OTP email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}