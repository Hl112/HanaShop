/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import lamhdt.orderdetails.OrderDetailsDAO;
import lamhdt.orderdetails.OrderDetailsDTO;
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
    private List<HistoryDTO> history;

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
                    if(productDTO.isStatus()){
                    if (items.get(productId) <= productDTO.getQuantity()) {
                        check = dao.insertOrderDetails(conn, id, productId, items.get(productId));
                        check = productDAO.updateQuantity(conn, productId, productDTO.getQuantity() - items.get(productId));
                    } else {
                        conn.rollback();
                        throw new Exception(productDTO.getProductName() + " is out of stock :" + "In stock: " + productDTO.getQuantity());
                    }} else{
                        conn.rollback();
                        throw new Exception(productDTO.getProductName() + " is inavaliable");
                    }
                    if(check == false) conn.rollback();
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

    public OrderDTO getOrder() {
        return order;
    }

    public List<HistoryDTO> getHistory() {
        return history;
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

    public void getAllOrder() throws SQLException, NamingException {
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT orderID, userID, orderDate, orderStatus, paymentId, address, phone FROM \"Order\"";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            history = new ArrayList<>();
            OrderDetailsDAO detailsDAO = new OrderDetailsDAO();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String userID = rs.getString("userID");
                Date orderDate = rs.getDate("orderDate");
                int orderStatus = rs.getInt("orderStatus");
                int paymentId = rs.getInt("paymentId");
                String address = rs.getNString("address");
                String phone = rs.getString("phone");
                OrderDTO orderDTO = new OrderDTO(orderID, userID, orderDate, orderStatus, paymentId, address, phone);
                List<OrderDetailsDTO> list = detailsDAO.getOrderDetailsById(conn, orderID);
                HistoryDTO hDTO = new HistoryDTO(orderDTO, list);
                history.add(hDTO);
            }
        } finally {
            closeConnection();
        }
    }

    public List<HistoryDTO> searchOrder(String name, Date startDate, Date endDate) throws SQLException, NamingException {
        List<HistoryDTO> result = null;
        getAllOrder();
        ProductDAO productDAO = new ProductDAO();
        ProductDTO productDTO = null;
        for (HistoryDTO historyDTO : getHistory()) {
            if (startDate.compareTo(historyDTO.getOrder().getOrderDate()) <= 0 && endDate.compareTo(historyDTO.getOrder().getOrderDate()) >= 0) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                if (name.equals("")) {
                    result.add(historyDTO);
                } else {
                    for (OrderDetailsDTO detail : historyDTO.getDetails()) {
                        productDTO = productDAO.getProductById(detail.getProductId());
                        if(productDTO.getProductName().matches(name)){
                            result.add(historyDTO);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

}
