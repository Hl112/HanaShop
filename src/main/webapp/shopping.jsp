<%-- 
    Document   : shopping
    Created on : Jan 8, 2021, 8:07:49 AM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping - Hana Shop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="google-signin-client_id"
              content="523448862507-7ascupmq7mgf5h8h0d2f6b871fug2rqs.apps.googleusercontent.com">
        <c:if test="${empty sessionScope.CATEGORY}">
            <c:redirect url="ShowProductServlet"></c:redirect>
        </c:if>
        <jsp:include page="header.jsp" flush="true"/>
    </head>
    <body>
        <c:set var="result" value="${sessionScope.PRODUCT}"/>
        <c:set var="cate" value="${sessionScope.CATEGORY}"/>
        <!--Start Shopping Content-->
        <div class="container">
            <div class="m-l-10 m-r-10">
                <div class="row">
                    <div class="col-md-6">
                        <c:if test="${not empty requestScope.NOTI}">
                            <a class="btn btn-block c-btn btn-lg c-theme-btn c-font-uppercase c-font-bold c-btn-square m-t-20" href="#">
                                ${requestScope.NOTI}
                            </a>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        <a class="btn btn-block c-btn btn-lg btn-danger c-font-uppercase c-font-bold c-btn-square m-t-20" href="ViewCartServlet">
                            VIEW CART
                        </a>
                    </div></div>
            </div><br/>
            <div class="m-l-10 m-r-10">
                <form class="form-inline m-b-10" action="DispatcherServlet" method="get">
                    <div class="input-group m-t-10 c-square col-md-3 col-sm-4">
                        <span class="input-group-addon" id="basic-addon1" >Category</span>
                        <select class="form-control c-square c-theme" name="category">
                            <c:forEach items="${cate}" var="cat" varStatus="counter">
                                <option value="${cat.categoryId}" <c:if test="${param.category eq cat.categoryId}">selected</c:if>>${cat.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-group m-t-10 c-square col-md-3 col-sm-5">
                        <span class="input-group-addon" id="basic-addon1">Price</span>
                        <select class="form-control c-square c-theme" name="price">
                            <option value="50000" <c:if test="${param.price == 50000}">selected</c:if>>Giá Dưới 50k</option>
                            <option value="100000"<c:if test="${param.price == 100000}">selected</c:if>>Giá Dưới 100k</option>
                            <option value="300000"<c:if test="${param.price == 300000}">selected</c:if>>Giá Dưới 300k</option>
                            <option value="500000"<c:if test="${param.price == 500000}">selected</c:if>>Giá Dưới 500k</option>
                            <option value="1000000"<c:if test="${param.price == 1000000}">selected</c:if>>Giá Dưới 1 Triệu</option>
                            <option value="3000000"<c:if test="${param.price == 3000000}">selected</c:if>>Giá Dưới 3 Triệu</option>
                            <option value="5000000"<c:if test="${param.price == 5000000}">selected</c:if>>Giá Dưới 5 Triệu</option>
                            </select>
                        </div>
                        <div class="input-group m-b-10 c-square col-md-4 col-sm-12">
                            <span class="input-group-addon" id="basic-addon1">Search</span>
                            <input type="text" class="form-control c-square c-theme" name="searchValue" placeholder="Search by name" value="${param.searchValue}">
                    </div>
                    <input type="submit" class="btn c-theme-btn c-btn-square m-b-10" name="btAction" value="Search">
                    <a href="ShowProductServlet" class="btn btn-danger">X</a>
                </form>

            </div>
            <div class="c-margin-t-30"></div>
            <div class="c-margin-t-20"></div>
            <c:if test="${empty result}">
                <div class="c-content-box c-size-md c-bg-grey-1">
                    <div class="container">
                        <div class="c-content-bar-1 c-opt-1">
                            <h3 class="c-font-uppercase c-font-bold">Can not find any product</h3>
                            <p class="c-font-uppercase">
                                Please search with another keyword  </p>
                        </div>
                    </div> 
                </div>
            </c:if>
            <c:forEach items="${result}" var="pro" varStatus="counter">
                <div class="col-lg-4 col-md-4 col-sm-6 c-margin-b-20 mt-element-ribbon">
                    <div class="c-content-product-2 c-bg-white c-border">
                        <div class="c-content-overlay">
                            <div class="ribbon ribbon-left ribbon-shadow ribbon-border-dash ribbon-round ribbon-color-danger uppercase z7">#${pro.productId}</div>
                            <div class="ribbon ribbon-right ribbon-vertical-right ribbon-border-dash-vert ribbon-color-warning c-font-white uppercase">
                                <div class="ribbon-sub ribbon-bookmark"></div>
                                <i class="fa fa-star-o"></i>
                            </div>

                            <div class="c-overlay-wrapper">
                                <div class="c-overlay-content">
                                    <!--<h3 class="c-content-isotope-overlay-title c-font-white c-font-uppercase"></h3>-->
                                    <h3 class="c-content-isotope-overlay-title c-font-white c-font-uppercase">Category: ${pro.category.categoryName}</h3>
                                    <p class="c-content-isotope-overlay-price c-font-white c-font-bold">${pro.productName}</p>
                                    <p class="c-content-isotope-overlay-desc c-font-white">${pro.productDescription}</p>
                                    <a href="/xemchitiet.php?id=<?=$gettom['ID']?>"&" class="btn btn-md c-btn-grey-1 c-btn-uppercase c-btn-bold c-btn-border-1x c-btn-square">View Detail</a>
                                </div>
                            </div>
                            <!--<div class="ribbon ribbon-left ribbon-shadow ribbon-border-dash ribbon-round ribbon-color-primary uppercase z7"><i class="fa fa-gift"></i></div>-->

                            <div class="ribbon ribbon-left ribbon-shadow ribbon-border-dash ribbon-round ribbon-color-warning uppercase z7">
                                <i class="fa fa-thumb-tack"></i>
                            </div>
                            <div class="c-bg-img-center c-overlay-object" data-height="height" media="(min-width: 768px)" style="height: 230px; background-image: url('upload/${pro.productImage}')"></div>            
                        </div>
                        <div class="c-info">
                            <p class="c-title c-font-16 c-font-bold">${pro.productName}</p>
                            <p class="c-title c-font-16 c-font-bold">Category: ${pro.category.categoryName}</p>
                            <p class="c-title c-font-16 c-font-bold">. . .</p>

                            <p class="c-price c-font-25 c-font-bold ">
                                ${pro.productPrice}đ
                                <!--<span class="c-font-14 c-font-line-through c-font-red m-l-0">2,373,000đ</span>-->
                            </p>
                            <div class="pull-right" style="margin-top: -35px; margin-right: -8px;">
                                <form action="DispatcherServlet">
                                    <input type="hidden" name="id" value="${pro.productId}" />
                                    <input type="submit" value="Add to cart" class="btn c-btn-green c-btn-square" name="btAction"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="col-md-12"> 

            </div>	

        </div>

    </div>



    <!-- End container -->
</body>
</html>
