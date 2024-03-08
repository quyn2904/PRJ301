/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quyenpq.util.DBHelper;

/**
 *
 * @author Goby
 */
//phải implements Serializable
//những đối tượng là object đặc biệt là JDBC API
//1. Phải khai báo
//2. thực hiện xử lý
//3. đóng lại trong tất cả mọi trường hợp
public class UsersDAO implements Serializable {

    //public boolean checkLogin(String username, String password)
    //        throws SQLException, /*ClassNotFoundException,*/ NamingException {
    public UsersDTO checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UsersDTO result = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //ném ra 2 lỗi: classNotFound và 
                //2. create SQL String
                String sql = "Select lastname, isAdmin "
                        + "From users "
                        + "Where username = ? "
                        + "And password = ?";
                //các mệnh đề phải viết trên từng dòng
                //mỗi khi gõ xong 1 từ thì phải space
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    //result = true;
                    //map ==> get Data từ resultset và set data to DTO property
                    String fullname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new UsersDTO(username, null, fullname, isAdmin);
                }//end username and password are check
            } //end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<UsersDTO> accounts;

    public List<UsersDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException /*ClassNotFoundException*/, NamingException {

        //    1 khai báo 
        Connection con = null;
        //          do câu lệnh sql mình đang dừ cú pháp của perparestament
        //  tại sao lại là PreparedStatement
        // vì đây là SQL có điều kiện mà 
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 2. xử lý
            //         1.Get Connection 
            con = DBHelper.getConnection();
//             kết nối rồi thì khác null
            if (con != null) {
//      2. create SQL String
//     B2 tạo câu SQL
                String sql = "Select username, password, lastname, isAdmin "
                        + "From users "
                        + "Where lastname like ?";
//     mỗi mệnh đề phải trên 1 dòng
// mỗi khi gõ xong 1 từ thì phải có khoảng trắng 
//--------------------------------------------------------------------------------------------
//      3. create Statement Object 
// tại sao lại là con.prepareStatement  do đây là câu SQL có điều kiện mà 
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

//  4 . Excute Query  (xử lý kết quả trả về)
                //
                rs = stm.executeQuery();
                while (rs.next()) {
//                    5.1 getData form ResultSet
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

//                     5.2 set data to DTO properties
//  map với DTO nè 
                    UsersDTO dto = new UsersDTO(username, password, fullname, role);

                    if (this.accounts == null) {
//                          ko có thì tạo
                        //this.accounts = new VirtualFlow.ArrayLinkedList<>();
                        this.accounts = new ArrayList<>();

                    }// end account have not exited

//                       nếu có thì nhét 
                    this.accounts.add(dto);

                } // end account has exited 

// 5. Proccess Result
            } // end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();

            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException /*ClassNotFoundException*/, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //ném ra 2 lỗi: classNotFound và 
                //2. create SQL String
                String sql = "Delete From users "
                        + "Where username = ? ";
                //các mệnh đề phải viết trên từng dòng
                //mỗi khi gõ xong 1 từ thì phải space
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }//end username and password are check
            } //end connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update users "
                        + "Set password = ? "
                        + ",isAdmin = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean createAccount(UsersDTO account) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert Into users ("
                        + "username, password, lastname, isAdmin"
                        + ") VALUES ("
                        + "?, ?, ?, ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

}
