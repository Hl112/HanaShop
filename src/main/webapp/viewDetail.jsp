<%-- 
    Document   : viewDetail
    Created on : Jan 11, 2021, 2:14:08 AM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Detail </title>
        <c:import url="header.jsp" />
    </head>
    <body>
        <c:set var="pro" value="${requestScope.PRODUCT}"/>
        <div class="c-content-box c-size-lg c-overflow-hide c-bg-white">
            <div class="container">
                <div class="c-shop-product-details-4">
                    <div class="row">
                        <div class="col-md-4 m-b-20">
                            <div class="c-product-header">

                                <div class="c-content-title-1">
                                    <h3 class="c-font-uppercase c-font-bold">#${pro.productId}</h3>
                                    <span class="c-font-red c-font-bold">Hana Shop</span>
                                </div>
                            </div>
                        </div>

                        
                        <div class="col-md-4">
                            <div class="c-product-meta">
                                <div class="c-product-price c-theme-font" style="float: none;text-align: center">
                                    ${pro.productName}
                                    <br>
                                    ${pro.productPrice}đ
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 text-right">
                            <div class="c-product-header">
                                <div class="c-content-title-1">

                                </div>					
                                <a class="btn c-btn btn-lg c-theme-btn c-font-uppercase c-font-bold"  href="#" onclick="buy_now(${pro.productId})">Buy Now</a>
                                <a class="btn c-btn btn-lg c-bg-green-4 c-font-white c-font-uppercase c-font-bold c-btn-square m-t-20" href="shopping.jsp">Shopping Page</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="c-product-meta visible-md visible-lg">
                    <div class="c-content-divider">
                        <i class="icon-dot"></i>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 col-xs-6 c-product-variant">
                            <p class="c-product-meta-label c-product-margin-1 c-font-uppercase c-font-bold">Category: <span class="c-font-red">${pro.category.categoryName}</span></p>
                        </div>
                        <div class="col-sm-4 col-xs-6 c-product-variant">
                            <p class="c-product-meta-label c-product-margin-1 c-font-uppercase c-font-bold">In stock: <span class="c-font-red">${pro.quantity}</span></p>
                        </div>
                        <div class="col-sm-4 col-xs-6 c-product-variant">
                            <p class="c-product-meta-label c-product-margin-1 c-font-uppercase c-font-bold">Create Date: <span class="c-font-red">${pro.createDate}</span></p>
                        </div><br>
                        <div class="col-12 c-product-variant ml-1">
                            <p class="c-product-meta-label c-product-margin-1 c-font-uppercase c-font-bold" style="margin-left: 15px; ">Description: <span class="c-font-red">${pro.productDescription}</span></p>
                        </div>
                    </div>
                    <div class="c-content-divider">
                        <i class="icon-dot"></i>
                    </div>
                </div>
            </div>
        </div>

        <div class="container m-t-20">
            <div class="c-content-tab-4 c-opt-3" role="tabpanel">
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="info">
                        <ul class="c-tab-items" style="padding-right:5px;padding-left: 5px;">
                            <li class="text-center">
                                <b>Image of product</b>
                                <div>
                                    <a href="#">
                                        <img src="upload/${pro.productImage}" alt="" class="c-content-media-2 c-bg-img-center" style="max-width: 100%;">
                                    </a><br>
                                   
                                </div>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>



    </div>
    <script src="assets/js/app.js"></script>
</body>
</html>
