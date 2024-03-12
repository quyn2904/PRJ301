package quyenpq.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import quyenpq.util.DBHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Goby
 */
public class OrderDAO {

    List<OrderDTO> orders;

    public int getNumOfOrders() throws SQLException, NamingException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(id) AS count FROM dbo.[order]";
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    result = rs.getInt("count");
                }
            }
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

    public String createId() throws SQLException, NamingException {
        int count = getNumOfOrders();
        if (orders != null) {
            count = orders.size() + 1;
        }
        if (count < 10) {
            return "OR0" + count;
        }
        return "OR" + count;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public String createOrder(String name)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert into [dbo].[order] ("
                        + "id, name, date, total"
                        + ") Values ("
                        + "?, ?, GETDATE(), ?"
                        + ")";
                stm = con.prepareStatement(sql);
                String id = createId();
                stm.setString(1, id);
                stm.setString(2, name);
                stm.setFloat(3, 0);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    result = id;
                }
            }
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

    public boolean completeOrder(String orderId, float total)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update [dbo].[order] "
                        + "set total = ? "
                        + "where id = ?";
                stm = con.prepareStatement(sql);
                stm.setFloat(1, total);
                stm.setString(2, orderId);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    result = true;
                }
            }
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
}
