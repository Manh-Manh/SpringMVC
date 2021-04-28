<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@include file="/common/taglib.jsp"%>

 <!-- ...:::: Start Breadcrumb Section:::... -->
    <div class="breadcrumb-section breadcrumb-bg-color--golden">
        <div class="breadcrumb-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3 class="breadcrumb-title">Thanh toán</h3>
                        <div class="breadcrumb-nav breadcrumb-nav-color--black breadcrumb-nav-hover-color--golden">
                            <nav aria-label="breadcrumb">
                                <ul>
                                    <li><a href="<c:url value="/app-view/home-page" />">Trang chủ</a></li>
                                    <!-- <li><a href="shop-grid-sidebar-left.html">Shop</a></li> -->
                                    <li class="active" aria-current="page">Thanh toán</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Breadcrumb Section:::... -->

    <!-- ...:::: Start Checkout Section:::... -->
    <div class="checkout-section">
        <div class="container">
            <div class="row">
                <!-- User Quick Action Form -->
                
                <!-- User Quick Action Form -->
            </div>
            <!-- Start User Details Checkout Form -->
            <div class="checkout_form" data-aos="fade-up"  data-aos-delay="400">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                      <form:form action="checkOut" method="POST" modelAttribute="userCheckOut">
                            <h3>Thông tin khách hàng</h3>
                            
                            <div class="row">
                                <div class="col-lg-13">
                                    <div class="default-form-box">
                                        <label>Tên khách hàng <span>*</span></label>
                                        <form:input  disabled="true" path="fullName" value = "${sessionScope.user.fullName }" />
                                    </div>
                                </div>
                                
                                
                                <div class="col-12">
                                    <div class="default-form-box">
                                        <label>Địa chỉ<span>*</span></label>
                                        <form:input path="address" value="${sessionScope.user.address }" placeholder="Số nhà, tên đường,..." type="text" />
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="default-form-box">
                                        <label>Số điện thoại<span>*</span></label>
                                        <form:input path="phoneNumber" value ="${sessionScope.user.phoneNumber }" />
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="default-form-box">
                                        <label> Email <span>*</span></label>
                                        <form:input disabled="true"  type="text"  value="${sessionScope.user.email }" path="email" />
                                    </div>
                                </div>
						</div>
                        
                    </div>
                    <div class="col-lg-6 col-md-6">
                       
                            <h3>Đơn hàng</h3>
                            <div class="order_table table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Sản phẩm</th>
                                            <th>Đơn giá</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                 <c:forEach var="item" items="${sessionScope.cart.listProduct }">
                                        <tr>
                                            <td style ="width: 75%">
                                            	<img style="width: 40%;float:left;" alt="" src="<c:url value='/assets/images/products/${item.image.trim()}' /> " >
                                             <div style="width:60%;float:right;margin-top:10%">
	                                             <c:url var="url" value="/app-view/viewDetail" >
	                                             	<c:param name="id" value="${item.productId }"/>  
	                                             </c:url>
	                                             <a href="<c:out value="${url}" /> " class="image-link" >
	                                             	<c:out value="${item.productName }" /> <strong> × <c:out value="${item.cartQuantity }" /></strong>
	                                             </a>
                                             </div>
                                              
                                            </td>
                                            <td style ="width: 25%"> <c:out value="${item.cartTotalString }" /></td>
                                        </tr>
                                </c:forEach>
                                        
                                    </tbody>
                                    <tfoot>
                                        <!-- <tr>
                                            <th>Thành tiền</th>
                                            <td><c:out value="${sessionScope.cart.totalString }" /></td>
                                        </tr>
                                        <!--  <tr>
                                            <th>Shipping</th>
                                            <td><strong>$5.00</strong></td>
                                        </tr>-->
                                        <tr class="order_total">
                                            <th>Tổng tiền</th>
                                            <td><strong><c:out value="${sessionScope.cart.totalString }" /></strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="payment_method">
                               
                                <div class="order_button pt-3">
                                    <button class="btn btn-md btn-black-default-hover" type="submit">Đặt hàng</button>
                                </div>
                            </div>
                        </div>
            </form:form>
                    </div>
                </div>
            </div> <!-- Start User Details Checkout Form -->
        </div>
    </div><!-- ...:::: End Checkout Section:::... -->