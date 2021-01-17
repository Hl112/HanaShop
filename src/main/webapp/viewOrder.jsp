<%-- 
    Document   : viewOrder
    Created on : Jan 16, 2021, 4:28:47 PM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order - HanaShop</title>
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
            <c:set var="order" value="${requestScope.ORDER}"/>
            
            <div class="c-layout-page" style="margin-top: 20px;">
                <div class="container">
                    <div class="m-l-10 m-r-10">
                    <div class="row">
                        <div class="col-md-6" id="noti">
                            <c:if test="${not empty requestScope.NOTI}">
                                <a class="btn btn-block c-btn btn-lg c-theme-btn c-font-uppercase c-font-bold c-btn-square m-t-20" href="#">
                                    ${requestScope.NOTI}
                                </a>
                            </c:if>
                        </div>
                        <div class="col-md-6">
                            <a class="btn btn-block c-btn btn-lg btn-danger c-font-uppercase c-font-bold c-btn-square m-t-20" href="ShowProductServlet">
                                CONTINUE TO SHOPPING
                            </a>
                        </div></div>
                </div><br/>      
                    <div class="c-layout-sidebar-content ">

                        <div class="col-md-6">
                            <div class="c-content-title-1">
                                <h3 class="c-font-uppercase c-font-bold">Order Details ID: ${requestScope.ORDER.orderId}</h3>
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
                                <h3 class="c-font-uppercase c-font-bold">Shipping Information</h3>
                                <div class="c-line-left"></div>
                            </div>
                            <form class="form-horizontal" method="POST" action="DispatcherServlet">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Name:</label>
                                    <div class="col-md-6">
                                        <p class="form-control  c-square c-theme" name="name" type="text" id="user-name" required="" value="${sessionScope.USER.fullname}">${sessionScope.USER.fullname}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Address:</label>
                                    <div class="col-md-6">
                                        <p class="form-control  c-square c-theme" name="address" type="text" id="address" required="" value="${requestScope.address}">${order.address}</p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">Phone:</label>
                                    <div class="col-md-6"> 
                                        <p class="form-control c-square c-theme qty" name="phone" type="text" required="" id="phone" value="${sessionScope.USER.phone}">${order.phone}</p>
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
                                        <input type="radio" id="payment" name="payment" value="${pay.paymentId}" class="col-md-3" <c:if test="${order.paymentId eq pay.paymentId}">checked</c:if>>
                                    </div>
                                </div> 
                                </c:forEach>
                               
                                <br>
                            </form>

                        </div>



                    </div>
                </div>
            </div>



        </c:if>
    </body>
</html>
