package id.seigan.dojo;

import java.io.Serializable;

public class Account implements Serializable {
    private String accountName;
    private String siginUrl;
    private final String username;
    private String password;

    public Account(String accountName, String username, String password){
        this.accountName = accountName;
        this.username = username;
        this.password = password;
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

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void passwordHistory(){

    }
}
