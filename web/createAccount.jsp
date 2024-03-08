<%-- 
    Document   : createAccount
    Created on : Mar 8, 2024, 1:39:43 PM
    Author     : Goby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>
            Register Page
        </h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}"/> (6-12chars) <br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError}
                </font>
                <br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font>
                <br/>
            </c:if>
            Password* <input type="text" name="txtPassword" value=""/> (8-20chars) <br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                </font>
                <br/>
            </c:if>
            Confirm Password* <input type="text" name="txtConfirmPassword" value=""/> (6-12chars) <br/>
            <c:if test="${not empty errors.confirmPasswordNotMatch}">
                <font color="red">
                    ${errors.confirmPasswordNotMatch}
                </font>
                <br/>
            </c:if>
            Fullname* <input type="text" name="txtFullname" value="${param.txtFullname}"/> (2-40chars) <br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                    ${errors.fullnameLengthError}
                </font>
                <br/>
            </c:if>
            <input type="submit" value="Create Account" name="btAction">
        </form>
    </body>
</html>
