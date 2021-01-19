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

    public OrderStatusDTO getStatusById(Connection conn, int id) throws SQLException {
        OrderStatusDTO dto = null;
        try {
            String sql = "SELECT orderStatusCode, orderStatusDescription FROM OrderStatus WHERE orderStatusCode = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                int orderStatusCode = rs.getInt("orderStatusCode");
                String orderStatusDescription = rs.getString("orderStatusDescription");
                dto = new OrderStatusDTO(orderStatusCode, orderStatusDescription);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return dto;
    }

}
