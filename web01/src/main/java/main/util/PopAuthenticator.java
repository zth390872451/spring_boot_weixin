package main.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Administrator on 2016/10/14.
 */
public class PopAuthenticator extends Authenticator {
    String userName = null;
    String password = null;
    public PopAuthenticator() {
    }
    public PopAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}