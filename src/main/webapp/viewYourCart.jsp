<%--
    Document   : viewYourCart
    Created on : Jan 9, 2021, 2:33:17 PM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:import url="header.jsp"></c:import>
        </head>
        <body>
            <c:if test="${sessionScope.USER.isAdmin}">
            <div class="c-content-box c-size-md c-bg-grey-1">
                <div class="container">
                    <div class="c-content-bar-1 c-opt-1">
                        <h3 class="c-font-uppercase c-font-bold">Function of User Role</h3>
                        <p class="c-font-uppercase">
                            Please go to home page to use another function.  </p>
                    </div>
                </div> 
            </div>
        </c:if>
        <c:if test="${!sessionScope.USER.isAdmin}">
        <c:set var="list" value="${sessionScope.LISTCART}"/>
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
                    <!-- BEGIN: PAGE CONTENT -->
                    <!-- BEGIN: CONTENT/SHOPS/SHOP-CUSTOMER-DASHBOARD-1 -->
                    <div class="c-content-title-1">
                        <h3 class="c-font-uppercase c-font-bold">Your Cart</h3>
                        <div class="c-line-left"></div>
                    </div>
                    <span class="label label-success" style="color: #5cb85c;display: block;"></span>
                    <c:if test="${not empty list}">
                        <table class="table table-hover">

                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Amount</th>
                                    <th>Total</th>
                                    <th>Select</th>
                                </tr>
                            </thead>
                            <tbody>
                            <form action="DispatcherServlet">
                                <c:set var="total" value="${0}"/>
                                <c:forEach items="${list}" var="pro" varStatus="counter">
                                    <tr>
                                        <th scope="row">${counter.count}</th>
                                        <td>${pro.productName}</td>
                                        <td><span class="price">${pro.productPrice}</span>đ</td>
                                        <td>
                                            <div class="quantity buttons_added">
                                                <input type="button" value="-" class="minus btn btn-danger btn-number" onclick="update_sub_quantity(${counter.count})">
                                                <input type="number" step="1" min="1" max="" name="quantity" value="${pro.quantity}" title="Qty" class="input-number qty" size="4" onfocusout="update_quantity(${counter.count})">
                                                <input type="button" value="+" class="plus btn btn-success btn-number" onclick="update_plus_quantity(${counter.count})">
                                            </div>
                                        </td>
                                        <td><span class="total_1">${pro.quantity * pro.productPrice}</span>đ</td>
                                        <c:set var="total" value="${total + pro.quantity * pro.productPrice}"/>
                                        <td> <input type="checkbox" name="id" class="id" value="${pro.productId}" /></td>
                                    </tr>
                                </c:forEach>
                                <span class="label label-success" style="color: #5cb85c;display: block;"></span> 
                                <tr>
                                    <th colspan="4">Total:</th>
                                    <th><span class="total">${total}</span>đ</th>
                                    <th>
                                        <a href="#" value="Remove from cart" data-toggle="modal" data-target="#basicModal" class="btn btn-danger">Remove from cart</a>
                                        <a href="DispatcherServlet?btAction=Payment" value="Payment" class="btn btn-success">Payment</a>

                                        <!-- Modal Confirm -->
                                        <div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="myModalLabel">Delete Confirm</h4>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <h3>Delete selected row?</h3>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <input type="submit" value="Remove from cart" class="btn btn-danger load-modal" name="btAction"/>
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </th>
                                </tr>
                            </form>
                            </tbody>
                        </table><Br/>

                    </c:if>
                </div>
                <c:if test="${empty list}">
                    <div class="c-content-box c-size-md c-bg-grey-1">
                        <div class="container">
                            <div class="c-content-bar-1 c-opt-1">
                                <h3 class="c-font-uppercase c-font-bold">Your cart is empty</h3>
                                <p class="c-font-uppercase">
                                    Shopping now to choose product  </p>
                            </div>
                        </div> 
                    </div>
                </c:if>

            </div>
        </div>
        </c:if>
        <script src="assets/js/app.js"></script>
    </body>
</html>
