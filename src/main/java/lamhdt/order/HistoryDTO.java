/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.order;

import java.io.Serializable;
import java.util.List;
import lamhdt.orderdetails.OrderDetailsDTO;

/**
 *
 * @author HL
 */
public class HistoryDTO implements Serializable{
    private OrderDTO order;
    private List<OrderDetailsDTO> details;

    public HistoryDTO() {
    }

    public HistoryDTO(OrderDTO order, List<OrderDetailsDTO> details) {
        this.order = order;
        this.details = details;
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
