/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.payment;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lamhdt.utilities.DBHelper;

/**
 *
 * @author HL
 */
public class PaymentDAO implements Serializable{
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
    
    public  List<PaymentDTO> getPaymnetMethod() throws NamingException, SQLException{
        List<PaymentDTO> result = null;
        PaymentDTO dto = null;
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT paymentId,paymentMethod FROM Payment";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("paymentId");
                String paymentMethod = rs.getNString("paymentMethod");
                dto = new PaymentDTO(id, paymentMethod);
                result.add(dto);
            }
        } finally{
            closeConnection();
        }
        return result;
        
    }
}
