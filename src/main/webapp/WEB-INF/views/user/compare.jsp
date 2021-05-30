<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@include file="/common/taglib.jsp"%>

<div class="breadcrumb-section breadcrumb-bg-color--golden">
        <div class="breadcrumb-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3 class="breadcrumb-title">So sánh giá</h3>
                        <div class="breadcrumb-nav breadcrumb-nav-color--black breadcrumb-nav-hover-color--golden">
                            <nav aria-label="breadcrumb">
                                <ul>
                                    <li><a href="<c:url value="/app-view/shop" />">Cửa hàng</a></li>
                                    <!-- <li><a href="shop-grid-sidebar-left.html">Shop</a></li> -->
                                    <li class="active" aria-current="page">So sánh giá</li>
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
                                        <img src="<c:url value='/assets/images/products/${dataSelected.product.image}' /> " alt="" >
                                    </div>
                                  
                                </div>
                        </div>
                        <!-- End Large Image -->
                         <!-- Start Thumbnail Image -->
                         <div class="product-image-thumb product-image-thumb-horizontal swiper-container pos-relative mt-5">
                           
                        </div> 
                         <!-- End Thumbnail Image -->
                    </div>
                </div>
                <div class="col-xl-7 col-lg-6">
                    <div class="product-details-content-area product-details--golden" data-aos="fade-up"  data-aos-delay="200">
                        <!-- Start  Product Details Text Area-->
                        <div class="product-details-text">
                            <h4 class="title">
                            <c:out value="${dataSelected.product.productName }"></c:out>
                            </h4>
                            
                            <c:if test="${dataSelected.product.discount > 0}">
		                        <div class="oldPrice price unitPrice " style="color: #929292;">
	                            	<s><i>₫<c:out value="${dataSelected.product.stringUnitPrice }"></c:out></i></s>
	                            </div>
	                            <div class="price discountPrice newPrice" style="color: red;">₫
	                            	<c:out value="${dataSelected.product.stringDiscountPrice } (-${dataSelected.product.discount }%)"></c:out>
	                            	 
	                            </div>
	                            <!-- Khuyen mai -->
	                            <hr>
	                             <h4 class="title" style="font-size:18px;"><b>Giảm giá</b></h4>
	                             <br>
	                            	 <c:forEach var="dis" items="${dataSelected.product.lstDiscount }">
	                            	 
		                                <div class="product-stock"> <span class="product-stock-in"><i class="ion-checkmark-circled"></i> </span>
			                            	 <c:out value="${dis.description }: " />
			                            	 <span  style="color: red;"><c:out value ="-${dis.discount }% "></c:out> <span>
		                            	 </div>
	                            	 </c:forEach>
	                            
                            </c:if>
                            <c:if test="${dataSelected.product.discount == null || dataSelected.product.discount <= 0}">
		                        <div class="price unitPrice newPrice" style="color: red;">₫ 
	                            	<c:out value="${dataSelected.product.stringUnitPrice }"></c:out>
	                            </div>
	                            <!-- <div class="price discountPrice">$ 
	                            	<out value="${dataSelected.product.stringDiscountPrice }
	                            </div> -->
                            </c:if>
                            
                            <p>
                            	<c:out value="${ dataSelected.product.description!=null?dataSelected.product.description:''}"></c:out>
                            </p>
                        </div> <!-- End  Product Details Text Area-->
                        <!-- Start Product Variable Area -->
                        <div class="product-details-variable">
                            <h4 class="title">Tình trạng hàng</h4>
                            <!-- Product Variable Single Item -->
                            <c:if test="${dataSelected.product.quantity > 0 }">
	                            <div class="variable-single-item">
	                                <div class="product-stock"> <span class="product-stock-in"><i class="ion-checkmark-circled"></i> </span>
	                               <%--<c:out value="${dataSelected.product.quantity }"></c:out> --%>Còn hàng
	                            </div>                            
                            </c:if>
                            <c:if test="${dataSelected.product.quantity <= 0 }">
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
                                	<form:hidden path="productId" value="${dataSelected.product.productId }" />
                                    <span>Số lượng</span>
                                    <div class="product-variable-quantity">
                                        <form:input path="cartQuantity" min="1" max="${dataSelected.product.quantity }" value="1" type="number" />
                                    </div>
                                </div>
								<form:button type="submit">
                                <div class="product-add-to-cart-btn">
                                    <div class="btn btn-block btn-lg btn-black-default-hover" data-bs-toggle="modal" data-bs-target="">+ Thêm vào giỏ</div>
                                </div>
                                </form:button>
                            </div>
                            </form:form>
                        </div> <!-- End Product Variable Area -->
                        
                    </div>
                </div>
            </div>
            
         <hr>
         <div style="text-align: center;margin: 4%;">
         	<h3 class="breadcrumb-title">Sản phẩm tương tự</h3>
         </div>
         <hr>   
         <div class="single-tab-content-item" style="margin: 5%;">
         <!-- Start - Review Comment -->
         <ul class="comment">
             <!-- Start - Review Comment list-->
             <c:forEach var="item" items="${dataList }"> 
             <li class="comment-list">
                 <div class="comment-wrapper">
                     <div class="comment-img">
                         <img class="" style="width: 150px;height: 150px;object-fit: cover;" src="${item.img_link }" alt="">
                     </div>
                     <div class="comment-content" style="width:90%;">
	                     <h3 class="title" style="width:80%;text-indent:5%;">
	                     <a href="${item.url }" target="_blank">
	                          <c:out value="${item.productName }"></c:out>
	                      </a>
	                      </h3>
	                      <div class="post-meta" style="text-indent: 5%;font-size: 20px;">
                          <c:if test="${item.discountPrice >0 }">
	                        	<div class="oldPrice price unitPrice " style="color: #929292;">
	                           		<s><i><c:out value="${item.stringUnitPrice }"></c:out></i></s>
	                           </div>
	                           <div class="price discountPrice newPrice" style="color: red;">
	                           		<c:out value="${item.stringDiscountPrice }"></c:out>
	                           </div>
                 		</c:if>
                 		<c:if test="${item.discountPrice == 0 }">
	                        	<div class="oldPrice price unitPrice " >
	                           		<c:out value="${item.stringUnitPrice }"></c:out>
	                           </div>
	                          
                 		</c:if>
                          </div>
                     </div>
                     <div class="comment-content">
	                     <h3 class="title" style="text-indent:5%;">
	                     <a href="${item.domainString }">
	                          <c:out value="${item.domain }"></c:out>
	                      </a>
	                      </h3>
                     </div>
                </div>
             </li>
             </c:forEach> <!-- End - Review Comment list-->
         </ul> <!-- End - Review Comment -->
         <hr>
         
     </div>
        
         <div class= "col-3">
         	<div class="secton-content">
               <h3 class="section-title">Có thể bạn quan tâm</h3>
           </div>
         </div>
        
        
     <div class= "col-9">
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
                                       <a href="wishlist.html"><i class="icon-heart"></i></a>-->
                                        <c:url var="urlCompare" value="/app-view/compare" >
                                  			<c:param name="id" value="${item.productId }"/>  
                                  		</c:url>
                                       <a href="${urlCompare}" data-toggle="tooltip" data-placement="bottom" title="So sánh giá">
                                       	<i class="icon-shuffle"></i>
                                       </a>
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
    </div> <!-- End Product Details Section -->
    
    
    
    
    
    
    
    
    