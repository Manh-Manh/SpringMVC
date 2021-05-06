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
