<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<div class="content-wrapper">
<form:form method="POST" action="addNewDiscount" modelAttribute="dataInsert" enctype="multipart/form-data" > 

	<section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Chi tiết giảm giá</h3>
              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Mã giảm giá</label>
                <c:if test="${dataSelected != null && dataSelected.discountId != null }">
                		<i>(<c:out value="${dataSelected.stringActive }"></c:out>)</i>
                </c:if>
                <form:input type="text" path="discountId" readonly="true" required="required" value="${dataSelected.discountId}" class="form-control" ></form:input>
            
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
                <label for="supplier">Tỷ lệ giảm</i></label>
                <form:input pattern="^\d*(\.\d{0,2})?$" placeholder="1.23" class="inpNumber form-control inpDiscount" path="discount" required="required" value="${dataSelected.discount }"/>
              </div>
				
              <div class="form-group">
                <label for="inputName">Ngày bắt đầu</label>
                <form:input type="date" path="startDate" required="required" value="${dataSelected.startDate}" class="form-control" ></form:input>
              </div>
			  <div class="form-group">
                <label for="inputName">Ngày kết thúc</label>
                <form:input type="date" path="endDate" required="required" value="${dataSelected.endDate}" class="form-control" ></form:input>
              </div>
			
               <div class="form-group">
                <label for="inputDescription">Mô tả</label>
                <form:textarea id="description" path="description" required="required" defaulValue="${dataSelected.description}" class="form-control" ></form:textarea>
              </div>
              <script>
              	document.getElementById("description").defaultValue = "<c:out value="${dataSelected.description}" />";
              	</script>
              
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