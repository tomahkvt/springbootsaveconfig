var regexpip = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
		+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

var terminalServerApp = angular.module('terminalServerApp', []);
terminalServerApp.controller('updatevalidateCtrl', function($scope) {
	$scope.regex = regexpip;
});

terminalServerApp.controller('addvalidateCtrl', function($scope) {
	$scope.regex = regexpip;
});

$(document).ready(
		function() {

			if (typeof idupdate === 'undefined') {
				$("#divUpdate").hide();
				$("#divAdd").hide();
				$("#hrefselectdevice").hide()
			} else {
				$("#divUpdate").show();
				$("#divAdd").hide();
				$("#button_add").hide();
				$("#button_edit").hide();
				$("#button_del").hide();
				$("#button_test").hide();
				$("#hrefselectdevice").show()
				getData(idupdate);
			}

			var array_for_delete_command = [];
			var array_for_delete_more = [];

			$('#terminalserver_table').DataTable({
				stateSave : true,

				"ajax" : {
					"url" : "api/terminalserver/list",
					"dataSrc" : ""
				},
				"columns" : [{
					"data" : "deviceName"
				}, {
					"data" : "deviceIp"
				}

				]
			});

			$('#terminalserver_table tbody').on('click', 'tr', function() {
				var table = $('#terminalserver_table').DataTable();
				if ($(this).hasClass('selected')) {
					$(this).removeClass('selected');
				} else {
					table.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}

				if ($('#divUpdate').is(':visible') == true) {

					var terminalserver = table.row('.selected').data();
					edit_refresh(terminalserver);

				}
			});

			$('#button_add').on('click', function() {
				if ($("#divUpdate").is(':hidden') == false) {
					$("#divUpdate").hide();
				}
				$("#divAdd").show()
				$('#button_del').show();

			});

			$('#button_del').click(function() {
				var table = $('#terminalserver_table').DataTable();
				$("#divUpdate").hide();
				var id_delete = table.row('.selected').id();
				if (id_delete == null) {
					alert("Select Terminal Server");
					return;
				}

				$.ajax({
					type : "DELETE",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					url : "api/terminalserver/delete/" + id_delete,
					success : function(response) {
						console.log(response);
						console.log(response.status);
						console.log(response.result);
						if (response.status == "FAIL") {
							$('#json_result_error').html(response.result);
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

			$('#btn_update').click(
					function() {
						var uterminalserver = {
							DT_RowId : $('#uterminalserver_id').val(),
							deviceName : $('#uterminalserver_name').val(),
							deviceIp : $('#uterminalserver_ip').val(),
							username1 : $('#uterminalserver_username1').val(),
							passw1 : $('#u_input_terminalserver_passw1').val(),
							promt1 : $('#uterminalserver_promt1').val(),
							username2 : $('#uterminalserver_username2').val(),
							passw2 : $('#u_input_terminalserver_passw2').val(),
							promt2 : $('#uterminalserver_promt2').val(),

						};
						var table = $('#terminalserver_table').DataTable();
						var data = JSON.stringify(uterminalserver);
						$.ajax({
							type : "PUT",
							url : "api/terminalserver",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$('#json_result_status').text("");
								$('#json_result_error').text("");
								$('#json_result_status').text(data.status);

								if (data.status == "SUCCESS") {

									var terminalserverForTable = {
										"DT_RowId" : data.result.DT_RowId,
										"deviceName" : data.result.deviceName,
										"deviceIp" : data.result.deviceIp

									}
									console.log(terminalserverForTable);
									if (typeof terminalserver_table !== "undefined") {
										table.row("#" + data.result.DT_RowId).data(
												terminalserverForTable).draw();
									}
								}
								if (data.status == "FAIL") {
									errorInfo = "";
									for (i = 0; i < data.result.length; i++) {
										errorInfo += "<br>" + data.result[i].field + ". "
												+ data.result[i].defaultMessage;
									}
									$('#json_result_error').html(
											"Please correct following errors: " + errorInfo);

									$('#json_result_error').show('slow');
								}
							}
						});

					})

			$('#btn_clone').click(
					function() {
						var uterminalserver = {
							DT_RowId : $('#uterminalserver_id').val(),
							deviceName : $('#uterminalserver_name').val(),
							deviceIp : $('#uterminalserver_ip').val(),
							username1 : $('#uterminalserver_username1').val(),
							passw1 : $('#u_input_terminalserver_passw1').val(),
							promt1 : $('#uterminalserver_promt1').val(),
							username2 : $('#uterminalserver_username2').val(),
							passw2 : $('#u_input_terminalserver_passw2').val(),
							promt2 : $('#uterminalserver_promt2').val(),
						};
						var table = $('#terminalserver_table').DataTable();
						var data = JSON.stringify(uterminalserver);
						// console.log(data);
						$.ajax({
							type : "PUT",
							url : "api/terminalserver/clone",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$('#json_result_status').text("");
								$('#json_result_error').text("");
								$('#json_result_status').text(data.status);

								if (data.status == "SUCCESS") {

									var terminalserverForTable = {
										"DT_RowId" : data.result.DT_RowId,
										"deviceName" : data.result.deviceName,
										"deviceIp" : data.result.deviceIp

									}
									if (typeof terminalserver_table !== "undefined") {
										table.row.add(terminalserverForTable).draw();
									}

								}
								if (data.status == "FAIL") {
									errorInfo = "";
									for (i = 0; i < data.result.length; i++) {
										errorInfo += "<br>" + data.result[i].field + ". "
												+ data.result[i].defaultMessage;
									}
									$('#json_result_error').html(
											"Please correct following errors: " + errorInfo);

									$('#json_result_error').show('slow');
								}
							}
						});

					})

			$('#btn_create').click(
					function() {
						var cterminalserver = {
							deviceName : $('#aterminalserver_name').val(),
							deviceIp : $('#aterminalserver_ip').val(),
							username1 : $('#aterminalserver_username1').val(),
							passw1 : $('#a_input_terminalserver_passw1').val(),
							promt1 : $('#aterminalserver_promt1').val(),
							username2 : $('#aterminalserver_username2').val(),
							passw2 : $('#a_input_terminalserver_passw2').val(),
							promt2 : $('#aterminalserver_promt2').val(),

						};

						var table = $('#terminalserver_table').DataTable();
						var data = JSON.stringify(cterminalserver);
						// console.log(data);
						$.ajax({
							type : "POST",
							url : "api/terminalserver",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$('#json_result_status').text("");
								$('#json_result_error').text("");
								$('#json_result_status').text(data.status);

								if (data.status == "SUCCESS") {

									var terminalserverForTable = {
										"DT_RowId" : data.result.DT_RowId,
										"deviceName" : data.result.deviceName,
										"deviceIp" : data.result.deviceIp

									}
									console.log(terminalserverForTable);
									table.row.add(terminalserverForTable).draw();
								}
								if (data.status == "FAIL") {
									errorInfo = "";
									for (i = 0; i < data.result.length; i++) {
										errorInfo += "<br>" + data.result[i].field + ". "
												+ data.result[i].defaultMessage;
									}
									$('#json_result_error').html(
											"Please correct following errors: " + errorInfo);

									$('#json_result_error').show('slow');
								}
							}
						});

					})

			$('#btn_compare1').click(
					function() {
						var uterminalserver = {
							DT_RowId : $('#uterminalserver_id').val(),
							passw1 : $('#u_input_terminalserver_passw1').val(),
						};

						var data = JSON.stringify(uterminalserver);
						console.log(data);
						$.ajax({
							type : "PUT",
							url : "api/terminalserver/comparepassword1",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$('#json_result_status').text("");
								$('#json_result_error').text("");
								if (data.status != "FAIL") {
									$('#u_span_terminalserver_passw1').text(data.status);
								}

								if (data.status == "FAIL") {
									errorInfo = "";
									for (i = 0; i < data.result.length; i++) {
										errorInfo += "<br>" + data.result[i].field + ". "
												+ data.result[i].defaultMessage;
									}
									$('#json_result_error').html(
											"Please correct following errors: " + errorInfo);

									$('#json_result_error').show('slow');
								}
							}
						});
					})

			$('#btn_compare2').click(
					function() {
						var uterminalserver = {
							DT_RowId : $('#uterminalserver_id').val(),
							passw2 : $('#u_input_terminalserver_passw2').val(),
						};

						var data = JSON.stringify(uterminalserver);
						console.log(data);
						$.ajax({
							type : "PUT",
							url : "api/terminalserver/comparepassword2",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$('#json_result_status').text("");
								$('#json_result_error').text("");
								if (data.status != "FAIL") {
									$('#u_span_terminalserver_passw2').text(data.status);
								}

								if (data.status == "FAIL") {
									errorInfo = "";
									for (i = 0; i < data.result.length; i++) {
										errorInfo += "<br>" + data.result[i].field + ". "
												+ data.result[i].defaultMessage;
									}
									$('#json_result_error').html(
											"Please correct following errors: " + errorInfo);

									$('#json_result_error').show('slow');
								}
							}
						});
					})

			$("#button_edit").on('click', function() {
				var table = $('#terminalserver_table').DataTable();
				// $('#button_del').hide();
				if ($("#divAdd").is(':visible') == true) {
					$("#divAdd").hide();

				}

				if ($("#divUpdate").is(':visible') == false) {
					$("#divUpdate").show();
					var terminalserver = table.row('.selected').data();
					if (terminalserver == null) {
						$("#divUpdate").hide();
						$('#button_del').show();
						alert("Select Terminal Server");

					} else {
						edit_refresh(terminalserver);
					}
				} else {
					$("#divUpdate").hide();
				}

			});

			$("#button_test").on(
					'click',
					function() {
						var table = $('#terminalserver_table').DataTable();
						var device = table.row('.selected').data();
						if (device == null) {
							alert("Select Terminal Server");
						} else {
							console.log(device);
							window.open('api/terminalserver/testTerminalServer/' + device.DT_RowId,
									'_blank');
						}

					});

			function edit_refresh(device) {
				var table = $('#terminalserver_table').DataTable();
				var id = table.row('.selected').id();
				$('#json_result_status').text("");
				$('#u_input_terminalserver_passw1').val("");
				$('#u_input_terminalserver_passw2').val("");
				getData(device.DT_RowId);
			}

			function getData(id) {
				$.getJSON("api/terminalserver/getDTO/" + id, function(terminalserver) {

					$("#uterminalserver_id").val(terminalserver.DT_RowId);

					$("#uterminalserver_name").val(terminalserver.deviceName).change();

					$("#uterminalserver_ip").val(terminalserver.deviceIp).change();
					$("#uterminalserver_username1").val(terminalserver.username1).change();
					$("#u_span_terminalserver_passw1").text(terminalserver.passw1);
					$("#uterminalserver_promt1").val(terminalserver.promt1).change();
					$("#uterminalserver_username2").val(terminalserver.username2);
					$("#u_span_terminalserver_passw2").text(terminalserver.passw2);
					$("#uterminalserver_promt2").val(terminalserver.promt2);
				});
			}
		});
