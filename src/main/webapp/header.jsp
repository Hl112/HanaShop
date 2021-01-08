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
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <!-- HL CSS-->
        <link rel="stylesheet" href="assets/css/theme.css">
        <!-- Icon CSS-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
        <!-- Google Login -->
        <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
        <script src="assets/js/app.js"></script>
    </head>
    <body>
       <!--Start topbar header-->
  <header class="topbar-nav bg-dark">
    <nav class="navbar navbar-expand fixed-top">
      <ul class="navbar-nav mr-auto align-items-center">
        <li class="nav-item">
          <a href="hanaShop.html">
            <img src="assets/img/logo.png" alt="" class="logo-icon">
            <h5 class="logo-text">HANA SHOP</h5>
          </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href=""
        </li>
      </ul>

      <ul class="navbar-nav align-items-center right-nav-link">
        <li class="nav-item">
          <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
              <c:if test="${not empty sessionScope.USER}">
              <span class="user-profile">
                  <h5 class="logo-text">Welcome, ${sessionScope.USER.fullname}</h5>
            </span>
              </c:if>
              <c:if test="${empty sessionScope.USER}">
                  <a href="login.html">
                  <h5 class="logo-text">Login</h5></a>
              </c:if>
          </a>
          <ul class="dropdown-menu dropdown-menu-right">

            <li class="dropdown-divider"></li>
            <li class="dropdown-item"><i class="icon-envelope mr-2"></i> Inbox</li>
            <li class="dropdown-divider"></li>
            <li class="dropdown-item"><i class="icon-wallet mr-2"></i> Account</li>
            <li class="dropdown-divider"></li>
            <li class="dropdown-item"><i class="icon-settings mr-2"></i> Setting</li>
            <li class="dropdown-divider"></li>
            <li class="dropdown-item">
              <a href="LogoutServlet" onclick="signOut();">
                <i class="icon-power mr-2"></i> Logout
              </a>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
  </header>
  <!--End topbar header-->
  
    </body>
</html>
