<%-- 
    Document   : header
    Created on : Jan 7, 2021, 6:58:00 AM
    Author     : HL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home - Hana Shop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="google-signin-client_id"
              content="523448862507-7ascupmq7mgf5h8h0d2f6b871fug2rqs.apps.googleusercontent.com">
        <!-- CSS -->
        <link href="assets/css/css.css" rel="stylesheet" type="text/css">
        <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="assets/css/simple-line-icons.min.css" rel="stylesheet" type="text/css">
        <link href="assets/css/animate.min.css" rel="stylesheet" type="text/css">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <!-- End CSS-->
        <!-- Theme CSS-->
        <link href="assets/css/plugins.css" rel="stylesheet" type="text/css">
        <link href="assets/css/components.css?v=1.1.2" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="assets/css/default.css">
        <!-- End Theme CSS-->
        <!-- Google Login -->
        <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
        <script src="assets/js/app.js"></script>
    </head>
    <body>

    <body class="c-layout-header c-layout-header-mobile c-layout-header-topbar c-layout-header-topbar-collapse">
        <!-- BEGIN: LaYOUT/HEaDERS/HEaDER-1 -->
        <!-- BEGIN: HEaDER -->
        <header class="c-layout-header c-layout-header-4 c-layout-header-default-mobile">

            <div class="c-navbar">
                <div class="container">
                    <!-- BEGIN: BRaND -->
                    <div class="c-navbar-wrapper clearfix">
                        <div class="c-brand c-pull-left">
                            <a class="c-logo" href="/index.html">
                                <img height="21" class="c-desktop-logo" alt="" src="assets/img/logo-icon.png">
                                <img height="21" class="c-desktop-logo-inverse" alt="" src="assets/img/logo-icon.png">
                                <img height="21" class="c-mobile-logo" alt="" src="assets/img/logo-icon.png">
                            </a><button class="c-hor-nav-toggler" type="button" data-target=".c-mega-menu">
                                <SPaN class="c-line"></SPaN><SPaN class="c-line"></SPaN><SPaN class="c-line"></SPaN>
                            </button> <button class="c-topbar-toggler" type="button">
                                <I class="fa fa-ellipsis-v"></I>
                            </button>

                        </div>
                        <!-- END: BRaND -->

                        <!-- BEGIN: HOR nav -->
                        <!-- BEGIN: LaYOUT/HEaDERS/MEGa-MENU -->
                        <!-- BEGIN: MEGa MENU -->
                        <!-- Dropdown menu toggle on mobile: c-toggler class can be applied to the link arrow or link itself depending on toggle mode -->

                        <nav
                            class="c-mega-menu c-pull-right c-mega-menu-dark c-mega-menu-dark-mobile c-fonts-uppercase c-fonts-bold">
                            <UL class="nav navbar-nav c-theme-nav">
                                <li class="c-menu-type-classic">
                                    <a class="c-link dropdown-toggle" href="/ngocrong.php">Menu 1</a>
                                </li>
                                <li class="c-menu-type-classic">
                                    <a class="c-link dropdown-toggle" href="https://www.youtube.com/watch?v=egVBCDIiUPE">Menu 2</a>
                                </li>
                                <li class="c-menu-type-classic">
                                    <a class="c-link dropdown-toggle" href="/napthe.php">Menu 3</a>
                                </li>
                                <li class="c-menu-type-classic">
                                    <a class="c-link dropdown-toggle" href="#" onclick="tomalert()">Menu 4</a>
                                </li>
                                <li class="c-menu-type-classic">
                                    <a class="c-link dropdown-toggle" href="/intro.php">Menu 5</a>
                                </li>
                                <c:if test="${not empty sessionScope.USER}">
                                    <c:if test="${sessionScope.USER.isAdmin}">
                                <li class="c-menu-type-classic">
                                    <a style="color: red;" class="c-link dropdown-toggle" href="/admin">Admin panel</a>
                                </li>
                                    </c:if>
                                </c:if>
                                <c:if test="${empty sessionScope.USER}">
                                    <li>
                                        <a class="c-btn-border-opacity-04 c-btn btn-no-focus c-btn-header btn btn-sm c-btn-border-1x c-btn-dark c-btn-circle c-btn-uppercase c-btn-sbold"
                                           href="login.html"><i class="icon-user"></i>Login</a>
                                    </li>
                                </c:if>
                                <c:if test="${not empty sessionScope.USER}">
                                    <li>
                                        <a href="/user.php"
                                           class="c-btn-border-opacity-04 c-btn btn-no-focus c-btn-header btn btn-sm c-btn-border-1x c-btn-dark c-btn-circle c-btn-uppercase c-btn-sbold">
                                            <i class="icon-user">${sessionScope.USER.fullname}</i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="LogoutServlet" onclick="signOut();"
                                           class="c-btn-border-opacity-04 c-btn btn-no-focus c-btn-header btn btn-sm c-btn-border-1x c-btn-dark c-btn-circle c-btn-uppercase c-btn-sbold">
                                            <i class="icon-user"></i>Logout
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav> <!-- END: MEGa MENU -->
                        <!-- END: LaYOUT/HEaDERS/MEGa-MENU -->
                        <!-- END: HOR nav -->
                    </div><!-- BEGIN: LaYOUT/HEaDERS/QUICK-CaRT -->
                    <!-- BEGIN: CaRT MENU -->
                    <!-- END: CaRT MENU -->
                    <!-- END: LaYOUT/HEaDERS/QUICK-CaRT -->
                </div>
            </div>
        </HEaDER>
        <!-- END: HEaDER -->
        <!-- END: LaYOUT/HEaDERS/HEaDER-1 -->
        <!-- BEGIN: PaGE CONTaINER -->
        <div class="c-layout-page">
            <!-- BEGIN: PaGE CONTENT -->




    </body>
</html>
