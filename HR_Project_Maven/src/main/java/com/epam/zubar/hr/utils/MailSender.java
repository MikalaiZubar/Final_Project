package com.epam.zubar.hr.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.entity.Candidate;

public class MailSender {

    private static final Logger LOGGER = LogManager.getLogger(MailSender.class);
    private static final String SMTP_HOST = "smtp.yandex.ru";
    private static final int PORT = 465;
    private static final String SECURITY = "javax.net.ssl.SSLSocketFactory";
    private static final String AUTH = "true";
    private static final String EMAIL = "mikalai.zubar@yandex.ru";
    private static final String PASS = "Nz115599";
    private static final String MESS_SUBJ = "I-T-T-T info";
    private static final String MESS_TEXT = "Interview status has been changed. Please check your profile.";



    private static Properties createMailProperties(){

        Properties p = new Properties();
        p.put("mail.smtp.host", SMTP_HOST);
        p.put("mail.smtp.socketFactory.port", PORT);
        p.put("mail.smtp.socketFactory.class", SECURITY);
        p.put("mail.smtp.auth", AUTH);
        p.put("mail.smtp.port", PORT);

        return p;
    }

    private static Session createSession(){
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(EMAIL, PASS);
            }
        };
        return Session.getDefaultInstance(createMailProperties(), auth);
    }


    private static Message createMessage(Candidate candidate) throws AddressException, MessagingException{
        Session session = createSession();
        Message message = null;
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(candidate.getEmail()));
        message.setSubject(MESS_SUBJ);
        message.setText(MESS_TEXT);

        return message;
    }

    public static void sendMessage(Candidate candidate){

        try {
            Message mess = createMessage(candidate);
            Transport.send(mess);
        } catch (MessagingException e) {
            LOGGER.error("Message sending error! " + e);
        }
    }

}
