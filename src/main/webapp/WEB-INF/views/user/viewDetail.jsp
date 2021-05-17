<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@include file="/common/taglib.jsp"%>

<div class="breadcrumb-section breadcrumb-bg-color--golden">
        <div class="breadcrumb-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3 class="breadcrumb-title">Chi tiết sản phẩm</h3>
                        <div class="breadcrumb-nav breadcrumb-nav-color--black breadcrumb-nav-hover-color--golden">
                            <nav aria-label="breadcrumb">
                                <ul>
                                    <li><a href="<c:url value="/app-view/shop" />">Cửa hàng</a></li>
                                    <!-- <li><a href="shop-grid-sidebar-left.html">Shop</a></li> -->
                                    <li class="active" aria-current="page">Chi tiết sản phẩm</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Breadcrumb Section:::... -->

    <!-- Start Product Details Section -->
    <div class="product-details-section">
        <div class="container">
            <div class="row">
                <div class="col-xl-5 col-lg-6">
                    <div class="product-details-gallery-area" data-aos="fade-up"  data-aos-delay="0">
                        <!-- Start Large Image -->
                        <div class="product-large-image product-large-image-horaizontal swiper-container">
                                <div class="swiper-wrapper">
                                    <div class="product-image-large-image swiper-slide zoom-image-hover img-responsive">
                                        <img src="<c:url value='/assets/images/products/${dataSelected.image}' /> " alt="" >
                                    </div>
                                    <%--
                                    <div class="product-image-large-image swiper-slide zoom-image-hover img-responsive">
                                        <img src="<c:url value='/assets/images/product/default/home-1/default-2.jpg' /> " alt="">
                                    </div>
                                    <div class="product-image-large-image swiper-slide zoom-image-hover img-responsive">
                                        <img src="<c:url value='/assets/images/product/default/home-1/default-3.jpg' /> " alt="">
                                    </div>
                                    <div class="product-image-large-image swiper-slide zoom-image-hover img-responsive">
                                        <img src="<c:url value='/assets/images/product/default/home-1/default-4.jpg'/> " alt="">
                                    </div>
                                    <div class="product-image-large-image swiper-slide zoom-image-hover img-responsive">
                                        <img src="<c:url value='assets/images/product/default/home-1/default-5.jpg'/> " alt="">
                                    </div>
                                    <div class="product-image-large-image swiper-slide zoom-image-hover img-responsive">
                                        <img src="<c:url value='/assets/images/product/default/home-1/default-6.jpg'/> " alt="">
                                    </div> 
                                    --%>
                                </div>
                        </div>
                        <!-- End Large Image -->
                         <!-- Start Thumbnail Image -->
                         <div class="product-image-thumb product-image-thumb-horizontal swiper-container pos-relative mt-5">
                               <!-- <div class="swiper-wrapper">
                                    <div class="product-image-thumb-single swiper-slide">
                                        <img class="img-fluid" src="<c:url value='/assets/images/product/default/home-1/default-1.jpg'/> " alt="">
                                    </div>
                                    <div class="product-image-thumb-single swiper-slide">
                                        <img class="img-fluid" src="<c:url value='/assets/images/product/default/home-1/default-2.jpg'/> " alt="">
                                    </div>
                                    <div class="product-image-thumb-single swiper-slide">
                                        <img class="img-fluid" src="<c:url value='/assets/images/product/default/home-1/default-3.jpg'/> " alt="">
                                    </div>
                                    <div class="product-image-thumb-single swiper-slide">
                                        <img class="img-fluid" src="<c:url value='/assets/images/product/default/home-1/default-4.jpg'/> " alt="">
                                    </div>
                                    <div class="product-image-thumb-single swiper-slide">
                                        <img class="img-fluid" src="<c:url value='/assets/images/product/default/home-1/default-5.jpg'/> " alt="">
                                    </div>
                                    <div class="product-image-thumb-single swiper-slide">
                                        <img class="img-fluid" src="<c:url value='/assets/images/product/default/home-1/default-6.jpg'/> " alt="">
                                    </div>
                            </div>
                             Add Arrows 
                            <div class="gallery-thumb-arrow swiper-button-next"></div>
                            <div class="gallery-thumb-arrow swiper-button-prev"></div>
                            -->
                        </div> 
                         <!-- End Thumbnail Image -->
                    </div>
                </div>
                <div class="col-xl-7 col-lg-6">
                    <div class="product-details-content-area product-details--golden" data-aos="fade-up"  data-aos-delay="200">
                        <!-- Start  Product Details Text Area-->
                        <div class="product-details-text">
                            <h4 class="title">
                            <c:out value="${dataSelected.productName }"></c:out>
                            </h4>
                            <!-- <div class="d-flex align-items-center">
                                <ul class="review-star">
                                    <li class="fill"><i class="ion-android-star"></i></li>
                                    <li class="fill"><i class="ion-android-star"></i></li>
                                    <li class="fill"><i class="ion-android-star"></i></li>
                                    <li class="fill"><i class="ion-android-star"></i></li>
                                    <li class="empty"><i class="ion-android-star"></i></li>
                                </ul>
                                <a href="#" class="customer-review ml-2">(customer review )</a>
                            </div> -->
                            
                            <c:if test="${dataSelected.discount > 0}">
		                        <div class="oldPrice price unitPrice " style="color: #929292;">
	                            	<s><i>₫<c:out value="${dataSelected.stringUnitPrice }"></c:out></i></s>
	                            </div>
	                            <div class="price discountPrice newPrice" style="color: red;">₫
	                            	<c:out value="${dataSelected.stringDiscountPrice } (-${dataSelected.discount }%)"></c:out>
	                            	 
	                            </div>
	                            <!-- Khuyen mai -->
	                            <hr>
	                             <h4 class="title" style="font-size:18px;"><b>Giảm giá</b></h4>
	                             <br>
	                            	 <c:forEach var="dis" items="${dataSelected.lstDiscount }">
	                            	 
		                                <div class="product-stock"> <span class="product-stock-in"><i class="ion-checkmark-circled"></i> </span>
			                            	 <c:out value="${dis.description }: " />
			                            	 <span  style="color: red;"><c:out value ="-${dis.discount }% "></c:out> <span>
		                            	 </div>
	                            	 </c:forEach>
	                            
                            </c:if>
                            <c:if test="${dataSelected.discount == null || dataSelected.discount <= 0}">
		                        <div class="price unitPrice newPrice" style="color: red;">₫ 
	                            	<c:out value="${dataSelected.stringUnitPrice }"></c:out>
	                            </div>
	                            <!-- <div class="price discountPrice">$ 
	                            	<out value="${dataSelected.stringDiscountPrice }
	                            </div> -->
                            </c:if>
                            
                            <p>
                            	<c:out value="${ dataSelected.description!=null?dataSelected.description:''}"></c:out>
                            </p>
                        </div> <!-- End  Product Details Text Area-->
                        <!-- Start Product Variable Area -->
                        <div class="product-details-variable">
                            <h4 class="title">Tình trạng hàng</h4>
                            <!-- Product Variable Single Item -->
                            <c:if test="${dataSelected.quantity > 0 }">
	                            <div class="variable-single-item">
	                                <div class="product-stock"> <span class="product-stock-in"><i class="ion-checkmark-circled"></i> </span>
	                               <%--<c:out value="${dataSelected.quantity }"></c:out> --%>Còn hàng
	                            </div>                            
                            </c:if>
                            <c:if test="${dataSelected.quantity <= 0 }">
	                            <div class="variable-single-item">
	                                <div class="product-stock"> <span class="product-stock-out"><i class="ion-checkmark-circled"></i> </span>
	                                <s style="color: red;"> Hết hàng </s> </div>
	                            </div>                            
                            </c:if>
                            
                            
                            <br>
                            <hr>
                            <!-- Product Variable Single Item -->
                            <form:form action="addToCart" method="POST" modelAttribute="prodSelected">
                            <div class="d-flex align-items-center ">
                                <div class="variable-single-item ">
                                	<form:hidden path="productId" value="${dataSelected.productId }" />
                                    <span>Số lượng</span>
                                    <div class="product-variable-quantity">
                                        <form:input path="cartQuantity" min="1" max="${dataSelected.quantity }" value="1" type="number" />
                                    </div>
                                </div>
								<form:button type="submit">
                                <div class="product-add-to-cart-btn">
                                    <div class="btn btn-block btn-lg btn-black-default-hover" data-bs-toggle="modal" data-bs-target="">+ Thêm vào giỏ</div>
                                </div>
                                </form:button>
                            </div>
                            </form:form>
                            <!-- Start  Product Details Meta Area
                            <div class="product-details-meta mb-20">
                                <a href="wishlist.html" class="icon-space-right"><i class="icon-heart"></i>Add to wishlist</a>
                                <a href="compare.html" class="icon-space-right"><i class="icon-refresh"></i>Compare</a>
                            </div> <!-- End  Product Details Meta Area-->
                        </div> <!-- End Product Variable Area -->
                        
                        <!-- Start  Product Details Catagories Area
                        <div class="product-details-catagory mb-2">
                            <span class="title">CATEGORIES:</span>
                            <ul>
                                <li><a href="#">BAR STOOL</a></li>
                                <li><a href="#">KITCHEN UTENSILS</a></li>
                                <li><a href="#">TENNIS</a></li>
                            </ul>
                        </div> End  Product Details Catagories Area-->
                        <!-- Start  Product Details Social Area
                        <div class="product-details-social">
                            <span class="title">SHARE THIS PRODUCT:</span>
                            <ul>
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div> End  Product Details Social Area-->
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Product Details Section -->

    <!-- Start Product Content Tab Section -->
    <div class="product-details-content-tab-section section-top-gap-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="product-details-content-tab-wrapper" data-aos="fade-up"  data-aos-delay="0">

                        <!-- Start Product Details Tab Button -->
                        <ul class="nav tablist product-details-content-tab-btn d-flex justify-content-center">
                            <li><a class="nav-link active" data-bs-toggle="tab" href="#specification" >
                                    Thông số kỹ thuật
                                </a></li>
                            <li><a class="nav-link" data-bs-toggle="tab" href="#description" >
                                    Mô tả
                                </a></li>
                            <li><a class="nav-link" data-bs-toggle="tab" href="#review">
                                    Đánh giá(<c:out value="${listComment!=null?listComment.size():0}"></c:out>)
                                </a></li>
                        </ul> <!-- End Product Details Tab Button -->

                        <!-- Start Product Details Tab Content -->
                        <div class="product-details-content-tab">
                            <div class="tab-content">
                                
                                <!-- Tab chi tiet -->
                                <div class="tab-pane active show" id="specification">
                                    <div class="single-tab-content-item">
                                        <table class="table table-bordered mb-20">
                                            <tbody>
                                            	<c:if test="${dataSelected.gender!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Giới tính                                                    
                                                    </th>
                                                    <td> <c:out value="${dataSelected.gender }"></c:out></td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.face.waterProof!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Độ chịu nước                                                   
                                                    </th>
                                                    <td> <c:out value="${dataSelected.face.waterProof }"></c:out> m</td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.face.thickness!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Độ dày mặt                                                  
                                                    </th>
                                                    <td> <c:out value="${dataSelected.face.thickness }"></c:out> mm</td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.face.faceSize!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Kích cỡ                                                
                                                    </th>
                                                    <td> <c:out value="${dataSelected.face.faceSize }"></c:out> mm</td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.supplier.supplierName!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Nhãn hiệu                                                
                                                    </th>
                                                    <td> <c:out value="${dataSelected.supplier.supplierName }"></c:out></td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.supplier.location!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Quốc gia                                               
                                                    </th>
                                                    <td> <c:out value="${dataSelected.supplier.location }"></c:out></td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.machine.machineName!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Kiểu máy                                               
                                                    </th>
                                                    <td> <c:out value="${dataSelected.machine.machineName }"></c:out></td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.strap.materialStrap!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Chất liệu dây                                             
                                                    </th>
                                                    <td> <c:out value="${dataSelected.strap.materialStrap }"></c:out></td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.face.glass!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Chất liệu kính                                            
                                                    </th>
                                                    <td> <c:out value="${dataSelected.face.glass }"></c:out></td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.material!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Chất liệu vỏ                                        
                                                    </th>
                                                    <td> <c:out value="${dataSelected.material }"></c:out></td>
                                                </tr>
                                                </c:if>
                                                
                                                <c:if test="${dataSelected.otherFunc!=null}">
                                                <tr>
                                                    <th scope="row">                                                    	
                                                    	Chức năng khác                                     
                                                    </th>
                                                    <td> <c:out value="${dataSelected.otherFunc }"></c:out></td>
                                                </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
                                      </div>
                                </div> <!-- End Product Details Tab Content Singel -->
                                <!-- Start Product Details Tab Content Singel -->
                                <!-- Descrip tion tab -->
                                <div class="tab-pane " id="description">
                                    <div class="single-tab-content-item">
                                        
                                        <c:out value="${dataSelected.description }"></c:out>
                                        
                                    </div>
                                </div> <!-- End Product Details Tab Content Singel -->
                                <!-- Tab Comment -->
                                <div class="tab-pane" id="review">
                                    <div class="single-tab-content-item">
                                        <!-- Start - Review Comment -->
                                        <ul class="comment">
                                            <!-- Start - Review Comment list-->
                                            <c:forEach var="item" items="${listComment }"> 
                                            <li class="comment-list">
                                                <div class="comment-wrapper">
                                                    <div class="comment-img">
                                                        <img class="cmt-avatar" src="<c:url value='/assets/images/user/${item.user.avatar }'/> " alt="">
                                                    </div>
                                                    <div class="comment-content">
                                                        <div class="comment-content-top">
                                                            <div class="comment-content-left">
                                                                <h6 class="comment-name">
                                                                	<c:out value="${item.user.fullName!=null?item.user.fullName:item.user.userName }"></c:out>
                                                                </h6>
                                                                <ul class="review-star">
                                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                                    <li class="empty"><i class="ion-android-star"></i></li>
                                                                </ul>
                                                            </div>
