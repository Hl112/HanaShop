/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author HL
 */
public class OrderDTO implements Serializable{
    private int orderId;
    private String userId;
    private Date orderDate;
    private int orderStatus;
    private int paymentId;
    private String address;
    private String phone;

    public OrderDTO() {
    }

    public OrderDTO(String userId, int orderStatus, int paymentId, String address, String phone) {
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.paymentId = paymentId;
        this.address = address;
        this.phone = phone;
    }

    public OrderDTO(int orderId, String userId, Date orderDate, int orderStatus, int paymentId, String address, String phone) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentId = paymentId;
        this.address = address;
        this.phone = phone;
    }

   

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    
    
    
}
