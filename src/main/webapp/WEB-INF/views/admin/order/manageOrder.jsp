<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Quản lý hóa đơn</h1>
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
                <h3 class="card-title">Quản lý hóa đơn</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã hóa đơn</th>
                    <th>Khách hàng</th>
                    <th>Ngày đặt</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                    <td><c:out value ="${ item.orderId }" /></td>
                    <td><c:out value ="${ item.user.userName }" /></td>
                    <td><c:out value ="${ item.oderDate }" /></td>
                    <td><c:out value = "${ item.total }" /></td>
                    <c:if test="${ item.status ==1 }">
                    	<td><c:out value ="${ AppConstant.ODER_NOT_PROCESS }" /></td>
                    </c:if>
                    <c:if test="${ item.status == 2 }">
                    	<td><c:out value ="${ AppConstant.ODER_PROCESS }" /></td>
                    </c:if>
                    <c:if test="${ item.status == 3 }">
                    	<td><c:out value ="${ AppConstant.ODER_CANCEL }" /></td>
                    </c:if>
                    
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