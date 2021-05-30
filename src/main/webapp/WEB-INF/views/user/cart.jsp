<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@include file="/common/taglib.jsp"%>


<!-- ...:::: Start Breadcrumb Section:::... -->
    <div class="breadcrumb-section breadcrumb-bg-color--golden">
        <div class="breadcrumb-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3 class="breadcrumb-title">Giỏ hàng</h3>
                        <div class="breadcrumb-nav breadcrumb-nav-color--black breadcrumb-nav-hover-color--golden">
                            <nav aria-label="breadcrumb">
                                <ul>
                                    <li><a href="<c:url value="/app-view/home-page" />">Trang chủ</a></li>
                                    <!-- <li><a href="shop-grid-sidebar-left.html">Shop</a></li>-->
                                    <li class="active" aria-current="page">Giỏ hàng</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Breadcrumb Section:::... -->

    <!-- ...:::: Start Cart Section:::... -->
    <div class="cart-section">
        <!-- Start Cart Table -->
        <div class="cart-table-wrapper"  data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="table_desc">
                            <div class="table_page table-responsive">
                                <table>
                                    <!-- Start Cart Table Head -->
                                    <thead>
                                        <tr>
                                            <th class="product_remove">Xóa</th>
                                            <th class="product_thumb">Hình ảnh</th>
                                            <th class="product_name">Tên sản phẩm</th>
                                            <th class="product-price">Giá</th>
                                            <th class="product_quantity">Số lượng</th>
                                            <th class="product_total">Thành tiền</th>
                                        </tr>
                                    </thead> <!-- End Cart Table Head -->
                                    <tbody>
                                    <c:forEach var ="item" items = "${sessionScope.cart.listProduct }">
                                        <!-- Start Cart Single Item-->
                                        <tr>
                                        <c:url var="url" value="/app-view/viewDetail" >
                                       		<c:param name="id" value="${item.productId }"/>  
                                        </c:url>
                                        	<input name="productId" class="productId" type="text" hidden  value="${item.productId}" >
                                            <td class="product_remove">
                                            	<button value="${item.productId}" class="delCart"><i class="fa fa-trash-o"></i></button>
                                            </td>
                                            <td class="product_thumb">
                                             
                                            	<a href="${url }"><img src="<c:url value='/assets/images/products/${item.image }' /> " alt="">
                                            	</a>
                                           	</td>
                                            <td class="product_name"><a href="${url }"><c:out value="${item.productName }" /></a></td>
                                            <td class="product-price">
                                            	<c:if test="${item.discount > 0}">
							                        <div class="price unitPrice oldPrice">₫ 
						                            	<s><i><c:out value="${item.stringUnitPrice }"></c:out></i></s>
						                            </div>
						                            <div class="price discountPrice newPrice">₫
						                            	<c:out value="${item.stringDiscountPrice }"></c:out>
						                            	 <i>(-<c:out value="${item.discount }"></c:out>%) </i>
						                            </div>
					                            </c:if>
					                            <c:if test="${item.discount == null || item.discount <= 0}">
							                        <div class="price unitPrice">₫
						                            	<c:out value="${item.stringUnitPrice }"></c:out>
						                            </div>
						                            <%-- <div class="price discountPrice">$ 
						                            	<out value="${dataSelected.stringDiscountPrice }
						                            </div> --%>
					                            </c:if>
                                            
                                            </td>
                                            <td class="product_quantity"><label></label> 
                                            	<input name="cartQuantity" min="1" max="${item.quantity }" value="${item.cartQuantity }" type="number">
                                            </td>
                                            <td class="product_total"><c:out value="${item.cartTotalString }" /></td>
                                        </tr> <!-- End Cart Single Item-->
                                        <!-- Start Cart Single Item-->
                                     </c:forEach> 
                                    </tbody>
                                </table>
                            </div>
                            <div class="cart_submit">
                                <button class="btn btn-md btn-golden updateCart" type="submit">Cập nhật giỏ hàng</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End Cart Table -->

        <!-- Start Coupon Start -->
        <div class="coupon_area">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="coupon_code right"  data-aos="fade-up"  data-aos-delay="400">
                            <h3>Tổng giỏ hàng </h3>
                            <div class="coupon_inner">
                                <div class="cart_subtotal">
                                    <p>Tổng sản phẩm</p>
                                    <p class="cart_amount">
                                    	<c:out value="${sessionScope.cart.listProduct.size() }"></c:out>
                                    </p>
                                </div>
                                <div class="cart_subtotal ">
                                    <p>Tổng tiền</p>
                                    <p class="cart_amount">
                                    	<c:out value="${sessionScope.cart.totalString }"></c:out>
                                    </p>
                                </div>
                                

                                <div class="cart_subtotal">
                                    <p>Thành tiền</p>
                                    <p class="cart_amount">
                                    	<c:out value="${sessionScope.cart.totalString }"></c:out>
                                    </p>
                                </div>
                                <div class="checkout_btn">
                                    <a href="<c:url value='/app-view/checkOut' />" class="btn btn-md btn-golden">Thanh toán</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End Coupon Start -->
    </div> <!-- ...:::: End Cart Section:::... -->