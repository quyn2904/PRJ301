/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quyenpq.cart.CartObject;
import quyenpq.order.OrderDAO;
import quyenpq.orderDetail.OrderDetailDAO;
import quyenpq.product.ProductDTO;
import quyenpq.users.UsersDTO;

/**
 *
 * @author Goby
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "login.html";
    private final String PRODUCT_PAGE = "product.jsp";
    private final String RESULT_PAGE = "checkOutSuccess.jsp";

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
        String url = LOGIN_PAGE;
        boolean result = false;
        try{
            HttpSession session = request.getSession(false);
            if(session != null){
                List<ProductDTO> listCheckOutItems = (List<ProductDTO>)session.getAttribute("LIST_ITEM_IN_VIEW");
                OrderDAO orderDAO = new OrderDAO();
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                UsersDTO user_info = (UsersDTO) session.getAttribute("USER_INFO");
                String orderId = orderDAO.createOrder(user_info.getFullName());
                if (orderId != null) {
                    float total = orderDetailDAO.createOrderDetails(listCheckOutItems, orderId);
                    result = orderDAO.completeOrder(orderId, total);
                    if (result) {
                        request.setAttribute("LIST_ITEMS_IN_CHECKOUT_VIEW", listCheckOutItems);
                        request.setAttribute("ORDER_ID", orderId);
                        request.setAttribute("TOTAL_PRICE", total);
                        session.removeAttribute("LIST_ITEM_IN_VIEW");
                        session.removeAttribute("CART");
                        url = RESULT_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
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
