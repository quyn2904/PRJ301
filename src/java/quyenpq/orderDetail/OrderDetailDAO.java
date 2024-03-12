/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.orderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import quyenpq.product.ProductDTO;
import quyenpq.util.DBHelper;

/**
 *
 * @author Goby
 */
public class OrderDetailDAO {
    List<OrderDetailDTO> orderDetails;
    
    public float createOrderDetails(List<ProductDTO> listCheckOutItems, String orderId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float total = 0;
        
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                for (ProductDTO product : listCheckOutItems) {
                    String sql = "Insert into orderDetail ("
                            + "productId, unitprice, quantity, total, orderId"
                            + ") Values ("
                            + "?, ?, ?, ?, ?"
                            + ")";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, product.getId());
                    stm.setFloat(2, product.getUnitprice());
                    stm.setInt(3, product.getQuantity());
                    float totalPerProduct = product.getQuantity() * product.getUnitprice();
                    stm.setFloat(4, totalPerProduct);
                    stm.setString(5, orderId);
                    int effectRows = stm.executeUpdate();
                    if (effectRows > 0) {
                        total += totalPerProduct;
                    }
                }
            }
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con !=  null) {
                con.close();
            }
        }
        
        return total;
    }

}
