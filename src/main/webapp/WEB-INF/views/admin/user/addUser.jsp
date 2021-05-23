<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
    
<div class="content-wrapper">
<form:form method="POST" action="addNewUser" modelAttribute="dataInsert" enctype="multipart/form-data" > 
<form:hidden path="userId" value="${dataSelected.userId }"/>
	<section class="content">
      <div class="row">
      <div class="col-md-12">
      <div class="team-single " data-aos="fade-up"  data-aos-delay="0">
	   <div class="team-img">
	   	<div class="img-fluid" style="text-align: center; padding: 10px;">
	   		<c:set var="default" value="default-avatar.png"></c:set>
	       	<img id="img-product" class="img-product" src="<c:url value='/assets/images/user/${dataSelected.userId}/${dataSelected.avatar}' /> " alt="">
	   	</div>
	   </div>
	   <div class="team-content"  style="text-align: center;">
	        <%--<div class="btn btn-success float-right btn-image">
	           <button class="btn btn-md btn-black-default-hover" type="button" onclick="document.getElementById('fileInput').click();">Chọn ảnh</button>
	           <form:input id="fileInput" path="fileImage" type="file" name ="file"  />
	           <form:hidden path="image" value = "${dataSelected.image }"/>
	          <script>
	           	document.getElementById('fileInput').hidden = true;
	          </script>
	              
	        </div>--%>
	        
	    </div>
	    </div>
      </div>
      
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Thêm tài khoản</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Tên người dùng</label>
                <form:input type="text" path="fullName" readonly="${!isInsert }" required="required" value="${dataSelected.fullName}" class="form-control" ></form:input>
            
              </div>
              
              <!-- Trạng thái -->
              <div class="form-group">
                <label for="inputDescription">Trạng thái</label>
                <form:select id="status" path="status" class="form-control custom-select" required="required">
                  <c:if test="${dataSelected.status != null }">
                  <form:option selected="selected" readonly="${!isInsert }" value="${dataSelected.status }" label="${dataSelected.statusString }" ></form:option>
                  </c:if>
                    <form:option value="1" label="Hoạt động" ></form:option>
					<form:option value="0" label="Không hoạt động" ></form:option>
                </form:select>            
              </div>
              
              <div class="form-group">
                <label for="inputName">Tên đăng nhập</label>
                <form:input type="text" path="userName" readonly="${!isInsert }" required="required" value="${dataSelected.fullName}" class="form-control" ></form:input>
              </div>
              
              <div class="form-group">
                <label for="inputName">Email</label>
                <form:input type="text" path="email" readonly="${!isInsert }" required="required" value="${dataSelected.email}" class="form-control" ></form:input>
              </div>
              
              <div class="form-group">
                <label for="inputName">Ngày sinh</label>
                <form:input type="date" path="birthDate" readonly="${!isInsert }" required="required" value="${dataSelected.birthDate}" class="form-control" ></form:input>
              </div>
              <div class="form-group">
                <label for="inputName">Mật khẩu</label>
                <form:input type="password" path="password" required="required" value="${dataSelected.password}" class="form-control" ></form:input>
              </div>
              
               <div class="form-group">
                <label for="inputName">Địa chỉ</label>
                <form:input type="text" path="address" readonly="${!isInsert }" value="${dataSelected.address}" class="form-control" ></form:input>
              </div>
              
               <div class="form-group">
                <label for="inputName">Điện thoại</label>
                <form:input type="tel" path="phoneNumber" readonly="${!isInsert }" value="${dataSelected.phoneNumber}" class="form-control" ></form:input>
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
          	<c:out value="${ isInsert==false?'Cập nhật':'Thêm mới' }"></c:out>
          </button>
        </div>
      </div>
    </section>
</form:form>
 </div>
    <!-- /.content -->