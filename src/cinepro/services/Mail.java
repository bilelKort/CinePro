package cinepro.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    private String username = "selim.boulaaba@esprit.tn";
    private String password = "fiiidryfibybvprp";

    public void envoyer(String name, String trailer, String date) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("votre_mail@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("selim.boulaaba@esprit.tn"));
            message.setSubject("Test email");
            message.setText("Nouveau Film: \n" + name + "\n\nDate de projection: " + date + "\n\nRegarder la bande-annonce: " + trailer);

            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
