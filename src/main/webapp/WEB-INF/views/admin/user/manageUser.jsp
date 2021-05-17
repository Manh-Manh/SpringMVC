<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Quản lý người dùng</h1>
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
                <h3 class="card-title">Quản lý người dùng</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                  	<th>Số thứ tự</th>
                    <th>Mã người dùng</th>
                    <th>Tên đăng nhập</th>
                    <th>Ảnh</th>
                    <th>Email</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:set value="0" var="stt"></c:set>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                  <td><c:out value ="${ stt=stt+1 }" /></td>
                    <td><c:out value ="${ item.userId }" /></td>
                    <td><c:out value ="${ item.userName }" /></td>
                    <td><img src="<c:url value='/assets/images/users/${item.avatar}' />" style="width: 100px" alt="" ></td>
                    <td><c:out value ="${ item.email }" /></td>
                    <td><c:out value = "${ item.address }" /></td>
                    <td><c:out value = "${item.phoneNumber }" /></td>
                    <td>
                    	<c:out value ="${ item.statusString }" />                    
                    </td>
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