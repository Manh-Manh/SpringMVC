<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = '/common/taglib.jsp' %>
<!-- ...:::: Start Customer Login Section :::... -->
<br>
<br>

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
                            
                            <div class="">
                                <button class="btn btn-block btn-md btn-black-default-hover mb-4" type="submit">Đăng nhập</button>
                                <!-- <label class="checkbox-default mb-4" for="offer">
                                    <input type="checkbox" id="offer">
                                    <span>Remember me</span>
                                </label>-->
                                <!-- <a href="">Lost your password?</a>-->
                            </div>
                            <p style="text-align: center;">-Hoặc-</p>
                            <div class="">
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http://localhost:8088/SpringMVC/app-view/login-google&response_type=code&client_id=733898855305-qq55bo8bqmokdrq0bkl41prqlkiiumri.apps.googleusercontent.com" class="">
						          <button class="btn btn-block btn-danger btnGG" type="button" >
						          <i class="fab fa-google-plus mr-2"></i> Đăng nhập bằng Google+
						          </button>
						        </a>
                            </div>
                        </form:form>
                    </div>
                </div>
                <!--login area start-->
	
                <!--register area start-->
         
                <div class="col-lg-6 col-md-6">
                
                    <div class="account_form " data-aos="fade-up"  data-aos-delay="200">
                    <h3>Đăng ký</h3>
                     <form:form method="POST" action="register" modelAttribute="userRegister">
                        
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
                            <div class="default-form-box">
                                <label>Ngày sinh</label>
                                <form:input type="date" name="birthday" required="required"  path="birthDate"/>
                            </div>
                            <div class="login_submit">
                                <button class="btn btn-block btn-md btn-black-default-hover" type="submit">Đăng kí</button>
                            </div>
                             </form:form>
                    </div>
                     
                </div>
      
                <!--register area end-->
            </div>
        </div>
    </div> <!-- ...:::: End Customer Login Section :::... -->