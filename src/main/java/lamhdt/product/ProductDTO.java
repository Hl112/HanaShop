/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.product;

import java.io.Serializable;
import java.sql.Date;
import lamhdt.category.CategoryDTO;

/**
 *
 * @author HL
 */
public class ProductDTO implements Serializable{
    private int productId;
    private String productName;
    private String productImage;
    private String productDescription;
    private int productPrice;
    private int quantity;
    private Date createDate;
    private int categoryId;
    private CategoryDTO category;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, String productImage, String productDescription, int productPrice, int quantity, Date createDate, int categoryId, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.createDate = createDate;
        this.categoryId = categoryId;
        this.status = status;
    }

    public ProductDTO(int productId, String productName, String productImage, String productDescription, int productPrice, int quantity, Date createDate, CategoryDTO category, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.createDate = createDate;
        this.category = category;
        this.status = status;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    
}
