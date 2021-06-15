<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@include file="/common/taglib.jsp"%>
<c:if test="${sessionScope.message!=null && sessionScope.message!=''  }">
<script>
	alert("${ sessionScope.message }");
</script>
<% session.removeAttribute("message"); %>
</c:if>
<!-- Offcanvas Overlay -->
    <div class="offcanvas-overlay"></div>

    <!-- Start Hero Slider Section-->
    <div class="hero-slider-section">
        <!-- Slider main container -->
        <div class="hero-slider-active swiper-container">
            <!-- Additional required wrapper -->
            <div class="swiper-wrapper">
                <!-- Start Hero Single Slider Item -->
                <div class="hero-single-slider-item swiper-slide">
                    <!-- Hero Slider Image -->
                    <div class="hero-slider-bg">
                        <img src="<c:url value='/assets/images/hero-slider/seiko-banner.jpg ' /> " alt="">
                    </div>
                    <!-- Hero Slider Content -->
                    <div class="hero-slider-wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="col-auto">
                                    <div class="hero-slider-content">
                                        <h4 class="subtitle">Seiko</h4>
                                        <h2 class="title">Ưu đãi hấp dẫn <br> Uy tín chất lượng </h2>
                                        <a href="<c:url value='/app-view/shop' />" class="btn btn-lg btn-outline-golden">Khám phá </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- End Hero Single Slider Item -->
                <!-- Start Hero Single Slider Item -->
                <div class="hero-single-slider-item swiper-slide">
                    <!-- Hero Slider Image -->
                    <div class="hero-slider-bg">
                        <img src="<c:url value='/assets/images/hero-slider/orient-banner.jpg' /> " alt="">
                    </div>
                    <!-- Hero Slider Content -->
                    <div class="hero-slider-wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="col-auto">
                                    <div class="hero-slider-content">
                                        <h4 class="subtitle">Kiểu dáng mới</h4>
                                        <h2 class="title">Thiết kế đẳng cấp <br> Sang trọng </h2>
                                        <a href="<c:url value='/app-view/shop' />" class="btn btn-lg btn-outline-golden">Khám phá</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- End Hero Single Slider Item -->
            </div>

            <!-- If we need pagination -->
            <div class="swiper-pagination active-color-golden"></div>

            <!-- If we need navigation buttons -->
            <div class="swiper-button-prev d-none d-lg-block"></div>
            <div class="swiper-button-next d-none d-lg-block"></div>
        </div>
    </div> 
    <!-- End Hero Slider Section-->
    
    <!-- Start Service Section -->
    <div class="service-promo-section section-top-gap-100">
        <div class="service-wrapper">
            <div class="container">
                <div class="row">
                <c:forEach var="item" items="${supplier }"> 
                    <!-- Start Service Promo Single Item -->
                    <div class="col-lg-4 col-sm-6 col-12 supplierLogo">
                    <input name="supplierLogo-id" hidden= "true" value="${item.supplierId }" >
                    <a href="#">
                        <div class="service-promo-single-item" data-aos="fade-up"  data-aos-delay="0">
                           <div class="image" style="width:45%">
                                <img src="<c:url value='/assets/images/company-logo/${item.logo }' /> " alt="">
                           </div>
                            <div class="content">
                                <h6 class="title">
									<c:out value="${item.supplierName }" />
								</h6>
