package com.project.honeycombi.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class Mailer {
    public void sendMail(
        String to, String subject, String content, SMTPAuthenticator smtp) {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "465");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.debug", "true");
            p.put("mail.smtp.socketFactory.port", "465");
            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.put("mail.smtp.socketFactory.fallback", "false");

            try {
               Session ses = Session.getInstance(p, smtp);
               ses.setDebug(true);
               MimeMessage msg = new MimeMessage(ses);
               msg.setSubject(subject);
               Address fromAddr = new InternetAddress("lgh0325@gamil.com", "꽃자루");
               msg.setFrom(fromAddr);
               Address toAddr = new InternetAddress(to);
               msg.addRecipient(Message.RecipientType.TO, toAddr);
               msg.setContent(content, "text/html;charset=UTF-8");
               Transport.send(msg);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    

