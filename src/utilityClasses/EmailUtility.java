package utilityClasses;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author raelg
 */
public class EmailUtility {

    public static void sendEmail(String recipient, String myMessage) throws MessagingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        // company email goes here
        String myaccountEmail = "";
        // company email password goes here
        String pass = "";

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myaccountEmail, pass);
            }
        });

        Message message = prepareMessage(session, myaccountEmail, recipient, myMessage);
        Transport.send(message);

    }

    private static Message prepareMessage(Session session, String myaccountEmail, String recipient, String myMessage) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myaccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Xtra vision");
            message.setText(myMessage);

            return message;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
