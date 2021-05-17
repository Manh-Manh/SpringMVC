<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
    
<div class="content-wrapper">
<form:form method="POST" action="addNewSupplier" modelAttribute="dataInsert" enctype="multipart/form-data" > 
<form:hidden path="supplierId" value="${dataSelected.supplierId }"/>
	<section class="content">
      <div class="row">
      <div class="col-md-12">
      <div class="team-single " data-aos="fade-up"  data-aos-delay="0">
	   <div class="team-img">
	   	<div class="img-fluid" style="text-align: center; padding: 10px;">
	   		<c:set var="default" value="default-avatar.png"></c:set>
	       	<img id="img-product" class="img-product" src="<c:url value='/assets/images/company-logo/${dataSelected.logo}' /> " alt="">
	   	</div>
	   </div>
	   <div class="team-content"  style="text-align: center;">
	       <div class="btn btn-success float-right btn-image">
	           <button class="btn btn-md btn-black-default-hover" type="button" onclick="document.getElementById('fileInput').click();">Chọn ảnh</button>
	           <form:input id="fileInput" path="fileLogo" type="file" name ="file"  />
	           <form:hidden path="logo" value = "${dataSelected.logo }"/>
	          <script>
	           	document.getElementById('fileInput').hidden = true;
	          </script>
	              
	        </div>
	        
	    </div>
	    </div>
	    
	</div>
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Chi tiết thương hiệu</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Tên thương hiệu</label>
                <form:input type="text" path="supplierName" required="required" value="${dataSelected.supplierName}" class="form-control" ></form:input>
            
              </div>
              
              <!-- Trạng thái -->
              <div class="form-group">
                <label for="inputDescription">Trạng thái</label>
                <form:select id="status" path="status" class="form-control custom-select" required="required">
                  <c:if test="${dataSelected.status != null }">
                  <form:option selected="selected" value="${dataSelected.status }" label="${dataSelected.statusString }" ></form:option>
                  </c:if>
                    <form:option value="1" label="Hoạt động" ></form:option>
					<form:option value="0" label="Không hoạt động" ></form:option>
                </form:select>            
              </div>
              
              <div class="form-group">
                <label for="inputName">Địa chỉ</label>
                <form:input type="text" path="address" required="required" value="${dataSelected.address}" class="form-control" ></form:input>
            
              </div>
              
              <div class="form-group">
                <label for="inputName">Số điện thoại</label>
                <form:input type="text" path="phoneNumber" required="required" value="${dataSelected.phoneNumber}" class="form-control" ></form:input>
            
              </div>
              
              <div class="form-group">
                <label for="inputName">Email</label>
                <form:input type="text" path="email" required="required" value="${dataSelected.email}" class="form-control" ></form:input>
            
              </div>
              
              <div class="form-group">
                <label for="inputName">Website</label>
                <form:input type="text" path="website" required="required" value="${dataSelected.website}" class="form-control" ></form:input>
            
              </div>
              
              <div class="form-group">
                <label for="inputName">Quốc gia</label>
                <form:input type="text" path="location" required="required" value="${dataSelected.location}" class="form-control" ></form:input>
            
              </div>
              <!-- Mô tả -->
               <div class="form-group">
                <label for="inputDescription">Mô tả</label>
                <form:textarea id="description" path="description" defaulValue="${dataSelected.description}" class="form-control" ></form:textarea>
              	<script>
              	document.getElementById("description").defaultValue = "<c:out value="${dataSelected.description}" />";
              	</script>
              </div>
              
              
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        
      </div>
      <div class="row">
        <div class="col-12">
          <a href="#" class="btn btn-secondary">Hủy</a>
          <button type="submit" value="Create new Porject" class="btn btn-success float-right">
          	<c:out value="${ isInsert==0?'Cập nhật':'Thêm mới' }"></c:out>
          </button>
        </div>
      </div>
    </section>
</form:form>
 </div>
    <!-- /.content -->