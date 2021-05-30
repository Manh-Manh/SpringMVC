<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Quản lý sản phẩm</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item">
              <a href="<c:url value='/admin/addProduct' /> " >
                	<button type="button" class="btn btn-primary float-right"><i class="fas fa-plus"></i> Thêm mới </button>
              </a>
              </li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
              <c:url var="urlSearch" value='/admin/doSearchProduct'>
              </c:url>
              <form:form action="${urlSearch }" method="POST" modelAttribute="productSearch" onsubmit="return doSearch();">
                <div class="row">
                    <div class="col-md-10 offset-md-1">
                        <div class="row">
                            <div class="col-4">
                                <div class="form-group">
					                <label for="inputDescription">Giới tính</label>
					                <form:select id="gender" path="gender" class="form-control custom-select" >
					                  <option value=""> <c:out value ="__Chọn__" /></option>
					                  <c:forEach var="g" items = "${gender }">
					                   <option value="${g }"> <c:out value ="${g }" /></option>
					                  </c:forEach>
					                  
					                </form:select>            
					              </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
					                <label for="inputDescription">Trạng thái</label>
					                <form:select id="status" path="status" class="form-control custom-select" >
					                  <form:option selected="selected" value="" label="__Chọn__" ></form:option>
					                    <form:option value="1" label="Hoạt động" ></form:option>
										<form:option value="0" label="Không hoạt động" ></form:option>
					                </form:select>            
					              </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
					                <label for="supplier">Thuơng hiệu</label>
					                <form:select id="supplier" path="supplierId" class="form-control custom-select">
					                  <form:option selected="selected" value="" label="__Chọn__"  ></form:option>
					                  	<form:options  items="${supplier}" itemValue="supplierId" itemLabel="supplierName" ></form:options>
					                  
					                </form:select>
					              </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="" style="text-align:center;">
                                <div class="">
                                    <button type="submit" class="btn btn-primary" >
                                        <i class="fa fa-search">Tìm kiếm</i>
                                    </button>
                                </div>
                                <script>
                                	function doSearch(e){
                                		
                                		return false;
                                		e.preventdefault();
                                	}
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                  	<th>Số thứ tự</th>
                    <th>Mã sản phẩm</th>
                    <th>Tên sản phẩm</th>
                    <th>Trạng thái</th>
                    <th>Hình ảnh</th>
                    <th>Thương hiệu</th>
                    <th>Số lượng</th>
                     <th>Giá</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:set var="stt" value="0"></c:set>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                  <td><c:out value ="${ stt=stt+1 }" /></td>
                    <td><c:out value ="${ item.productId }" /></td>
                    <td><c:out value ="${ item.productName }" /></td>
                    <td><c:out value ="${ item.statusString }" /></td>
                    <td><img src="<c:url value='/assets/images/products/${item.image}' />" style="width: 100px" alt="" ></td>
                    <td><c:out value = "${item.supplier.supplierName }" /></td>
                    <td><c:out value ="${ item.quantity }" /></td>
                    <td><c:out value ="${ item.stringUnitPrice }" /> đ</td>
                    <td class="text-center py-0 align-middle">
                    
                    <c:url var="urlEdit" value='/admin/editProduct'>
                    	<c:param name="productId" value="${item.productId}"></c:param>
                    </c:url>
                    <c:url var="urlDel" value='/admin/deleteProduct'>
                    	<c:param name="productId" value="${item.productId}"></c:param>
                    </c:url>
                    <div class="btn-group btn-group-sm">
                        <a href="${urlEdit }" class="btn btn-info" data-toggle="tooltip" data-placement="bottom" title="Sửa"><i class="fas fa-edit"></i></a>
                        <a href="${urlDel }" class="btn btn-danger urlConfirm" data-toggle="tooltip" data-placement="bottom" title="Xóa"><i class="fas fa-trash"></i></a>
                      </div>
                    </td>
                  </tr>  
              </c:forEach>                
                  </tbody>
                  
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>