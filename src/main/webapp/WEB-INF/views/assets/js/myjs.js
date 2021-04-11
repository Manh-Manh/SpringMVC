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
		
		// Strap
		data= new Array();
		$("input:checkbox[name='strapId']:checked").each(function() {
			data.push($(this).val().trim());
		});
		map.set('strapId', data);
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
				window.location.reload();
				console.log("SEUCCESS");
                console.log("SUCCESS: ", d);
            },
            error : function(e) {
				window.location.reload();
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