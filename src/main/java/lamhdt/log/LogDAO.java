/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.log;

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
public class LogDAO implements Serializable{
     private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public boolean createLog(LogDTO dto) throws NamingException, SQLException{
        boolean  check = false;
        try {
            conn = DBHelper.makeConnection();
            String sql = "INSERT INTO Log(productID,userID,command) VALUES(?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getProductId());
            preStm.setString(2, dto.getUserId());
            preStm.setString(3, dto.getCommand());
            check = preStm.executeUpdate()>0;
        } finally{
            closeConnection();
        }
        return check;
    }
}
