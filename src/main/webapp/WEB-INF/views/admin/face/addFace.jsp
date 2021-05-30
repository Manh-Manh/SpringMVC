<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<div class="content-wrapper">
<form:form method="POST" action="addNewFace" modelAttribute="dataInsert" enctype="multipart/form-data" > 
<form:hidden path="faceId" value="${dataSelected.faceId }"/>
	<section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Chi tiết mặt đồng hồ</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Tên mặt đồng hồ</label>
                <form:input type="text" path="faceName" required="required" value="${dataSelected.faceName}" class="form-control" ></form:input>
            
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
              <!-- Do day -->
              <div class="form-group">
                <label for="supplier">Độ dày <i>(mm)</i></label>
                <form:input pattern="^\d*(\.\d{0,2})?$" placeholder="1.23" class="inpNumber form-control" path="thickness" required="required" value="${dataSelected.thickness }"/>
              </div>
              <!-- Do rong -->
               <div class="form-group">
                <label for="inputDescription">Độ rộng <i>(mm)</i></label>
                <form:input pattern="^\d*(\.\d{0,2})?$" placeholder="1.23" class="inpNumber form-control" required="required" path="faceSize" value="${dataSelected.faceSize}"  ></form:input>
              </div>
               <!-- Do chiu nuoc -->
               <div class="form-group">
                <label for="inputDescription">Độ chịu nước <i>(m)</i></label>
                <form:input pattern="^\d*(\.\d{0,2})?$" placeholder="1.23" class="inpNumber form-control" path="waterProof" value="${dataSelected.waterProof}" ></form:input>
              </div>
               <!-- Loại kính -->
               <div class="form-group">
                <label for="inputDescription">Loại kính</label>
                <form:input  path="glass" value="${dataSelected.glass}" class="form-control" ></form:input>
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