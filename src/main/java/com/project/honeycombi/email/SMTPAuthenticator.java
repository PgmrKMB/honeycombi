package com.project.honeycombi.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator{

    @Override
    protected PasswordAuthentication  getPasswordAuthentication() {
        return new PasswordAuthentication("lgh0325@gmail.com", "qefgbkmbuahvfije");
    }
    
}
