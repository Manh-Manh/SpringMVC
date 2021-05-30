<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Quản lý thương hiệu</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<c:url value='/admin/addSupplier' />" >
					<button type="button" class="btn btn-primary float-right"><i class="fas fa-plus"></i> Thêm mới</button>
			</a></li>
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
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                  	<th>Số thứ tự</th>
                    <th>Mã thương hiệu</th>
                    <th>Tên thương hiệu</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Email</th>
                    <th>Quốc gia</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
             <c:set value="0" var="stt"></c:set>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                  <td><c:out value ="${ stt=stt+1 }" /></td>
                    <td><c:out value ="${ item.supplierId }" /></td>
                    <td><c:out value ="${ item.supplierName }" /></td>
                    <td><c:out value = "${ item.address }" /></td>
                    <td><c:out value = "${item.phoneNumber }" /></td>
                    <td><a href ="<c:url value ='${ item.website }' /> " >  <c:out value ="${ item.website }" /> </a></td>
                    <td><c:out value ="${ item.location }" /></td>
                    <td class="text-center py-0 align-middle">
                    <c:url var="urlEdit" value='/admin/editSupplier'>
                    	<c:param name="supplierId" value="${item.supplierId}"></c:param>
                    </c:url>
                    <c:url var="urlDel" value='/admin/deleteSupplier'>
                    	<c:param name="supplierId" value="${item.supplierId}"></c:param>
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