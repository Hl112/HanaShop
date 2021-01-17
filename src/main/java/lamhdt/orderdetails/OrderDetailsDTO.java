/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.orderdetails;

import java.io.Serializable;
import lamhdt.product.ProductDTO;

/**
 *
 * @author HL
 */
public class OrderDetailsDTO implements Serializable{
    private int productId;
    private ProductDTO product;
    private int quantity;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int productId, ProductDTO product, int quantity) {
        this.productId = productId;
        this.product = product;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
