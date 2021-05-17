<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@include file="/common/taglib.jsp"%>

   <div class="breadcrumb-section breadcrumb-bg-color--golden">
        <div class="breadcrumb-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3 class="breadcrumb-title">Trang chủ - Cửa hàng</h3>
                        <div class="breadcrumb-nav breadcrumb-nav-color--black breadcrumb-nav-hover-color--golden">
                            <nav aria-label="breadcrumb">
                                <ul>
                                    <li><a href="<c:url value='/app-view/home-page' />">Trang chủ</a></li>
                                    <li><a href="<c:url value='/app-view/' />">Cửa hàng</a></li>
                                    <!-- <li class="active" aria-current="page">Shop Grid Left Sidebar</li>-->
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Breadcrumb Section:::... -->

    <!-- ...:::: Start Shop Section:::... -->
    <div class="shop-section">
        <div class="container">
            <div class="row flex-column-reverse flex-lg-row">
                <div class="col-lg-3">
                    <!-- Start Sidebar Area -->
                    <div class="siderbar-section" data-aos="fade-up"  data-aos-delay="0">
				<%--
                        <!-- Start Single Sidebar Widget -->
                        <div class="sidebar-single-widget" >
                            <h6 class="sidebar-title">Thương hiệu</h6>
                            <div class="sidebar-content">
                                <ul class="sidebar-menu">
                                    
                                <c:forEach var = "sup" items = "${ supplier }" >
                                    
                                   <li ><a href="<c:url value='#' /> "><c:out value="${sup.supplierName}" /></a></li>   
                                   
                                </c:forEach>
                                </ul>
                            </div>
                        </div>--%> <!-- End Single Sidebar Widget -->
						
						<div class="sidebar-single-widget">
                            <h6 class="sidebar-title">Thương hiệu</h6>
                            <div class="sidebar-content">
                                <div class="filter-type-select">
                                    <ul>
                              	<c:forEach var = "sup" items="${ supplier }">
                                        <li>
                                            <label class="checkbox-default" for="brakeParts">
                                                <input type="checkbox" value = "${ sup.supplierId } " name="supplierId" class="advSearch"
                                                <c:if test="${mapSearch.size()>0&& mapSearch.get('supplierId')!=null &&  mapSearch.get('supplierId').contains(sup.supplierId) }">
                                                 	<c:out value="checked" />
                                                 </c:if>
                                                >
                                                <span><c:out value = "${ sup.supplierName } " /></span>
                                            </label>
                                        </li>
                                </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- Start Single Sidebar Widget -->
                        <div class="sidebar-single-widget">
                            <h6 class="sidebar-title">Mức giá</h6>
                            <div class="sidebar-content">
                                <div id="slider-range" class= "advSearch"></div>
                                <div class="filter-type-price" class="advSearch">
                                    <label for="amount">Khoảng giá:</label>
                                    <input type="text" id="amount" name="amount" class="advSearch" 
                                    	<c:if test="${mapSearch.size()>0&& mapSearch.get('amount')!=null&& mapSearch.get('amount').size()>0 }">
                                              value = "${mapSearch.get('amount').get(0)} - ${mapSearch.get('amount').get(1)}"
                                              
                                        </c:if>
                                    >
                                    <script type="text/javascript">
                           	          
                                    </script>
                                </div>
                            </div>
                        </div> <!-- End Single Sidebar Widget -->

                        <!-- Start Single Sidebar Widget -->
                        <div class="sidebar-single-widget">
                            <h6 class="sidebar-title">Bộ máy</h6>
                            <div class="sidebar-content">
                                <div class="filter-type-select">
                                    <ul>
                              	<c:forEach var = "m" items="${ machine }">
                                        <li>
                                            <label class="checkbox-default" for="brakeParts">
                                                <input type="checkbox" id="machine" class="advSearch" name="machineId"
                                                 name="machineId" 
                                                 value="${ m.machineId }"
                                                 <c:if test="${mapSearch.size()>0&& mapSearch.get('machineId')!=null &&  mapSearch.get('machineId').contains(m.machineId) }">
                                                 	<c:out value="checked" />
                                                 </c:if>
                                                 
                                                  >
                                                <span><c:out value = "${ m.machineName } " /></span>
                                            </label>
                                        </li>
                                </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div> <!-- End Single Sidebar Widget -->

						<div class="sidebar-single-widget">
                            <h6 class="sidebar-title">Giới tính</h6>
                            <div class="sidebar-content">
                                <div class="filter-type-select">
                                    <ul>
                              	<c:forEach var = "m" items="${ gender }">
                                        <li>
                                            <label class="checkbox-default" for="brakeParts">
                                                <input type="checkbox" id="gender" class="advSearch" name="gender"
                                                 
                                                 value="${ m }"
                                                 <c:if test="${mapSearch.size()>0&& mapSearch.get('gender')!=null &&  mapSearch.get('gender').contains(m) }">
                                                 	<c:out value="checked" />
                                                 </c:if>
                                                 
                                                  >
                                                <span><c:out value = "${ m } " /></span>
                                            </label>
                                        </li>
                                </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div> <!-- End Single Sidebar Widget -->
                        <!-- Start Single Sidebar Widget -->
                        <div class="sidebar-single-widget">
                            <h6 class="sidebar-title">Loại dây</h6>
                            <div class="sidebar-content">
                                <div class="filter-type-select">
                                    <ul>
                                   <c:forEach var ="s" items = "${ strap }">
                                        <li>
                                            <label class="checkbox-default" for="black">
                                            	<input type="checkbox" id="strap" name="strapId" value="${ s.strapId } " class="advSearch"
                                            	<c:if test="${mapSearch.size()>0&& mapSearch.get('strapId')!=null &&  mapSearch.get('strapId').contains(s.strapId) }">
                                                 	<c:out value="checked" />
                                                 </c:if>
                                            	>
                                                
                                                <span><c:out value = "${s.strapName}" /></span>
                                            </label>
                                        </li>
                                   </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div> <!-- End Single Sidebar Widget -->
                    <%--    
						<!-- Start Single Sidebar Widget -->
                        <div class="sidebar-single-widget">
                            <h6 class="sidebar-title">Size mặt</h6>
                            <div class="sidebar-content">
                                <div class="filter-type-select">
                                    <ul>
                                        <li>
                                            <label class="checkbox-default" for="black">
                                                <input type="checkbox" id="black">
                                                <span>Black (6)</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="blue">
                                                <input type="checkbox" id="blue">
                                                <span>Blue (8)</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="brown">
                                                <input type="checkbox" id="brown">
                                                <span>Brown (10)</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="Green">
                                                <input type="checkbox" id="Green">
                                                <span>Green (6)</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="pink">
                                                <input type="checkbox" id="pink">
                                                <span>Pink (4)</span>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div> <!-- End Single Sidebar Widget -->
                        
                        <!-- Start Single Sidebar Widget -->
                        <div class="sidebar-single-widget">
                            <h6 class="sidebar-title">Xuất xứ</h6>
                            <div class="sidebar-content">
                                <div class="filter-type-select">
                                    <ul>
                                        <li>
                                            <label class="checkbox-default" for="black">
                                                <input type="checkbox" id="black">
                                                <span>Việt Nam</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="blue">
                                                <input type="checkbox" id="blue">
                                                <span>Blue (8)</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="brown">
                                                <input type="checkbox" id="brown">
                                                <span>Brown (10)</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="Green">
                                                <input type="checkbox" id="Green">
                                                <span>Green (6)</span>
                                            </label>
                                        </li>
                                        <li>
                                            <label class="checkbox-default" for="pink">
                                                <input type="checkbox" id="pink">
                                                <span>Pink (4)</span>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div> <!-- End Single Sidebar Widget -->
                        <!-- Start Single Sidebar Widget -->
                 --%>       

                        <!-- Start Single Sidebar Widget -->
                        <div class="sidebar-single-widget">
                            <div class="sidebar-content">
                                <!-- <a href="product-details-default.html" class="sidebar-banner img-hover-zoom">
                                    <img class="img-fluid" src="<c:url value='/assets/images/banner/side-banner.jpg'/>" alt="">
                                </a>-->
                            </div>
                        </div> <!-- End Single Sidebar Widget -->

                    </div> <!-- End Sidebar Area -->
                </div>
                <div class="col-lg-9">
                    <!-- Start Shop Product Sorting Section -->
                    <div class="shop-sort-section">
                        <div class="container">
                            <div class="row">
                                <!-- Start Sort Wrapper Box -->
                                <div class="sort-box d-flex justify-content-between align-items-md-center align-items-start flex-md-row flex-column" data-aos="fade-up"  data-aos-delay="0">
                                    <!-- Start Sort tab Button -->
                                    <div class="sort-tablist d-flex align-items-center">
                                        <!-- <ul class="tablist nav sort-tab-btn">
                                            <li><a class="nav-link active" data-bs-toggle="tab" href="#layout-3-grid">
                                            		<img src="<c:url value='/assets/images/icons/bkg_grid.png'/> " alt="">
                                            	</a></li>
                                            <li><a class="nav-link" data-bs-toggle="tab" href="#layout-list">
                                            	<img src="<c:url value='/assets/images/icons/bkg_list.png'/> " alt="">
                                            </a></li>
                                        </ul>-->

                                        <!-- Start Page Amount -->
                                        
                                        <div class="page-amount ml-2">
                                        <c:if test="${dataList!=null && dataList.size()>0 }">
                                            <span>Hiển thị <c:out value="${(page-1)*6+1 }"></c:out>–<c:out value="${((page)*6)>count?count:((page)*6) }"></c:out> trên <c:out value="${count }"></c:out></span>
                                         <!-- End Page Amount -->
                                        </c:if>
                                        </div>
                                       
                                    </div> <!-- End Sort tab Button -->
									<%--
                                    <!-- Start Sort Select Option -->
                                    <div class="sort-select-list d-flex align-items-center">
                                        <label class="mr-2">Sort By:</label>
                                        <form action="#">
                                            <fieldset>
                                                <select name="speed" id="speed">
                                                    <option>Sort by average rating</option>
                                                    <option>Sort by popularity</option>
                                                    <option selected="selected">Sort by newness</option>
                                                    <option>Sort by price: low to high</option>
                                                    <option>Sort by price: high to low</option>
                                                    <option>Product Name: Z</option>
                                                </select>
                                            </fieldset>
                                        </form>
                                    </div> <!-- End Sort Select Option -->--%>

                                    

                                </div> <!-- Start Sort Wrapper Box -->
                            </div>
                        </div>
                    </div> <!-- End Section Content -->

