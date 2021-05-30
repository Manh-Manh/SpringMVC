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
              <!-- <li class="breadcrumb-item"><a href="#">Thêm mới</a></li> -->
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
            <c:url var="urlSearch" value='/admin/doSearchOrder'>
              </c:url>
              <form:form action="${urlSearch }" method="POST" modelAttribute="orderSearch">
                <div class="row">
                    <div class="col-md-10 offset-md-1">
                        <div class="row">
                           
                            <div class="col-4">
                                <div class="form-group">
					                <label for="inputDescription">Trạng thái</label>
					                <form:select id="status" path="status" class="form-control custom-select" >
					                  <form:option selected="selected" value="" label="__Chọn__" ></form:option>
					                    <form:option value="2" label="Chưa xử lý" ></form:option>
										<form:option value="3" label="Đã xử lý" ></form:option>
										<form:option value="4" label="Đã hủy" ></form:option>
					                </form:select>            
					              </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
					                <label for="supplier">Từ ngày</label><br>
					                <form:input path="fromDate" type="date" id="fromDate" class="form-control " />
					              </div>
                            </div>
                            
                            <div class="col-4">
                                <div class="form-group">
					                <label for="supplier">Đến ngày</label><br>
					                <form:input path="toDate" type="date" id="toDate" class="form-control" />
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
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
              <div class="card-header">
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                  	<th>Số thứ tự</th>
                    <th>Mã hóa đơn</th>
                    <th>Khách hàng</th>
                    <th>Ngày đặt</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  <tbody>
             <c:set var="stt" value="0"></c:set>
             <c:forEach var = "item" items="${dataList}">
                  <tr>
					<td><c:out value ="${ stt=stt+1 }" /></td>
                    <td><c:out value ="${ item.orderId }" /></td>
                    <td><c:out value ="${ item.user.userName }" /></td>
                    <td><c:out value ="${ item.orderDate }" /></td>
                    <td><c:out value = "${ item.total }" /></td>
                    <td><c:out value = "${ item.statusString }" /></td>
                    
                    <td class="text-center py-0 align-middle">
                    	<c:url var="urlView" value='/admin/viewOrder'>
                    		<c:param name="orderId" value="${item.orderId}"></c:param>
	                    </c:url>	
	                    <c:url var="urlAccept" value='/admin/acceptOrder'>
	                    	<c:param name="orderId" value="${item.orderId}"></c:param>
	                    </c:url>
	                    <c:url var="urlReject" value='/admin/rejectOrder'>
	                    	<c:param name="orderId" value="${item.orderId}"></c:param>
	                    </c:url>
	                    <div class="btn-group btn-group-sm">
                     
                    	<a href="${urlView }" class="btn btn-info" data-toggle="tooltip" data-placement="bottom" title="Xem"><i class="fas fa-eye"></i></a>
                       
                    	<c:if test="${item.status == 2 }">
                    		<a class="urlConfirm btn btn-info"  data-toggle="tooltip" data-placement="bottom" title="Xác nhận" href="#" data-url="${urlAccept }"><i class="fas fa-check"></i></a>
	                    	<a class="urlConfirm btn btn-danger" data-toggle="tooltip" data-placement="bottom" title="Từ chối"  href="#" data-url="${urlReject }"><i class="fas fa-trash"></i></a>
                    	</c:if>
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