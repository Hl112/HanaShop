/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lamhdt.category.CategoryDAO;
import lamhdt.category.CategoryDTO;
import lamhdt.product.ProductDAO;
import lamhdt.product.ProductDTO;

/**
 *
 * @author HL
 */
@WebServlet(name = "SearchProductServlet", urlPatterns = {"/SearchProductServlet"})
public class SearchProductServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "shopping.jsp";

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
        String searchValue = request.getParameter("searchValue");
        String categoryId = request.getParameter("category");
        String price = request.getParameter("price");
        searchValue = URLEncoder.encode(searchValue, "ISO-8859-1");
        searchValue = URLDecoder.decode(searchValue, "UTF-8");
        request.setAttribute("searchValue", searchValue);
        int id = -1, priceMax = -1;
        try {
            if(categoryId != null && !categoryId.equals("---Select Category---")){
                id = Integer.parseInt(categoryId);
            }
            if(price != null && !price.equals("---Select Price---")){
                priceMax = Integer.parseInt(price);
            }
            CategoryDAO cateDAO = new CategoryDAO();
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = dao.searchProduct(searchValue, id, 0, priceMax,true);
            List<CategoryDTO> listCategory = cateDAO.getAllCategory();
            HttpSession session = request.getSession();
            session.setAttribute("CATEGORY", listCategory);
            session.setAttribute("PRODUCT", list);
            session.setAttribute("LOAD", 1);
        } catch (NamingException ex) {
            log("SearchProductServlet _ Naming : " + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchProductServlet _ SQL : " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(SHOPPING_PAGE);
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
