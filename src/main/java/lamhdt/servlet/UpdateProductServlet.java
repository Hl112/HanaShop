/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamhdt.product.ProductDAO;
import lamhdt.product.ProductDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author HL
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    public final String ADMIN_PAGE = "admin.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                HashMap<String, String> params = new HashMap<>();
                String filename = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    log("UploadProductServlet _ FileUpload :" + e.getMessage());
                    request.setAttribute("NOTI", "Input Error");
                }
                for (Object item : items) {
                    FileItem castItem = (FileItem) item;
                    if (castItem.isFormField()) {
                        params.put(castItem.getFieldName(), castItem.getString());
                    } else {
                        try {
                            filename = castItem.getName();
                            if(!filename.isBlank()){
                            String path = getServletContext().getRealPath("/upload") + "\\" + filename;
                            File f = new File(path);
                            f.delete();
                            castItem.write(f);
                            }
                        } catch (Exception ex) {
                            log("UploadProductServlet _ WriteFile : " + ex.getMessage());
                            request.setAttribute("NOTI", "Upload FIle Error");
                        }
                    }
                }
                String id = params.get("id");
                if (id != null) {
                    int productId = Integer.parseInt(id);
                    if(filename.isBlank()){
                        filename = params.get("imgOld");
                    }
                    String category = params.get("category");
                    String productName = params.get("name");
                    productName = URLEncoder.encode(productName, "ISO-8859-1");
                    productName = URLDecoder.decode(productName, "UTF-8");
                    String productPrice = params.get("price");
                    String quantity = params.get("quantity");
                    String description = params.get("description");
                    description = URLEncoder.encode(description, "ISO-8859-1");
                    description = URLDecoder.decode(description, "UTF-8");
                    int categoryId = Integer.parseInt(category);
                    int price = Integer.parseInt(productPrice);
                    int quantity_num = Integer.parseInt(quantity);
                    ProductDTO dto = new ProductDTO(productId, productName, filename, description, price, quantity_num, categoryId, true);
                    ProductDAO dao = new ProductDAO();
                     if(dao.updateProduct(dto)){
                         HttpSession session = request.getSession();
                         session.setAttribute("LOAD", 0);
                         request.setAttribute("NOTI", "Update product Successful!");
                     }else{
                         request.setAttribute("NOTI", "Update product Fail");
                     }
                }
            }

        } catch (SQLException ex) {
            log("UpdateProductServlet _ SQL : " + ex.getMessage());
        } catch (NamingException ex) {
           log("UpdateProductServlet _ Naming : " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(ADMIN_PAGE);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
