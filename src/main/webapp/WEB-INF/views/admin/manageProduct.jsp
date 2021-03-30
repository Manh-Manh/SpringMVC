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
                <h3 class="card-title">Quản lý sản phẩm</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã sản phẩm</th>
                    <th>Tên sản phẩm</th>
                    <th>Hình ảnh</th>
                    <th>Thương hiệu</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    <th>Giảm giá</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                    <td><c:out value ="${ item.productId }" /></td>
                    <td><c:out value ="${ item.productName }" /></td>
                    <td><img src="<c:url value='/assets/images/products/${item.image}' />" style="width: 100px" alt="" ></td>
                    <td><c:out value = "${item.supplier.supplierName }" /></td>
                    <td><c:out value ="${ item.quantity }" /></td>
                    <td><c:out value ="${ item.discount }" /></td>
                    <td><c:out value ="${ item.stringUnitPrice }" /> đ</td>
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