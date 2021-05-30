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
              <c:url var="urlSearch" value='/admin/statisticProductSearch'>
              </c:url>
              <form:form action="${urlSearch }" method="POST" modelAttribute="itemSearch" onsubmit="return doSearch();">
                <div class="row">
                    <div class="col-md-10 offset-md-1">
                        <div class="row">
                            
                            <div class="col-6">
                                <div class="form-group">
					                <label for="supplier">Từ ngày</label><br>
					                <form:input path="fromDate" type="date" value="${dataSelected.fromDate }" id="fromDate" class="form-control " />
					              </div>
                            </div>
                            
                            <div class="col-6">
                                <div class="form-group">
					                <label for="supplier">Đến ngày</label><br>
					                <form:input path="toDate" value="${dataSelected.toDate }" type="date" id="toDate" class="form-control" />
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
                    <th>Hình ảnh</th>
                    <th>Tổng số lượng bán</th>
                    <th>Tổng tiền</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:set var="stt" value="0"></c:set>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
                  <td><c:out value ="${ stt=stt+1 }" /></td>
                    <td><c:out value ="${ item.product.productId }" /></td>
                    <td><c:out value ="${ item.product.productName }" /></td>
                    <td><img src="<c:url value='/assets/images/products/${item.product.image}' />" style="width: 100px" alt="" ></td>
                    <td><c:out value ="${ item.quantity }" /></td>
                    <td><c:out value ="${ item.totalString }" /> đ</td>
                    <td class="text-center py-0 align-middle">
                    
                    <c:url var="urlEdit" value='/admin/editProduct'>
                    	<c:param name="productId" value="${item.product.productId}"></c:param>
                    </c:url>
                    <c:url var="urlDel" value='/admin/deleteProduct'>
                    	<c:param name="productId" value="${item.product.productId}"></c:param>
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