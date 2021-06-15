<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
    
<div class="content-wrapper">
<form:form method="POST" action="addNewStrap" modelAttribute="dataInsert" enctype="multipart/form-data" > 
<form:hidden path="strapId" value="${dataSelected.strapId }"/>
	<section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Chi tiết dây đeo</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Tên loại dây</label>
                <form:input type="text" path="strapName" required="required" value="${dataSelected.strapName}" class="form-control" ></form:input>
            
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
              <!-- Chất liệu -->
               <div class="form-group">
                <label for="inputName">Chất liệu</label>
                <form:input type="text" path="materialStrap" required="required" value="${dataSelected.materialStrap}" class="form-control" ></form:input>
            
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
          <a href="#" onclick="goBack()" class="btn btn-secondary">Hủy</a>
          <button type="submit" value="Create new Porject" class="btn btn-success float-right">
          	<c:out value="${ isInsert==0?'Cập nhật':'Thêm mới' }"></c:out>
          </button>
        </div>
      </div>
    </section>
</form:form>
 </div>
    <!-- /.content -->