<!--                                                             <div class="comment-content-right">
                                                                <a href="#"><i class="fa fa-reply"></i>Reply</a>
                                                            </div>-->
                                                        </div>

                                                        <div class="para-content">
                                                            <p><c:out value="${item.content }"></c:out> </p>
                                                        </div>
                                                        
                                                    </div>
                                                 
                                                </div>
                                                <!-- Start - Review Comment Reply-->
                                                <ul class="comment-reply">
                                                 <c:forEach var="child" items="${item.listCmtReply }">
                                                    <li class="comment-reply-list">
                                                        <div class="comment-wrapper">
                                                            <div class="comment-img">
                                                                <img class="cmt-avatar" src="<c:url value='/assets/images/user/${child.user.avatar }'/> " alt="">
                                                            </div>
                                                            <div class="comment-content">
                                                                <div class="comment-content-top">
                                                                    <div class="comment-content-left">
                                                                        <h6 class="comment-name">
                                                                        <c:out value="${child.user.fullName!=null?child.user.fullName:child.user.userName }"></c:out>
                                                                        </h6>
                                                                    </div>
                                                                </div>

                                                                <div class="para-content">
                                                                <p>
                                                               	 	<c:out value="${child.content }"></c:out>
                                                                </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    </c:forEach>
                                                </ul> <!-- End - Review Comment Reply-->
                                                <!-- Repply comment -->
                                                <hr>
                                                <div class="inp-reply" >
                                                <c:if test="${sessionScope.user != null }">
	                                               	<form:form action="replyComment" method="POST" modelAttribute="comment">
	                                               	<c:set var ="u" value="${sessionScope.user }"></c:set>
	                                               		<form:hidden path="productId" value="${dataSelected.productId }"/>
	                                                   	<form:hidden path="userId" value="${u.userId }"/>
	                                                   	<form:hidden path="parentId" value="${item.commentId }"/>
	                                                   	<div class="" style="display: flex;">
	                                                   	<form:textarea rows="1" id="comment-review-text" placeholder="Trả lời ${item.user.fullName!=null?item.user.fullName:item.user.userName }" path="content" required="required" />
                                                        <input class="btn btn-md btn-black-default-hover btn-reply" type="submit" value="Gửi">
                                                    	</div>
	                                               	</form:form>
	                                               	</c:if>
                                               </div>
                                            </li>
                                            </c:forEach> <!-- End - Review Comment list-->
                                            
                                        </ul> <!-- End - Review Comment -->
                                        <hr>
                                        
                                        <div class="review-form">
                                            <c:if test="${sessionScope.user == null }">
                                            <div class="review-form-text-top">
                                                <h4>Đăng nhập để viết đánh giá của bạn.</h4>
                                                
                                            </div>
                                            <div class="col-12">
                                                	<a href="<c:url value='/app-view/login' /> " >
                                                        <input class="btn btn-md btn-black-default-hover" value="Đăng nhập" >
                                                    </a>
                                                 </div>
                                            </c:if>
                                        
                                        
                                        <c:if test="${sessionScope.user != null }">
                                            <div class="review-form-text-top">
                                                <h5>Viết đánh giá</h5>
                                                
                                            </div>
							
                                            <form:form action="comment" method="post" modelAttribute="comment" >
                                                <div class="row">
                                                    
                                                    <div class="col-12">
                                                        <div class="default-form-box">
                                                            <label for="comment-review-text">Đánh giá của bạn<span>*</span></label>
                                                            <form:textarea id="comment-review-text" placeholder="Viết đánh giá" path="content" required="required" />
                                                            
                                                            <form:hidden path="productId" value="${dataSelected.productId }"/>
                                                            <form:hidden path="userId" value="${sessionScope.user.userId }"/>
                                                            
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <button class="btn btn-md btn-black-default-hover" type="submit">Gửi</button>
                                                    </div>
                                                </div>
                                            </form:form>
                              </c:if>
                                        </div>
                                    </div>
                                </div> <!-- End Product Details Tab Content Singel -->
                            </div>
                        </div> <!-- End Product Details Tab Content -->

                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Product Content Tab Section -->
