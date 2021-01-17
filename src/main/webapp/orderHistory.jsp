<%-- 
    Document   : orderHistory
    Created on : Jan 17, 2021, 12:43:48 PM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping History</title>
        <c:import url="header.jsp"/>
    </head>
    <body>
        <div class="m-t-20 visible-sm visible-xs"></div>

    
        


    <div class="c-layout-page" style="margin-top: 20px;">
        <div class="container">

            <c:import url="menu.jsp"/>



            <div class="c-layout-sidebar-content ">
                <!-- BEGIN: PAGE CONTENT -->
                <!-- BEGIN: CONTENT/SHOPS/SHOP-CUSTOMER-DASHBOARD-1 -->
                <div class="c-content-title-1">
                    <h3 class="c-font-uppercase c-font-bold">Order History</h3>
                    <div class="c-line-left"></div>
                </div>


                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Order #</th>
                            <th>Order Date</th>
                            <th>Status</th>
                            <th>Address</th>
                            <th>Phone</th>
                        </tr>
                    </thead>
                    <tbody>
                       
                        <tr>
                            <th scope="row">Order #<?php echo $gettom[idnick]; ?></th>
                            <td><?php echo $gettom[taikhoan]; ?></td>
                             <span class="label label-success" style="color: #5cb85c;display: block;"></span>
                            <td><?php echo $gettom[matkhau]; ?></td>
                            <td><?php echo date('d/m/Y - H:i:s', $gettom['time']); ?></td>
                        </tr> 
                       
                    </tbody>
                </table><Br/>
               
            </div>


        </div>
    </div>
</body>
</html>
