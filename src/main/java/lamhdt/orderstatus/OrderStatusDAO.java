/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.orderstatus;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HL
 */
public class OrderStatusDAO implements Serializable {
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
    
    public List<OrderStatusDTO> getStatus(Connection conn) throws SQLException{
        List<OrderStatusDTO> result = null;
        OrderStatusDTO dto = null;
        try {
           String sql = "SELECT orderStatusCode, orderStatusDescription FROM OrderStatus";
           preStm = conn.prepareStatement(sql);
           rs = preStm.executeQuery();
           result = new ArrayList<>();
           while(rs.next()){
               int orderStatusCode = rs.getInt("orderStatusCode");
               String orderStatusDescription = rs.getString("orderStatusDescription");
               dto = new OrderStatusDTO(orderStatusCode, orderStatusDescription);
               result.add(dto);
           }
        } finally{
            closeConnection();
        }
        return result;
    }
    
  
}