<div class="product-default-slider-section section-top-gap-100 section-fluid">
        <!-- Start Section Content Text Area -->
        <div class="section-title-wrapper" data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="row flex-column-reverse flex-lg-row">
	                    <div class="col-3">
	                    <!-- SAN PHAM LIEN QUAN -->  
	                            <div class="secton-content">
	                                <h3 class="section-title">Sản phẩm liên quan</h3>
	                                
	                            </div>
		                    
		                                
	                    </div>
                    <div class= "col-9">
				    <!-- Start Product Default Slider Section -->
				          
				        <!-- Start Section Content Text Area -->
				      <!-- Start Section Content Text Area -->
	                    <div class="blog-default-slider default-slider-nav-arrow">
	                        <!-- Slider main container -->
	                        <div class="swiper-container blog-slider">
	                            <!-- Additional required wrapper -->
	                            <div class="swiper-wrapper">
	                                <!-- Start Product Default Single Item -->
	                    <!-- Sản phẩm gợi ý -->
	                              <c:forEach var="item" items="${listSuggest }">
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
	                                           <!--  <a href="#" data-bs-toggle="modal" data-bs-target="#modalQuickview"><i class="icon-magnifier"></i></a>
	                                            <a href="wishlist.html"><i class="icon-heart"></i></a>
	                                            <a href="compare.html"><i class="icon-shuffle"></i></a>-->
	                                        </div>
	                                    </div>
	                                    </div>
	                                    <div class="content">
	                                        <b><h7 class="title"><a href="${url}">
												<c:out value="${item.productName }" />                                        
	                                        </a></h7></b>
	                                        <!-- <p>Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus. Praesent</p>
	                                        -->
	                                        <div class="inner">
	                                           <!--  <a href="blog-single-sidebar-left.html" class="read-more-btn icon-space-left">Read More <span><i class="ion-ios-arrow-thin-right"></i></span></a>
	                                            -->
	                                            <div class="post-meta">
	                                                <c:if test="${item.discount!=null&&item.discount>0 }">
						                        <div class="oldPrice price unitPrice " style="color: #929292;">
					                            	<s><i><c:out value="${dataSelected.stringUnitPrice }"></c:out></i></s>
					                            </div>
					                            <div class="price discountPrice newPrice" style="color: red;">
					                            	<c:out value="${dataSelected.stringDiscountPrice } (-${dataSelected.discount }%)"></c:out>
					                            </div>
                       					     </c:if>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                                <!-- End Product Default Single Item -->
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
</div>
 