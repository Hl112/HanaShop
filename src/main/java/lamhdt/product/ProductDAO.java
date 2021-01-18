/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lamhdt.category.CategoryDAO;
import lamhdt.category.CategoryDTO;
import lamhdt.utilities.DBHelper;

/**
 *
 * @author HL
 */
public class ProductDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

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

    public List<ProductDTO> getAllProduct() throws NamingException, SQLException {
        List<ProductDTO> result = null;
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT productID,productName,productImage,productDescription,productPrice,quantity,createDate,categoryId,status FROM Product WHERE status = 1 AND quantity >0 ORDER BY createDate DESC";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            CategoryDAO dao = new CategoryDAO();
            while (rs.next()) {
                int id = rs.getInt("productID");
                String name = rs.getNString("productName");
                String img = rs.getString("productImage");
                String description = rs.getNString("productDescription");
                int price = rs.getInt("productPrice");
                int quantity = rs.getInt("quantity");
                Date createDate = rs.getDate("createDate");
                int categoryId = rs.getInt("categoryId");
                boolean status = rs.getBoolean("status");
                CategoryDTO category = dao.getCategoryById(categoryId, conn);
                ProductDTO dto = new ProductDTO(id, name, img, description, price, quantity, createDate, category, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getMaxPrice(Connection conn) throws SQLException {
        String sql = "SELECT MAX(productPrice) AS maxPrice FROM dbo.Product";
        preStm = conn.prepareStatement(sql);
        rs = preStm.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxPrice");
        }
        return 9999999;
    }

    public int getMinPrice(Connection conn) throws SQLException {
        String sql = "SELECT MIN(productPrice) AS minPrice FROM dbo.Product";
        preStm = conn.prepareStatement(sql);
        rs = preStm.executeQuery();
        if (rs.next()) {
            return rs.getInt("minPrice");
        }
        return 0;
    }

    public List<ProductDTO> searchProduct(String searchName, int searchCategoryId, int min, int max,boolean statuss) throws NamingException, SQLException {
        List<ProductDTO> result = null;
        String searchCategory = (searchCategoryId != -1) ? " AND categoryId = " + searchCategoryId : "";
        try {
            conn = DBHelper.makeConnection();
            if (min == -1) {
                min = getMinPrice(conn);
            }
            if (max == -1) {
                max = getMaxPrice(conn);
            }
            String sql = "SELECT productID,productName,productImage,productDescription,productPrice,quantity,createDate,categoryId,status \n"
                    + "FROM Product\n"
                    + "WHERE productName LIKE '%" + searchName + "%' AND productPrice BETWEEN " + min + " AND " + max + "" + searchCategory + " AND status = ? AND quantity >0 ORDER BY createDate DESC";
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, statuss);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            CategoryDAO dao = new CategoryDAO();
            while (rs.next()) {
                int id = rs.getInt("productID");
                String name = rs.getNString("productName");
                String img = rs.getString("productImage");
                String description = rs.getNString("productDescription");
                int price = rs.getInt("productPrice");
                int quantity = rs.getInt("quantity");
                Date createDate = rs.getDate("createDate");
                int categoryId = rs.getInt("categoryId");
                boolean status = rs.getBoolean("status");
                CategoryDTO category = dao.getCategoryById(categoryId, conn);
                ProductDTO dto = new ProductDTO(id, name, img, description, price, quantity, createDate, category, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ProductDTO getProductById(int id) throws NamingException, SQLException {
        ProductDTO dto = null;
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT productName,productImage,productDescription,productPrice,quantity,createDate,categoryId,status FROM Product WHERE productID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                CategoryDAO dao = new CategoryDAO();
                String name = rs.getNString("productName");
                String img = rs.getString("productImage");
                String description = rs.getNString("productDescription");
                int price = rs.getInt("productPrice");
                int quantity = rs.getInt("quantity");
                Date createDate = rs.getDate("createDate");
                int categoryId = rs.getInt("categoryId");
                boolean status = rs.getBoolean("status");
                CategoryDTO category = dao.getCategoryById(categoryId, conn);
                dto = new ProductDTO(id, name, img, description, price, quantity, createDate, category, status);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public ProductDTO getQuantityById(Connection conn, int id) throws SQLException {
        ProductDTO dto  = null;
        try {
            String sql = "SELECT productName,quantity,status FROM Product WHERE productId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getNString("productName");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                 dto = new ProductDTO();
                dto.setProductName(name);
                dto.setQuantity(quantity);
                dto.setStatus(status);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                rs.close();
            }
        }
        return dto;
    }

    public boolean removeProductById(int id) throws SQLException, NamingException {
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            String sql = "UPDATE PRODUCT SET status = 0 WHERE productId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean addProduct(ProductDTO dto) throws SQLException, NamingException {
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            String sql = "INSERT INTO Product(productName,productImage,productDescription,productPrice,quantity,createDate,categoryId,status)\n"
                    + "VALUES(?,?,?,?,?,GETDATE(),?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, dto.getProductName());
            preStm.setString(2, dto.getProductImage());
            preStm.setNString(3, dto.getProductDescription());
            preStm.setInt(4, dto.getProductPrice());
            preStm.setInt(5, dto.getQuantity());
            preStm.setInt(6, dto.getCategoryId());
            preStm.setBoolean(7, true);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateProduct(ProductDTO dto) throws SQLException, NamingException {
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            String sql = "UPDATE Product SET productName = ?, productImage = ?, productDescription = ?, productPrice = ?,quantity = ?, categoryId = ?, status = ? WHERE productID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, dto.getProductName());
            preStm.setString(2, dto.getProductImage());
            preStm.setNString(3, dto.getProductDescription());
            preStm.setInt(4, dto.getProductPrice());
            preStm.setInt(5, dto.getQuantity());
            preStm.setInt(6, dto.getCategoryId());
            preStm.setBoolean(7, dto.isStatus());
            preStm.setInt(8, dto.getProductId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean updateQuantity(Connection conn,int id, int quantity) throws SQLException{
        boolean check = false;
        try {
            String sql = "UPDATE Product SET quantity = ? WHERE productId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setInt(2, id);
            check = preStm.executeUpdate()>0;
        } finally{
            if(preStm!=null)preStm.close();
        }
        return check;
    }
}
