/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.orderdetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HL
 */
public class OrderDetailsDAO implements Serializable {

    private PreparedStatement stm = null;
    private Connection conn;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean insertOrderDetails(Connection conn, int orderId, int productId, int quantity) throws SQLException {
        boolean check = false;
        try {
            String sql = "INSERT INTO OrderDetails(orderID, productID, quantity) VALUES(?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, orderId);
            stm.setInt(2, productId);
            stm.setInt(3, quantity);
            check = stm.executeUpdate() > 0;
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }

    public List<OrderDetailsDTO> searchOrder(String name, Date startDate, Date endDate) {
        List<OrderDetailsDTO> result = null;
        try {

        } finally {

        }
        return result;
    }
}
