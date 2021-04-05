<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
    
<div class="content-wrapper">
<form:form method="POST" action="addProduct" modelAttribute="dataInsert" > 
	<section class="content">
      <div class="row">
        <div class="col-md-6">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title"></h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Tên sản phẩm</label>
                <form:input type="text" id="inputName" path="productName" value="${dataSelected.productName}" class="form-control" ></form:input>
            
              </div>
              <div class="form-group">
                <label for="inputDescription">Giới tính</label>
                <form:input type="text" id="inputName" path="productName" value="${dataSelected.gender}" class="form-control" ></form:input>
            
              </div>
              <!-- Thương hiệu -->
              <div class="form-group">
                <label for="supplier">Thuơng hiệu</label>
                <form:select id="supplier" path="supplierId" class="form-control custom-select">
                  <option selected disabled>Select one</option>
                  	<form:options  items="${supplier}" itemValue="supplierId" itemLabel="supplierName" ></form:options>
                  
                </form:select>
              </div>
              
              <!-- Dây -->
              <div class="form-group">
                <label for="supplier">Loại dây</label>
                <form:select path="strapId" class="form-control custom-select">
                  <option selected disabled>Select one</option>
                  	<form:options  items="${strap}" itemValue="strapId" itemLabel="strapName" ></form:options>
                  
                </form:select>
              </div>
              
              <!-- Mặt đồng hồ -->
              <div class="form-group">
                <label for="supplier">Mặt đồng hồ</label>
                <form:select path="faceId" class="form-control custom-select">
                  <option selected disabled>Select one</option>
                  	<form:options  items="${face}" itemValue="faceId" itemLabel="faceName" ></form:options>
                  
                </form:select>
              </div>
              
              
              <div class="form-group">
                <label for="inputDescription">Số lượng</label>
                <form:input type="number" id="inputName" path="quantity" value="${dataSelected.quantity}" class="form-control" ></form:input>
              </div>
              
              <div class="form-group">
                <label for="inputDescription">Giá</label>
                <form:input type="number" id="inputName" path="unitPrice" value="${dataSelected.unitPrice}" class="form-control" ></form:input>
              </div>
              
              <div class="form-group">
                <label for="inputDescription">Số lượng</label>
                <form:input type="number" id="inputName" path="quantity" value="${dataSelected.quantity}" class="form-control" ></form:input>
              </div>
              
              <div class="form-group">
                <label for="inputProjectLeader">Project Leader</label>
                <input type="text" id="inputProjectLeader" class="form-control">
              </div>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        <div class="col-md-6">
          <div class="card card-secondary">
            <div class="card-header">
              <h3 class="card-title">Budget</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputEstimatedBudget">Estimated budget</label>
                <input type="number" id="inputEstimatedBudget" class="form-control">
              </div>
              <div class="form-group">
                <label for="inputSpentBudget">Total amount spent</label>
                <input type="number" id="inputSpentBudget" class="form-control">
              </div>
              <div class="form-group">
                <label for="inputEstimatedDuration">Estimated project duration</label>
                <input type="number" id="inputEstimatedDuration" class="form-control">
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
          <input type="submit" value="Create new Porject" class="btn btn-success float-right">
        </div>
      </div>
    </section>
</form:form>
 </div>
    <!-- /.content -->