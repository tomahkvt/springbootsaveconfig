var array_for_delete_schedule = [];
$(document).ready(
		function() {
			$("#divUpdate").hide();
			$("#divAdd").hide();
			var array_for_delete_command = [];

			// load table template
			$('#task_table').DataTable({
				stateSave : true,

				"ajax" : {
					"url" : "api/task/fortable",
					"dataSrc" : ""
				},
				"columns" : [ {
					"data" : "name"
				} ]
			});

			$('#task_table tbody').on('click', 'tr', function() {
				var table = $('#task_table').DataTable();
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

			$("#u_add_group").click(function() {
				var id;
				var text;
				$('#u_groups_right :selected').each(function() {
					id = $(this).val();
					text = $(this).text();
					$("#u_groups_left").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$("#a_add_group").click(function() {
				var id;
				var text;
				$('#a_groups_right :selected').each(function() {
					id = $(this).val();
					text = $(this).text();
					$("#a_groups_left").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$("#u_remove_group").click(function() {
				var id;
				var text;
				$('#u_groups_left :selected').each(function() {
					id = $(this).val();
					text = $(this).text();

					$("#u_groups_right").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$("#a_remove_group").click(function() {
				var id;
				var text;
				$('#a_groups_left :selected').each(function() {
					id = $(this).val();
					text = $(this).text();

					$("#a_groups_right").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$("#u_add_device").click(function() {
				var id;
				var text;
				$('#u_devices_right :selected').each(function() {
					id = $(this).val();
					text = $(this).text();

					$("#u_devices_left").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$("#a_add_device").click(function() {
				var id;
				var text;
				$('#a_devices_right :selected').each(function() {
					id = $(this).val();
					text = $(this).text();

					$("#a_devices_left").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$("#u_remove_device").click(function() {
				var id;
				var text;
				$('#u_devices_left :selected').each(function() {
					id = $(this).val();
					text = $(this).text();

					$("#u_devices_right").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$("#a_remove_device").click(function() {
				var id;
				var text;
				$('#a_devices_left :selected').each(function() {
					id = $(this).val();
					text = $(this).text();

					$("#a_devices_right").append($('<option>', {
						value : id,
						text : text
					}));
					$(this).remove();
				});
			});

			$('#u_groups_right').change(function() {
				$groupid = $("#u_groups_right").val();
				// console.log($groupid);
				$.getJSON("api/schedule/getlistdeviceforgroup/" + $groupid, function(data) {
					$("#u_devices_right").empty();
					$.each(data, function(index, value) {

						$("#u_devices_right").append($('<option>', {
							value : value.DT_RowId,
							text : value.deviceName
						}));
					});
				});
			});

			$('#a_groups_right').change(function() {
				$groupid = $("#a_groups_right").val();
				// console.log($groupid);
				$.getJSON("api/schedule/getlistdeviceforgroup/" + $groupid, function(data) {
					$("#a_devices_right").empty();
					$.each(data, function(index, value) {

						$("#a_devices_right").append($('<option>', {
							value : value.DT_RowId,
							text : value.deviceName
						}));
					});
				});
			});

			$('#u_table_schedule').on('click', 'tr ', function(e) {
				if ($(this).hasClass('select-row') == false) {
					$('#u_table_schedule tr').removeClass('select-row');
					$(this).addClass('select-row');
					sort_u_table_schedule();
				}

			});

			$('#a_table_schedule').on('click', 'tr ', function(e) {
				if ($(this).hasClass('select-row') == false) {
					$('#a_table_schedule tr').removeClass('select-row');
					$(this).addClass('select-row');
					sort_u_table_schedule();
				}

			});

			$("#u_btn_rowtest_schedule").click(function() {
				sort_u_table_schedule();
			});

			// utable_more insert row
			$('#u_btn_rowinsert_schedule').click(
					function() {
						var selectrow;
						selectrow = $("#u_table_schedule  tr:first");
						addrow = "<tr><td>";
						addrow = addrow + "<select>" + "<option value='1' selected>Monday</option>"
								+ "<option value='2' >Tuesday</option>" + "<option value='3' >Wednesday</option>"
								+ "<option value='4' >Thursday</option>" + "<option value='5' >Friday</option>"
								+ "<option value='6' >Saturday</option>" + "<option value='7' >Sunday</option>"
								+ "</select>" + "</td>";
						addrow = addrow + '<td>' + "<input type='number' min=1 max=24 value=1></td>";
						addrow = addrow + '<td>' + "<input type='number' min=1 max=59 value=1></td>";
						addrow = addrow + '</tr>';
						selectrow.after(addrow);
					})

			$('#a_btn_rowinsert_schedule').click(
					function() {
						var selectrow;
						selectrow = $("#a_table_schedule  tr:first");
						addrow = "<tr><td>";
						addrow = addrow + "<select>" + "<option value='1' selected>Monday</option>"
						+ "<option value='2' >Tuesday</option>" + "<option value='3' >Wednesday</option>"
						+ "<option value='4' >Thursday</option>" + "<option value='5' >Friday</option>"
						+ "<option value='6' >Saturday</option>" + "<option value='7' >Sunday</option>"
						+ "</select>" + "</td>";
						addrow = addrow + '<td>' + "<input type='number' min=1 max=24 value=1></td>";
						addrow = addrow + '<td>' + "<input type='number' min=1 max=59 value=1></td>";
						addrow = addrow + '</tr>';
						selectrow.after(addrow);
					})

			// utable_more insert row
			$('#u_btn_rowdelete_schedule').click(function() {
				var id = $('#u_table_schedule tr.select-row').attr('id');
				$('#u_table_schedule tr.select-row').remove();
				if (typeof (id) != "undefined") {
					array_for_delete_schedule.push(id);
				}
			});

			$('#a_btn_rowdelete_schedule').click(function() {
				var id = $('#a_table_schedule tr.select-row').attr('id');
				$('#a_table_schedule tr.select-row').remove();
				// if (typeof (id) != "undefined") {
				// array_for_delete_schedule.push(id);
				// }
			});

			var counter = 1;
			// save changes template
			$('#btn_update').click(function() {
				$('#json_result_status').text("");
				$('#json_result_error').text("");
				sort_u_table_schedule();

				var table = $('#task_table').DataTable();
				if (!$('#u_task_id').val()) {
					alert("Выберите задачу");
					return;
				}
				var array_include_groups = new Array();
				$('#u_groups_left option').map(function(index, elem) {
					array_include_groups.push({
						DT_RowId : $(elem).val(),
						name : $(elem).text()

					});
				});
				var array_include_devices = new Array();
				$('#u_devices_left option').map(function(index, elem) {
					array_include_devices.push({
						DT_RowId : $(elem).val(),
						deviceName : $(elem).text()

					});
				});

				var utask = {
					DT_RowId : $('#u_task_id').val(),
					name : $('#u_task_name').val(),
					groups : array_include_groups,
					devices : array_include_devices
				};

				var data = JSON.stringify(utask);
				// console.log(data);
				$.ajax({
					type : "PUT",
					url : "api/task",
					data : data,
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {
						// console.log(data);

						$('#json_result_status').append(data.status);

						if (data.status == "SUCCESS") {

							var taskForTable = {
								"DT_RowId" : data.result.DT_RowId,
								"name" : data.result.name

							}

							table.row("#" + data.result.DT_RowId).data(taskForTable).draw();

						}
						if (data.status == "FAIL") {
							errorInfo = "";
							for (i = 0; i < data.result.length; i++) {
								errorInfo += "<br>" + data.result[i].field + ". " + data.result[i].defaultMessage;
							}
							$('#json_result_error').append("Please correct following errors: " + errorInfo);

							$('#json_result_error').show('slow');
						}
					}
				});

				var index;
				$(".tdEdit").each(function() {
					$(this).parent('td').html($(this).val());
				});
				/*
				 * for (index = 0; index < array_for_delete_command.length;
				 * ++index) { console.log(array_for_delete_command[index]);
				 * $.ajax({ type : "DELETE", url : "api/command/delete/" +
				 * array_for_delete_command[index], success : function(value) { }
				 * }); } array_for_delete_command = [];
				 */
				for (index = 0; index < array_for_delete_schedule.length; ++index) {
					// console.log(array_for_delete_schedule[index]);
					$.ajax({
						type : "DELETE",
						url : "api/schedule/delete/" + array_for_delete_schedule[index],
						success : function(value) {
						}
					});
				}
				array_for_delete_schedule = [];

				$("#u_table_schedule > tbody > tr:not(:first)").each(function() {

					var data = JSON.stringify({
						DT_RowId : $(this).attr('id'),
						taskid : $("#u_task_id").val(),
						dayOfWeek : $(this).find("td").eq(0).find("select").val(),
						hour : $(this).find("td").eq(1).find("input").val(),
						minute : $(this).find("td").eq(2).find("input").val()
					});

					// console.log(data);

					if (typeof ($(this).attr('id')) === "undefined") {
						$.ajax({
							type : "POST",
							url : "api/schedule",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								// console.log(data);

							}
						});

					} else {
						// console.log(data);
						$.ajax({
							type : "PUT",
							url : "api/schedule",
							data : data,
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								// console.log(data);

							}
						});
					}
					;
				});

			})

			// delete template
			$('#button_del').click(function() {
				var table = $('#task_table').DataTable();
				$("#divUpdate").hide();

				var id_delete = table.row('.selected').id();
				if (id_delete == null)
					return;
				// console.log("delete id= "
				// + id_delete);

				$.ajax({
					type : "DELETE",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					url : "api/task/delete/" + id_delete,
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

			$('#button_add').on('click', function() {
				if ($("#divUpdate").is(':hidden') == false) {
					$("#divUpdate").hide();
				}
				$("#divAdd").show()
				$('#button_del').show();

				$.getJSON("api/schedule/getallgroups", function(allGrops) {
					$("#a_groups_right").empty();
					$.each(allGrops, function(index, value) {
						$("#a_groups_right").append($('<option>', {
							value : value.DT_RowId,
							text : value.name
						}));
					});
					// $("#a_groups_right").empty();

					$("#a_devices_left").empty();
					$("#a_devices_right").empty();
				});

			});

			// edit template
			$("#button_edit").on('click', function() {
				var table = $('#task_table').DataTable();
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
						alert("Select task");
						return;

					} else {
						edit_refresh(device);
					}
				} else {
					$("#divUpdate").hide();
					// $('#button_del').show();
				}
			});

			function edit_refresh(device) {
				var table = $('#task_table').DataTable();
				array_for_delete_command = [];
				array_for_delete_schedule = [];
				// console.log(device);
				$("#u_task_name").val(device.name);
				$("#u_task_id").val(device.DT_RowId);
				$('#json_result_status').text("");
				$('#json_result_error').text("");
				$.getJSON("api/task/task/" + device.DT_RowId, function(dtoTask) {
					$("#u_groups_left").empty();
					$.each(dtoTask.groups, function(index, value) {
						$("#u_groups_left").append($('<option>', {
							value : value.DT_RowId,
							text : value.name
						}));
					});
					$("#u_groups_right").empty();
					$.each(dtoTask.excludeGroups, function(index, value) {
						$("#u_groups_right").append($('<option>', {
							value : value.DT_RowId,
							text : value.name
						}));
					});
					$("#u_devices_left").empty();
					$("#u_devices_right").empty();
					$.each(dtoTask.devices, function(index, value) {
						$("#u_devices_left").append($('<option>', {
							value : value.DT_RowId,
							text : value.deviceName
						}));
					});
				});

				$.getJSON("api/schedule/getbytskid/" + device.DT_RowId, function(data) {
					$("#u_table_schedule tr").has('td').remove();
					$.each(data, function(index, schedule) {
						var row = '<tr id = ' + schedule.DT_RowId + '>';
						row = row + "<td>";
						row = row + "<select id = 'selectday'>" + "<option value='1' " + (schedule.dayOfWeek == 1)
								+ ">Monday</option>" + "<option value='2' "
								+ ((schedule.dayOfWeek == 2) ? 'selected' : '') + ">Tuesday</option>"
								+ "<option value='3' " + ((schedule.dayOfWeek == 3) ? 'selected' : '')
								+ ">Wednesday</option>" + "<option value='4' "
								+ ((schedule.dayOfWeek == 4) ? 'selected' : '') + ">Thursday</option>"
								+ "<option value='5' " + ((schedule.dayOfWeek == 5) ? 'selected' : '')
								+ ">Friday</option>" + "<option value='6' "
								+ ((schedule.dayOfWeek == 6) ? 'selected' : '') + ">Saturday</option>"
								+ "<option value='7' " + ((schedule.dayOfWeek == 7) ? 'selected' : '')
								+ ">Sunday</option>" + "</select>" + "</td>";
						row = row + '<td>' + "<input class = 'selecthour' type='number' min=1 max=24 value="
								+ schedule.hour + '></td>';
						row = row + '<td>' + "<input id = 'selectminute' type='number' min=1 max=59 value="
								+ schedule.minute + '></td>';
						row = row + '</tr>';
						// console.log(row);
						$('#u_table_schedule').append(row);
					});

					// $('#u_table_schedule :input').on('click', function() {
					// console.log(this);
					// console.log("click");
					// });

				});

			}
			;

			$("#btn_create").click(function() {
				$('#json_result_status').text("");
				$('#json_result_error').text("");
				sort_u_table_schedule();

				var table = $('#task_table').DataTable();
				// if (!$('#u_task_id').val()) {
				// alert("Выберите задачу");
				// return;
				// }
				var array_include_groups = new Array();
				$('#a_groups_left option').map(function(index, elem) {
					array_include_groups.push({
						DT_RowId : $(elem).val(),
						name : $(elem).text()

					});
				});
				var array_include_devices = new Array();
				$('#a_devices_left option').map(function(index, elem) {
					array_include_devices.push({
						DT_RowId : $(elem).val(),
						deviceName : $(elem).text()

					});
				});

				var utask = {
					DT_RowId : null,
					name : $('#a_task_name').val(),
					groups : array_include_groups,
					devices : array_include_devices
				};

				var data = JSON.stringify(utask);
				// console.log(data);
				$.ajax({
					type : "POST",
					url : "api/task",
					data : data,
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {
						// console.log(data);

						$('#json_result_status').append(data.status);

						if (data.status == "SUCCESS") {

							var taskForTable = {
								"DT_RowId" : data.result.DT_RowId,
								"name" : data.result.name
							}
							save_atable(data.result.DT_RowId);
							table.row.add(taskForTable).draw();

						}
						if (data.status == "FAIL") {
							errorInfo = "";
							for (i = 0; i < data.result.length; i++) {
								errorInfo += "<br>" + data.result[i].field + ". " + data.result[i].defaultMessage;
							}
							$('#json_result_error').append("Please correct following errors: " + errorInfo);

							$('#json_result_error').show('slow');
						}
					}
				});

			});


			function updateVal(currentEditCell, value) {

				if (currentEditCell.children().hasClass("tdEdit") == false) {
					$(".tdEdit").each(function() {
						$(this).parent('td').html($(this).val());
					});
					$(currentEditCell).html('<input class="tdEdit" type="text" value="' + value + '" />');
					$(".tdEdit").focus();
				} else {
					$(currentEditCell).html($(".tdEdit").val().trim());
				}

				$(".tdEdit").keyup(function(event) {
					if (event.keyCode == 13) {
						$(currentEditCell).html($(".tdEdit").val().trim());
					}
				});
			}

		});

function remove_duplicate_row(arr) {
	var result = [];
	var A = 0;
	var B = 0;
	var a, b;
	var find = 0;
	for (var i = 0; i < arr.length; i++) {
		find = 0;
		a = arr[i];
		A = 0;
		A = A + 1 * parseInt($(a).children('td').eq(2).find("input").val());
		A = A + 100 * parseInt($(a).children('td').eq(1).find("input").val());
		A = A + 10000 * parseInt($(a).children('td').eq(0).find("select").val());
		for (var j = 0; j < result.length; j++) {
			b = result[j];
			B = 0;
			B = B + 1 * parseInt($(b).children('td').eq(2).find("input").val());
			B = B + 100 * parseInt($(b).children('td').eq(1).find("input").val());
			B = B + 10000 * parseInt($(b).children('td').eq(0).find("select").val());
			if (A == B) {
				find = 1;
				continue;
			}
		}
		if (find == 0) {
			result.push(arr[i]);
		} else {
			$('#json_result_error').append("Элемент дублируется и будет удален");
			$('#json_result_error').append($(a));
			var id = $(a).attr('id');
			if (typeof (id) != "undefined") {
				array_for_delete_schedule.push(id);
			}
		}
	}
	return result;
}

function sort_u_table_schedule() {
	var rows = $("#u_table_schedule > tbody > tr:not(:first)").get();

	rows = remove_duplicate_row(rows);
	rows.sort(function(a, b) {
		var A = 0;
		var B = 0;
		A = A + 1 * parseInt($(a).children('td').eq(2).find("input").val());
		A = A + 100 * parseInt($(a).children('td').eq(1).find("input").val());
		A = A + 10000 * parseInt($(a).children('td').eq(0).find("select").val());

		B = B + 1 * parseInt($(b).children('td').eq(2).find("input").val());
		B = B + 100 * parseInt($(b).children('td').eq(1).find("input").val());
		B = B + 10000 * parseInt($(b).children('td').eq(0).find("select").val());

		if (A < B) {
			return -1;
		}
		if (A > B) {
			return 1;
		}

		return 0;
	});

	$('#u_table_schedule td').parent().remove();
	$.each(rows, function(index, row) {
		$("#u_table_schedule").children('tbody').append(row);
	});
};

function save_atable(atask_id) {
	var index;
	$(".tdEdit").each(function() {
		$(this).parent('td').html($(this).val());
	});

	for (index = 0; index < array_for_delete_schedule.length; ++index) {
		// console.log(array_for_delete_schedule[index]);
		$.ajax({
			type : "DELETE",
			url : "api/schedule/delete/" + array_for_delete_schedule[index],
			success : function(value) {
			}
		});
	}
	array_for_delete_schedule = [];

	$("#a_table_schedule > tbody > tr:not(:first)").each(function() {

		var data = JSON.stringify({
			DT_RowId : $(this).attr('id'),
			taskid : atask_id,
			dayOfWeek : $(this).find("td").eq(0).find("select").val(),
			hour : $(this).find("td").eq(1).find("input").val(),
			minute : $(this).find("td").eq(2).find("input").val()
		});

		// console.log(data);

		if (typeof ($(this).attr('id')) === "undefined") {
			$.ajax({
				type : "POST",
				url : "api/schedule",
				data : data,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					// console.log(data);

				}
			});

		} else {
			// console.log(data);
			$.ajax({
				type : "PUT",
				url : "api/schedule",
				data : data,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					// console.log(data);

				}
			});
		}
		;
	});
}