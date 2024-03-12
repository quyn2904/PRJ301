/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.naming.NamingException;
import quyenpq.users.UsersDTO;
import quyenpq.util.DBHelper;

/**
 *
 * @author Goby
 */
public class ProductDAO {
    private List<ProductDTO> products;
    
     private List<ProductDTO> listItemInViewCart;

    public List<ProductDTO> getProducts() {
        return products;
    }
    
    public List<ProductDTO> getListItemInViewCart() {
        return listItemInViewCart;
    }
    
    public List<ProductDTO> getAvailableProducts() {
        List<ProductDTO> tmp = null;
        if (this.products != null) {
            tmp = new ArrayList<>();
            for (ProductDTO product : this.products) {
                if(product.getStatus()) {
                    tmp.add(product);
                }
            }
        }
        return tmp;
    }
    
    public void getAllProducts()
        throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select id, name, description, unitprice, quantity, status "
                        + "From product";
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float unitprice = rs.getFloat("unitprice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    ProductDTO dto = new ProductDTO(id, name, description, unitprice, quantity, status);
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }
                    this.products.add(dto);
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
    }
    
    public ArrayList<ProductDTO> loadPriceOfItemToViewCart(Map<String,Integer> itemCart)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1 get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                for (String productId : itemCart.keySet()) {
                    //2 Create String sql
                    String sql = "Select name, unitprice, description "
                            +"From product "
                            +"Where id = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, productId);
                    rs = stm.executeQuery();
                    int quantity = itemCart.get(productId);
                    if(this.listItemInViewCart == null){
                        this.listItemInViewCart = new ArrayList<>();
                    }//end if list Item In View Cart is null
                    while (rs.next()) {
                        String name = rs.getString("name");
                        float price = rs.getFloat("unitprice");
                        String description = rs.getString("description");
                        ProductDTO dto = new ProductDTO(productId,name,description,price,quantity,true);
                        this.listItemInViewCart.add(dto);
                    }//end if result ends
                }// loop keyset in ItemCart
            }//end if Connection is successful
        } finally {
            
            if(rs != null){
                rs.close();
            }
            
            if(stm != null){
                stm.close();
            }
            
            if(con != null){
                con.close();
            }
        }
        return null;
    }
    
    
}
