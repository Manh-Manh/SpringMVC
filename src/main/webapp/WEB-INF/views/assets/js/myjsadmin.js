// preview anh
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#img-avatar').attr('src', e.target.result);
			$('#img-product').attr('src', e.target.result);
		}

		reader.readAsDataURL(input.files[0]); // convert to base64 string
	}
}

$("#fileInput").change(function() {
	readURL(this);
});
$(".inpNumber").change(function() {
	var v = $(this).val();
	if (v < 0) {
		$(this).val(0);
	}
});

$(".inpDiscount").change(function() {
	var v = $(this).val();
	if (v > 100) {
		$(this).val(100);
	}
});

$(".urlConfirm").click(function(e) {
	if (!confirm('Bạn chắc chắn muốn thực hiện chứ?'))  {
		e.preventDefault();
	} else {
		$(this).attr('href', $(this).attr('data-url'));
	}
	//		alert($(this).attr('data-url'));

});

function doSearch(e) {
	var gender = $("#gender").val();
	var sup = $("#supplier").val();
	var status = $("#status").val();
	if(gender!=""||sup!=""||status!=""){
		return true;
	}
	window.location.href="http://localhost:8088/SpringMVC/admin/manageProduct";
	return false;
}
function doSearchOrder(e) {
	var gender = $("#gender").val();
	var sup = $("#supplier").val();
	var status = $("#status").val();
	if(gender!=""||sup!=""||status!=""){
		return true;
	}
	window.location.href="http://localhost:8088/SpringMVC/admin/manageProduct";
	return false;
}