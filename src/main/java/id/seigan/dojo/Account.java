package id.seigan.dojo;

import java.io.Serializable;
import java.util.HashMap;

public class Account implements Serializable {
//    private static final long serialVersionUID = 1L;
    private String accountName;
    private String signinUrl;
    private final String username;
    private String password;
    private HashMap<String, String> Password = new HashMap<String, String>();

    public Account(String accountName, String signinUrl, String username, String password, HashMap<String, String> Password){
        this.accountName = accountName;
        this.signinUrl = signinUrl;
        this.username = username;
        this.password = password;
        this.Password = Password;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getSiginUrl() {
        return signinUrl;
    }

    public HashMap getPasswordHistory(){
        return Password;
    }

    public void setPassword(String password, HashMap<String, String> Password){
        this.password = password;
        this.Password = Password;
    }

    public void passwordHistory(){
        for (String i : Password.keySet()) {
            System.out.println("waktu modifikasi : " + i + " || password: " + Password.get(i));
        }
    }
}
