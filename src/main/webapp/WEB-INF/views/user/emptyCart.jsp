<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@include file="/common/taglib.jsp"%>

 <div class="empty-cart-section section-fluid">
        <div class="emptycart-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-10 offset-md-1 col-xl-6 offset-xl-3">
                        <div class="emptycart-content text-center">
                            <div class="image">
                                <img class="img-fluid" src="<c:url value='/assets/images/emprt-cart/empty-cart.png' /> " alt="">
                            </div>
                            <h4 class="title">Giỏ hàng rỗng</h4>

                            <a href="<c:url value='/app-view/shop' />" class="btn btn-lg btn-golden">Quay lại mua sắm</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...::::End  About Us Center Section:::... -->