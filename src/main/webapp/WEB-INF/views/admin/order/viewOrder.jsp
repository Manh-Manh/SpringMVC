<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@include file="/common/taglib.jsp"%>
<!-- ...:::: Start Breadcrumb Section:::... -->
 <div class="content-wrapper">
	<section class="content">
      <div class="row">
	  <div class="col-md-12">
      <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Thông tin khách hàng</h3>
              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
      <div class="card-body">
      <table style="width:100%;">
      <tr>
      <c:set var="u" value="dataSelected.user" />
      	<td style="width:45%;"><label>Tên khách hàng:&nbsp; </label><c:out value=" ${dataSelected.user.fullName!=null?dataSelected.user.fullName:dataSelected.user.userName }" />
		<td style="width:9%;"></td>
	      	
      	<td style="width:45%;"><label>Địa chỉ:&nbsp; </label><c:out value="${dataSelected.user.address }" />
      	
      </tr>
      <tr>
      <c:set var="u" value="dataSelected.user" />
      	<td style="width:45%;"><label>Email:&nbsp; </label><c:out value=" ${dataSelected.user.email }" />
		<td style="width:9%;"></td>
	      	
      	<td style="width:45%;"><label>Số điện thoại:&nbsp; </label><c:out value="${dataSelected.user.phoneNumber }" />
      	
      </tr>
      </table>
      </div>
      </div>
	</div>
	
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Danh sách sản phẩm</h3>
              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <table style="width: 100%;">
	             <thead>
	                 <tr>
	                     <th class="product_thumb">Hình ảnh</th>
	                     <th class="product_name">Tên sản phẩm</th>
	                     <th class="product-price">Giá</th>
	                     <th class="product_quantity">Số lượng</th>
	                     <th class="product_total">Thành tiền</th>
	                 </tr>
	             </thead> <!-- End Cart Table Head -->
	             
	             <c:forEach var ="item" items = "${dataSelected.listProduct }">
	                 <!-- Start Cart Single Item-->
	                 <tr>
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
             	<input disabled="disabled" name="cartQuantity" min="1" max="${item.quantity }" value="${item.cartQuantity }" type="number">
             </td>
             <td class="product_total"><c:out value="${item.cartTotalString }" /></td>
             
         </tr>
          <!-- End Cart Single Item-->
      </c:forEach> 
     
 </table>
              
              
            </div>
            <!-- /.card-body -->
          </div>
          </div>
          <!-- /.card -->
       <div class="col-md-12">
        <div class="col-lg-6 col-md-6">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Tổng giỏ hàng</h3>
              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
            <table style="width:100%;">
            <tr>
            	<td ><label>Tổng sản phẩm:&nbsp; </label><c:out value="${dataSelected.listProduct.size() }" />
            <tr>
            	<td ><label>Tổng tiền:&nbsp; </label><c:out value="${dataSelected.subTotalString }" />₫</td> 
            <tr>
            	<td ><label>Giảm giá:&nbsp; </label><c:out value="${dataSelected.totalDiscountString }" />₫</td>
            </tr>
            <tr>
            	<td ><label>Thành tiền:&nbsp; </label><c:out value="${dataSelected.totalString }" />₫
            </tr>
            </table>
                        </div>
                    </div>
                </div>
            </div>
      <div class="row " style="width: 100%">
        <div class="col-12 " style="width: 100%;margin-bottom: 10px;">
        <c:url var="urlAccept" value='/admin/acceptOrder'>
       	<c:param name="orderId" value="${dataSelected.orderId}"></c:param>
       </c:url>
       <c:url var="urlReject" value='/admin/rejectOrder'>
       	<c:param name="orderId" value="${dataSelected.orderId}"></c:param>
       </c:url>
       	<c:if test="${dataSelected.status == 2 }">
       		<a style="font-size: 28px" class="urlConfirm btn btn-info float-right"  data-toggle="tooltip" data-placement="bottom" title="Xác nhận" href="#" data-url="${urlAccept }"><i class="fas fa-check"></i></a>
        	<a style="font-size: 28px; margin-left:1%;" class="urlConfirm btn btn-danger" data-toggle="tooltip" data-placement="bottom" title="Từ chối"  href="#" data-url="${urlReject }"><i class="fas fa-trash"></i></a>
       	</c:if>
       	<c:if test="${dataSelected.status != 2 }">
       	<a href="#" onclick="goBack()" class="btn btn-secondary">Trở về</a>
       	</c:if>
      </div>
      </div>
    </section>

    <!-- ...:::: Start Cart Section:::... -->
    

        
    </div> <!-- ...:::: End Cart Section:::... -->
