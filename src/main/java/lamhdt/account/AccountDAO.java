/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import lamhdt.utilities.DBHelper;

/**
 *
 * @author HL
 */
public class AccountDAO implements Serializable {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private AccountDTO info = null;
    
    private void closeConnection() throws SQLException{
        if(rs!=null) rs.close();
        if(preStm!=null) preStm.close();
        if(conn!=null) conn.close();
    }
    
    public boolean checkLogin(String username, String password) throws SQLException, NamingException{
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT isAdmin, fullname FROM Users WHERE userID = ? and password = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if(rs.next()){
                boolean isAdmin = rs.getBoolean("isAdmin");
                String fullname = rs.getNString("fullname");
                info = new AccountDTO(username, password, fullname, isAdmin);
                return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }

    public AccountDTO getInfo() {
        return info;
    }
    
    public boolean registration(String userID, String password, String fullname) throws NamingException, SQLException{
        try {
            conn = DBHelper.makeConnection();
            String sql = "INSERT INTO Users(userID, fullname, password, isAdmin) VALUES(?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setNString(2, fullname);
            preStm.setString(3, password);
            preStm.setBoolean(4, false);
            return preStm.executeUpdate() > 0;
        } finally{
            closeConnection();
        }
    }
    
}
