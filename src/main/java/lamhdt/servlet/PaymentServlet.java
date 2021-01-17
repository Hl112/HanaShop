/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import lamhdt.cart.CartObj;
import lamhdt.payment.PaymentDAO;
import lamhdt.payment.PaymentDTO;
import lamhdt.product.ProductDAO;
import lamhdt.product.ProductDTO;

/**
 *
 * @author HL
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    private final String PAYMENT_PAGE = "paymentProcess.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        String url = PAYMENT_PAGE;
        try {
            HttpSession session = request.getSession();
            PaymentDAO paymentDAO = new PaymentDAO();
            List<PaymentDTO> list = paymentDAO.getPaymnetMethod();
            System.out.println(Arrays.toString(list.toArray()));
            session.setAttribute("PAYMENT", list);
                CartObj cart = (CartObj) session.getAttribute("CART");
                List<ProductDTO> listProduct = null;
                if (cart != null) {
                    Map<Integer, Integer> items = cart.getItems();
                    if (items != null) {
                        if (listProduct == null) {
                            listProduct = new ArrayList<>();
                        }
                        ProductDAO dao = new ProductDAO();
                        for (Integer item : items.keySet()) {
                            int quantity = items.get(item);
                            ProductDTO dto = dao.getProductById((int) item);
                            dto.setQuantity(quantity);
                            listProduct.add(dto);
                        }
                    }
                    session.setAttribute("LISTCART", listProduct);
                }
          
        } catch (NamingException ex) {
            log("PaymentServlet _ Naming : " + ex.getMessage());
        } catch (SQLException ex) {
            log("PaymentServlet _ SQL : " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
