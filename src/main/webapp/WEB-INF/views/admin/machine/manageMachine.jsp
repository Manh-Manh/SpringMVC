<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Quản lý bộ máy</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<c:url value='/admin/addMachine' </c:url>">Thêm mới</a></li>
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
                <h3 class="card-title">Quản lý bộ máy</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã bộ máy</th>
                    <th>Tên máy</th>
                    <th>Mô tả</th>
                    
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                    <td><c:out value ="${ item.machineId }" /></td>
                    <td><c:out value ="${ item.machineName }" /></td>
                    <td><c:out value = "${ item.description }" /></td>
                    <td>
                    <c:url var="urlEdit" value='/admin/editMachine'>
                    	<c:param name="faceId" value="${item.machineId}"></c:param>
                    </c:url>
                    <c:url var="urlDel" value='/admin/deleteMachine'>
                    	<c:param name="machineId" value="${item.machineId}"></c:param>
                    </c:url>
                    	<a href="${urlEdit }">Sửa</a>
                    	<a href="${urlDel }">Xóa</a>
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