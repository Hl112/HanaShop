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
        <c:if test="${empty sessionScope.PRODUCT}">
            <c:redirect url="ShowProductServlet"></c:redirect>
        </c:if>
        <jsp:include page="header.jsp" flush="true"/>
    </head>
    <body>
        <div class="content-wrapper">
            <div class="container-fluid">
                <c:set var="result" value="${sessionScope.PRODUCT}"/>
                <!--Start Dashboard Content-->

                <div class="card mt-3">
                    <div class="card-content">
                        <div class="row row-group m-0">
                            <div class="col-12 col-lg-6 col-xl-3 border-light">
                                <div class="card-body p-1">
                                    <div class="form-group text-white">
                                        <label for="input-1">Category</label>
                                        <select name="category" class="form-control">
                                            <option>Food</option>
                                            <option>Drink</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-6 col-xl-3 border-light">
                                <div class="card-body p-1">
                                    <div class="form-group text-white" data-role="rangeslider">

                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-6 col-xl-3 border-light">
                                <div class="card-body p-1">
                                    <div class="form-group text-white">
                                        <label for="input-1">Search</label>
                                        <input type="text" class="form-control" id="input-1" placeholder="Enter Your Product Name">
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-6 col-xl-3 border-light">
                                <div class="card-body">
                                    <input type="submit" value="Search" class="btn btn-danger">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <c:forEach items="${result}" var="pro" varStatus="counter">
                        <div class="col-12 col-lg-3 col-xl-4">
                            <div class="card">
                                <div class="card-header">${pro.category.categoryName}</div>
                                <div clsass="card-body">
                                    <img src="${pro.productImage}" alt="IMG ${pro.productName}" class="pd-img"/>
                                </div>
                                <div class="row m-0 row-group text-center border-top border-light-3">

                                    <div class="col-12 col-lg-7">
                                        <div class="p-3 text-white">
                                            <h5 class="mb-0 text-center">${pro.productName}</h5>
                                        </div>
                                    </div>
                                    <div class="col-12 col-lg-5 mt-1 mb-2">
                                        <small class="mb-0 text-white">${pro.productPrice}VND<br>
                                            <input type="submit" value="Add to cart" class="btn btn-primary"></small>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- Product 1-->
                    <div class="col-12 col-lg-3 col-xl-4">
                        <div class="card">
                            <div class="card-header">Product 1</div>
                            <div class="card-body">
                                <img src="" alt="IMG Product" sizes="200px" srcset="200px">
                                </ul>
                            </div>
                            <div class="row m-0 row-group text-center border-top border-light-3">
                                <div class="col-12 col-lg-4">
                                    <div class="p-3 text-white">
                                        <h5 class="mb-0">Keo Mut</h5>
                                        <small class="mb-0">2000d <span> <br> Add to Cart</span></small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Product 1-->
                    <div class="col-12 col-lg-3 col-xl-4">
                        <div class="card">
                            <div class="card-header">Product 1</div>
                            <div class="card-body">
                                <img src="" alt="IMG Product" sizes="200px" srcset="200px"/>

                            </div>
                            <div class="row m-0 row-group text-center border-top border-light-3">
                                <div class="col-12 col-lg-4">
                                    <div class="p-3 text-white">
                                        <h5 class="mb-0">Keo Mut</h5>
                                        <small class="mb-0">2000d <span> <br> Add to Cart</span></small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Product 1-->
                <div class="col-12 col-lg-3 col-xl-4">
                    <div class="card">
                        <div class="card-header">Product 1</div>
                        <div class="card-body">
                            <img src="" alt="IMG Product" sizes="200px" srcset="200px"/>

                        </div>
                        <div class="row m-0 row-group text-center border-top border-light-3">
                            <div class="col-hl12 col-lg-4">
                                <div class="p-3 text-white">
                                    <h5 class="mb-0">Keo Mut</h5>
                                    <small class="mb-0">2000d <span> <br> Add to Cart</span></small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Product 1-->
                <div class="col-12 col-lg-3 col-xl-4">
                    <div class="card">
                        <div class="card-header">Product 1</div>
                        <div class="card-body">
                            <img src="" alt="IMG Product" sizes="200px" srcset="200px">
                            </ul>
                        </div>
                        <div class="row m-0 row-group text-center border-top border-light-3">
                            <div class="col-12 col-lg-4">
                                <div class="p-3 text-white">
                                    <h5 class="mb-0">Keo Mut</h5>
                                    <small class="mb-0">2000d <span> <br> Add to Cart</span></small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Product 1-->
                <div class="col-12 col-lg-3 col-xl-4">
                    <div class="card">
                        <div class="card-header">Product 1</div>
                        <div class="card-body">
                            <img src="" alt="IMG Product" sizes="200px" srcset="200px">

                        </div>
                        <div class="row m-0 row-group text-center border-top border-light-3">
                            <div class="col-12 col-lg-4">
                                <div class="p-3 text-white">
                                    <h5 class="mb-0">Keo Mut</h5>
                                    <small class="mb-0">2000d <span> <br> Add to Cart</span></small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>



            <!-- End container -->

        </div>
    </div>
</body>
</html>