<!--                                 <p>Get 10% cash back, free shipping, free returns, and more at 1000+ top retailers!</p>-->
                            </div>
                        </div>
                      </a>
                   </div>
                </c:forEach>
                    
                </div>
            </div>
        </div>
    </div>
    <!-- End Service Section -->

    <!-- Start Banner Section -->
    <div class="banner-section section-top-gap-100 section-fluid">
        <div class="banner-wrapper">
            <div class="container-fluid">
                <div class="row mb-n6">
                   
                    <div class="col-lg-6 col-12 mb-6">
                         <!-- Start Banner Single Item -->
                        <div class="banner-single-item banner-style-1 banner-animation img-responsive" data-aos="fade-up"  data-aos-delay="0">
                            <div class="image">
                                <img src="<c:url value='/assets/images/logo/bst-dong-ho-cho-nam.jpg' /> " alt="">
                            </div>
                            <div class="content action-link">
                                <h3 class="title" style="color: wheat;">Đồng hồ dành cho nam</h3>
                                <h4><a href="#" class="genderLogo" data-val="Nam" class="btn btn-lg btn-outline-golden icon-space-left"><span class="d-flex align-items-center">Xem ngay <i class="ion-ios-arrow-thin-right"></i></span></a></h4>
                            </div>
                        </div> 
                        <!-- End Banner Single Item -->
                    </div>
                    
                    <div class="col-lg-6 col-12 mb-6">
                        <div class="banner-single-item banner-style-1 banner-animation img-responsive" data-aos="fade-up"  data-aos-delay="0">
                            <div class="image">
                                <img src="<c:url value='/assets/images/logo/bst-danh-cho-nu.jpg' /> " alt="">
                            </div>
                            <div class="content action-link">
                                <h3 class="title" style="color: black;">Đồng hồ dành cho nữ</h3>
                                <h4><a href="#" class="genderLogo" data-val="Nữ" class="btn btn-lg btn-outline-golden icon-space-left"><span class="d-flex align-items-center">Xem ngay <i class="ion-ios-arrow-thin-right"></i></span></a></h4>
                            </div>
                        </div> 
                    </div>
                </div>
                
            </div>
        </div>
    </div>
    <!-- End Banner Section -->

    <!-- Start Product Default Slider Section -->
    <div class="product-default-slider-section section-top-gap-100 section-fluid">
        <!-- Start Section Content Text Area -->
        
        <!-- Start Section Content Text Area -->
        <div class="section-title-wrapper" data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section-content-gap">
                            <div class="secton-content">
                                <h3  class="section-title">Đang giảm giá</h3>
                                <p></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Start Section Content Text Area -->
        <div class="product-wrapper" data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="product-slider-default-1row default-slider-nav-arrow">
                            <!-- Slider main container -->
                            <div class="swiper-container product-default-slider-4grid-1row">
                                <!-- Additional required wrapper -->
                                <div class="swiper-wrapper">
                                 <!-- Sản phẩm giảm giá -->
                                <c:forEach var="item" items="${listSale }">
                                    <div class="product-default-single-item product-color--golden swiper-slide">
                                        <div class="image-box">
                                            <c:url var="url" value="/app-view/viewDetail" >
                                            	<c:param name="id" value="${item.productId }"/>  
                                            </c:url>
                                                <a href="<c:out value="${url}" /> " class="image-link" >
                                                <img src="<c:url value='/assets/images/products/${item.image.trim()}' /> " alt="">
                                             <c:if test="${item.discount!=null&&item.discount>0 }">
                                                <div class="tag">
                                       			 <span>sale</span>
			                                    </div>
			                                 </c:if>
			                                 	</a>
                                            <div class="action-link">
	                                            <div class="action-link-left">
                                                <c:url var="urlAdd" value="/app-view/addToCart" >
                                        			<c:param name="id" value="${item.productId }"/>  
                                        		</c:url>
                                                    <a href="<c:out value='${urlAdd}'/>" data-bs-toggle="modal" data-bs-target="">Thêm vào giỏ</a>
                                                </div>
                                                <div class="action-link-right">
		                                           <c:url var="urlCompare" value="/app-view/compare" >
	                                       			<c:param name="id" value="${item.productId }"/>  
	                                       			</c:url>
	                                            <a href="${urlCompare}" target="_blank" data-toggle="tooltip" data-placement="bottom" title="So sánh giá" >
	                                            	<i class="icon-shuffle"></i>
	                                            </a>
		                                        </div>
                                            </div>
                                        </div>
                                        <div class="content">
                                            <div class="content-left">
                                                <h6 class="title">
                                               	<a href="${url }">
                                               		<c:out value="${item.productName }"></c:out>
                                               	</a>
                                               </h6>
                                                    <ul class="review-star">
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="empty"><i class="ion-android-star"></i></li>
                                                </ul>
                                            </div>
                                            <div class="content-right">
                                            <c:if test="${item.discount!=null&&item.discount>0 }">
						                        <div class="oldPrice price unitPrice " style="color: #929292;">
					                            	<s><i><c:out value="${item.stringUnitPrice }"></c:out></i></s>
					                            </div>
					                            <div class="price discountPrice newPrice" style="color: red;">
					                            	<c:out value="${item.stringDiscountPrice } (-${item.discount }%)"></c:out>
					                            </div>
                       					     </c:if>
                       					     <c:if test="${(item.discount==null||item.discount<=0) }">
	                                                <span class="price">
	                                               	<c:out value="${item.stringUnitPrice }"></c:out>
                                               </span>
                                              </c:if>		
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                </div>
                            </div>
                            <!-- If we need navigation buttons -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 
     <!-- End Product Default Slider Section -->
     <div class="product-default-slider-section section-top-gap-100 section-fluid">
        <!-- Start Section Content Text Area -->
        
        <!-- Start Section Content Text Area -->
        <div class="section-title-wrapper" data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section-content-gap">
                            <div class="secton-content">
                                <h3  class="section-title">Sản phẩm mới</h3>
                                <p></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Start Section Content Text Area -->
        <div class="product-wrapper" data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="product-slider-default-1row default-slider-nav-arrow">
                            <!-- Slider main container -->
                            <div class="swiper-container product-default-slider-4grid-1row">
                                <!-- Additional required wrapper -->
                                <div class="swiper-wrapper">
                             <!-- Sản phẩm mới -->
                                <c:forEach var="item" items="${listNew }">
                                    <div class="product-default-single-item product-color--golden swiper-slide">
                                        <div class="image-box">
                                            <c:url var="url" value="/app-view/viewDetail" >
                                            	<c:param name="id" value="${item.productId }"/>  
                                            </c:url>
                                                <a href="<c:out value="${url}" /> " class="image-link" >
                                                <img src="<c:url value='/assets/images/products/${item.image.trim()}' /> " alt="">
                                             <c:if test="${item.discount!=null&&item.discount>0 }">
                                                <div class="tag">
                                       			 <span>sale</span>
			                                    </div>
			                                 </c:if>
			                                 	</a>
                                            <div class="action-link">
	                                            <div class="action-link-left">
                                                <c:url var="urlAdd" value="/app-view/addToCart" >
                                        			<c:param name="id" value="${item.productId }"/>  
                                        		</c:url>
                                                    <a href="<c:out value='${urlAdd}'/>" data-bs-toggle="modal" data-bs-target="">Thêm vào giỏ</a>
                                                </div>
                                                <div class="action-link-right">
		                                          <c:url var="urlCompare" value="/app-view/compare" >
		                                     			<c:param name="id" value="${item.productId }"/>  
		                                     		</c:url>
		                                          <a href="${urlCompare}" target="_blank" data-toggle="tooltip" data-placement="bottom" title="So sánh giá">
		                                          <i class="icon-shuffle"></i></a>
		                                        </div>
                                            </div>
                                        </div>
                                        <div class="content">
                                            <div class="content-left">
                                                <h6 class="title">
                                               	<a href="${url }">
                                               		<c:out value="${item.productName }"></c:out>
                                               	</a>
                                               </h6>
                                                    <ul class="review-star">
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="empty"><i class="ion-android-star"></i></li>
                                                </ul>
                                            </div>
                                            <div class="content-right">
	                                            <c:if test="${item.discount!=null&&item.discount>0 }">
							                        <div class="oldPrice price unitPrice " style="color: #929292;">
							                           	<s><i><c:out value="${item.stringUnitPrice }"></c:out></i></s>
							                           </div>
							                           <div class="price discountPrice newPrice" style="color: red;">
							                           	<c:out value="${item.stringDiscountPrice } (-${item.discount }%)"></c:out>
							                           </div>
							                 	</c:if>
							                 	<c:if test="${(item.discount==null || item.discount<=0) }">
	                                                <span class="price">
	                                               	<c:out value="${item.stringUnitPrice }"></c:out>
                                               </span>
                                               </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                </div>
                            </div>
                            <!-- If we need navigation buttons -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 
    <!-- Start Banner Section -->
    <div class="banner-section section-top-gap-100">
        <div class="container">
            <div class="row">
                <div class="col-xl-8 offset-xl-2">
                    <!-- Start Banner Single Item -->
                    <div class="banner-single-item banner-style-3 banner-animation img-responsive" data-aos="fade-up"  data-aos-delay="0">
                        <div class="image">
                            <img class="img-fluid" src="assets/images/banner/banner-style-3-img-1.jpg' /> " alt="">
                        </div>
                        <div class="content">
                            <h3 class="title">Modern Furniture
                                Basic Collection</h3>
                            <h5 class="sub-title">We design your home more beautiful</h5>
                            <a href="product-details-default.html" class="btn btn-lg btn-outline-golden icon-space-left"><span class="d-flex align-items-center">discover now <i class="ion-ios-arrow-thin-right"></i></span></a>
                        </div>
                    </div> 
                    <!-- End Banner Single Item -->
                </div>
            </div>
        </div>
    </div>
    <!-- End Banner Section -->
		
                    
    
    <!-- End Product Default Slider Section -->

   <!-- Start Banner Section -->
   <div class="banner-section">
        <div class="banner-wrapper clearfix">
            <!-- Start Banner Single Item -->
            <div class="banner-single-item banner-style-4 banner-animation banner-color--golden float-left img-responsive" data-aos="fade-up"  data-aos-delay="0">
                <div class="image">
                    <img class="img-fluid" src="assets/images/banner/banner-style-4-img-1.jpg' /> " alt="">
                </div>
                <a href="product-details-default.html" class="content">
                    <div class="inner">
                        <h4 class="title">Bar Stool</h4>
                        <h6 class="sub-title">20 products</h6>
                    </div>
                    <span class="round-btn"><i class="ion-ios-arrow-thin-right"></i></span>
                </a>
            </div> 
            <!-- End Banner Single Item -->
            <!-- Start Banner Single Item -->
            <div class="banner-single-item banner-style-4 banner-animation banner-color--golden float-left img-responsive" data-aos="fade-up"  data-aos-delay="200">
                <div class="image">
                    <img class="img-fluid" src="assets/images/banner/banner-style-4-img-2.jpg' /> " alt="">
                </div>
                <a href="product-details-default.html" class="content">
                    <div class="inner">
                        <h4 class="title">Armchairs</h4>
                        <h6 class="sub-title">20 products</h6>
                    </div>
                    <span class="round-btn"><i class="ion-ios-arrow-thin-right"></i></span>
                </a>
            </div> 
            <!-- End Banner Single Item -->
            <!-- Start Banner Single Item -->
            <div class="banner-single-item banner-style-4 banner-animation banner-color--golden float-left img-responsive" data-aos="fade-up"  data-aos-delay="400">
                <div class="image">
                    <img class="img-fluid" src="assets/images/banner/banner-style-4-img-3.jpg' /> " alt="">
                </div>
                <a href="product-details-default.html" class="content">
                    <div class="inner">
                        <h4 class="title">lighting</h4>
                        <h6 class="sub-title">20 products</h6>
                    </div>
                    <span class="round-btn"><i class="ion-ios-arrow-thin-right"></i></span>
                </a>
            </div> 
            <!-- End Banner Single Item -->
            <!-- Start Banner Single Item -->
            <div class="banner-single-item banner-style-4 banner-animation banner-color--golden float-left img-responsive" data-aos="fade-up"  data-aos-delay="600">
                <div class="image">
                    <img class="img-fluid" src="assets/images/banner/banner-style-4-img-4.jpg' /> " alt="">
                </div>
                <a href="product-details-default.html" class="content">
                    <div class="inner">
                        <h4 class="title">Easy chairs</h4>
                        <h6 class="sub-title">20 products</h6>
                    </div>
                    <span class="round-btn"><i class="ion-ios-arrow-thin-right"></i></span>
                </a>
            </div> 
            <!-- End Banner Single Item -->
        </div>
   </div>
   <!-- End Banner Section -->
