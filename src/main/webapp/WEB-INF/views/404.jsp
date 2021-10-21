<%-- 
    Document   : errorPage
    Created on : Sep 25, 2019, 10:58:53 PM
    Author     : SANGWA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cxtRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
          <%@include file="header_footer/styles_links.jsp" %>     
    </head>
    <body>
    <center>
        <div class="radius">
        <h1>We could not find the contents!</h1>
        </div>
        <a href="${cxtRoot}/">continue>></a>
    </center> 
    </body>
</html>
