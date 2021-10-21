 

<%-- 
    Document   : login
    Created on : Aug 23, 2019, 1:55:28 PM
    Author     : SANGWA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CODEGURU</title>
        <link rel="stylesheet" href='<spring:url value="resources/css/basic_layout.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/css/Panes.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/css/close_buttons.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/css/data_styles.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/css/forms.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/css/menus.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/css/Overlays.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/css/flex_two_sized_pane.css"/>'/>

        <link rel="stylesheet" href='<spring:url value="resources/date_picker/jquery-ui.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/date_picker/jquery-ui.min.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/date_picker/jquery-ui.structure.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/date_picker/jquery-ui.structure.min.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/date_picker/jquery-ui.theme.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="resources/date_picker/jquery-ui.theme.min.css"/>'/>

        <title>Login</title>
        <style>
            body{
                /*background-color: #3679c8;*/
            }
            .padlock{
                width: 300px;
                height: 300px;
            }
            .login_box{
                width: 30%;
                height: 400px;
                background-color: #3679c8;
            }
            .padlock{
                width: 400px;
                height: 200px;
                background-image: url('resources/images/padlock2.png');
                background-repeat: no-repeat;
                width: 100px;
                height: 160px;
                cursor:url('resources/images/key2.png'),auto;
                
            }
        </style>
    </head>
    <body>
        <h1>Login</h1>
        <div class="login_box">

            <div class="page_container">
                <div class="part1">
                    <div class="page_container">
                        <div class="part2">
                            <div><input type="text" class="textbox" required="true" name="name"   /></div>
                            <div><input type="text" class="textbox" required="true" name="name"   /></div>
                            <div class="padlock">

                            </div>
                            <div><input type="button" value="Login" required="true" name="name"   /></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
