<%-- 
    Document   : user
    Created on : Jan 11, 2021, 11:39:32 AM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <c:import url="header.jsp"/>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <div class="c-content-box c-size-md c-bg-grey-1">
                <div class="container">
                    <div class="c-content-bar-1 c-opt-1">
                        <h3 class="c-font-uppercase c-font-bold">You are not logged in</h3>
                        <p class="c-font-uppercase">
                            You must login to continue  </p>
                    </div>
                </div> 
            </div>
        </c:if>



        <c:if test="${not empty sessionScope.USER}">
            <c:set var="acc" value="${sessionScope.USER}"/>
            <div class="m-t-20 visible-sm visible-xs"></div>
            <div class="container">
                <div class="c-page-title c-pull-left">
                    <h3 class="c-font-uppercase c-font-bold c-font-white c-font-20 c-font-slim">&nbsp;</h3>
                </div>
            </div>




            <div class="c-layout-page" style="margin-top: 20px;">
                <div class="container">

                    <c:import url="menu.jsp"/>

                    <div class="c-layout-sidebar-content ">
                        <!-- BEGIN: PAGE CONTENT -->
                        <!-- BEGIN: CONTENT/SHOPS/SHOP-CUSTOMER-DASHBOARD-1 -->
                        <div class="c-content-title-1">
                            <h3 class="c-font-uppercase c-font-bold">Account Infomation</h3>
                            <div class="c-line-left"></div>
                        </div>
                        <table class="table table-striped">
                            <tbody>
                                <tr>
                                    <th scope="row">ID/Username:</th>
                                    <th><span class="c-font-uppercase">${acc.userID}</span></th>
                                </tr>
                                <tr>
                                    <th scope="row">Fullname:</th>
                                    <th>${acc.fullname}</th>
                                </tr>
                                <tr>
                                    <th scope="row">Address:</th>
                                    <td><b class="text-black-50">
                                            ${acc.address}
                                        </b></td>
                                </tr>
                                <tr>
                                    <th scope="row">Nhóm tài khoản:</th>
                                    <td><b class="text-danger">
                                            <c:if test="${acc.isAdmin}">Admin</c:if>
                                            <c:if test="${!acc.isAdmin}">User</c:if></b>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- END: PAGE CONTENT -->
                        </div>
                    </div>
                </div>
        </c:if>
    </body>
</html>
