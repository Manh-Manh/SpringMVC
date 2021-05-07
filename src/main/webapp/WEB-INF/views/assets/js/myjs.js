$(document).ready(function() {
	$('.advSearch').change(function() {
		var map = new Map();
		var data = [];
		$("input:checkbox[name='supplierId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('supplierId', data);
		//alert("The best cricketers are: " + data.join(", "));
		var am;
		$("input:text[name='amount']").each(function() {
			am = $(this).val();
		});
		am=am.replaceAll("Tr","").trim();
		am=am.split("-");
		data = new Array();		
		data.push(1000000*(am[0].trim()));
		data.push(1000000*(am[1].trim()));
		map.set('amount', data);
		
		// Machine
		data= new Array();
		$("input:checkbox[name='machineId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('machineId', data);
		// gender
		data= new Array();
		$("input:checkbox[name='gender']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('gender', data);
		// Strap
		data= new Array();
		$("input:checkbox[name='strapId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('strapId', data);
		data = new Array();
		data.push('1');
		map.set('page', data);
		//console.log(map);
		array = Array.from(map, ([name, value]) => ({ name, value }));

		//console.log(array);
		console.log(JSON.stringify(array));
		$.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/advSearch?page=1",
            data : JSON.stringify(array),
            dataType : 'json',
			beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
						xhr.responseType = 'json';
			        },
            timeout : 100000,
            success : function(d) {
				window.location.reload();
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				//window.location.reload();
				
				window.location.href='http://localhost:8088/SpringMVC/app-view';
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
	});
	
	
	//link
	$('.advSearch-link').click(function() {
		
		var map = new Map();
		var data = [];
		$("input:checkbox[name='supplierId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('supplierId', data);
		//alert("The best cricketers are: " + data.join(", "));
		var am;
		$("input:text[name='amount']").each(function() {
			am = $(this).val();
		});
		if(typeof am!="undefined" && am){
			am=am.replaceAll("Tr","");
			am=am.split("-");
			data = new Array();		
			data.push(1000000*(am[0].trim()));
			data.push(1000000*(am[1].trim()));
			map.set('amount', data);
		}
		// gender
		data= new Array();
		$("input:checkbox[name='gender']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('gender', data);
		// Machine
		data= new Array();
		$("input:checkbox[name='machineId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('machineId', data);
		
		// Strap
		data= new Array();
		$("input:checkbox[name='strapId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('strapId', data);
		// search string
		data= new Array();
		$("input[id='searchString']").each(function() {
			data.push($(this).val().trim());
		});
		map.set('searchString', data);
		// page
		data= new Array();
		var p=$(this).val();
		data.push(p);
		map.set('page',data);
		
		
		//console.log(map);
		array = Array.from(map, ([name, value]) => ({ name, value }));

		//console.log(array);
		console.log(JSON.stringify(array));
		$.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/advSearch",
            data : JSON.stringify(array),
            dataType : 'json',
			beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
						xhr.responseType = 'json';
			        },
            timeout : 100000,
            success : function(d) {
				window.location.href='http://localhost:8088/SpringMVC/app-view';
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				window.location.href='http://localhost:8088/SpringMVC/app-view';
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
	});
	
	//Slide
	$("#slider-range").slider({
		change: function(event, ui){
        
		var map = new Map();
		var data = [];
		$("input:checkbox[name='supplierId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('supplierId', data);
		//alert("The best cricketers are: " + data.join(", "));
		var am;
		$("input:text[name='amount']").each(function() {
			am = $(this).val();
		});
		am=am.replaceAll("Tr","").trim();
		am=am.split("-");
		data = new Array();		
		data.push(1000000*(am[0].trim()));
		data.push(1000000*(am[1].trim()));
		map.set('amount', data);
		// gender
		data= new Array();
		$("input:checkbox[name='gender']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('gender', data);
		// Machine
		data= new Array();
		$("input:checkbox[name='machineId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('machineId', data);
		
		// Strap
		data= new Array();
		$("input:checkbox[name='strapId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('strapId', data);
		data = new Array();
		data.push('1');
		map.set('page', data);
		//console.log(map);
		array = Array.from(map, ([name, value]) => ({ name, value }));

		//console.log(array);
		console.log(JSON.stringify(array));
		$.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/advSearch?page=1",
            data : JSON.stringify(array),
            dataType : 'json',
			beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
						xhr.responseType = 'json';
			        },
            timeout : 100000,
            success : function(d) {
				window.location.reload();
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				//window.location.reload();
				
				window.location.href='http://localhost:8088/SpringMVC/app-view';
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
      }
	
	});
	
	
	// Update Cart
	$('.updateCart').click(function() {
		var map = new Map();
		var data = [];
		$("input:text[name='productId']").each(function() {
			data.push($(this).val().trim());
		});
		map.set('productId', data);
		//alert("The best cricketers are: " + data.join(", "));
		
		data= new Array();
		$("input[name='cartQuantity']").each(function() {
			data.push($(this).val());
		});
		map.set('cartQuantity', data);
		
		// Strap
		data= new Array();
		$("input[name='action']").each(function() {
			data.push($(this).val());
		});
		map.set('action', data);
		
		array = Array.from(map, ([name, value]) => ({ name, value }));

		//console.log(array);
		console.log(JSON.stringify(array));
		$.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/updateCart",
            data : JSON.stringify(array),
            dataType : 'json',
			beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
						xhr.responseType = 'json';
			        },
            timeout : 100000,
            success : function(d) {
				window.location.reload();
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				//window.location.reload();
				
				window.location.href='http://localhost:8088/SpringMVC/app-view/cart';
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
	});
	
	// Xoa san pham
	$('.delCart').click(function() {
		var map = new Map();
		var data = [];
		var id=$(this).val();
		data.push(id);
		map.set('productId',data);
		data= new Array();
		data.push('0');
		map.set('cartQuantity', data);
				
		array = Array.from(map, ([name, value]) => ({ name, value }));

		//console.log(array);
		console.log(JSON.stringify(array));
		$.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/updateCart",
            data : JSON.stringify(array),
            dataType : 'json',
			beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
						xhr.responseType = 'json';
			        },
            timeout : 100000,
            success : function(d) {
				window.location.reload();
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				//window.location.reload();
				
				window.location.href='http://localhost:8088/SpringMVC/app-view/cart';
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
	});
	
	// so luong k dc nho hon 1
	$("input[name='cartQuantity']").change(function(){
		var v =$(this).val();
		if(v<1){
			$(this).val(1);
		}
	});
	
	//
	$('.supplierLogo').click(function(){
		
		var map = new Map();
		var data = [];
		var sup = $(this).find("input[name='supplierLogo-id']");
		$(sup).each(function() {
			data.push($(this).val());
		});
		map.set('supplierId',data);
		data= new Array();			
		data.push("1");
		map.set("page", data);	
		array = Array.from(map, ([name, value]) => ({ name, value }));
		
		$.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/advSearch",
            data : JSON.stringify(array),
            dataType : 'json',
			beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
						xhr.responseType = 'json';
			        },
            timeout : 100000,
            success : function(d) {
				window.location.href='http://localhost:8088/SpringMVC/app-view';
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				//window.location.reload();
				
				window.location.href='http://localhost:8088/SpringMVC/app-view';
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
	});
	
	$('.genderLogo').click(function(){
		
		var map = new Map();
		var data = [];
		var gender = $(this).attr('data-val');
	/*	$(sup).each(function() {
			data.push($(this).val());
		});*/
		data.push(gender);
		map.set('gender',data);
		data= new Array();			
		data.push("1");
		map.set("page", data);	
		array = Array.from(map, ([name, value]) => ({ name, value }));
		
		$.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/advSearch",
            data : JSON.stringify(array),
            dataType : 'json',
			beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
						xhr.responseType = 'json';
			        },
            timeout : 100000,
            success : function(d) {
				window.location.href='http://localhost:8088/SpringMVC/app-view';
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				//window.location.reload();
				
				window.location.href='http://localhost:8088/SpringMVC/app-view';
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
	});
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
	
	$("#urlCancel").click(function(e){
		if(!confirm('Bạn có chắc muốn hủy không?')){
			e.preventDefault();
		}else{
			$(this).attr('href',$(this).attr('data-url'));	
		}
//		alert($(this).attr('data-url'));
		
	});
	//alert($("#slider").slider('values'));
});
/*// ajax Search
function advSearch(map) {
        //var data = {}
        //data["query"] = $("#query").val();
        console.log(JSON.stringify(map));
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/SpringMVC/app-view/advSearch",
            data : JSON.stringify(map),
            dataType : 'json',
            timeout : 100000,
            success : function(data) {
                console.log("SUCCESS: ", data);
                console.log(data);
            },
            error : function(e) {
                console.log("ERROR: ", e);
                console.log(e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
}*/