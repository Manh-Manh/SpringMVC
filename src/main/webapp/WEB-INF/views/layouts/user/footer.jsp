<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<!-- Start Footer Section -->
    <footer class="footer-section footer-bg section-top-gap-100">
        <div class="footer-wrapper">
        <!-- Start Footer Top -->
        <div class="footer-top">
        <div class="container">
            <div class="row mb-n6">
                <!-- <div class="col-lg-3 col-sm-6 mb-6">
                    <!-- Start Footer Single Item -
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="0">
                        <h5 class="title">INFORMATION</h5>
                        <ul class="footer-nav">
                            <li><a href="#">Delivery Information</a></li>
                            <li><a href="#">Terms & Conditions</a></li>
                            <li><a href="contact-us.html">Contact</a></li>
                            <li><a href="#">Returns</a></li>
                        </ul>
                    </div>
                    <!-- End Footer Single Item -
                </div>-->
                <div class="col-lg-4 col-sm-4 mb-4">
                    <!-- Start Footer Single Item -->
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="200">
                        <h5 class="title">TÀI KHOẢN</h5>
                        <ul class="footer-nav"> 
                            <li><a href="<c:url value='/app-view/myAccount' /> ">Tài khoản </a></li>
                            <li><a href="<c:url value='/app-view/checkOut' /> ">Thanh toán</a></li>
                            <li><a href="<c:url value='/app-view/cart' /> ">Giỏ hàng</a></li>
                            <li><a href="<c:url value='/app-view/logout' /> ">Đăng xuất</a></li>
                        </ul>
                    </div>
                    <!-- End Footer Single Item -->
                </div>
                <div class="col-lg-4 col-sm-4 mb-4">
                    <!-- Start Footer Single Item -->
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="400">
                        <h5 class="title">Thương hiệu</h5>
                        <ul class="footer-nav">
                            <c:forEach var = "sup" items="${ supplier }">
                                        <li>
                                            <label class="checkbox-default" for="brakeParts">
                                                <input type="checkbox" value = "${ sup.supplierId } " name="supplierId" class="advSearch"
                                                <c:if test="${mapSearch.size()>0&& mapSearch.get('supplierId')!=null &&  mapSearch.get('supplierId').contains(sup.supplierId) }">
                                                 	<c:out value="checked" />
                                                 </c:if>
                                                >
                                                <span><c:out value = "${ sup.supplierName } " /></span>
                                            </label>
                                        </li>
                                </c:forEach>
                        </ul>
                    </div>
                    <!-- End Footer Single Item -->
                </div>
                <div class="col-lg-4 col-sm-4 mb-4">
                    <!-- Start Footer Single Item -->
                    <div class="footer-widget-single-item footer-widget-color--golden" data-aos="fade-up"  data-aos-delay="600">
                        <h5 class="title">Thông tin</h5>
                        <div class="footer-about">
                            <table>
                            <tbody>
                                <tr style="ine-height: 40px;">
                                    <td style="width:30%;text-align:left;"><b>Họ tên</b></td>
                                    <td style="text-align:left;">Nguyễn Đình Mạnh</td>
                                </tr>
                                <tr style="ine-height: 40px;">
                                    <td style="width:30%;text-align:left;"><b>Mã sinh viên</b></td>
                                    <td style="text-align:left;">171202958</td>
                                </tr>
                                <tr style="ine-height: 40px;">
                                    <td style="width:30%;text-align:left;"><b>Lớp</b></td>
                                    <td style="text-align:left;">Công nghệ thông tin 4</td>
                                </tr>
                                <tr style="ine-height: 40px;">
                                    <td style="width:30%;text-align:left;"><b>Khóa</b></td>
                                    <td style="text-align:left;">58</td>
                                </tr>
                            </tbody>
                        </table>
                        </div>
                    </div>
                    <!-- End Footer Single Item -->
                </div>
            </div>
        </div>
        </div>
        <!-- End Footer Top -->

        <!-- Start Footer Center -->
        
        <!-- Start Footer Bottom -->
        </div>
    </footer>
    <!-- End Footer Section -->

    <!-- material-scrolltop button -->
    <button class="material-scrolltop" type="button"></button>

    <!-- Start Modal Add cart -->
    <div class="modal fade" id="modalAddcart" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col text-right">
                                <button type="button" class="close modal-close" data-bs-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true"> <i class="fa fa-times"></i></span>
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="modal-add-cart-product-img">
                                            <img class="img-fluid" src="assets/images/product/default/home-1/default-1.jpg" alt="">
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="modal-add-cart-info"><i class="fa fa-check-square"></i>Added to cart successfully!</div>
                                        <div class="modal-add-cart-product-cart-buttons">
                                            <a href="cart.html">View Cart</a>
                                            <a href="checkout.html">Checkout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5 modal-border">
                                <ul class="modal-add-cart-product-shipping-info">
                                    <li> <strong><i class="icon-shopping-cart"></i> There Are 5 Items In Your Cart.</strong></li>
                                    <li> <strong>TOTAL PRICE: </strong> <span>$187.00</span></li>
                                    <li class="modal-continue-button"><a href="#" data-bs-dismiss="modal">CONTINUE SHOPPING</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Modal Add cart -->


    <!-- ::::::::::::::All JS Files here :::::::::::::: -->
    <!-- Global Vendor, plugins JS -->
    <script src="<c:url value='/assets/js/vendor/modernizr-3.11.2.min.js'/> "></script>
    <script src="<c:url value='/assets/js/vendor/jquery-3.5.1.min.js'/> "></script>
    <script src="<c:url value='/assets/js/vendor/jquery-migrate-3.3.0.min.js'/> "></script>
    <script src="<c:url value='/assets/js/vendor/popper.min.js'/> "></script>
    <script src="<c:url value='/assets/js/vendor/bootstrap.min.js'/> "></script>
    <script src="<c:url value='/assets/js/vendor/jquery-ui.min.js'/> "></script> 

    <!--Plugins JS-->
    <script src="<c:url value='/assets/js/plugins/swiper-bundle.min.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/material-scrolltop.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/jquery.nice-select.min.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/jquery.zoom.min.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/venobox.min.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/jquery.waypoints.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/jquery.lineProgressbar.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/aos.min.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/jquery.instagramFeed.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/ajax-mail.js'/> "></script>
    <!-- Use the minified version files listed below for better performance and remove the files listed above -->
    <script src="<c:url value='/assets/js/vendor/vendor.min.js'/> "></script>
    <script src="<c:url value='/assets/js/plugins/plugins.min.js'/> "></script> 
    <!-- Main JS -->
    <script src="<c:url value='/assets/js/main.js'/> "></script>
    
    <script src="<c:url value='/assets/js/myjs.js'/>"></script>
</body>

<!-- Mirrored from htmldemo.hasthemes.com/hono/hono/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 06 Jan 2021 00:31:38 GMT -->
</html>