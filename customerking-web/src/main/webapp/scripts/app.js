var customerModule = (function () {
	
	var addCustomer = function(){
		var customerDTO = createDTO();
		$.ajax({
			type: "POST",
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			dataType: 'json',
			data: JSON.stringify(customerDTO),
			url:  resourceURL + '/customer/addCustomer',
			success: function (result) {
				if(result.hasAnyError === true){
					error(result.messages);
				}else{
					success(result.messages[0]);
					resetForm();
				}
			},
			failure: function (result) {
				console.log(result);
			},
			error: function (result) {
				console.log(result);
			}
		});
	}

	var createDTO = function(){
		var customerDTO = new Object();
		customerDTO.id = $("#id").val();
		customerDTO.name = $("#name").val();
		customerDTO.email = $("#email").val();
		customerDTO.address = $("#address").val();
		customerDTO.gender = $("input[name='gender-radio']:checked").val();
		customerDTO.dob = $("#dob").val();
		customerDTO.profession = $("#profession").val();
		return customerDTO;
	}
	
	var getAllCustomers = function(){
		var url = resourceURL + '/customer/getCustomers';
		var gridColumns= [];
		var source = {
			datatype: "json",
			url: url,
			root: 'results',
			sort: function (field, dir) {
                $("#jqxgrid").jqxGrid('updatebounddata', 'sort');
            },
            beforeprocessing: function(result){	
        		source.totalrecords = Number(result.data[1]);
        		if(!jQuery.isEmptyObject(result.data[0]) && !jQuery.isEmptyObject(result.data[0].columnNames)){
        			var gridData = result.data[0].gridRecords;
            		for (var eachColumn = 0; eachColumn < result.data[0].columnNames.length; eachColumn++) {
            			gridColumns.push({
            				dataField: result.data[0].columnNames[eachColumn],
            				text: result.data[0].columnNames[eachColumn],
            				//width: '10%'
            			});
            		}
            		var linkrenderer = function (row, columnfield, value, defaulthtml, columnproperties, rowdata) {
            			return "<div style='text-align: center;'>" +			
            			"<a type='button' class='btn btn-sm btn-secondary mr-2' href=\"/CustomerKing/customer/home/"+rowdata.id+"\" id='editCustomer'>Edit</a>"  +
            			"<button type='button' class='btn btn-sm btn-danger' onClick=\"deleteCustomer('" + (rowdata.id) + "')\" id='deleteCustomer'>Delete</button></div>";
            		}
            		gridColumns.push({
            			text: '',
            			datafield: 'unhide',
            			cellsrenderer: linkrenderer,
            			menu: false,
            			editable:false,
            			align: 'center',
            			width: '20%'
            		});
            		return gridData;
        		}
			},
		};

		var adapter = new $.jqx.dataAdapter(source);
		
		$("#jqxgrid").jqxGrid({
			width: '1050',
			filterable: false,
			source: adapter,
			sortable: true,
			pageable: true,
			autoheight: true,
			columnsresize: true,
			pagesize: 5,
			pagermode: 'simple',
			columns: gridColumns,
			autoshowloadelement: false,
            virtualmode: true,
            rendergridrows: function (data) {
            	return data.data;
            },
		});
		
		$("#jqxgrid").bind('bindingcomplete', function () {
			$("#jqxgrid").jqxGrid('autoresizecolumns');
		}); 
		
	}
	
	var deleteCustomer = function(id){
		$.ajax({
			type: "POST",
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			dataType: 'json',
			data: JSON.stringify(),
			url:  resourceURL + '/customer/deleteCustomer/'+id,
			success: function (result) {
				if(result.hasAnyError === true){
					 error(result.messages);
				}else{
					success(result.messages[0]);
					getAllCustomers();
				}
			},
			failure: function (result) {
				console.log(result);
			},
			error: function (result) {
				console.log(result);
			}
		});
	}
	
	var error = function(errorList){
		var htmlCode = "";
		if(errorList!=null){
			if(errorList.length > 1){
				htmlCode = '<ul>';
				for (var index = 0; index < errorList.length; index++) {
					htmlCode = htmlCode + '<li>'
							+ errorList[index] + '</li>';
				}
				htmlCode = htmlCode + '</ul>'
			}else{
				htmlCode = errorList[0]
			}	
		}		

		$(".failureId").html(htmlCode);
		$(".failureId").removeClass("nodisplay");
		$(".failureId").show();
		$(".failureId").delay(4000).fadeOut('slow');
	};

	var success = function(msg){
		$(".successId").html(msg);
		$(".failureId").addClass("nodisplay");
		$(".successId").removeClass("nodisplay");
		$('.successId').delay(4000).fadeOut('slow');
		$(".successId").show();
		window.setTimeout(function() {
			$(".successId").addClass("nodisplay");
		},7000);
	};
	
	var resetForm= function(){
		$("#id").val("0");
		$("#name").val("");
		$("#email").val("");
		$("#address").val("");
		$("#gender").val("");
		$("#dob").val("");
		$("#profession").val("");
	}
	
	var searchCustomer = function(){
		$.ajax({
			type: "POST",
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			dataType: 'json',
			data: JSON.stringify(),
			url:  resourceURL + '/customer/searchCustomer?searchText='+$("#searchKeyword").val(),
			success: function (result) {
				if(!jQuery.isEmptyObject(result.data[0]) && !jQuery.isEmptyObject(result.data[0].columnNames)){
					var totalrecords = Number(result.data[1]);
	                var gridData = result.data[0].gridRecords;
	                var gridColumns = [];
	                for (var eachColumn = 0; eachColumn < result.data[0].columnNames.length; eachColumn++) {
	                	gridColumns.push({
	                		dataField: result.data[0].columnNames[eachColumn],
	                		text: result.data[0].columnNames[eachColumn]
	                	});
	                }
	                var linkrenderer = function (row, columnfield, value, defaulthtml, columnproperties, rowdata) {
	                	return "<div style='text-align: center;'>" +			
	    				"<a type='button' class='btn btn-sm btn-secondary mr-2' href=\"/CustomerKing/customer/home/"+rowdata.id+"\" id='editCustomer'>Edit</a>"  +
	    				"<button type='button' class='btn btn-sm btn-danger' onClick=\"deleteCustomer('" + (rowdata.id) + "')\" id='deleteCustomer'>Delete</button></div>";
	        		}
	                gridColumns.push({
	        			text: '',
	        			datafield: 'unhide',
	        			cellsrenderer: linkrenderer,
	        			menu: false,
	        			editable:false,
	        			align: 'center',
	        			width: '20%'
	        		});
	                var source = {
	            			localdata: gridData,
	            			datatype: "json",
	            			totalrecords: totalrecords
	            		};
	                var adapter = new $.jqx.dataAdapter(source);
	        		
	        		$("#searchresultjqxgrid").jqxGrid({
	        			width: '1050',
	        			filterable: false,
	        			source: adapter,
	        			sortable: true,
	        			pageable: true,
	        			autoheight: true,
	        			columnsresize: true,
	        			pagesize: 5,
	        			pagermode: 'simple',
	        			columns: gridColumns,
	        			autoshowloadelement: false,
	                    virtualmode: true,
	                    rendergridrows: function (data) {
	                    	return data.data;
	                    },
	        		});
				}
			},
			failure: function (result) {
				console.log(result);
			},
			error: function (result) {
				console.log(result);
			}
		});
	}
	
	return {
		addCustomer: addCustomer,
		getAllCustomers: getAllCustomers,
		deleteCustomer: deleteCustomer,
		searchCustomer: searchCustomer
	}
	
})();

$("#addCustomer").click(function (e){
	customerModule.addCustomer();
})

function deleteCustomer(id){
	customerModule.deleteCustomer(id);
}

$("#search").click(function (e){
	customerModule.searchCustomer();
})