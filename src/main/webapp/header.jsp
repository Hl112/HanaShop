<%-- 
    Document   : header
    Created on : Jan 7, 2021, 6:58:00 AM
    Author     : HL
--%>
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
        <script>
            function onLoad() {
                gapi.load('auth2', function () {
                    gapi.auth2.init();
                });
            }
        </script>
    </head>
    <body>
       <!--Start topbar header-->
  <header class="topbar-nav">
    <nav class="navbar navbar-expand fixed-top">
      <ul class="navbar-nav mr-auto align-items-center">
        <li class="nav-item">
          <a href="hanaShop.html">
            <img src="assets/img/logo.png" alt="" class="logo-icon">
            <h5 class="logo-text">HANA SHOP</h5>
          </a>
        </li>
        <li class="nav-item">
          <form class="search-bar">
            <input type="text" class="form-control" placeholder="Enter keywords">
            <a href="javascript:void();"><i class="icon-magnifier"></i></a>
          </form>
        </li>
      </ul>

      <ul class="navbar-nav align-items-center right-nav-link">
        <li class="nav-item">
          <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="user-profile">
              <h5 class="logo-text">Welcome, ADMIN</h5>
            </span>
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
