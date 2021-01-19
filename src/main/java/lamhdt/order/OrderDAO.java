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
import lamhdt.orderstatus.OrderStatusDAO;
import lamhdt.payment.PaymentDAO;
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
                    if (productDTO.isStatus()) {
                        if (items.get(productId) <= productDTO.getQuantity()) {
                            check = dao.insertOrderDetails(conn, id, productId, items.get(productId));
                            check = productDAO.updateQuantity(conn, productId, productDTO.getQuantity() - items.get(productId));
                        } else {
                            conn.rollback();
                            throw new Exception(productDTO.getProductName() + " is out of stock :" + "In stock: " + productDTO.getQuantity());
                        }
                    } else {
                        conn.rollback();
                        throw new Exception(productDTO.getProductName() + " is inavaliable");
                    }
                    if (check == false) {
                        conn.rollback();
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

    public void searchOrder(String userId, String name, String sDate, String eDate) throws SQLException, NamingException {
        String min = "1/1/2000";
        String max = "12/12/2999";
        if (!sDate.equals("")) {
            min = sDate;
        }
        if (!eDate.equals("")) {
            max = eDate;
        }
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT orderID,orderDate,orderStatus,paymentId,address,phone "
                    + "FROM [ORDER] "
                    + "WHERE orderDate BETWEEN ? AND ? AND userID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, min);
            preStm.setString(2, max);
            preStm.setString(3, userId);
            rs = preStm.executeQuery();
            history = new ArrayList<>();
            OrderDetailsDAO detailsDAO = new OrderDetailsDAO();
            ProductDAO proDAO = new ProductDAO();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                Date orderDate = rs.getDate("orderDate");
                int orderStatus = rs.getInt("orderStatus");
                int paymentId = rs.getInt("paymentId");
                String address = rs.getNString("address");
                String phone = rs.getString("phone");
                OrderDTO orderDTO = new OrderDTO(orderID, userId, orderDate, orderStatus, paymentId, address, phone);
                List<OrderDetailsDTO> list = detailsDAO.getOrderDetailsById(conn, orderID);
                OrderStatusDAO sDAO = new OrderStatusDAO();
                PaymentDAO pDAO = new PaymentDAO();
                boolean check = false;
                for (OrderDetailsDTO orderDetailsDTO : list) {
                    ProductDTO dto = proDAO.getProductById(orderDetailsDTO.getProductId());
                    orderDetailsDTO.setProduct(dto);
                    if (dto.getProductName().contains(name)) {
                        check = true;
                    }
                }
                if (check) {
                    HistoryDTO hDTO = new HistoryDTO(orderDTO, list);
                    hDTO.setStatus(sDAO.getStatusById(conn,orderStatus));
                    hDTO.setPayment(pDAO.getPaymnetMethodById(conn, paymentId));
                    history.add(hDTO);
                }
            }
        } finally {
            closeConnection();
        }
    }

}
