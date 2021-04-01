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
              <li class="breadcrumb-item"><a href="#">Thêm mới</a></li>
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
                <h3 class="card-title">Quản lý thương hiệu</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
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
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                    <td><c:out value ="${ item.supplierId }" /></td>
                    <td><c:out value ="${ item.supplierName }" /></td>
                    <td><c:out value = "${ item.address }" /></td>
                    <td><c:out value = "${item.phoneNunber }" /></td>
                    <td><a href ="<c:url value ='${ item.website }' /> ">  <c:out value ="${ item.website }" /> </a></td>
                    <td><c:out value ="${ item.location }" /></td>
                    <td>
                    	<a href="<c:url value = '#' /> ">Sửa</a>
                    	<a href="<c:url value = '#' /> ">Xóa</a>
                    	<%--<a href="<c:url value = '#' /> ">Sửa</a>--%>
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