

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cxtRoot" value="${pageContext.request.contextPath}" />

<html>
    <head>
        <title>CODEGURU</title>
        <style>
            #show_hide{
                font-size: 70px;
                color: #ff6100;
            }
            body{
                background-color: #174aff;
            }
              body {
	margin: 0;
	width: 100%;
	height: 100vh;
	font-family: "Exo", sans-serif;
	color: #fff;
	/*background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);*/
        background: linear-gradient(-45deg, #28b342, #2abf45, #23a6d5, #fd8604);
	background-size: 400% 400%;
	animation: gradientBG 40s ease infinite;
}

@keyframes gradientBG {
	0% {
		background-position: 0% 50%;
	}
	50% {
		background-position: 100% 50%;
	}
	100% {
		background-position: 0% 50%;
	}
}

        </style>
    </head>
    <body>
    <center>
        <div id="show_hide">Coming soon!</div>
    </center>
    
      <script src='<spring:url value="/resources/js/jquery-2.1.3.min.js" />' type="text/javascript"></script>
        <script src='<spring:url value="/resources/js/scripts.js" />' type="text/javascript"></script>
        <script src='<spring:url value="/resources/js/db.js" />' type="text/javascript"></script>
        <script src='<spring:url value="/resources/js/globals.js" />' type="text/javascript"></script>
        <script src='<spring:url value="/resources/js/win_size_menu.js" />' type="text/javascript"></script>
        <script src='<spring:url value="/resources/js/audio.js" />' type="text/javascript"></script>
    </body>
</html>
