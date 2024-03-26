package com.leox;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, MessagingException, IOException, InterruptedException
    {

        SendingEmail email = new SendingEmail();
        email.message();

    }
}
