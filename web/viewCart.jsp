<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 2:36:00 PM
    Author     : Goby
--%>

<%@page import="java.util.Map"%>
<%@page import="quyenpq.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            //1. Cust goes to his/her cart place - session scope có sẵn trên JSP vì nó là implicit object
            if (session != null) {
                //2. Cust gets his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust shows all items
        %>
        <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= key%></td>
                        <td><%= items.get(key)%></td>
                        <td>
                            <input type="checkbox" value="<%= key%>" name="chkItem"/>
                        </td>
                    </tr>    
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="product.html">Add More Books to Your Cart</a>
                        </td>
                        <td>
                            <button name="btAction" type="submit" value="Remove Selected Items">Remove</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%
                        return;
                    }
                }
            }
        %>
        <h2>
            <font color="red">
            No cart exits
            </font>
        </h2>
    </body>
</html>
