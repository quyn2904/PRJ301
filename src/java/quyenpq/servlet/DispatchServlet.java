/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// thằng này là controller, nó là thằng điều phối nó không có xử lý , nó cũng giống như là Controller trong 
// án TW z á, nó chỉ gọi những thằng khác ra xử lý mà không có xử lý tại đây
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String VIEW_CART = "viewCart.jsp";
    private final String REMOVE_FROM_CART_CONTROLLER = "RemoveFromCartServlet";

//        chỗ này phải coi lại nè 
//        do mình tạo sai file
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //   1. Which button did usser click
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (button == null) { //first time request and app starts up
                // transfer Login Page
                // check cookies to determine which page is transfered
                url = START_UP_CONTROLLER;
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("Delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Add Book To Your Cart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("View Your Cart")) {
                url = VIEW_CART;
            } else if (button.equals("Remove Selected Items")) {
                url = REMOVE_FROM_CART_CONTROLLER;
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
