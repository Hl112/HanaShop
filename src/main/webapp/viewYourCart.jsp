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
                                                <input type="number" step="1" min="1" max="" name="quantity" value="${pro.quantity}" title="Qty" class="input-number qty" size="4" readonly>
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
                                        <input type="submit" value="Remove from cart" data-toggle="modal" data-target="#confirm-delete" class="btn btn-danger load-modal" name="btAction"/>
                                        <input type="submit" value="Payment" name="btAction" class="btn btn-success"/>
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
        <script>
            $('.btn-number').click(function (e) {
                e.preventDefault();

                fieldName = $(this).attr('data-field');
                type = $(this).attr('data-type');
                var input = $("input[name='" + fieldName + "']");
                var currentVal = parseInt(input.val());
                if (!isNaN(currentVal)) {
                    if (type == 'minus') {

                        if (currentVal > input.attr('min')) {
                            input.val(currentVal - 1).change();
                        }
                        if (parseInt(input.val()) == input.attr('min')) {
                            $(this).attr('disabled', true);
                        }

                    } else if (type == 'plus') {

                        if (currentVal < input.attr('max')) {
                            input.val(currentVal + 1).change();
                        }
                        if (parseInt(input.val()) == input.attr('max')) {
                            $(this).attr('disabled', true);
                        }

                    }
                } else {
                    input.val(0);
                }
            });
            $('.input-number').focusin(function () {
                $(this).data('oldValue', $(this).val());
            });
            $('.input-number').change(function () {

                minValue = parseInt($(this).attr('min'));
                maxValue = parseInt($(this).attr('max'));
                valueCurrent = parseInt($(this).val());

                name = $(this).attr('name');
                if (valueCurrent >= minValue) {
                    $(".btn-number[data-type='minus'][data-field='" + name + "']").removeAttr('disabled')
                } else {
                    alert('Sorry, the minimum value was reached');
                    $(this).val($(this).data('oldValue'));
                }
                if (valueCurrent <= maxValue) {
                    $(".btn-number[data-type='plus'][data-field='" + name + "']").removeAttr('disabled')
                } else {
                    alert('Sorry, the maximum value was reached');
                    $(this).val($(this).data('oldValue'));
                }


            });
            $(".input-number").keydown(function (e) {
                // Allow: backspace, delete, tab, escape, enter and .
                if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
                        // Allow: Ctrl+A
                                (e.keyCode == 65 && e.ctrlKey === true) ||
                                // Allow: home, end, left, right
                                        (e.keyCode >= 35 && e.keyCode <= 39)) {
                            // let it happen, don't do anything
                            return;
                        }
                        // Ensure that it is a number and stop the keypress
                        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                            e.preventDefault();
                        }
                    });

        </script>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>
</html>
