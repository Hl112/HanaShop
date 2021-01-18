/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.orderstatus;

import java.io.Serializable;

/**
 *
 * @author HL
 */
public class OrderStatusDTO implements Serializable{
    private int orderStatusCode;
    private String orderStatusDescription;

    public OrderStatusDTO() {
    }

    public OrderStatusDTO(int orderStatusCode, String orderStatusDescription) {
        this.orderStatusCode = orderStatusCode;
        this.orderStatusDescription = orderStatusDescription;
    }

    public int getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(int orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public String getOrderStatusDescription() {
        return orderStatusDescription;
    }

    public void setOrderStatusDescription(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
    }
    
    
    
    
}
