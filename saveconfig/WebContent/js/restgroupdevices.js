var regexpip = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
		+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

var terminalServerApp = angular.module('deviceApp', []);
terminalServerApp.controller('updatevalidateCtrl', function($scope) {
	$scope.regex = regexpip;
});
terminalServerApp.controller('addvalidateCtrl', function($scope) {
	$scope.regex = regexpip;
});

$(document).ready(function() {
	$("#divUpdate").hide();
	$("#divAdd").hide();

	$('#devicegroup_table').DataTable({
		stateSave : true,

		"ajax" : {
			"url" : "api/groupdevice/tableDTO",
			"dataSrc" : ""
		},
		"columns" : [ {
			"data" : "name"
		} ]
	});

	$('#devicegroup_table tbody').on('click', 'tr', function() {
		var table = $('#devicegroup_table').DataTable();
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		if ($('#divUpdate').is(':visible') == true) {

			var device = table.row('.selected').data();
			edit_refresh(device);

		}
	});

	$("#uaddgroup").click(function() {
		var id;
		var text;
		$('#ugroups_right :selected').each(function() {
			id = $(this).val();
			text = $(this).text();

			$("#ugroups_left").append($('<option>', {
				value : id,
				text : text
			}));
			$(this).remove();
		});
	});

	$("#uremovegroup").click(function() {
		var id;
		var text;
		$('#ugroups_left :selected').each(function() {
			id = $(this).val();
			text = $(this).text();

			$("#ugroups_right").append($('<option>', {
				value : id,
				text : text
			}));
			$(this).remove();
		});
	});

	$("#caddgroup").click(function() {
		var id;
		var text;
		$('#cgroups_right :selected').each(function() {
			id = $(this).val();
			text = $(this).text();

			$("#cgroups_left").append($('<option>', {
				value : id,
				text : text
			}));
			$(this).remove();
		});
	});

	$("#cremovegroup").click(function() {
		var id;
		var text;
		$('#cgroups_left :selected').each(function() {
			id = $(this).val();
			text = $(this).text();

			$("#cgroups_right").append($('<option>', {
				value : id,
				text : text
			}));
			$(this).remove();
		});
	});

	$('#button_del').click(function() {
		var table = $('#devicegroup_table').DataTable();
		$("#divUpdate").hide();
		// $("#divAdd").hide();
		var id_delete = table.row('.selected').id();
		if (id_delete == null)
			return;
		// console.log("delete id= "
		// + id_delete);

		$.ajax({
			type : "DELETE",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			url : "api/groupdevice/delete/" + id_delete,
			success : function(response) {
				console.log(response);
				console.log(response.status);
				console.log(response.result);
				if (response.status == "FAIL") {
					$('#json_result_error').html(response.result);
					// $('#json_result_error').show('slow');
					$('#json_result_status').text(response.status);
				}
				if (response.status == "SUCCESS") {
					$('#json_result_status').text(response.status);
					$('#json_result_error').html(response.result);
					table.row('.selected').remove().draw(false);
				}
			}
		});

	});

	var counter = 1;

	$('#btn_update').click(function() {

		var table = $('#devicegroup_table').DataTable();
		if (!$('#udevicegroup_id').val()) {
			alert("Выберите устройство");
			return;
		}

		var udevicegroup = {
			DT_RowId : $('#udevicegroup_id').val(),
			name : $("#uname_devicegroup").val(),
		};

		// console.log(udevice);
		var data = JSON.stringify(udevicegroup);
		console.log(data);

		$.ajax({
			type : "PUT",
			url : "api/groupdevice",
			data : data,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$('#json_result_status').text("");
				$('#json_result_error').text("");
				$('#json_result_status').text(data.status);

				if (data.status == "SUCCESS") {

					var deviceGrpoupForTable = {
						"DT_RowId" : data.result.DT_RowId,

						"name" : data.result.name

					}

					table.row("#" + data.result.DT_RowId).data(deviceGrpoupForTable).draw();

				}
				if (data.status == "FAIL") {
					errorInfo = "";
					for (i = 0; i < data.result.length; i++) {
						errorInfo += "<br>" + data.result[i].field + ". " + data.result[i].defaultMessage;
					}
					$('#json_result_error').html("Please correct following errors: " + errorInfo);

					$('#json_result_error').show('slow');
				}
			}
		});

	})

	$('#button_add').on('click', function() {
		if ($("#divUpdate").is(':hidden') == false) {
			$("#divUpdate").hide();
		}
		$("#divAdd").show()
		$('#button_del').show();

		$('#cgroups_right').empty();

		$.getJSON("api/device/groups", function(data) {
			$("#cgroups_right").empty();
			$.each(data, function(index, value) {

				$("#cgroups_right").append($('<option>', {
					value : value.DT_RowId,
					text : value.name
				}));
			});
		});

		$.getJSON("api/deviceType", function(data) {
			$("#ctype_device").empty();
			$.each(data, function(index, value) {

				$("#ctype_device").append($('<option>', {
					value : value.id,
					text : value.deviceType
				}));
			});
		});

		$.getJSON("api/template/list", function(data) {
			$("#ctemplate").empty();
			$.each(data, function(index, value) {
				$("#ctemplate").append($('<option>', {
					value : value.DT_RowId,
					text : value.templateName
				}));
			});
		});

	});

	$("#button_edit").on('click', function() {
		var table = $('#devicegroup_table').DataTable();
		if ($("#divAdd").is(':visible') == true) {
			$("#divAdd").hide();

		}

		if ($("#divUpdate").is(':visible') == false) {
			$("#divUpdate").show();
			var devicegroup = table.row('.selected').data();
			if (devicegroup == null) {
				$("#divUpdate").hide();
				$('#button_del').show();
				alert("Выберите группу");

			} else {
				edit_refresh(devicegroup);
			}
		} else {
			$("#divUpdate").hide();
		}

	});

	function edit_refresh(device) {
		var table = $('#devicegroup_table').DataTable();

		id = table.row('.selected').id();
		console.log(id);
		$.getJSON("api/groupdevice/getDTO/" + id, function(devicegroup) {
			$("#udevicegroup_id").val(devicegroup.DT_RowId)
			$("#uname_devicegroup").val(devicegroup.name);
		})

	}
	;

	$("#btn_create").click(function() {
		var table = $('#devicegroup_table').DataTable();
		$('#button_del').show();

		var cdevicegroup = {

			name : $("#cname_devicegroup").val()

		};

		var data = JSON.stringify(cdevicegroup);

		console.log(data);
		$.ajax({
			type : "POST",
			url : "api/groupdevice",
			data : data,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$('#json_result_status').text("");
				$('#json_result_error').text("");
				$('#json_result_status').text(data.status);

				if (data.status == "SUCCESS") {

					var devicegroupForTable = {
						"DT_RowId" : data.result.DT_RowId,
						"name" : data.result.name

					}

					table.row.add(devicegroupForTable).draw();

				}
				if (data.status == "FAIL") {
					errorInfo = "";
					for (i = 0; i < data.result.length; i++) {
						errorInfo += "<br>" + data.result[i].field + ". " + data.result[i].defaultMessage;
					}
					$('#json_result_error').html("Please correct following errors: " + errorInfo);

					$('#json_result_error').show('slow');
				}

			}
		});
	});

	$('.btn_compare').click(function() {
		var value_compare_password = $(this).val();
		var udevice = {
			DT_RowId : $('#udevice_id').val()
		};
		udevice['password' + value_compare_password] = $('#upassword' + value_compare_password).val()

		var data = JSON.stringify(udevice);
		console.log(data);

		$.ajax({
			type : "PUT",
			url : "api/device/comparepassword/" + value_compare_password,
			data : data,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$('#json_result_status').text("");
				$('#json_result_error').text("");
				if (data.status != "FAIL") {
					$('#u_span_password' + value_compare_password).text(data.status);
				}

				if (data.status == "FAIL") {
					errorInfo = "";
					for (i = 0; i < data.result.length; i++) {
						errorInfo += "<br>" + data.result[i].field + ". " + data.result[i].defaultMessage;
					}
					$('#json_result_error').html("Please correct following errors: " + errorInfo);

					$('#json_result_error').show('slow');
				}
			}
		});
	})
});
