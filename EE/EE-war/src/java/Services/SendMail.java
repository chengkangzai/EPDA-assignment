/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author CCK
 */
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    private String to;
    private String subject;
    private final ArrayList<String> content = new ArrayList();

    private final Integer PORT = 465;
    private final Boolean SSL_ENABLED = true;
    private final Boolean AUTH_ENABLED = true;

    private final String FROM = "";
    private final String HOST = "smtp.gmail.com";
    private final String PASSWORD = "";

    public SendMail() {
    }

    public SendMail to(String email) {
        this.to = email;
        return this;
    }

    public SendMail subject(String subject) {
        this.subject = subject;
        return this;
    }

    public SendMail salute() {
        this.content.add("Hi There!");
        return this;
    }

    public SendMail content(String content) {
        this.content.add(content);
        return this;
    }

    public SendMail signature() {
        this.content.add("");
        this.content.add("Thank you and stay safe");
        this.content.add("APStock");
        return this;
    }

    public SendMail send() {
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", this.HOST);
        properties.put("mail.smtp.port", this.PORT);
        properties.put("mail.smtp.ssl.enable", this.SSL_ENABLED);
        properties.put("mail.smtp.auth", this.AUTH_ENABLED);

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));

            message.setSubject(this.subject);

            message.setText(this.content.stream().collect(Collectors.joining("\n")));

            Transport.send(message);
        } catch (MessagingException mex) {
            System.out.println(mex.getMessage());
        }
        return this;

    }

}
