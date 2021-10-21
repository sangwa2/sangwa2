<%-- 
    Document   : index
    Created on : Jul 8, 2019, 10:41:46 PM
    Author     : SANGWA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cxtRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CODEGURU</title>
        <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbars/"/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/basic_layout.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/Overlays.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/Panes.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/close_buttons.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/data_styles.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/forms.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/css/menus.css"/>'/>
        <link rel="stylesheet" href='<spring:url value="/resources/bs/bootstrap.css"/>'/>

        <c:set var="root" value="${pageContext.request.contextPath}"/>
    <input type="hidden" class="root" required="true" name="name" value="${root}"   />
    <style>
        body{
            color: #fff;
            overflow-y: scroll;
        }
    </style>


</head>
<body style="background-color: #011627;">
    <!--width="480" height="480"-->

    <div class="container">
        <div class="row mb-3" style="background-color: #0f1f40;">
            <div class="col-12 col-lg-5  border-light"> 
                <h3>CODEGURU LTD</h3>
                <h6 class="" style="color: #9faac1;">Software Development</h6>
            </div>
            <div class="col-12 col-lg-4 d-flex align-items-center p-sm-3"><small>Tel: +250784113888</small></div>
            <div class="col-12 col-lg-3 push_right d-flex align-items-center">
                <small class="p-sm-3"><a href="#" style="color: #fff;">Apps</a> </small>
            </div>
        </div>

        <div class="container-fluid   row d-lg-flex justify-content-between ">
            <div class="card ml-3 ml-lg-0 col-12 col-lg-4 sm_mt_40" style="width: 60rem; background-color: #011627; box-shadow: none;">
                <a href="#" style="color: #000; text-decoration: none;">  
                    <img src="<spring:url value="/resources/images/Balls rolling.gif" />" class="card-img-top mt-3" alt="...">
                    <div class="card-body d-none">

                    </div>
                </a>
            </div>

            <div class="card ml-3 ml-lg-0 col-12 col-lg-6 sm_mt_40 d-flex align-content-center" style="width: 70rem;background-color: transparent; ">
                <div class="row border border-light rounded " style="height: 250px;  background-color: #253454;" >
                    <h5 style="margin: auto;" class="rounded d-flex justify-content-center row">Sign in</h5>
                    <form class=" rounded col-lg-12"  action="/login" method="post"  >
                        <div class="form-group row mt-3  " style="margin-bottom: 0px; padding-bottom: 0px; height: 50px;" >
                            <label for="name" class="col-md-3" id="label" style="color: #fff;">Username</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control"  name="txt_username" autocomplete="off" placeholder="Enter username" id="txt_username">
                            </div>
                        </div>
                        <div class="form-group row  mb-0 pb-0 " style="margin-top: 0px; padding-top: 0px; height: 80px;">
                            <label for="name" class="col-md-3" id="label" style="color: #fff;">Password</label>
                            <div class="col-md-9">
                                <input type="password" class="form-control"  name="txt_password" autocomplete="off" placeholder="Enter password" id="txt_password">
                            </div>
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn_login btn btn-success float-right mr-3" id="btn_login" value="Login">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="row p-3 d-flex justify-content-center justify-content-lg-start">
            <h2> Platforms </h2>
        </div>
        <div class="container mt-5 row" style="color: #fff">
            <div class="card-deck ml-auto mb-3 text-center col-12 row ">
                <div class="card mb-4 shadow-sm col-12 col-lg-4" style="background-color: #253454;">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Web Applications</h4>
                    </div>
                    <div class="card-body" style="background-color: #141c2f">
                        <h1 class="card-title pricing-card-title">$0 <small class="text-muted">/ mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>10 users included</li>
                            <li>2 GB of storage</li>
                            <li>Email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
                    </div>
                </div>
                <div class="card mb-4 shadow-sm col-12 col-lg-4" style="background-color: #253454;">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Mobile Apps</h4>
                    </div>
                    <div class="card-body" style="background-color: #141c2f">
                        <h1 class="card-title pricing-card-title">$15 <small class="text-muted">/ mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>20 users included</li>
                            <li>10 GB of storage</li>
                            <li>Priority email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-primary">Get started</button>
                    </div>
                </div>
                <div class="card mb-4 shadow-sm col-12 col-lg-4" style="background-color: #253454;">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">System Integration</h4>
                    </div>
                    <div class="card-body" style="background-color: #141c2f">
                        <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>30 users included</li>
                            <li>15 GB of storage</li>
                            <li>Phone and email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-primary">Contact us</button>
                    </div>
                </div>
            </div>

            <footer class="pt-4 my-md-5 pt-md-5 border-top">
                <div class="row">
                    <div class="col-12 col-md">
                        <img class="mb-2" src="../assets/brand/bootstrap-solid.svg" alt="" width="24" height="24">
                        <small class="d-block mb-3 text-muted">© 2017-2020</small>
                    </div>
                    <div class="col-6 col-md d-none">
                        <h5>Features</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Cool stuff</a></li>
                            <li><a class="text-muted" href="#">Random feature</a></li>
                            <li><a class="text-muted" href="#">Team feature</a></li>
                            <li><a class="text-muted" href="#">Stuff for developers</a></li>
                            <li><a class="text-muted" href="#">Another one</a></li>
                            <li><a class="text-muted" href="#">Last time</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5>Resources</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Resource</a></li>
                            <li><a class="text-muted" href="#">Resource name</a></li>
                            <li><a class="text-muted" href="#">Another resource</a></li>
                            <li><a class="text-muted" href="#">Final resource</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5>About</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Team</a></li>
                            <li><a class="text-muted" href="#">Locations</a></li>
                            <li><a class="text-muted" href="#">Privacy</a></li>
                            <li><a class="text-muted" href="#">Terms</a></li>
                        </ul>
                    </div>
                </div>
            </footer>
        </div>


        <div class="row p-3" style="margin-top: 200px;">
            <div class="col-4"></div>
            <div class="col-4">© Copyrights All Reserved - CODEGURU LTD</div>
        </div>
    </div>
    <!--<div class="body">-->
    <!--            <div class="home_layout">
                    <div class="parts part2 wid_1">
                        <div class="round_icons txt_left">
                            <div class="push_left">  Accounts</div>
                        </div>
                        <div class="round_icons txt_right">
                            <div class="push_right"> Business</div>
                        </div>
                    </div>
    
                    <div class="parts part2 wid_2">
                        <div class="round_icons txt_left">
                            <div class="push_left"> App types</div>
                        </div>
                        <div class="round_icons txt_right">
                            <div class="push_right">     Desktop </div>
                        </div>  
                    </div>
                    <div class="parts part2 wid_3">
                        <div class="round_icons txt_left web_link"><div class="push_left">  <a href="${cxtRoot}/web">web</a>  </div>    </div>  
                        <div class="round_icons txt_right"> <div class="push_right">Android </div>  </div>
                    </div>
                    <div class="parts part2 wid_4">
                        <div class="round_icons txt_left"><div class="push_left">Services</div>   </div>
                        <div class="round_icons txt_right"><div class="push_right">Databases/ Others</div> </div>
                    </div>
                </div>
    
                <div class="box_division item box_6_2 top_1h off">
                    <div id="color1" class="item padding_3 big_font_2 web_link">    Web</div>
                    <div id="color2" class="item padding_3 big_font_2 android_link">Android</div>
                    <div id="color3" class="item padding_3 big_font_2 desktop_link">Desktop</div>
                    <div id="color4" class="item padding_3 big_font_2 service_link">Services</div>
    
                </div>-->
    <!--</div>-->
    <script src='<spring:url value="/resources/js/jquery-2.1.3.min.js" />' type="text/javascript"></script>
    <script src='<spring:url value="/resources/js/scripts.js" />' type="text/javascript"></script>
    <script src='<spring:url value="/resources/js/db.js" />' type="text/javascript"></script>
    <script src='<spring:url value="/resources/js/globals.js" />' type="text/javascript"></script>
    <script src='<spring:url value="/resources/js/win_size_menu.js" />' type="text/javascript"></script>
    <script src='<spring:url value="/resources/js/audio.js" />' type="text/javascript"></script>
    <script src='<spring:url value="/resources/bs/bootstrap.js"/>'     type="text/javascript"/>
    <script src='<spring:url value="/resources/bs/bootstrap.min.js"/>' type="text/javascript"/>
</body>
</html>
