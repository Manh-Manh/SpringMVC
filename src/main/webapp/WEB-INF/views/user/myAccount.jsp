<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = '/common/taglib.jsp' %>


<div class="breadcrumb-section breadcrumb-bg-color--golden">
        <div class="breadcrumb-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3 class="breadcrumb-title">Tài khoản</h3>
                        <div class="breadcrumb-nav breadcrumb-nav-color--black breadcrumb-nav-hover-color--golden">
                            <nav aria-label="breadcrumb">
                                <ul>
                                    <li><a href="<c:url value='/app-view/home-page' /> ">Trang chủ</a></li>
                                    <!-- <li><a href="shop-grid-sidebar-left.html">Shop</a></li> -->
                                    <li class="active" aria-current="page">Tài khoản</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Breadcrumb Section:::... -->

    <!-- ...:::: Start Account Dashboard Section:::... -->
    <div class="account-dashboard">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-3 col-lg-3">
                    <!-- Nav tabs -->
                    <div class="dashboard_tab_button" data-aos="fade-up"  data-aos-delay="0">
                        <ul role="tablist" class="nav flex-column dashboard-list">
                            <li><a href="#account-details" data-bs-toggle="tab" class="nav-link btn btn-block btn-md btn-black-default-hover active">Thông tin</a></li>
                            <li> <a href="#orders" data-bs-toggle="tab" class="nav-link btn btn-block btn-md btn-black-default-hover">Đơn hàng</a></li>
                            <li><a href="#downloads" data-bs-toggle="tab" class="nav-link btn btn-block btn-md btn-black-default-hover">Mật khẩu</a></li>
                            <!-- <li><a href="#address" data-bs-toggle="tab" class="nav-link btn btn-block btn-md btn-black-default-hover">Addresses</a></li> -->
                            <!-- <li><a href="#account-details" data-bs-toggle="tab" class="nav-link btn btn-block btn-md btn-black-default-hover">Account details</a></li> -->
                            <li><a href="login.html" class="nav-link btn btn-block btn-md btn-black-default-hover">Đăng xuất</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-12 col-md-9 col-lg-9">
                    <!-- Tab panes -->
                    <div class="tab-content dashboard_content" data-aos="fade-up"  data-aos-delay="200">
                        <div class="tab-pane fade show " id="dashboard">
                            <h4>Dashboard </h4>
                            <p>From your account dashboard. you can easily check &amp; view your <a href="#">recent orders</a>, manage your <a href="#">shipping and billing addresses</a> and <a href="#">Edit your password and account details.</a></p>
                        </div>
                        <div class="tab-pane fade" id="orders">
                            <h4>Đơn hàng của tôi</h4>
                            <div class="table_page table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Số thứ tự</th>
                                            <th>Ngày đặt</th>
                                            <th>Trạng thái</th>
                                            <th>Tổng tiền</th>
                                            <th>Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                <c:set var="stt" value="0"></c:set>
                                <c:forEach var="item" items="${ listOrders}">
                                        <c:if test="${item.status != 1 }">
                                        <tr>
                                        <c:set var="stt" value="${stt+1 }"></c:set>
                                            <td><c:out value="${stt }" ></c:out></td>
                                            <td><c:out value="${item.updated_date!=null?item.updated_date:item.created_date }"></c:out></td>
                                            <td>
                                            	<c:if test="${item.status == 3 }">
                                            		<span class="success">
                                            	</c:if>
                                            	<c:out value="${item.statusString }"></c:out>
                           	               		<c:if test="${item.status == 3 }">
                                					</span >
                                            	</c:if>
                                            </td>
                                            <td> <c:out value="${item.totalString }"></c:out></td>
                                            <td>
                                            	<c:url var="urlView" value="/app-view/viewOrder" >
                                               		<c:param name="orderId" value="${item.orderId }"/>  
                                               </c:url>
                                               
                                            	<a id="urlView" data-url="${urlView }" href="${urlView }" class="view">Xem </a>
                                            	<c:if test="${item.status == 2 }">
	                      	                       <c:url var="urlCancel" value="/app-view/cancelOrder" >
	                         							<c:param name="orderId" value="${item.orderId }"/>  
	                                              </c:url>
	                                            	<a id="urlCancel" href="#" data-url="${urlCancel }" class="view"> Hủy</a>
	                                           	</c:if>	
                                            </td>
                                            
                                        </tr>
                                        </c:if>
                                </c:forEach>
                                       
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="downloads">
                            <h4>Downloads</h4>
                            <div class="table_page table-responsive">
                                <form:form action="changePassword" modelAttribute="userUpdate" >
                                        <c:set var ="u" value="${sessionScope.user }"></c:set>
                                            
                                            <br>
                                                <form:hidden value="${u.fullName }" path="fullName"  />
                                                <form:hidden  value="${u.userName }" path="userName"  />
                                                <form:hidden  value="${u.email }" path="email"  />
                                                <form:hidden  name="birthday" value="${u.birthDate }" path="birthDate"/>
                                                <form:hidden  name="address" value="${u.address }" path="address"/>
                                            <div class="default-form-box mb-20">
                                                <label>Mật khẩu cũ</label>
                                                <form:input type="password" name="phoneNumber" path="password" />
                                            </div>
                                            <div class="default-form-box mb-20">
                                                <label>Mật khẩu mới</label>
                                                <form:input type="password" name="phoneNumber" path="newPassword" />
                                            </div>
                                            <div class="default-form-box mb-20">
                                                <label>Nhập lại mật khẩu</label>
                                                <input type="password" name="phoneNumber"  />
                                            </div>
                                            <div class="save_button mt-3">
                                                <button class="btn btn-md btn-black-default-hover" type="submit">Thay đổi</button>
                                            </div>
                                        </form:form>
                            </div>
                        </div>
                        <div class="tab-pane" id="address">
                            <p>The following addresses will be used on the checkout page by default.</p>
                            <h5 class="billing-address">Billing address</h5>
                            <a href="#" class="view">Edit</a>
                            <p><strong>Bobby Jackson</strong></p>
                            <address>
                                House #15<br>
                                    Road #1<br>
                                    Block #C <br>
                                    Banasree <br>
                                    Dhaka <br>
                                    1212
                            </address>
                            <p>Bangladesh</p>
                        </div>
                        <div class="tab-pane fade active" id="account-details">
                            
                            <div class="login">
                                <div class="login_form_container">
                                    <div class="account_login_form">
                                        <form:form action="updateUser" modelAttribute="userUpdate" enctype="multipart/form-data">
                                        <c:set var ="u" value="${sessionScope.user }"></c:set>
                                            <div class="default-form-box mb-20">
                                                <div class="team-single " data-aos="fade-up"  data-aos-delay="0">
                                                    <div class="team-img">
                                                    	<div class="img-fluid">
                                                    		<c:set var="default" value="default-avatar.png"></c:set>
                                                        	<img id="img-avatar" class="img-avatar" src="<c:url value='/assets/images/user/${u.avatar}' /> " alt="">
                                                    	</div>
                                                    </div>
                                                    
                                                    <div class="team-content">
                                                        <h6 class="team-name font--bold mt-5">
                                                        	<c:out value="${u.fullName!=null?u.fullName:u.userName }" /></h6>
                                                        <br>
                                                        <div class="save_button mt-3 team-social pos-absolute">
                                                            <button class="btn btn-md btn-black-default-hover" type="button" onclick="document.getElementById('fileInput').click();">Chọn ảnh</button>
                                                            <form:input id="fileInput" path="fileAvatar" type="file" name ="file"  />
                                                           <script>
                                                            	document.getElementById('fileInput').hidden = true;
                                                           </script>
                                                              
                                                        </div>
                                                        
                                                    </div>
                                                    
                                                </div>
                                                
                                                
                                            </div>
                                            <br>
                                            <div class="default-form-box mb-20">
                                                <label>Tên người dùng</label>
                                                <form:input type="text" value="${u.fullName }" path="fullName" name="first-name" />
                                            </div>
                                            <div class="default-form-box mb-20">
                                                <label>Tên đăng nhập</label>
                                                <form:input type="text" value="${u.userName }" disabled="true" path="userName" name="first-name" />
                                            </div>
                                            <div class="default-form-box mb-20">
                                                <label>Email</label>
                                                <form:input type="text" value="${u.email }" path="email" name="email-name" />
                                            </div>
                                            
                                            <div class="default-form-box mb-20">
                                                <label>Ngày sinh</label>
                                                <form:input type="date" name="birthday" value="${u.birthDate }" path="birthDate"/>
                                            </div>
                                            <div class="default-form-box mb-20">
                                                <label>Địa chỉ</label>
                                                <form:input type="text" name="address" value="${u.address }" path="address"/>
                                            </div>
                                            <div class="default-form-box mb-20">
                                                <label>Số điện thoại</label>
                                                <input type="text" name="phoneNumber"value="${u.phoneNumber }" path="phoneNumber" />
                                            </div>
                                            <div class="save_button mt-3">
                                                <button class="btn btn-md btn-black-default-hover" type="submit">Lưu</button>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Account Dashboard Section:::... -->

    <!-- Start Footer Section -->
    <footer class="footer-section footer-bg section-top-gap-100">
        <div class="footer-wrapper">
        <!-- Start Footer Top -->
        <div class="footer-top">
        <div class="container">
            <div class="row mb-n6">
                <div class="col-lg-3 col-sm-6 mb-6">
                    <!-- Start Footer Single Item -->
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="0">
                        <h5 class="title">INFORMATION</h5>
                        <ul class="footer-nav">
                            <li><a href="#">Delivery Information</a></li>
                            <li><a href="#">Terms & Conditions</a></li>
                            <li><a href="contact-us.html">Contact</a></li>
                            <li><a href="#">Returns</a></li>
                        </ul>
                    </div>
                    <!-- End Footer Single Item -->
                </div>
                <div class="col-lg-3 col-sm-6 mb-6">
                    <!-- Start Footer Single Item -->
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="200">
                        <h5 class="title">MY ACCOUNT</h5>
                        <ul class="footer-nav"> 
                            <li><a href="my-account.html">My account</a></li>
                            <li><a href="wishlist.html">Wishlist</a></li>
                            <li><a href="privacy-policy.html">Privacy Policy</a></li>
                            <li><a href="faq.html">Frequently Questions</a></li>
                            <li><a href="#">Order History</a></li>
                        </ul>
                    </div>
                    <!-- End Footer Single Item -->
                </div>
                <div class="col-lg-3 col-sm-6 mb-6">
                    <!-- Start Footer Single Item -->
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="400">
                        <h5 class="title">CATEGORIES</h5>
                        <ul class="footer-nav">
                            <li><a href="#">Decorative</a></li>
                            <li><a href="#">Kitchen utensils</a></li>
                            <li><a href="#">Chair & Bar stools</a></li>
                            <li><a href="#">Sofas and Armchairs</a></li>
                            <li><a href="#">Interior lighting</a></li>
                        </ul>
                    </div>
                    <!-- End Footer Single Item -->
                </div>
                <div class="col-lg-3 col-sm-6 mb-6">
                    <!-- Start Footer Single Item -->
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="600">
                        <h5 class="title">ABOUT US</h5>
                        <div class="footer-about">
                            <p>We are a team of designers and developers that create high quality Magento, Prestashop, Opencart.</p>
                            
                            <address class="address">
                                <span>Address: 4710-4890 Breckinridge St, Fayettevill</span> 
                                <span>Email: yourmail@mail.com</span>    
                            </address>
                        </div>
                    </div>
                    <!-- End Footer Single Item -->
                </div>
            </div>
        </div>
        </div>
        <!-- End Footer Top -->

        <!-- Start Footer Center -->
        <div class="footer-center">
            <div class="container">
                <div class="row mb-n6">
                    <div class="col-xl-3 col-lg-4 col-md-6 mb-6">
                        <div class="footer-social" data-aos="fade-up"  data-aos-delay="0">
                            <h4 class="title">FOLLOW US</h4>
                            <ul class="footer-social-link">
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-xl-7 col-lg-6 col-md-6 mb-6">
                        <div class="footer-newsletter" data-aos="fade-up"  data-aos-delay="200">
                            <h4 class="title">DON'T MISS OUT ON THE LATEST</h4>
                            <div class="form-newsletter">
                                <form action="#" method="post">
                                    <div class="form-fild-newsletter-single-item input-color--golden">
                                        <input type="email" placeholder="Your email address..." required>
                                        <button type="submit">SUBSCRIBE!</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        