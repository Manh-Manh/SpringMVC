<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
    
<div class="content-wrapper">
<form:form method="POST" action="addNewProduct" modelAttribute="dataInsert" enctype="multipart/form-data" > 
<form:hidden path="productId" value="${dataSelected.productId }"/>
<%--
	<div class="team-single " data-aos="fade-up"  data-aos-delay="0">
	   <div class="team-img">
	   	<div class="img-fluid">
	   		<c:set var="default" value="default-avatar.png"></c:set>
	       	<img id="img-avatar" class="img-avatar" src="<c:url value='/assets/images/products/${dataSelected.image}' /> " alt="">
	   	</div>
	   </div>
	   
	   <div class="team-content">
	       <h6 class="team-name font--bold mt-5">
	       	<%--><c:out value="${u.fullName!=null?u.fullName:u.userName }" /></h6> 
	       <br>
	       <div class="save_button mt-3 team-social pos-absolute">
	           <button class="btn btn-md btn-black-default-hover" type="button" onclick="document.getElementById('fileInput').click();">Chọn ảnh</button>
	           <form:input id="fileInput" path="image" type="file" name ="file"  />
	          <script>
	           	document.getElementById('fileInput').hidden = true;
	          </script>
	              
	        </div>
	        
	    </div>
	    
	</div>--%>
	<section class="content">
      <div class="row">
	  <div class="col-md-12">
      <div class="team-single " data-aos="fade-up"  data-aos-delay="0">
	   <div class="team-img">
	   	<div class="img-fluid" style="text-align: center; padding: 10px;">
	   		<c:set var="default" value="default-avatar.png"></c:set>
	       	<img id="img-product" class="img-product" src="<c:url value='/assets/images/products/${dataSelected.image}' /> " alt="">
	   	</div>
	   </div>
	   <div class="team-content"  style="text-align: center;">
	       <div class="btn btn-success float-right btn-image">
	           <button class="btn btn-md btn-black-default-hover" type="button" onclick="document.getElementById('fileInput').click();">Chọn ảnh</button>
	           <form:input id="fileInput" path="fileImage" type="file" name ="file"  />
	           <form:hidden path="image" value = "${dataSelected.image }"/>
	          <script>
	           	document.getElementById('fileInput').hidden = true;
	          </script>
	              
	        </div>
	        
	    </div>
	    </div>
	    
	</div>
        <div class="col-md-6">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Chi tiết sản phẩm</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Tên sản phẩm</label>
                <form:input type="text" id="inputName" path="productName" required="required" value="${dataSelected.productName}" class="form-control" ></form:input>
            
              </div>
              <div class="form-group">
                <label for="inputDescription">Giới tính</label>
                <form:select id="gender" path="gender" class="form-control custom-select" required="required">
                  <c:if test="${dataSelected.gender != null }">
                  <form:option selected="selected" value="${dataSelected.gender }" label="${dataSelected.gender }" ></form:option>
                  </c:if>
                  <c:forEach var="g" items = "${gender }">
                   <option value="${g }"> <c:out value ="${g }" /></option>
                  </c:forEach>
                  
                </form:select>            
              </div>
              <!-- Trạng thái -->
              <div class="form-group">
                <label for="inputDescription">Trạng thái</label>
                <form:select id="status" path="status" class="form-control custom-select" required="required">
                  <c:if test="${dataSelected.gender != null }">
                  <form:option selected="selected" value="${dataSelected.status }" label="${dataSelected.statusString }" ></form:option>
                  </c:if>
                    <form:option value="1" label="Hoạt động" ></form:option>
					<form:option value="0" label="Không hoạt động" ></form:option>
                </form:select>            
              </div>
              <!-- Thương hiệu -->
              <div class="form-group">
                <label for="supplier">Thuơng hiệu</label>
                <form:select id="supplier" path="supplierId" required="required" class="form-control custom-select">
                  <c:if test="${dataSelected.supplierId != null }">
                  <form:option selected="selected" value="${dataSelected.supplier.supplierId }" label="${dataSelected.supplier.supplierName }"  ></form:option>
                  </c:if>                  
                  	<form:options  items="${supplier}" itemValue="supplierId" itemLabel="supplierName" ></form:options>
                  
                </form:select>
              </div>
              
              <!-- Dây -->
              <div class="form-group">
                <label for="supplier">Loại dây</label>
                <form:select path="strapId" class="form-control custom-select" required="required">
                  <c:if test="${dataSelected.strapId != null }">
                  <form:option selected="selected" value="${dataSelected.strapId }" label="${dataSelected.strap.strapName }" ></form:option>
                  </c:if>
                  	<form:options  items="${strap}" itemValue="strapId" itemLabel="strapName" ></form:options>
                  
                </form:select>
              </div>
              
              <!-- Mặt đồng hồ -->
              <div class="form-group">
                <label for="face">Mặt đồng hồ</label>
                <form:select path="faceId" class="form-control custom-select" required="required">
                  <c:if test="${dataSelected.faceId != null }">
                  <form:option selected="selected" value="${dataSelected.faceId }" label="${dataSelected.face.detailString }" ></form:option>
                  </c:if>
                  	<form:options  items="${lstFace}" itemValue="faceId" itemLabel="detailString" ></form:options>
                </form:select>
              </div>
              <!-- kieu may -->
              <div class="form-group">
                <label for="machine">Kiểu máy</label>
                <form:select path="machineId" class="form-control custom-select" required="required">
                  <c:if test="${dataSelected.machineId != null }">
                  <form:option selected="selected" value="${dataSelected.machineId }" label="${dataSelected.machine.machineName }" ></form:option>
                  </c:if>
                  	<form:options  items="${machine}" itemValue="machineId" itemLabel="machineName" ></form:options>
                </form:select>
              </div>
              <div class="form-group">
                <label for="inputDescription">Giá</label>
                <form:input type="number" id="inputName" required="required" path="unitPrice" value="${dataSelected.unitPrice}" class="form-control" ></form:input>
              </div>
              
              <div class="form-group">
                <label for="inputDescription">Số lượng</label>
                <form:input type="number" id="inputName" required="required" path="quantity" value="${dataSelected.quantity}" class="form-control" ></form:input>
              </div>
              <!-- Mô tả -->
               <div class="form-group">
                <label for="inputDescription">Loại kính</label>
                <form:textarea path="description" value="${dataSelected.description}" class="form-control" ></form:textarea>>
              </div>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        <div class="col-md-6">
          <div class="card card-secondary">
            <div class="card-header">
              <h3 class="card-title">Giảm giá</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
              <c:forEach var="item" items = "${dataSelected.lstDiscount }">
                <form:checkbox path="strLstDiscount" value="${item.discountId }"  checked="checked" /> <c:out value="${item.description } (-${item.discount }%)"></c:out><br> 
              </c:forEach>
              <c:forEach var="item" items = "${lstDiscount }">
                <form:checkbox path="strLstDiscount" value="${item.discountId }"  /> <c:out value="${item.description } (-${item.discount }%)"></c:out><br> 
              </c:forEach>
              </div>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <a href="#" class="btn btn-secondary">Cancel</a>
          <button type="submit" value="Create new Porject" class="btn btn-success float-right">
          	<c:out value="${ isInsert==0?'Cập nhật':'Thêm mới' }"></c:out>
          </button>
        </div>
      </div>
    </section>
</form:form>
 </div>
    <!-- /.content -->