<!-- Product -->
                    <!-- Start Tab Wrapper -->
                    <div class="sort-product-tab-wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="col-12">
                                    <div class="tab-content tab-animate-zoom">
                                        <!-- Start Grid View Product -->
                                        <div class="tab-pane active show sort-layout-single" id="layout-3-grid">
                                            <div class="row">
                                     <c:if test="${dataList==null || dataList.size()==0 }">
                                     	<h2>Không có sản phẩm phù hợp</h2>
                                     </c:if>
                                     <c:forEach var="item" items="${dataList}">
                                                <div class="col-xl-4 col-sm-6 col-12">                                                
                                                    <!-- Start Product Default Single Item -->
                                                    <div class="product-default-single-item product-color--golden" data-aos="fade-up"  data-aos-delay="0">
                                                        <div class="image-box">
                                                        
                                                        <!-- Link den trang chi tiet -->
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
                                           </c:forEach>
                                                    <!-- End Product Default Single Item -->
                                                </div>                                          
                                 </div>
<!-- Phan trangs -->     
<c:if test="${dataList!=null && dataList.size()>0 }">
  <div class="page-pagination text-center">
    <tag:paginate  page="${page}" count="${count}" type="${type}" uri="/SpringMVC/app-view" next="&raquo;" previous="&laquo;"/>
  </div>
</c:if>
                                        </div> <!-- End Grid View Product -->
                                        <!-- Start List View Product -->
                                        
	<!-- SAN PHAM LIEN QUAN -->  
	<div class="product-default-slider-section section-top-gap-100 section-fluid">
        <!-- Start Section Content Text Area -->
        <div class="section-title-wrapper" data-aos="fade-up"  data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section-content-gap">
                            <div class="secton-content">
                                <h3 class="section-title">Sản phẩm đang giảm giá</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>                                      
        
        <!-- Start Section Content Text Area -->
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
                                                <!-- <a href="#" class="date">24 Apr</a> -->
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
    <!-- End Blog Slider Section -->
       
    <!-- End Product Default Slider Section -->
        
    </div> 
                                         <!-- End List View Product -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> <!-- End Tab Wrapper -->

                    
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Shop Section:::... -->
