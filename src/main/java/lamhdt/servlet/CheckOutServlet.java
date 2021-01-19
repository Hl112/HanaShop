/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
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
import lamhdt.order.OrderDAO;
import lamhdt.order.OrderDTO;

/**
 *
 * @author HL
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String VIEWCART_SERVLET = "ViewCartServlet";
    private final String VIEWORDER_PAGE = "viewOrder.jsp";

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
        String url = VIEWCART_SERVLET;
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String payment = request.getParameter("payment");
            int status = 0;
            if (payment != null) {
                int payment_id = Integer.parseInt(payment);
                if (payment_id != 2) {
                    status = 1;
                }
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    AccountDTO acc = (AccountDTO) session.getAttribute("USER");
                    if (cart != null && acc != null) {
                        Map<Integer, Integer> items = cart.getItems();
                        if (items != null) {
                            OrderDAO dao = new OrderDAO();
                            OrderDTO dto = new OrderDTO(acc.getUserID(), status, payment_id, address, phone);
                            if (dao.createOrder(dto, items)) {
                                url = VIEWORDER_PAGE;
                                request.setAttribute("ORDER", dto);
                                request.setAttribute("NOTI", "Order successfuly");
                            } else {
                                request.setAttribute("NOTI", "ORDER Fail");
                            }
                        }
                    }
                }

            }
        } catch (NamingException ex) {
            log("CheckOutServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckOutServlet _ SQL: " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("NOTI", ex.getMessage());
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