<%--
   <!-- Start Blog Slider Section -->
   <div class="blog-default-slider-section section-top-gap-100 section-fluid">
        <!-- Start Section Content Text Area -->
        <div class="section-title-wrapper" data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section-content-gap">
                            <div class="secton-content">
                                <h3  class="section-title">THE LATEST BLOGS</h3>
                                <p>Present posts in a best way to highlight interesting moments of your blog.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Start Section Content Text Area -->
        <div class="blog-wrapper" data-aos="fade-up"  data-aos-delay="200">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="blog-default-slider default-slider-nav-arrow">
                            <!-- Slider main container -->
                            <div class="swiper-container blog-slider">
                                <!-- Additional required wrapper -->
                                <div class="swiper-wrapper">
                                    <!-- Start Product Default Single Item -->
                                    <div class="blog-default-single-item blog-color--golden swiper-slide">
                                        <div class="image-box">
                                            <a href="blog-single-sidebar-left.html" class="image-link">
                                                <img class="img-fluid" src="assets/images/blog/blog-grid-home-1-img-1.jpg' /> " alt="">
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h6 class="title"><a href="blog-single-sidebar-left.html">Blog Post One</a></h6>
                                            <p>Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus. Praesent</p>
                                            <div class="inner">
                                                <a href="blog-single-sidebar-left.html" class="read-more-btn icon-space-left">Read More <span><i class="ion-ios-arrow-thin-right"></i></span></a>
                                                <div class="post-meta">
                                                    <a href="#" class="date">24 Apr</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Product Default Single Item -->
                                    <!-- Start Product Default Single Item -->
                                    <div class="blog-default-single-item blog-color--golden swiper-slide">
                                        <div class="image-box">
                                            <a href="blog-single-sidebar-left.html" class="image-link">
                                                <img class="img-fluid" src="assets/images/blog/blog-grid-home-1-img-2.jpg' /> " alt="">
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h6 class="title"><a href="blog-single-sidebar-left.html">Blog Post Two</a></h6>
                                            <p>Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus. Praesent</p>
                                            <div class="inner">
                                                <a href="#" class="read-more-btn icon-space-left">Read More <span><i class="ion-ios-arrow-thin-right"></i></span></a>
                                                <div class="post-meta">
                                                    <a href="blog-single-sidebar-left.html" class="date">24 Apr</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Product Default Single Item -->
                                    <!-- Start Product Default Single Item -->
                                    <div class="blog-default-single-item blog-color--golden swiper-slide">
                                        <div class="image-box">
                                            <a href="blog-single-sidebar-left.html" class="image-link">
                                                <img class="img-fluid" src="assets/images/blog/blog-grid-home-1-img-3.jpg' /> " alt="">
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h6 class="title"><a href="blog-single-sidebar-left.html">Blog Post Three</a></h6>
                                            <p>Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus. Praesent</p>
                                            <div class="inner">
                                                <a href="blog-single-sidebar-left.html" class="read-more-btn icon-space-left">Read More <span><i class="ion-ios-arrow-thin-right"></i></span></a>
                                                <div class="post-meta">
                                                    <a href="#" class="date">24 Apr</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Product Default Single Item -->
                                    <!-- Start Product Default Single Item -->
                                    <div class="blog-default-single-item blog-color--golden swiper-slide">
                                        <div class="image-box">
                                            <a href="blog-single-sidebar-left.html" class="image-link">
                                                <img class="img-fluid" src="assets/images/blog/blog-grid-home-1-img-4.jpg' /> " alt="">
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h6 class="title"><a href="blog-single-sidebar-left.html">Blog Post Four</a></h6>
                                            <p>Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus. Praesent</p>
                                            <div class="inner">
                                                <a href="blog-single-sidebar-left.html" class="read-more-btn icon-space-left">Read More <span><i class="ion-ios-arrow-thin-right"></i></span></a>
                                                <div class="post-meta">
                                                    <a href="#" class="date">24 Apr</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Product Default Single Item -->
                                    <!-- Start Product Default Single Item -->
                                    <div class="blog-default-single-item blog-color--golden swiper-slide">
                                        <div class="image-box">
                                            <a href="blog-single-sidebar-left.html" class="image-link">
                                                <img class="img-fluid" src="assets/images/blog/blog-grid-home-1-img-5.jpg' /> " alt="">
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h6 class="title"><a href="blog-single-sidebar-left.html">Blog Post Five</a></h6>
                                            <p>Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus. Praesent</p>
                                            <div class="inner">
                                                <a href="blog-single-sidebar-left.html" class="read-more-btn icon-space-left">Read More <span><i class="ion-ios-arrow-thin-right"></i></span></a>
                                                <div class="post-meta">
                                                    <a href="#" class="date">24 Apr</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Product Default Single Item -->
                                    <!-- Start Product Default Single Item -->
                                    <div class="blog-default-single-item blog-color--golden swiper-slide">
                                        <div class="image-box">
                                            <a href="blog-single-sidebar-left.html" class="image-link">
                                                <img class="img-fluid" src="assets/images/blog/blog-grid-home-1-img-6.jpg' /> " alt="">
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h6 class="title"><a href="blog-single-sidebar-left.html">Blog Post Six</a></h6>
                                            <p>Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus. Praesent</p>
                                            <div class="inner">
                                                <a href="blog-single-sidebar-left.html" class="read-more-btn icon-space-left">Read More <span><i class="ion-ios-arrow-thin-right"></i></span></a>
                                                <div class="post-meta">
                                                    <a href="#" class="date">24 Apr</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Product Default Single Item -->
                                </div>
                            </div>
                            <!-- If we need navigation buttons -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </div> 
   <!-- End Blog Slider Section -->
