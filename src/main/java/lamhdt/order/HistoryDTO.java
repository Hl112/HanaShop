/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.order;

import java.io.Serializable;
import java.util.List;
import lamhdt.orderdetails.OrderDetailsDTO;
import lamhdt.orderstatus.OrderStatusDTO;
import lamhdt.payment.PaymentDTO;

/**
 *
 * @author HL
 */
public class HistoryDTO implements Serializable{
    private OrderDTO order;
    private List<OrderDetailsDTO> details;
    private OrderStatusDTO status;
    private PaymentDTO payment;

    public HistoryDTO() {
    }

    public HistoryDTO(OrderDTO order, List<OrderDetailsDTO> details) {
        this.order = order;
        this.details = details;
    }

    public OrderStatusDTO getStatus() {
        return status;
    }

    public void setStatus(OrderStatusDTO status) {
        this.status = status;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public List<OrderDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailsDTO> details) {
        this.details = details;
    }
    
    
}
