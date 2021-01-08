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
import lamhdt.account.AccountDTO;
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
    private AccountDTO info = null;

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
            String sql = "SELECT productID,productName,productImage,productDescription,productPrice,quantity,createDate,categoryId,status FROM Product";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            CategoryDAO dao = new CategoryDAO();
            while (rs.next()) {
                int id = rs.getInt("productID");
                String name = rs.getNString("productName");
                String img = rs.getString("productImage");
                String description = rs.getNString("productDescription");
                float price = rs.getFloat("productPrice");
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

    public float getMaxPrice(Connection conn) throws SQLException {
        String sql = "SELECT MAX(productPrice) AS maxPrice FROM dbo.Product";
        preStm = conn.prepareStatement(sql);
        rs = preStm.executeQuery();
        if (rs.next()) {
            return rs.getFloat("maxPrice");
        }
        return 999999;
    }

    public float getMinPrice(Connection conn) throws SQLException {
        String sql = "SELECT MIN(productPrice) AS minPrice FROM dbo.Product";
        preStm = conn.prepareStatement(sql);
        rs = preStm.executeQuery();
        if (rs.next()) {
            return rs.getFloat("minPrice");
        }
        return 0;
    }

    public List<ProductDTO> searchProduct(String searchName, int searchCategoryId, float min, float max) throws NamingException, SQLException {
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
                    + "WHERE productName LIKE '%" + searchName + "%' AND productPrice BETWEEN " + min + " AND " + max + "" + searchCategory + " AND status = 1 AND quantity >0 ORDER BY createDate DESC";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            CategoryDAO dao = new CategoryDAO();
            while (rs.next()) {
                int id = rs.getInt("productID");
                String name = rs.getNString("productName");
                String img = rs.getString("productImage");
                String description = rs.getNString("productDescription");
                float price = rs.getFloat("productPrice");
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
}
