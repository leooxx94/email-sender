package com.leox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendingEmail {
    
    String mitt = "info@testing.com";
    String sbj = "Email di congratulazioni";

    String msg = "Gentile Sig.ra/Mr...., <br><br> Congratulazioni per i risultati ottenuti da parte di una società immaginaria. "+
    "Si allega un documento (in questo caso, un Report Mensile) per fornire all'utente una visione dettagliata dei successi raggiunti. "+
    "<br><br>Si invita l'utente a contattare la società per ulteriori dettagli o informazioni.";
    String record = "";

    MailAuth auth = new MailAuth();

    public void message() throws MessagingException, FileNotFoundException, IOException, InterruptedException{

        BufferedReader reader = new BufferedReader(new FileReader("resources/csv/indirizzi.csv"));
                List<String> lines = new ArrayList<>();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            for(int i = 0; i < lines.size(); i++){
                Message message = new MimeMessage(auth.getSession());
                message.setFrom(new InternetAddress(mitt, "Testing Spa"));
                message.setRecipients(
                        Message.RecipientType.TO, InternetAddress.parse(lines.get(i))); //dest
                message.setSubject(sbj);

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                
                File file = new File("resources/allegato/allegato1.pdf");
                mimeBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                mimeBodyPart.setDataHandler(new DataHandler(source));
                mimeBodyPart.setFileName("allegato_report_mensile.pdf");
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);

                Transport.send(message);
                System.out.println("email sent to: " + lines.get(i));
                Thread.sleep(5000);
            }
       
    }



}
