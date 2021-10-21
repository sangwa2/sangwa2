<%-- 
    Document   : data_list
    Created on : Jul 12, 2019, 7:53:28 PM
    Author     : SANGWA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Desktop</title>
        <link rel="stylesheet" href='<spring:url value="/resources/css/basic_layout.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/Overlays.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/Panes.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/close_buttons.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/data_styles.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/forms.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/menus.css"/>'/>
        <style>
            .body{
                overflow-y: scroll;
            }
            .colors_board{
                background-color: #fff;
                padding: 20px;
                margin: auto;
                min-height: 300px;
                width: 90%;
                max-height: 600px;
                overflow-y: scroll; 
                display: grid;
                grid-template-columns: 1fr 1fr 1fr 1fr;
                grid-row-gap: 80px;
                grid-column-gap: 60px;

            }

            .colored{
                height: 280px;
                border: 2px solid #fff;
                box-shadow: 0px 0px 10px #000;
                position: relative;
                padding: 5px;
            }
            .templ_header{
                height: 30px;
            }
            .templ_body{
                min-height: 120px;
            }
            .templ_footer{
                position: absolute;
                bottom: 0px;
            }
        </style>
    </head>
    <body>

        <h1 id="color_number"></h1>
        <p class="testtimer"></p>
        
        <div class="bg_full off"></div>
        <div class="template_view off">
            Template
        </div>
        
        
        <div class="colors_board">
            <div class="">Colored</div>
            <div>Header</div>
            <div>Contents</div>
            <div>Footer</div>
            
        </div>

        <script src='<spring:url value="/resources/js/jquery-2.1.3.min.js" />' type="text/javascript"></script>
        <script src='<spring:url value="/resources/js/templates.js" />' type="text/javascript"></script>
    </body>
</html>
