/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lamhdt.account.AccountDTO;
import lamhdt.utilities.DBHelper;

/**
 *
 * @author HL
 */
public class CategoryDAO implements Serializable {

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

    public List<CategoryDTO> getAllCategory() throws NamingException, SQLException {
        List<CategoryDTO> result = null;
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT categoryId, categoryName, categoryDescription FROM Category";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("categoryId");
                String name = rs.getNString("categoryName");
                String description = rs.getNString("categoryDescription");
                CategoryDTO dto = new CategoryDTO(id, name, description);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public CategoryDTO getCategoryById(int id,Connection conn) throws NamingException, SQLException {
        CategoryDTO dto = null;
            String sql = "SELECT categoryName, categoryDescription FROM Category WHERE categoryId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getNString("categoryName");
                String description = rs.getNString("categoryDescription");
                dto = new CategoryDTO(id, name, description);
            }
        return dto;
    }
}
