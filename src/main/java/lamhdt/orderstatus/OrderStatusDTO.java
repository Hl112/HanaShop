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
    private String orderStatusCode;
    private String orderStatusDescription;

    public OrderStatusDTO() {
    }

    public OrderStatusDTO(String orderStatusCode, String orderStatusDescription) {
        this.orderStatusCode = orderStatusCode;
        this.orderStatusDescription = orderStatusDescription;
    }
    
    
}
