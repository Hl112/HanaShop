<%-- 
    Document   : paymentProcess
    Created on : Jan 16, 2021, 3:34:44 AM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment - HanaShop</title>
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
                         <a class="c-btn-border-opacity-04 c-btn btn-no-focus c-btn-header btn btn-sm c-btn-border-1x c-btn-dark c-btn-circle c-btn-uppercase c-btn-sbold"
                                           href="login.html"><i class="icon-user"></i>Login</a>
                    </div>
                </div> 
            </div>
        </c:if>
        
        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${empty sessionScope.PAYMENT}">
                <c:redirect url="DispatcherServlet?btAction=Payment"/>
            </c:if>
            
            <c:set var="list" value="${sessionScope.LISTCART}"/>
            <c:set var="pay" value="${sessionScope.PAYMENT}"/>
            
            
            <div class="c-layout-page" style="margin-top: 20px;">
                <div class="container">
                    <div class="c-layout-sidebar-content ">

                        <div class="col-md-6">
                            <div class="c-content-title-1">
                                <h3 class="c-font-uppercase c-font-bold">Your Cart</h3>
                                <div class="c-line-left"></div>
                            </div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Product Name</th>
                                        <th>Price</th>
                                        <th>Amount</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="sum" value="${0}"/>
                                    <c:forEach items="${list}" var="pro" varStatus="counter">
                                        <tr>
                                            <th scope="row">${pro.productName}</th>
                                            <th scope="row">${pro.productPrice}đ</th>
                                            <th><span class="c-font-uppercase">${pro.quantity}</span></th>
                                            <th><span class="c-font-uppercase">${pro.productPrice * pro.quantity}đ</span></th>
                                                <c:set var="sum" value="${sum + pro.productPrice * pro.quantity}"/>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <th colspan="3">Total</th>
                                        <th class="c-bg-grey-1 alert-danger">${sum}đ</th>
                                    </tr>
                                </tbody>
                            </table>  


                        </div>

                        <div class="col-md-6">
                            <div class="c-content-title-1">
                                <h3 class="c-font-uppercase c-font-bold">Fill Information</h3>
                                <div class="c-line-left"></div>
                            </div>
                            <form class="form-horizontal" method="POST" action="DispatcherServlet">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Name:</label>
                                    <div class="col-md-6">
                                        <input class="form-control  c-square c-theme" name="name" type="text" id="user-name" required="" value="${sessionScope.USER.fullname}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Address:</label>
                                    <div class="col-md-6">
                                        <input class="form-control  c-square c-theme" name="address" type="text" id="address" required="" value="${sessionScope.USER.address}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">Phone:</label>
                                    <div class="col-md-6"> 
                                        <input class="form-control c-square c-theme qty" name="phone" type="text" required="" id="phone" value="${sessionScope.USER.phone}">
                                    </div>
                                </div>

                                <div class="c-content-title-1">
                                    <h3 class="c-font-uppercase c-font-bold">Payment Method</h3>
                                    <div class="c-line-left"></div>
                                </div>
                                <c:forEach items="${pay}" var="pay" varStatus="counter">
                                <div class="form-group">
                                    <div class="col-md-offset-3 col-md-6">
                                        <label class="col-md-4">${pay.paymentMethod} </label>
                                        <input type="radio" id="payment" name="payment" value="${pay.paymentId}" class="col-md-3" checked>
                                    </div>
                                </div> 
                                </c:forEach>
                                <div class="form-group c-margin-t-40">
                                    <div class="col-md-offset-3 col-md-6">
                                        <button type="submit" name="btAction" class="btn c-theme-btn c-btn-square c-btn-uppercase c-btn-bold btn-block" value="Check out">Check out</button>
                                    </div>
                                </div>
                                <br>
                            </form>

                        </div>



                    </div>
                </div>
            </div>



        </c:if>
    </body>
</html>
