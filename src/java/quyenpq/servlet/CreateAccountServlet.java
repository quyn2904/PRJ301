/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.servlet;

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
import quyenpq.users.UsersCreateError;
import quyenpq.users.UsersDAO;
import quyenpq.users.UsersDTO;

/**
 *
 * @author Goby
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
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
        //1. get all params
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullname");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        boolean foundError = false;
        UsersCreateError errors = new UsersCreateError();
        String url = ERROR_PAGE;

        try {
            //2. Check users' errors
            if (username.trim().length() < 6 || username.trim().length() > 12) {
                foundError = true;
                errors.setUsernameLengthError("Username is required typing from 6 to 12 characters");
            }
            if (password.trim().length() < 8 || password.trim().length() > 20) {
                foundError = true;
                errors.setPasswordLengthError("Password is required typing from 8 to 20 characters");
            } else if (!confirmPassword.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmPasswordNotMatch("Confirm password must match password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 20) {
                foundError = true;
                errors.setFullnameLengthError("Fullname is required typing from 2 to 20 characters");
            }
            if (foundError) {
                //catching a specific attribute then to go error page to show
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                //3. create account - call model
                UsersDAO dao = new UsersDAO();
                UsersDTO dto = new UsersDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }
            //4. process result
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }// username is existed
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
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
