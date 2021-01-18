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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lamhdt.utilities.DBHelper;

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

    public List<OrderDetailsDTO> searchOrder(String name, String startDate, String endDate) throws NamingException, SQLException {
        List<OrderDetailsDTO> result = null;
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT orderID,orderDate, orderStatus,paymentId, productId, quantity "
                    + "FROM [ORDER] AS A JOIN OrderDetails AS B ON A.orderID = B.orderID "
                    + "WHERE  A.orderDate <= '12-12-2022' AND A.orderDate >= '1/1/2000'";
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<OrderDetailsDTO> getOrderDetailsById(Connection conn,int id) throws SQLException{
        List<OrderDetailsDTO> result = null;
        OrderDetailsDTO dto = null;
        try {
            String sql = "SELECT productID, quantity FROM OrderDetails WHERE orderID = ?";
            stm =  conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
               int productId = rs.getInt("productID");
               int quantity = rs.getInt("quantity");
               dto = new OrderDetailsDTO(id, productId, quantity);
               result.add(dto);
            }
        } finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
        }
        return result;
    }
}
