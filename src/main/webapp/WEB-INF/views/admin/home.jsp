<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        
<div class="row">
<h3>

</h3>
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-info">
              <div class="inner">
                <h3><c:out value="${listNewOrder.size() }" /></h3>
                <p>Đơn hàng chưa xử lý</p>
              </div>
              <div class="icon">
                <i class="ion ion-bag"></i>
              </div>
              <a href="<c:url value='/admin/manageOrder'></c:url>" class="small-box-footer">Xem ngay <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
              <div class="inner">
                <h3><c:out value="${listDiscount.size() }" /></h3>

                <p>Mã giảm giá</p>
              </div>
              <div class="icon">
                <i class="ion ion-stats-bars"></i>
              </div>
              <a href="<c:url value='/admin/manageDiscount'></c:url>" class="small-box-footer">Xem ngay <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-warning">
              <div class="inner">
                <h3><c:out value="${saleCost}" /> </h3>
                <p>Doanh số trong tháng</p>
              </div>
              <div class="icon">
                <i class="ion ion-person-add"></i>
              </div>
              <c:url var="staURL" value="/admin/statisticProduct"></c:url>
              <a href="${staURL }" class="small-box-footer">Xem ngay<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -
            <div class="small-box bg-danger">
              <div class="inner">
                <h3>65</h3>

                <p>Unique Visitors</p>
              </div>
              <div class="icon">
                <i class="ion ion-pie-graph"></i>
              </div>
              <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            </div>-->
          </div>
          <!-- ./col -->
        </div>
            
           
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>