/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HL
 */
public class DispatcherServlet extends HttpServlet {

    private final String STARTUP_SERVLET = "StartupServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String SEARCH_SERVLET = "SearchProductServlet";
    private final String SEARCH_AD_SERVLET = "GetProductServlet";
    private final String SHOWPRODUCT_SERVLET = "ShowProductServlet";
    // Cart
    private final String ADDTOCART_SERVLET = "AddToCartServlet";
    private final String VIEWCART_SERVLET = "ViewCartServlet";
    private final String REMOVEFROMCART_SERVLET = "RemoveFromCartServlet";
    private final String UPDATECART_SERVLET = "UpdateCartServlet";
    // Admin
    private final String ADDPRODUCT_SERVLET = "AddProductServlet";
    private final String DELETEPRODUCT_SERVLET = "DeleteProductServlet";
    private final String UPDATEPRODUCT_SERVLET = "UpdateProductServlet";
    // Check out
    private final String PAYMENT_SERVLET = "PaymentServlet";
    private final String CHECKOUT_SERVLET = "CheckOutServlet";

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
        String url = STARTUP_SERVLET;
        String button = request.getParameter("btAction");
        try {
            if (button != null) {
                if (button.equals("Login")) {
                    url = LOGIN_SERVLET;
                }
                if (button.equals("Search")) {
                    url = SEARCH_SERVLET;
                }
                if (button.equals("Add to cart")) {
                    url = ADDTOCART_SERVLET;
                }
                if (button.equals("View Cart")) {
                    url = VIEWCART_SERVLET;
                }
                if (button.equals("Remove from cart")) {
                    url = REMOVEFROMCART_SERVLET;
                }
                if (button.equals("Update Cart")) {
                    url = UPDATECART_SERVLET;
                }
                if (button.equals("Search Product")) {
                    url = SEARCH_AD_SERVLET;
                }
                if (button.equals("Add Product")) {
                    url = ADDPRODUCT_SERVLET;
                }
                if (button.equals("Show")) {
                    url = SHOWPRODUCT_SERVLET;
                }
                if (button.equals("Delete Product")) {
                    url = DELETEPRODUCT_SERVLET;
                }
                if (button.equals("Update Product")) {
                    url = UPDATEPRODUCT_SERVLET;
                }
                if (button.equals("Payment")) {
                    url = PAYMENT_SERVLET;
                }
                if(button.equals("Check out")){
                    url = CHECKOUT_SERVLET;
                }

            }

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
