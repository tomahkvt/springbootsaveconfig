var regexpip = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
		+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

var terminalServerApp = angular.module('deviceApp', []);
terminalServerApp.controller('updatevalidateCtrl', function($scope) {
	$scope.regex = regexpip;
});

terminalServerApp.controller('addvalidateCtrl', function($scope) {
	$scope.regex = regexpip;
});

$(document).ready(
		function() {
			$("#divUpdate").hide();
			$("#divAdd").hide();

			$('#device_table').DataTable({
				stateSave : true,

				"ajax" : {
					"url" : "api/device/tableDTO",
					"dataSrc" : ""
				},
				"columns" : [{
					"data" : "template.templateName"
				}, {
					"data" : "deviceIp"
				}, {
					"data" : "deviceName"
				}, {
					"data" : "switchEnable"
				}],
				"columnDefs" : [{
					"render" : function(data, type, row) {
						if (data === true) {
							return "Enable";
						} else {
							return "Disable";
						}
					},
					"targets" : -1
				}]
			});

			$("#button_test").on('click', function() {
				var table = $('#device_table').DataTable();
				var device = table.row('.selected').data();

				if (device == null) {
					alert("Select device");
				} else {
					console.log(device);
					window.open('api/device/testDevice/' + device.DT_RowId, '_blank');
				}

			});

			$('#device_table tbody').on('click', 'tr', function() {
				var table = $('#device_table').DataTable();
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
				var table = $('#device_table').DataTable();
				$("#divUpdate").hide();
				// $("#divAdd").hide();
				var id_delete = table.row('.selected').id();
				if (id_delete == null)
					return;
				// console.log("delete id= "
				// + id_delete);

				$.ajax({
					type : "DELETE",
					dataType : 'text',
					url : "api/device/delete/" + id_delete,
					success : function(value) {
						console.log(value);
						table.row('.selected').remove().draw(false);
					}
				});

			});

			var counter = 1;
			$('#btn_update').click(
					function() {

						var table = $('#device_table').DataTable();
						if (!$('#udevice_id').val()) {
							alert("Select Device");
							return;
						}

						var array_groups_left = new Array();
						$('#ugroups_left option').map(function(index, elem) {
							array_groups_left.push({
								DT_RowId : $(elem).val(),
								name : $(elem).text()

							});
						});
						var array_groups_right = new Array();
						$('#ugroups_right option').map(function(index, elem) {
							array_groups_right.push({
								DT_RowId : $(elem).val(),
								name : $(elem).text()

							});
						});

						var udevice = {
							DT_RowId : $('#udevice_id').val(),
							template : {
								DT_RowId : $("#utemplate").val()

							},
							deviceName : $("#uhostname_device").val(),
							deviceIp : $("#uip_device").val(),
							username1 : $("#uusername1").val(),
							password1 : $("#upassword1").val(),
							promt1 : $("#upromt1").val(),
							username2 : $("#uusername2").val(),
							password2 : $("#upassword2").val(),
							promt2 : $("#upromt2").val(),
							username3 : $("#uusername3").val(),
							password3 : $("#upassword3").val(),
							promt3 : $("#upromt3").val(),
							username4 : $("#uusername4").val(),
							password4 : $("#upassword4").val(),
							promt4 : $("#upromt4").val(),

							switchEnable : $("#uenable_device").val(),
							groups : array_groups_left,
							rightGroups : array_groups_right
						};

						var data = JSON.stringify(udevice);
						console.log(data);

						$.ajax({
							type : "PUT",
							url : "api/device",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$('#json_result_status').text("");
								$('#json_result_error').text("");
								$('#json_result_status').text(data.status);

								if (data.status == "SUCCESS") {

									var deviceForTable = {
										"DT_RowId" : data.result.DT_RowId,
										"template" : {
											"templateName" : data.result.template.templateName
										},
										"deviceIp" : data.result.deviceIp,
										"deviceName" : data.result.deviceName,
										"switchEnable" : data.result.switchEnable
									}

									table.row("#" + data.result.DT_RowId).data(deviceForTable)
											.draw();

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
				var table = $('#device_table').DataTable();
				// $('#button_del').hide();

				if ($("#divAdd").is(':visible') == true) {
					$("#divAdd").hide();

				}

				if ($("#divUpdate").is(':visible') == false) {
					$("#divUpdate").show();
					var device = table.row('.selected').data();
					if (device == null) {
						$("#divUpdate").hide();
						$('#button_del').show();
						alert("Select Device");

					} else {
						edit_refresh(device);
					}
				} else {
					$("#divUpdate").hide();
					// $('#button_del').show();
				}

			});

			function edit_refresh(device) {
				var table = $('#device_table').DataTable();

				$.getJSON("api/deviceType", function(data) {
					$("#utype_device").empty();
					$.each(data, function(index, value) {

						$("#utype_device").append($('<option>', {
							value : value.id,
							text : value.deviceType
						}));
					});
				}).done(function() {
					if (device != null)
						$("#utype_device").val(device.idDeviceType);
				})

				$.getJSON("api/template/list", function(data) {
					$("#utemplate").empty();
					$.each(data, function(index, value) {
						$("#utemplate").append($('<option>', {
							value : value.DT_RowId,
							text : value.templateName
						}));
					});
					
				}).done(function() {
					if (device != null)
						$("#utemplate").val(device.template.DT_RowId);
				})

				id = table.row('.selected').id();
				console.log(id);
				$.getJSON("api/device/getDTO/" + id, function(device) {
					$('#upassword1').val("");
					$('#upassword2').val("");
					$('#upassword3').val("");
					$('#upassword4').val("");
					$("#utype_device").val(device.idDeviceType);
					$("#uidTemplate").val(device.template.id);
					$("#udevice_id").val(device.DT_RowId);
					$("#uip_device").val(device.deviceIp).change();
					$("#uhostname_device").val(device.deviceName).change();
					$("#ulogin_device").val(device.deviceUsername);
					$("#upasswd_device").val(device.devicePassw);
					$("#uusername1").val(device.username1);
					$("#u_span_password1").text(device.password1);
					$("#uusername2").val(device.username2);
					$("#u_span_password2").text(device.password2);
					$("#uusername3").val(device.username3);
					$("#u_span_password3").text(device.password3);
					$("#uusername4").val(device.username4);
					$("#u_span_password4").text(device.password4);
					$("#upromt1").val(device.promt1);
					$("#upromt2").val(device.promt2);
					$("#upromt3").val(device.promt3);
					$("#upromt4").val(device.promt4);
					$("#uenable_device").val(device.switchEnable.toString());
					$("#btn_update").val(table.row('.selected').id());
					$('#ugroups_left').empty();
					$.each(device.groups, function(order, object) {
						key = object.DT_RowId;
						value = object.name;
						$('#ugroups_left').append($('<option>', {
							value : key
						}).text(value));
					});
					$('#ugroups_right').empty();
					$.each(device.rightGroups, function(order, object) {
						key = object.DT_RowId;
						value = object.name;
						$('#ugroups_right').append($('<option>', {
							value : key
						}).text(value));
					});
				})

			};

			$("#btn_create").click(function() {
				var table = $('#device_table').DataTable();
				$('#button_del').show();

				var array_groups_left = new Array();
				$('#cgroups_left option').map(function(index, elem) {
					array_groups_left.push({
						DT_RowId : $(elem).val(),
						name : $(elem).text()

					});
				});
				var array_groups_right = new Array();
				$('#cgroups_right option').map(function(index, elem) {
					array_groups_right.push({
						DT_RowId : $(elem).val(),
						name : $(elem).text()

					});
				});

				var cdevice = {
					template : {
						DT_RowId : $("#ctemplate").val(),
						templateName : $("#ctemplate").find('option:selected').text()
					},
					idDeviceType : $("#ctype_device").val(),
					deviceIp : $("#cip_device").val(),
					deviceName : $("#chostname_device").val(),
					deviceUsername : $("#clogin_device").val(),
					devicePassw : $("#cpasswd_device").val(),
					promt1 : $("#cpromt1").val(),
					promt2 : $("#cpromt2").val(),
					promt3 : $("#cpromt3").val(),
					promt4 : $("#cpromt4").val(),
					username1 : $("#cusername1").val(),
					username2 : $("#cusername2").val(),
					username3 : $("#cusername3").val(),
					username4 : $("#cusername4").val(),
					password1 : $("#cpassword1").val(),
					password2 : $("#cpassword2").val(),
					password3 : $("#cpassword3").val(),
					password4 : $("#cpassword4").val(),

					switchEnable : $("#cenable_device").val(),
					groups : array_groups_left,
					rightGroups : array_groups_right
				};
				var data = JSON.stringify(cdevice);
				console.log(data);
				$.ajax({
					type : "POST",
					url : "api/device",
					data : data,
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {
						console.log(data);
						$('#json_result_status').text("");
						$('#json_result_error').text("");
						$('#json_result_status').text(data.status);

						if (data.status == "SUCCESS") {

							var deviceForTable = {
								"DT_RowId" : data.result.DT_RowId,
								"template" : {
									"templateName" : data.result.template.templateName
								},
								"deviceIp" : data.result.deviceIp,
								"deviceName" : data.result.deviceName,
								"switchEnable" : data.result.switchEnable
							}

							table.row.add(deviceForTable).draw();

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
			});

			$('.btn_compare').click(
					function() {
						var value_compare_password = $(this).val();
						var udevice = {
							DT_RowId : $('#udevice_id').val()
						};
						udevice['password' + value_compare_password] = $(
								'#upassword' + value_compare_password).val()

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
									$('#u_span_password' + value_compare_password)
											.text(data.status);
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
						var table = $('#device_table').DataTable();
						var array_groups_left = new Array();
						$('#groups_left option').map(function(index, elem) {
							array_groups_left.push({
								DT_RowId : $(elem).val(),
								name : $(elem).text()

							});
						});
						var array_groups_right = new Array();
						$('#groups_right option').map(function(index, elem) {
							array_groups_right.push({
								DT_RowId : $(elem).val(),
								name : $(elem).text()

							});
						});

						var udevice = {
							DT_RowId : $('#udevice_id').val(),
							template : {
								DT_RowId : $("#utemplate").val()

							},
							deviceName : $("#uhostname_device").val(),
							deviceIp : $("#uip_device").val(),
							username1 : $("#uusername1").val(),
							password1 : $("#upassword1").val(),
							promt1 : $("#upromt1").val(),
							username2 : $("#uusername2").val(),
							password2 : $("#upassword2").val(),
							promt2 : $("#upromt2").val(),
							username3 : $("#uusername3").val(),
							password3 : $("#upassword3").val(),
							promt3 : $("#upromt3").val(),
							username4 : $("#uusername4").val(),
							password4 : $("#upassword4").val(),
							promt4 : $("#upromt4").val(),

							switchEnable : $("#uenable_device").val(),
							groups : array_groups_left,
							rightGroups : array_groups_right
						};

						// console.log(udevice);
						var data = JSON.stringify(udevice);
						console.log(data);

						$.ajax({
							type : "PUT",
							url : "api/device/clone",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								console.log(data);
								$('#json_result_status').text("");
								$('#json_result_error').text("");
								$('#json_result_status').text(data.status);

								if (data.status == "SUCCESS") {

									var deviceForTable = {
										"DT_RowId" : data.result.DT_RowId,
										"template" : {
											"templateName" : data.result.template.templateName
										},
										"deviceIp" : data.result.deviceIp,
										"deviceName" : data.result.deviceName,
										"switchEnable" : data.result.switchEnable
									}

									table.row.add(deviceForTable).draw();

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
		});
