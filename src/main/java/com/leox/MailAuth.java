package com.leox;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailAuth {

    String username = "info@testing.com";
    String password =  "Testing999@!";
    
    public Properties config(Properties prop) {
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.office365.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return prop;
    }

    Authenticator auth = new Authenticator() {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    };

    public Session getSession() {
        Session session = Session.getInstance(config(new Properties()), auth);
        return session;
    }

}
