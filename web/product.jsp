<%-- 
    Document   : product.jsp
    Created on : Mar 9, 2024, 1:24:48 PM
    Author     : Goby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Store</title>
    </head>
    <body>
        <c:set var="productList" value="${applicationScope.PRODUCT_LIST}" />
        <c:if test="${not empty productList}">
            <h1>Product is available</h1>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${productList}">
                        <form action="AddProductToCartServlet">
                            <tr>
                                <td>
                                    <input type="hidden" name="productId" 
                                           value="${product.id}"/>
                                    ${product.id}
                                </td>
                                <td>${product.name}</td>
                                <td>${product.description}</td>
                                <td>${product.unitprice}</td>
                                <td>${product.quantity}</td>
                                <td>${product.status}</td>
                                <td>
                                    <button type="submit" name="btAction" value="Add Product To Your Cart">
                                        Add to cart
                                    </button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <form action="DispatchServlet">
                <button name="btAction" value="View Your Cart" type="submit" >View Your Cart</button>
                <button name="btAction" value="Check Out" type="submit" >Check Out</button>
            </form>
        </c:if>
            
    <c:if test="${empty productList}">
        <h1>ProductList is empty</h1>
    </c:if>
</body>
</html>


