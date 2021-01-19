/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamhdt.account.AccountDTO;
import lamhdt.order.HistoryDTO;
import lamhdt.order.OrderDAO;
import lamhdt.orderstatus.OrderStatusDAO;

/**
 *
 * @author HL
 */
@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/OrderHistoryServlet"})
public class OrderHistoryServlet extends HttpServlet {

    private final String HISTORY_PAGE = "orderHistory.jsp";
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
            String name = request.getParameter("name");
            System.out.println(name);
//            if (!name.equals("") && name != null) {
//                name = URLEncoder.encode(name, "ISO-8859-1");
//                name = URLDecoder.decode(name, "UTF-8");
//            }
            request.setAttribute("name", name);
            String startDate = request.getParameter("sDate");

            String endDate = request.getParameter("eDate");
           
            OrderDAO dao = new OrderDAO();
            HttpSession session = request.getSession(false);
            if (session != null) {
                AccountDTO acc = (AccountDTO) session.getAttribute("USER");
                if (acc != null) {
                    dao.searchOrder(acc.getUserID(), name, startDate, endDate);
                    session.setAttribute("HISTORY", dao.getHistory());

                }
            }
            request.setAttribute("LOADHISTORY", 1);
        } catch (SQLException ex) {
            log("OrderHistory _ SQL : " + ex.getMessage());
        } catch (NamingException ex) {
            log("OrderHistory _ Naming : " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(HISTORY_PAGE);
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
