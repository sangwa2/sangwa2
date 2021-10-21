<%--
    Document   : index
    Created on : Jul 8, 2019, 10:41:46 PM
    Author     : SANGWA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="header_footer/styles_links.jsp" %> 
        <style>
            .ui-datepicker-header {
                background-color: #0099ff;
                color: #ffffff;
                font-family:'Times New Roman';
                border-width: 1px 0 0 0;
                border-style: solid;
                border-color: #111;
            }
            .ui-datepicker table  {
                border: 1px solid #0099ff;
            }
            .ui-widget-content{
                border:1px solid #0099ff;
            }
            .ui-datepicker td a:hover{
                background-color: #da1dce;
                color: #fff;
            }
            .left_menu, .right_menu{
                background-image: url('resources/images/left_right_menu.png');
                background-repeat: no-repeat;
            }

            div {
                /*cursor:url('resources/images/key.png'),auto;*/
            }
        </style>
    </head>
    <body>
        <img class="img_rotating" src="<spring:url value="/resources/images/rotatingbg2.png"/>" style="position: absolute; z-index: -2;" widh="1700" height="800">
        <div class="check_load">
            <!--this is the bg that loads-->
        </div>
        <div class="whole_doc_load_gif"> Codeguru Ltd ...</div>
        <%@include  file="/WEB-INF/views/two_sized_pane.jsp"%>
        
        <!-- pane With other menus on the side (This is independent cause it is position: absolute)-->
        <div class="bg_full"></div>
        <!--These are included forms-->

        <%@include  file="/WEB-INF/views/layout_merge.jsp"%>
        <%@include  file="/WEB-INF/views/forms/frm_structure.jsp"%>
        <%@include  file="/WEB-INF/views/forms/frm_stru_settings.jsp"%>
        <%@include  file="/WEB-INF/views/forms/frm_unit.jsp"%>
        <%@include  file="/WEB-INF/views/forms/frm_tuple.jsp"%>
        <%@include  file="/WEB-INF/views/delete_dialog.jsp"%>
        <%@include  file="/WEB-INF/views/diagrams/dictionary.jsp"%>
        <%@include  file="/WEB-INF/views/main_page_parts/existing_database.jsp"%>
        
        <div class="app_switcher"> </div>

        <div class="off" id="p1"> </div>
        <div id="p2">
            <%@include file="main_page_parts/slides.jsp"  %>
            <%@include file="main_page_parts/project_page.jsp"  %>
        </div>
        <%@include file="header_footer/scripts_links.jsp" %>
          <script>
            $(window).load(function () {
                $('.page2').hide(0);
                $('.frm_tuple_pane').hide(0);
                $('.frm_unit_pane').hide(0);
            });
            $(document).ready(function () {
                $('.date_field').datepicker({
                    dateFormat: 'yy-mm-dd'
                });
                $('.whole_doc_load_gif').delay(1000).hide(0, function () {
                    $('.quick_notify').delay(300).show("drop", {direction: "up"}, "slow");
                });

                $('.check_load').delay(1000).hide(0);

            });
        </script>

    </body>
</html>
