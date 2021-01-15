<%-- 
    Document   : admin
    Created on : Jan 11, 2021, 12:51:13 PM
    Author     : HL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
        <c:import url="header.jsp"/>
        <c:if test="${!empty requestScope.NOTI}">
            <c:set var="NOTI" value="${requestScope.NOTI}" scope="request"/>
        </c:if>
        <c:if test="${sessionScope.LOAD == 0 || empty sessionScope.LOAD}">
            <c:if test="${!empty requestScope.NOTI}">
                <c:set var="NOTI" value="${requestScope.NOTI}" scope="request"/>
            </c:if>
            <c:redirect url="DispatcherServlet?btAction=Search Product&searchValue="/>
        </c:if>
    </head>
    <body>   
        <c:if test="${!sessionScope.USER.isAdmin}">
            <div class="c-content-box c-size-md c-bg-grey-1">
                <div class="container">
                    <div class="c-content-bar-1 c-opt-1">
                        <h3 class="c-font-uppercase c-font-bold">Function of Admin Role</h3>
                        <p class="c-font-uppercase">
                            Please go to home page to use another function.  </p>
                    </div>
                </div> 
            </div>
        </c:if>
        <c:if test="${sessionScope.USER.isAdmin}">
            <c:if test="${empty sessionScope.CATEGORY}">
                <c:redirect url="GetProductServlet"></c:redirect>
            </c:if>
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
                            <a class="btn btn-block c-btn btn-lg btn-danger c-font-uppercase c-font-bold c-btn-square m-t-20" href="addProduct.jsp">
                                ADD PRODUCT
                            </a>
                        </div></div>
                </div><br/>
                <div class="m-l-10 m-r-10">
                    <form class="form-inline m-b-10" action="DispatcherServlet" method="POST">
                        <div class="input-group m-t-10 c-square col-md-3 col-sm-4">
                            <span class="input-group-addon" id="basic-addon1" >Category</span>
                            <select class="form-control c-square c-theme" name="category">
                                <option value="-1" <c:if test="${param.category == -1}">selected</c:if>>--- Select Category---</option>
                                <c:forEach items="${cate}" var="cat" varStatus="counter">
                                    <option value="${cat.categoryId}" <c:if test="${param.category eq cat.categoryId}">selected</c:if>>${cat.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group m-t-10 c-square col-md-3 col-sm-5">
                            <span class="input-group-addon" id="basic-addon1">Price</span>
                            <select class="form-control c-square c-theme" name="price">
                                <option value="-1" <c:if test="${param.price == -1}">selected</c:if>>---Select Price---</option>
                                <option value="50000" <c:if test="${param.price == 50000}">selected</c:if>>Price under 50k</option>
                                <option value="100000" <c:if test="${param.price == 100000}">selected</c:if>>Price under 100k</option>
                                <option value="300000" <c:if test="${param.price == 300000}">selected</c:if>>Price under 300k</option>
                                <option value="500000" <c:if test="${param.price == 500000}">selected</c:if>>Price under 500k</option>
                                <option value="1000000" <c:if test="${param.price == 1000000}">selected</c:if>>Price under 1m</option>
                                <option value="3000000" <c:if test="${param.price == 3000000}">selected</c:if>>Price under 3m</option>
                                <option value="5000000" <c:if test="${param.price == 5000000}">selected</c:if>>Price under 5m</option>
                                </select>
                            </div>
                            <div class="input-group m-b-10 c-square col-md-3 col-sm-12">
                                <span class="input-group-addon" id="basic-addon1">Search</span>
                                <input type="text" class="form-control c-square c-theme" name="searchValue" placeholder="Search by name" value="${param.searchValue}">
                        </div>
                        <input type="submit" class="btn c-theme-btn c-btn-square m-b-10" name="btAction" value="Search Product">
                        <c:if test="${not empty param.category || not empty param.price}">
                            <c:if test="${param.category != -1 || param.price != -1}">
                                <a href="GetProductServlet?category=-1&price=-1&searchValue=" class="btn btn-danger">X</a>
                            </c:if></c:if>
                        </form>
                    </div>
                    <div class="c-margin-t-30"></div>
                    <div class="c-margin-l-20"></div>
                    <!-- BEGIN: PAGE CONTENT -->
                    <!-- BEGIN: CONTENT/SHOPS/SHOP-CUSTOMER-DASHBOARD-1 -->
                    <div class="c-content-title-1">
                        <h3 class="c-font-uppercase c-font-bold">Manager Product</h3>
                        <div class="c-line-left"></div>
                    </div>
                    <span class="label label-success" style="color: #5cb85c;display: block;"></span>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Product Name</th>
                                <th>Product Price</th>
                                <th>Quantity</th>
                                <th>Category</th>
                                <th>Image</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody class="list-wrapper">
                        <c:if test="${empty result}">
                        <div class="c-content-box c-size-md c-bg-grey-1">
                            <div class="container">
                                <div class="c-content-bar-1 c-opt-1">
                                    <h3 class="c-font-uppercase c-font-bold">Can not find any product</h3>
                                    <p class="c-font-uppercase">Please search with another keyword</p>
                                </div>
                            </div> 
                        </div>
                    </c:if>

                    <c:forEach items="${result}" var="pro" varStatus="counter">
                        <form action="DispatcherServlet" method="POST">
                            <tr class="list-item">
                                <th>#${pro.productId}</th>
                            <input type="hidden" name="id" value="${pro.productId}" />
                            <td>${pro.productName}</td>
                            <td>${pro.productPrice}</td>
                            <td>${pro.quantity}</td> 
                            <td>${pro.category.categoryName}</td>
                            <td>
                                <img src="upload/${pro.productImage}" width="120px" height="120px" alt="No Img"/>
                            </td>
                            <td>
                                <a href="updateProduct.jsp?id=${pro.productId}" class="btn btn-primary">Update</a>
                                <a href="#" value="Remove Product" data-toggle="modal" data-target=".pd${pro.productId}" class="btn btn-danger">Delete</a>

                                <!-- Modal Confirm -->
                                <div class="modal fade pd${pro.productId}" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title" id="myModalLabel">Delete Confirm</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <h3>Delete ${pro.productName} Product?</h3>
                                            </div>
                                            <div class="modal-footer">
                                                <input type="submit" value="Delete Product" class="btn btn-danger load-modal" name="btAction"/>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            </tr> 
                        </form>
                    </c:forEach>
                    </tbody>
                </table><br/>
                <div class="col-md-12">
                    <div id="pagination-container"></div>
                </div>
            </div>
        </div>
    </div>
</c:if>

</div>
<script src="assets/js/app.js"></script>
<script>
    $(document).ready(function () {
        $("#myBtn").click(function () {
            $("#myModal").modal();
        });
    });
</script>

</body>
</html>
