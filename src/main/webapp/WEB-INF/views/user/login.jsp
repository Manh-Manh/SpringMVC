<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = '/common/taglib.jsp' %>
<!-- ...:::: Start Customer Login Section :::... -->
<br>
<br>
<c:if test="${ message != null && message !='' }" >
	<div class ="breadcrumb-title" >
		<c:out value="${message }"></c:out>
	</div>
	<c:out value="${ sessionScope.user.userName }" />
</c:if>
     <div class="customer-login">
        <div class="container">
            <div class="row">
                <!--login area start-->
                <div class="col-lg-6 col-md-6">
                    <div class="account_form" data-aos="fade-up"  data-aos-delay="0">
                        <h3>Đăng nhập</h3>
  				<form:form method="POST" action="login" modelAttribute="userSearch">   
                        
                            <div class="default-form-box">
                                <label>Email <span>*</span></label>
                                <form:input path="userName"  required="required" type="text" />
                            </div>
                                                                                   
                            <div class="default-form-box">
                                <label>Passwords <span>*</span></label>
                                <form:input path="password"  required="required" type="password" />
                            </div>
                            <div class="login_submit">
                                <button class="btn btn-md btn-black-default-hover mb-4" type="submit">Login</button>
                                <!-- <label class="checkbox-default mb-4" for="offer">
                                    <input type="checkbox" id="offer">
                                    <span>Remember me</span>
                                </label>-->
                                <a href="#">Lost your password?</a>
                            </div>
                        </form:form>
                    </div>
                </div>
                <!--login area start-->
	
                <!--register area start-->
         
                <div class="col-lg-6 col-md-6">
                 <form:form method="POST" action="register" modelAttribute="userRegister">
                    <div class="account_form register" data-aos="fade-up"  data-aos-delay="200">
                        <h3>Đăng ký</h3>
                            <div class="default-form-box">
                                <label>Tên đăng nhập <span>*</span></label>
                                <form:input  path="userName"  required="required" type="text" />
                            </div>
                            <div class="default-form-box">
                                <label>Email <span>*</span></label>
                                
                                <form:input  path="email" type="text" required="required" />
                           </div>                                                                                  
                            <div class="default-form-box">
                                <label>Mật khẩu <span>*</span></label>
                                <form:input  path="password"  required="required" type="password" />
                            </div>
                            <div class="login_submit">
                                <button class="btn btn-md btn-black-default-hover" type="submit">Đăng kí</button>
                            </div>
                    </div>
                      </form:form>
                </div>
      
                <!--register area end-->
            </div>
        </div>
    </div> <!-- ...:::: End Customer Login Section :::... -->