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
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author HL
 */
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

    private final String ADDPRODUCT_PAGE = "addProduct.jsp";

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
                HashMap<String, String> params = new HashMap();
                String filename = "noimg.png";
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    request.setAttribute("NOTI", "Input Error");
                }
                for (Object it : items) {
                    FileItem castItem = (FileItem) it;
                    if (castItem.isFormField()) {
                        params.put(castItem.getFieldName(), castItem.getString());
                    } else {
                        try {
                            filename = castItem.getName();
                            if (!filename.isBlank()) {
                                String path = getServletContext().getRealPath("/upload") + "\\" + castItem.getName();
                                System.out.println(path);
                                File f = new File(path);
                                castItem.write(f);
                            }
                        } catch (Exception e) {
                            request.setAttribute("NOTI", "Upload File Error");
                        }
                    }
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
                if(filename.isBlank()) filename = "noimg.png";
                ProductDTO dto = new ProductDTO(productName, filename, description, price, quantity_num, categoryId, true);
                ProductDAO dao = new ProductDAO();
                if (dao.addProduct(dto)) {
                    request.setAttribute("NOTI", "Add product successfuly");
                    HttpSession session = request.getSession();
                    session.setAttribute("LOAD", 0);
                } else {
                    request.setAttribute("NOTI", "Add product fail");
                }
            }
        } catch (SQLException ex) {
            log("AddProductServlet _ SQL :" + ex.getMessage());
        } catch (NamingException ex) {
            log("AddProductServlet _ Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(ADDPRODUCT_PAGE);
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
