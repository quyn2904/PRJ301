<%-- 
    Document   : newjspcheckOutSuccess
    Created on : Mar 13, 2024, 2:27:25 AM
    Author     : Goby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Success</title>
    </head>
    <body>
        <h1>Thank You For Buying Our Product</h1>
        <h2>Your OrderId: ${requestScope.ORDER_ID}</h2>
        <c:set var="listItems"  value="${requestScope.LIST_ITEMS_IN_CHECKOUT_VIEW}"/>
        <c:if test="${not empty listItems}">
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listItems}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.unitprice}</td>
                            <td>${item.quantity}</td>
                            <td>${item.unitprice * item.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <h3>Total: ${requestScope.TOTAL_PRICE}</h3>
        </c:if>
    
    </body>
</html>