--%>
   <!-- Start Instagramr Section -->
 <%-->  <div class="instagram-section section-top-gap-100 section-inner-bg">
       <div class="instagram-wrapper" data-aos="fade-up"  data-aos-delay="0">
           <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="instagram-box">
                            <div id="instagramFeed" class="instagram-grid clearfix">
                                <a href="https://www.instagram.com/p/CCFOZKDDS6S/" target="_blank" class="instagram-image-link float-left banner-animation"><img src="<c:url value='/assets/images/instagram/instagram-1.jpg' /> " alt=""></a>
                                <a href="https://www.instagram.com/p/CCFOYDNjWF5/" target="_blank" class="instagram-image-link float-left banner-animation"><img src="<c:url value='/assets/images/instagram/instagram-2.jpg' /> " alt=""></a>
                                <a href="https://www.instagram.com/p/CCFOXH6D-zQ/" target="_blank" class="instagram-image-link float-left banner-animation"><img src="<c:url value='/assets/images/instagram/instagram-3.jpg' /> " alt=""></a>
                                <a href="https://www.instagram.com/p/CCFOVcrDDOo/" target="_blank" class="instagram-image-link float-left banner-animation"><img src="<c:url value='/assets/images/instagram/instagram-4.jpg' /> " alt=""></a>
                                <a href="https://www.instagram.com/p/CCFOUajjABP/" target="_blank" class="instagram-image-link float-left banner-animation"><img src="<c:url value='/assets/images/instagram/instagram-5.jpg' /> " alt=""></a>
                                <a href="https://www.instagram.com/p/CCFOS2MDmjj/" target="_blank" class="instagram-image-link float-left banner-animation"><img src="<c:url value='/assets/images/instagram/instagram-6.jpg' /> " alt=""></a>
                            </div>
                            <div class="instagram-link">
                                <h5><a href="https://www.instagram.com/myfurniturecom/" target="_blank" rel="noopener noreferrer">HONOTEMPLATE</a></h5>
                            </div>
                        </div>
                    </div>
                </div>
           </div>
       </div>
   </div>
   <!-- End Instagramr Section -->
--%>