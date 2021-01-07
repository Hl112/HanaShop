/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.account;

import java.io.Serializable;

/**
 *
 * @author HL
 */
public class AccountDTO implements Serializable{
    private String userID;
    private String password;
    private String fullname;
    private boolean isAdmin;

    public AccountDTO() {
    }

    public AccountDTO(String userID, String password, String fullname, boolean isAdmin) {
        this.userID = userID;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

 
    
}
