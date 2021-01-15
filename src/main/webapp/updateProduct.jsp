<%-- 
    Document   : updateProduct
    Created on : Jan 13, 2021, 2:45:57 AM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
        <c:import url="header.jsp"/>
        
        <c:if test="${empty sessionScope.CATEGORY || empty param.id}">
            <jsp:forward page="DispatcherServlet?btAction=Search Product&searchValue="/>
        </c:if>
        <c:set var="cate" value="${sessionScope.CATEGORY}"/>
        <c:if test="${!empty sessionScope.PRODUCT}">
            <c:forEach items="${sessionScope.PRODUCT}" var="pro" varStatus="counter">
                <c:if test="${pro.productId eq param.id}">
                    <c:set var="product" value="${pro}"/>
                </c:if>
            </c:forEach>
        </c:if>
    </head>
    <body>
        <div class="container">
            <div class="c-page-title c-pull-left">
                <h3 class="c-font-uppercase c-font-bold c-font-white c-font-20 c-font-slim">&nbsp;</h3>
            </div>
        </div>	
        <div class="c-layout-page" style="margin-top: 20px;">
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
                            <a class="btn btn-block c-btn btn-lg btn-danger c-font-uppercase c-font-bold c-btn-square m-t-20" href="admin.jsp">
                                Manager Product
                            </a>
                        </div></div>
                </div><br/>
                <div class="c-layout-sidebar-content ">
                    <!-- BEGIN: PAGE CONTENT -->
                    <!-- BEGIN: CONTENT/SHOPS/SHOP-CUSTOMER-DASHBOARD-1 -->
                    <div class="c-content-title-1">
                        <h3 class="c-font-uppercase c-font-bold">Update Product</h3>
                        <div class="c-line-left"></div>
                    </div>
                    <form class="form-horizontal" method="POST" enctype="multipart/form-data" action="UpdateProductServlet" onsubmit="return validate()">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Category:</label>
                            <div class="col-md-6">
                                <select class="form-control c-square c-theme" name="category" id="cate" required="">
                                    <c:forEach items="${cate}" var="cat" varStatus="counter">
                                        <option value="${cat.categoryId}" <c:if test="${param.id eq cat.categoryId}">selected</c:if>>${cat.categoryName}</option>
                                    </c:forEach>
                                </select>  </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Product Name:</label>
                            <div class="col-md-6">
                                <input class="form-control  c-square c-theme" name="name" type="text" id="productName" required="" value="${product.productName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Product Price:</label>
                            <div class="col-md-6">
                                <input class="form-control  c-square c-theme" name="price" type="number" min="1" max="" id="productPrice" required="" value="${product.productPrice}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Quantity:</label>
                            <div class="col-md-6"> 
                                <input class="form-control c-square c-theme qty" name="quantity" type="number" min="1" max="" required="" id="productQuantity" value="${product.quantity}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Product Description:</label>
                            <div class="col-md-6">
                                <input class="form-control c-square c-theme" name="description" type="text" id="productDescription" value="${product.productDescription}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Image File:</label>
                            <div class="col-md-6">
                                <input type="hidden" name="id" value="${param.id}"/>
                                <input type="hidden" name="imgOld" value="${product.productImage}" />
                                <input class="form-control c-square c-theme" name="image" type="file" id="productImage" >
                                <img id="preview" src="#" alt="Your Image">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-6">
                                <label></label>
                            </div>
                        </div> 
                        <div class="form-group c-margin-t-40">
                            <div class="col-md-offset-3 col-md-6">
                                <button type="submit" name="btAction" class="btn c-theme-btn c-btn-square c-btn-uppercase c-btn-bold btn-block">Update Product</button>
                            </div>
                        </div>
                        <br>
                    </form>
                    <script>
                        window.onload = function () {
                            document.getElementById("cate").onchange = validate();
                            document.getElementById('productName').onchange = validate();
                            document.getElementById("productPrice").onchange = validate();
                            document.getElementById("productQuantity").onchange = validate();
                        };
                    </script>
                </div>
            </div>
        </div>
    </body>
</html>
