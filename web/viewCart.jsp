<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 2:36:00 PM
    Author     : Goby
--%>

<%@page import="java.util.Map"%>
<%@page import="quyenpq.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>Coffee Store</h1>
        <c:set var="listItem" value="${sessionScope.LIST_ITEM_IN_VIEW}"/>
        <c:if test="${not empty listItem}"> 
            <form action="DispatchServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listItem}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.id}</td>
                                <td>
                                    ${item.name}
                                    <input type="hidden" name="txtName" value="${item.name}"/>
                                </td>
                                <td>${item.unitprice}</td>
                                <td>${item.quantity}</td>
                                <td>${item.unitprice * item.quantity}</td>
                                <td>
                                    <input type="checkbox" value="${entry.key}" name="chkItem"/>
                                </td>
                            </tr>    
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                                <a href="product.html">Add More Products to Your Cart</a>
                            </td>
                            <td>
                                <button name="btAction" type="submit" value="Remove Selected Items">Remove</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </c:if>
        <a href="DispatchServlet?btAction=Check+Out">Check Out</a>
    </body>
</html>
