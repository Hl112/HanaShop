/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import lamhdt.account.AccountDTO;
import lamhdt.log.LogDAO;
import lamhdt.log.LogDTO;
import lamhdt.product.ProductDAO;
import lamhdt.product.ProductDTO;

/**
 *
 * @author HL
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {

    private final String ADMIN_PAGE = "admin.jsp";
    private final String SEARCH_AD_SERVLET = "GetProductServlet";

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
            String productId = request.getParameter("id");
            System.out.println(productId);
            if (productId != null) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    AccountDTO acc = (AccountDTO) session.getAttribute("USER");
                    if (acc != null) {
                        int id = Integer.parseInt(productId);
                        ProductDAO dao = new ProductDAO();
                        ProductDTO dto = dao.getProductById(id);
                        String path = getServletContext().getRealPath("/upload") + "\\" + dto.getProductImage();
                        File f = new File(path);
                        f.delete();
                        LogDAO logDAO = new LogDAO();
                        if (dao.removeProductById(id)) {
                            LogDTO log = new LogDTO(dto.getProductId(), acc.getUserID(), "Delete");
                            logDAO.createLog(log);
                            request.setAttribute("NOTI", "Delete Product Successfuly");
                            session.setAttribute("LOAD", 0);
                        } else {
                            request.setAttribute("NOTI", "Delete Fail");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("DeleteProductServlet _ SQL :" + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteProductServlet _ Naming: " + ex.getMessage());
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
