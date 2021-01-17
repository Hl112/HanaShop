/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import lamhdt.orderdetails.OrderDetailsDAO;
import lamhdt.product.ProductDAO;
import lamhdt.product.ProductDTO;
import lamhdt.utilities.DBHelper;

/**
 *
 * @author HL
 */
public class OrderDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private OrderDTO order;

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

    public boolean createOrder(OrderDTO dto, Map<Integer, Integer> items) throws NamingException, SQLException, Exception {
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                int id = getLastId(conn);
                conn.setAutoCommit(false);
                String sql = "INSERT INTO \"Order\"(orderID,userID, orderStatus, paymentId,address, phone) VALUES(?,?,?,?,?,?)";
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, id);
                preStm.setString(2, dto.getUserId());
                preStm.setInt(3, dto.getOrderStatus());
                preStm.setInt(4, dto.getPaymentId());
                preStm.setString(5, dto.getAddress());
                preStm.setString(6, dto.getPhone());
                check = preStm.executeUpdate() > 0;
                OrderDetailsDAO dao = new OrderDetailsDAO();
                ProductDAO productDAO = new ProductDAO();
                for (Integer productId : items.keySet()) {
                    ProductDTO productDTO = productDAO.getQuantityById(conn, productId);
                    if (items.get(productId) <= productDTO.getQuantity()) {
                        check = dao.insertOrderDetails(conn, id, productId, items.get(productId));
                    } else {
                        conn.rollback();
                        throw new Exception(productDTO.getProductName() + "is out of stock :" + "In stock: " + productDTO.getQuantity());

                    }
                }
                conn.commit();
                order = dto;
                order.setOrderId(id);
            }

        } finally {
            closeConnection();
        }
        return check;
    }

    public int getLastId(Connection conn) throws SQLException {
        int id = 0;
        try {
            String sql = "SELECT TOP 1 orderID FROM dbo.[Order] ORDER BY orderID DESC";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("orderID");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return id + 1;
    }

}
