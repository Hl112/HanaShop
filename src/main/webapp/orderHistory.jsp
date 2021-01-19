<%-- 
    Document   : orderHistory
    Created on : Jan 17, 2021, 12:43:48 PM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <% request.setCharacterEncoding("UTF-8"); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping History</title>
        <c:import url="header.jsp"/>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <div class="c-content-box c-size-md c-bg-grey-1">
                <div class="container">
                    <div class="c-content-bar-1 c-opt-1">
                        <h3 class="c-font-uppercase c-font-bold">Function of Login user</h3>
                        <p class="c-font-uppercase">
                            Please login to use function.  </p>
                    </div>
                </div> 
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.USER.isAdmin}">


            <c:if test="${requestScope.LOADHISTORY != 1}">
                <c:redirect url="DispatcherServlet?btAction=Search Order&name=&sDate=&eDate="/>
            </c:if>

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
                        <div class="m-l-10 m-r-10">
                <form class="form-inline m-b-10" action="DispatcherServlet" method="GET" accept-charset="utf-8">
                    <div class="input-group m-t-10 c-square col-md-6 col-sm-4">
                        <span class="input-group-addon" id="basic-addon1" >Start Date</span>
                        <input type="date" name="sDate" value="${param.sDate}" class="form-control">
                    </div>
                    <div class="input-group m-t-10 c-square col-md-5 col-sm-5">
                        <span class="input-group-addon" id="basic-addon1">End Date</span>
                        <input type="date" name="eDate" value="${param.eDate}" class="form-control">
                        </div>
                        <div class="input-group m-b-10 c-square col-md-9 col-sm-12">
                            <span class="input-group-addon" id="basic-addon1">Search</span>
                            <input type="text" class="form-control c-square c-theme" name="name" placeholder="Search by name" value="${requestScope.name}">
                    </div>
                    <input type="submit" class="btn c-theme-btn c-btn-square m-b-10" name="btAction" value="Search Order">
                    <c:if test="${not empty param.sDate || not empty param.eDate || not empty requestScope.name}">
                            <a href="OrderHistoryServlet?sDate=&eDate=&name=" class="btn btn-danger">X</a>
                        </c:if>
                    </form>

                </div>
                        <c:if test="${empty sessionScope.HISTORY}">
                            <div class="c-content-box c-size-sm c-bg-grey-1">

                                <div class="c-content-bar-1 c-opt-1">
                                    <h3 class="c-font-uppercase c-font-bold">No history Order</h3>
                                    <p class="c-font-uppercase">
                                        Please go to home page to order now.  </p>
                                </div>

                            </div>
                        </c:if>
                        <c:set var="history" value="${sessionScope.HISTORY}"/>
                        <div class="list-wrapper">
                        <c:forEach items="${history}" var="list" >
                            
                            <table class="table table-hover list-item">
                                <span class="label label-success" style="color: #5cb85c;display: block;"></span>
                                <thead>
                                    <tr>
                                        <th>Order #${list.order.orderId}</th>
                                        <th>Date: ${list.order.orderDate}</th>
                                        <th>Status: ${list.status.orderStatusDescription}</th>
                                        <th>Payment: ${list.payment.paymentMethod}</th>
                                    </tr>
                                    <tr>
                                        <th colspan="3">Address: ${list.order.address}</th>
                                        <th colspan="1">Phone: ${list.order.phone}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>Product Name</th>
                                        <th>Product Image</th>
                                        <th>Price</th>
                                        <th>Amount</th>
                                        <th>Total</th>
                                    </tr>
                                    <c:set var="total" value="${0}"/>
                                    <c:forEach var="pro" items="${list.details}">
                                        <tr>
                                            <th>${pro.product.productName}</th>
                                            <td>
                                                <img src="upload/${pro.product.productImage}" width="200px" height="200px" alt="alt"/>
                                            </td>

                                            <td>${pro.product.productPrice}đ</td>
                                            <td>${pro.quantity}</td>
                                            <td>${pro.quantity * pro.product.productPrice}đ</td>
                                            <c:set var="total" value="${total + pro.quantity * pro.product.productPrice}"/>
                                        </tr> 
                                    </c:forEach>
                                        <tr>
                                            <th colspan="4">Total: </th>
                                            <th>${total}đ</th>
                                        </tr>
                                </tbody>
                            </table>
                            <span class="label label-danger" style="color: #5cb85c;display: block;"></span>
                            <Br/>
                        </c:forEach>
                    </div>
                    </div>

                </div>
            </div>
        </c:if>
        <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
    </body>
</html>
