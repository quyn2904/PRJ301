/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.servlet;

import quyenpq.users.UsersDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quyenpq.users.UsersDAO;

/**
 *
 * @author Goby
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.html";
    private final String RESULT_PAGE = "search.jsp"; // trang này sẽ trình bài dữ liệu đọc

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException /*NamingException*/, IOException {
        response.setContentType("text/html;charset=UTF-8");

//        1.get all client information
        String searchValue = request.getParameter("txtSearchValue");
        String url = SEARCH_PAGE;
        try {
            // khi nào search
            // khi có giá trj trong form input            
            if (!searchValue.trim().isEmpty()) {
                // 2. call DAO 
                // 2.1 new DAO Object
                UsersDAO dao = new UsersDAO();
                // 2.2 call method of DAO
                dao.searchLastName(searchValue);

                List<UsersDTO> result = dao.getAccounts();
//                  attribute giúp lưu dữ liueej vào trong scope
                request.setAttribute("SEARCH_RESULT", result);
                // 3. proccess Result 
                url = RESULT_PAGE;
            } // end user type a valid value
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
//            tại lại là dispatcher là do nếu sendirect thì mà về tới br nó sẽ xóa hết 
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
