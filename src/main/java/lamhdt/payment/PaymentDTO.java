/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.payment;

import java.io.Serializable;

/**
 *
 * @author HL
 */
public class PaymentDTO implements Serializable{
    private int paymentId;
    private String paymentMethod;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentId, String paymentMethod) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
    }

    public PaymentDTO(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    
}
