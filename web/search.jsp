<%-- 
    Document   : search
    Created on : Feb 20, 2024, 12:14:12 PM
    Author     : Goby
--%>

<%--<%@page import="quyenpq.users.UsersDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER_INFO.fullName}
        </font>
        <a href="DispatchServlet?btAction=View+Store">Go Shopping</a>
        <h1>Search</h1>
        <form action="DispatchServlet">
            Search Valued  <input type="text" name="txtSearchValue" 
                                  value="${param.txtSearchValue}"/><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table>
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>                            
                            <th>Delete</th>  
                            <th>Update</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}"/>
                                </td>
                                <td>${dto.fullName}</td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                        <c:param name="btAction" value="Delete"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}"/>
                                    <input type="submit" name="btAction" value="Update"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="{empty result}">
            No record is matched!!!
        </c:if>
    </c:if>


    <%-- 
    <%
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie newestCookie = cookies[cookies.length - 1];
            String username = newestCookie.getName();
    %>
    <font color="red">
    Welcome, <%= username%>
    </font>
    <%
        }// end no first time
    %>
    <h1>Search</h1>
    <form action="DispatchServlet">
        Search Valued  <input type="text" name="txtSearchValue" 
                              value="<%= request.getParameter("txtSearchValue")%>"/> <br/>
        <input type="submit" value="Search" name="btAction" />
    </form>
    <br/>
    <%
        String searchValue = request.getParameter("txtSearchValue");

            if (searchValue != null) {
                List<UsersDTO> result = (List<UsersDTO>) request.getAttribute("SEARCH_RESULT");

                if (result != null) {//search is found
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (UsersDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=Delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + request.getParameter("txtSearchValue");
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername"
                               value="<%= dto.getUsername()%>"/>
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>"/>
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON"
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }//end role is an admin
%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" value="<%= request.getParameter("txtSearchValue")%>"/>
                    </td>
                </tr>
            </form>
            <%
                }//get each dto to process
            %>
        </tbody>
    </table>

    <%
    } else {//search is NOT found
    %>
    <h2>
        <font color ="red">
        No record is matched!!!
        </font>
    </h2>
    <%
            }//end search is NOT found
        }//end search Value is not active when accessing directly
%>
    --%>
</body>
</html>