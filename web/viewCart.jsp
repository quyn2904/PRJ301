<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 2:36:00 PM
    Author     : Goby
--%>

<%@page import="java.util.Map"%>
<%@page import="quyenpq.Cart.CartObject"%>
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
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
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
                </tr>    
                <%
                    }
                %>
            </tbody>
        </table>
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
