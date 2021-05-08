<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Quản lý mặt đồng hồ</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<c:url value = '/admin/addFace' />">Thêm mới</a></li>
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
                <h3 class="card-title">Quản lý mặt đồng hồ</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã mặt</th>
                    <th>Tên mặt</th>
                    <th>Độ dày</th>
                    <th>Độ rộng</th>
                    <th>Độ chịu nước</th>
                    <th>Chất liệu kính</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                    <td><c:out value ="${ item.faceId }" /></td>
                    <td><c:out value ="${ item.faceName }" /></td>
                    <td><c:out value = "${ item.thickness }" /></td>
                    <td><c:out value = "${item.faceSize }" /></td>
                    <td><c:out value = "${item.waterProof }" /></td>
                    <td><c:out value = "${item.glass }" /></td>
                    <td>
                    <c:url var="urlEdit" value='/admin/editFace'>
                    	<c:param name="faceId" value="${item.faceId}"></c:param>
                    </c:url>
                    <c:url var="urlDel" value='/admin/deleteFace'>
                    	<c:param name="faceId" value="${item.faceId}"></c:param>
                    </c:url>
                    	<a href="${urlEdit }">Sửa</a>
                    	<a href="${urlDel }">Xóa</a>
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