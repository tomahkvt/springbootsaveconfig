!function() {
	var array_for_delete_command = [];
	var array_for_delete_more = [];

	$(document)
			.ready(
					function() {
						$("#divUpdate").hide();
						$("#divAdd").hide();

						// load table template
						$('#template_table').DataTable({
							stateSave : true,

							"ajax" : {
								"url" : "api/template/tableDTO",
								"dataSrc" : ""
							},
							"columns" : [ {
								"data" : "templateName"
							}, {
								"data" : "terminalServer.deviceName"
							}, {
								"data" : "terminalServer.deviceIp"
							} ]
						});

						// utable_command_start on sortable
						$("#utable_command_start tbody").sortable({
							items : "> tr:not(:first)",
							stop : function(event, ui) {
								// console.log("drag");
								$("#utable_command_start > tbody > tr:not(:first)").each(function() {
									// console.log($(this).index()
									// + " = " +
									// $(this).find(".cell_order").text());
									$(this).find(".cell_order").text($(this).index());
								});
							}
						});
						// atable_command_start on sortable
						$("#atable_command_start tbody").sortable({
							items : "> tr:not(:first)",
							stop : function(event, ui) {
								// console.log("drag");
								$("#atable_command_start > tbody > tr:not(:first)").each(function() {
									// console.log($(this).index()
									// + " = " +
									// $(this).find(".cell_order").text());
									$(this).find(".cell_order").text($(this).index());
								});
							}
						});

						// utable_command_body on sortable
						$("#utable_command_body tbody").sortable({
							items : "> tr:not(:first)",
							stop : function(event, ui) {
								// console.log("drag");
								$("#utable_command_body > tbody > tr:not(:first)").each(function() {
									// console.log($(this).index()
									// + " = " +
									// $(this).find(".cell_order").text());
									$(this).find(".cell_order").text(50 + $(this).index());
								});
							}
						});

						// atable_command_body on sortable
						$("#atable_command_body tbody").sortable({
							items : "> tr:not(:first)",
							stop : function(event, ui) {
								// console.log("drag");
								$("#atable_command_body > tbody > tr:not(:first)").each(function() {
									// console.log($(this).index()
									// + " = " +
									// $(this).find(".cell_order").text());
									$(this).find(".cell_order").text(50 + $(this).index());
								});
							}
						});

						// utable_command_end on sortable
						$("#utable_command_end tbody").sortable({
							items : "> tr:not(:first)",
							stop : function(event, ui) {
								// console.log("drag");
								$("#utable_command_end > tbody > tr:not(:first)").each(function() {
									// console.log($(this).index()
									// + " = " +
									// $(this).find(".cell_order").text());
									$(this).find(".cell_order").text(100 + $(this).index());
								});
							}
						});

						// utable_command_end on sortable
						$("#atable_command_end tbody").sortable({
							items : "> tr:not(:first)",
							stop : function(event, ui) {
								// console.log("drag");
								$("#atable_command_end > tbody > tr:not(:first)").each(function() {
									// console.log($(this).index()
									// + " = " +
									// $(this).find(".cell_order").text());
									$(this).find(".cell_order").text(100 + $(this).index());
								});
							}
						});

						// utable_more on tr click select
						$('#utable_more').on('click', 'tr ', function(e) {

							$('#utable_more tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// atable_more on tr click select
						$('#atable_more').on('click', 'tr ', function(e) {

							$('#atable_more tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// utable_command_start on tr click select
						$('#utable_command_start').on('click', 'tr ', function(e) {

							$('#utable_command_start tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// atable_command_start on tr click select
						$('#atable_command_start').on('click', 'tr ', function(e) {

							$('#atable_command_start tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// utable_command_body on tr click select
						$('#utable_command_body').on('click', 'tr ', function(e) {

							$('#utable_command_body tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// atable_command_body on tr click select
						$('#atable_command_body').on('click', 'tr ', function(e) {

							$('#atable_command_body tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// utable_command_end on tr click select
						$('#utable_command_end').on('click', 'tr ', function(e) {

							$('#utable_command_end tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// utable_command_end on tr click select
						$('#atable_command_end').on('click', 'tr ', function(e) {

							$('#atable_command_end tr').removeClass('select-row');
							$(this).addClass('select-row');
						});

						// utable_more insert row
						$('#ubtn_rowinsert_more')
								.click(
										function() {
											var selectrow;
											selectrow = $("#utable_more  tr:first");
											selectrow
													.after("<tr><td>more</td><td>moreDo</td><td><input type='checkbox'</td><td></td></tr>");
										})

						// utable_more insert row
						$('#abtn_rowinsert_more')
								.click(
										function() {
											var selectrow;
											selectrow = $("#atable_more  tr:first");
											selectrow
													.after("<tr><td>more</td><td>moreDo</td><td><input type='checkbox'</td><td></td></tr>");
										})

						// utable_command_start insert row
						$('#ubtn_rowinsert_start')
								.click(
										function() {
											var selectrow;
											if ($('#utable_command_start tr.select-row').length == 0) {
												selectrow = $("#utable_command_start  tr:first")
											} else {
												selectrow = $('#utable_command_start tr.select-row')
											}
											;
											selectrow
													.after("<tr><td class='cell_order'></td><td></td><td></td><td></td><td><input type='checkbox'</td></td></tr>");

											$("#utable_command_start > tbody > tr:not(:first)").each(function() {
												$(this).find(".cell_order").text($(this).index());
											});
										})

						// utable_command_start insert row
						$('#abtn_rowinsert_start')
								.click(
										function() {
											if ($('#atable_command_start tr.select-row').length == 0) {
												selectrow = $("#atable_command_start  tr:first")
											} else {
												selectrow = $('#atable_command_start tr.select-row')
											}
											;
											selectrow
													.after("<tr><td class='cell_order'></td><td></td><td></td><td>0</td><td><input type='checkbox'</td></td></tr>");

											$("#atable_command_start > tbody > tr:not(:first)").each(function() {
												$(this).find(".cell_order").text($(this).index());
											});
										})

						// utable_command_body insert row
						$('#ubtn_rowinsert_body')
								.click(
										function() {
											var selectrow;
											if ($('#utable_command_body tr.select-row').length == 0) {
												selectrow = $("#utable_command_body  tr:first")
											} else {
												selectrow = $('#utable_command_body tr.select-row')
											}
											;
											selectrow
													.after("<tr><td class='cell_order'></td><td></td><td></td><td>0</td><td><input type='checkbox'</td></td></tr>");

											$("#utable_command_body > tbody > tr:not(:first)").each(function() {
												$(this).find(".cell_order").text(50 + $(this).index());
											});
										})

						// atable_command_body insert row
						$('#abtn_rowinsert_body')
								.click(
										function() {
											var selectrow;
											if ($('#atable_command_body tr.select-row').length == 0) {
												selectrow = $("#atable_command_body  tr:first")
											} else {
												selectrow = $('#atable_command_body tr.select-row')
											}
											;
											selectrow
													.after("<tr><td class='cell_order'></td><td></td><td></td><td>0</td><td><input type='checkbox'</td></td></tr>");

											$("#atable_command_body > tbody > tr:not(:first)").each(function() {
												$(this).find(".cell_order").text(50 + $(this).index());
											});
										})

						// utable_command_body insert row
						$('#ubtn_rowinsert_end')
								.click(
										function() {
											var selectrow;
											if ($('#utable_command_body tr.select-row').length == 0) {
												selectrow = $("#utable_command_end  tr:first")
											} else {
												selectrow = $('#utable_command_end tr.select-row')
											}
											;
											selectrow
													.after("<tr><td class='cell_order'></td><td></td><td></td><td>0</td><td><input type='checkbox'</td></td></tr>");

											$("#utable_command_end > tbody > tr:not(:first)").each(function() {
												$(this).find(".cell_order").text(100 + $(this).index());
											});
										})

						// utable_command_body insert row
						$('#abtn_rowinsert_end')
								.click(
										function() {
											var selectrow;
											if ($('#atable_command_body tr.select-row').length == 0) {
												selectrow = $("#atable_command_end  tr:first")
											} else {
												selectrow = $('#atable_command_end tr.select-row')
											}
											;
											selectrow
													.after("<tr><td class='cell_order'></td><td></td><td></td><td>0</td><td><input type='checkbox'</td></td></tr>");

											$("#atable_command_end > tbody > tr:not(:first)").each(function() {
												$(this).find(".cell_order").text(100 + $(this).index());
											});
										})

						// utable_more row delete
						$('#ubtn_rowdelete_more').click(function() {
							var id = $('#utable_more tr.select-row').attr('id');
							$('#utable_more tr.select-row').remove();
							if (typeof (id) != "undefined") {
								array_for_delete_more.push(id);
							}
						})

						// utable_more row delete
						$('#abtn_rowdelete_more').click(function() {
							$('#atable_more tr.select-row').remove();
						})

						// utable_command_start row delete
						$('#ubtn_rowdelete_start').click(function() {
							var id = $('#utable_command_start tr.select-row').attr('id');
							$('#utable_command_start tr.select-row').remove();
							if (typeof (id) != "undefined") {
								array_for_delete_command.push(id);
							}
							$("#utable_command_start > tbody > tr:not(:first)").each(function() {
								$(this).find(".cell_order").text($(this).index());
							});
						})

						// atable_command_start row delete
						$('#abtn_rowdelete_start').click(function() {
							$('#atable_command_start tr.select-row').remove();
							$("#atable_command_start > tbody > tr:not(:first)").each(function() {
								$(this).find(".cell_order").text($(this).index());
							});
						})

						// utable_command_start row delete
						$('#ubtn_rowdelete_body').click(function() {
							var id = $('#utable_command_body tr.select-row').attr('id');
							$('#utable_command_body tr.select-row').remove();
							if (typeof (id) != "undefined") {
								array_for_delete_command.push(id);
							}
							$("#utable_command_body > tbody > tr:not(:first)").each(function() {
								$(this).find(".cell_order").text($(this).index());
							});
						})

						// atable_command_start row delete
						$('#abtn_rowdelete_body').click(function() {

							$('#atable_command_body tr.select-row').remove();
							$("#atable_command_body > tbody > tr:not(:first)").each(function() {
								$(this).find(".cell_order").text($(this).index());
							});
						})

						// utable_command_end row delete
						$('#ubtn_rowdelete_end').click(function() {
							var id = $('#utable_command_end tr.select-row').attr('id');
							$('#utable_command_end tr.select-row').remove();
							if (typeof (id) != "undefined") {
								array_for_delete_command.push(id);
							}
							$("#utable_command_end > tbody > tr:not(:first)").each(function() {
								$(this).find(".cell_order").text($(this).index());
							});
						})

						// utable_command_end row delete
						$('#abtn_rowdelete_end').click(function() {

							$('#atable_command_end tr.select-row').remove();
							$("#atable_command_end > tbody > tr:not(:first)").each(function() {
								$(this).find(".cell_order").text($(this).index());
							});
						})

						// update
						$('#ubtn_rowupdate_start').click(function() {
							var index;
							$(".tdEdit").each(function() {
								$(this).parent('td').html($(this).val());
							});
							for (index = 0; index < array_for_delete_command.length; ++index) {
								console.log(array_for_delete_command[index]);
								$.ajax({
									type : "DELETE",
									url : "api/command/delete/" + array_for_delete_command[index],
									success : function(value) {
									}
								});
							}

							$("#utable_command_start > tbody > tr:not(:first)").each(function() {

								var data = JSON.stringify({
									DT_RowId : $(this).attr('id'),
									order : $(this).find("td").eq(0).html(),
									command : $(this).find("td").eq(1).html(),
									waitFor : $(this).find("td").eq(2).html(),
									waitTime : $(this).find("td").eq(3).html(),
									save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
									idTemplate : $("#utemplate_id").val()
								});

								console.log(data);

								if (typeof ($(this).attr('id')) === "undefined") {
									$.ajax({
										type : "POST",
										url : "api/command",
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
										url : "api/command",
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
						});

						// $( "#utable_command_start" ).disableSelection();
						// utable_command_start edit cell
						$("#utable_command_start").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// atable_command_start edit cell
						$("#atable_command_start").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// utable_command_body edit cell
						$("#utable_command_body").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// utable_command_body edit cell
						$("#atable_command_body").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// utable_command_end edit cell
						$("#utable_command_end").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// atable_command_end edit cell
						$("#atable_command_end").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// utable_more edit cell
						$("#utable_more").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// utable_more edit cell
						$("#atable_more").on('dblclick', 'td', function(e) {
							e.stopPropagation();
							var currentEle = $(this);
							var value = $(this).html();
							updateVal(currentEle, value);
						});

						// template_table select row
						$('#template_table tbody').on('click', 'tr', function() {
							var table = $('#template_table').DataTable();
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

						// delete template
						$('#button_del').click(function() {
							var table = $('#template_table').DataTable();
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
								url : "api/template/delete/" + id_delete,
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

						// var counter = 1;
						// save changes template
						$('#btn_update').click(
								function() {

									var table = $('#template_table').DataTable();
									if (!$('#utemplate_id').val()) {
										alert("Select Template");
										return;
									}

									var uid_terminalserver = $('#uterminalserver').val();

									var uterminalserver = {
										DT_RowId : uid_terminalserver
									}

									var utemplate = {
										DT_RowId : $('#utemplate_id').val(),
										templateName : $('#utemplate_name').val(),
										terminalServer : uterminalserver
									};

									var data = JSON.stringify(utemplate);
									console.log(data);
									$.ajax({
										type : "PUT",
										url : "api/template",
										data : data,
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(data) {
											console.log(data);
											$('#json_result_status').text("");
											$('#json_result_error').text("");
											$('#json_result_status').text(data.status);

											if (data.status == "SUCCESS") {
												update_template();

												var templateForTable = {
													"DT_RowId" : data.result.DT_RowId,
													"templateName" : data.result.templateName,
													"terminalServer" : {
														"DT_RowId" : data.result.terminalServer.DT_RowId,
														"deviceName" : data.result.terminalServer.deviceName,
														"deviceIp" : data.result.terminalServer.deviceIp
													}
												}

												table.row("#" + data.result.DT_RowId).data(templateForTable).draw();

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

						// clone template
						$('#btn_clone').click(
								function() {
									var table = $('#template_table').DataTable();
									var uid_terminalserver = $('#uterminalserver').val();

									var uterminalserver = {
										DT_RowId : uid_terminalserver
									}

									var utemplate = {
										DT_RowId : $('#utemplate_id').val(),
										templateName : $('#utemplate_name').val(),
										terminalServer : uterminalserver
									};

									var data = JSON.stringify(utemplate);
									console.log(data);
									$.ajax({
										type : "PUT",
										url : "api/template/clone",
										data : data,
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(data) {
											console.log(data);
											$('#json_result_status').text("");
											$('#json_result_error').text("");
											$('#json_result_status').text(data.status);

											if (data.status == "SUCCESS") {
												clone_template(data.result.DT_RowId);
												var templateForTable = {
													"DT_RowId" : data.result.DT_RowId,
													"templateName" : data.result.templateName,
													"terminalServer" : {
														"DT_RowId" : data.result.terminalServer.DT_RowId,
														"deviceName" : data.result.terminalServer.deviceName,
														"deviceIp" : data.result.terminalServer.deviceIp
													}

												}

												table.row.add(templateForTable).draw();

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

						// add template
						$('#button_add').on('click', function() {
							if ($("#divUpdate").is(':hidden') == false) {
								$("#divUpdate").hide();
							}
							$("#divAdd").show()
							$('#button_del').show();

							$.getJSON("api/terminalserver/list", function(data) {
								$("#uterminalserver").empty();
								$.each(data, function(index, value) {
									$("#aterminalserver").append($('<option>', {
										value : value.DT_RowId,
										text : value.deviceName
									}));
								});
							})

						});
						// edit template
						$("#button_edit").on('click', function() {
							var table = $('#template_table').DataTable();
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

								} else {
									edit_refresh(device);
								}
							} else {
								$("#divUpdate").hide();
								// $('#button_del').show();
							}

						});

						function edit_refresh(device) {
							var table = $('#template_table').DataTable();
							array_for_delete_command = [];
							array_for_delete_more = [];
							$('#json_result_status').text("");
							$('#json_result_error').text("");
							$.getJSON("api/terminalserver/list", function(data) {
								$("#uterminalserver").empty();
								$.each(data, function(index, value) {
									$("#uterminalserver").append($('<option>', {
										value : value.DT_RowId,
										text : value.deviceName
									}));
								});
							}).done(function() {
								if (device != null)
									$("#uterminalserver").val(device.terminalServer.DT_RowId);
							})

							id = table.row('.selected').id();

							$.getJSON("api/template/getDTO/" + id, function(template) {
								$("#utemplate_name").val(template.templateName);
								$("#uterminalserver").val(template.terminalServer.DT_RowId);
								$("#utemplate_id").val(template.DT_RowId);

							});

							$.getJSON("api/more/" + id, function(data) {
								$("#utable_more tr").has('td').remove();
								$.each(data, function(index, more) {
									var row = '<tr id = ' + more.id + '>';
									row = row + "<td>" + more.more + '</td>';
									row = row + '<td>' + more.moreDo + '</td>';
									var checked;
									if (more.isdelete == true) {
										checked = "checked";
									} else {
										checked = "";
									}
									row = row + "<td><input type='checkbox' " + checked + '></td>';
									row = row + '<td>' + more.moreDelete + '</td>';
									row = row + '</tr>';
									// console.log(row);
									$('#utable_more').append(row);
								});
							});

							$.getJSON("api/command/startfortemplate/" + id, function(data) {
								$("#utable_command_start tr").has('td').remove();
								$.each(data, function(index, command) {
									var row = '<tr id = ' + command.id + '>';
									row = row + "<td class='cell_order'>" + command.order + '</td>';
									row = row + '<td>' + command.command + '</td>';
									row = row + '<td>' + command.waitFor + '</td>';
									row = row + '<td>' + command.waitTime + '</td>';
									var checked;
									if (command.save == true) {
										checked = "checked";
									} else {
										checked = "";
									}
									row = row + "<td><input type='checkbox' " + checked + '></td>';
									row = row + '</tr>';

									$('#utable_command_start').append(row);
								});
							});

							$.getJSON("api/command/bodyfortemplate/" + id, function(data) {
								$("#utable_command_body tr").has('td').remove();
								$.each(data, function(index, command) {
									var row = '<tr id = ' + command.id + '>';
									row = row + "<td class='cell_order'>" + command.order + '</td>';
									row = row + '<td>' + command.command + '</td>';
									row = row + '<td>' + command.waitFor + '</td>';
									row = row + '<td>' + command.waitTime + '</td>';
									var checked;
									if (command.save == true) {
										checked = "checked";
									} else {
										checked = "";
									}
									row = row + "<td><input type='checkbox' " + checked + '></td>';
									row = row + '</tr>';

									$('#utable_command_body').append(row);
								});
							});

							$.getJSON("api/command/endfortemplate/" + id, function(data) {
								$("#utable_command_end tr").has('td').remove();
								$.each(data, function(index, command) {
									var row = '<tr id = ' + command.id + '>';
									row = row + "<td class='cell_order'>" + command.order + '</td>';
									row = row + '<td>' + command.command + '</td>';
									row = row + '<td>' + command.waitFor + '</td>';
									row = row + '<td>' + command.waitTime + '</td>';
									var checked;
									if (command.save == true) {
										checked = "checked";
									} else {
										checked = "";
									}
									row = row + "<td><input type='checkbox' " + checked + '></td>';
									row = row + '</tr>';

									$('#utable_command_end').append(row);
								});
							});

						}
						;

						$("#btn_create").click(
								function() {
									var table = $('#template_table').DataTable();

									var atemplate = {
										DT_RowId : $('#atemplate_id').val(),
										templateName : $('#atemplate_name').val(),
										more : $("#atemplate_more").val(),
										moreDo : $("#atemplate_moredo").val(),
										terminalServer : {
											DT_RowId : $('#aterminalserver').val()
										}
									};
									var data = JSON.stringify(atemplate);

									$.ajax({
										type : "POST",
										url : "api/template",
										data : data,
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(data) {
											console.log(data);
											$('#json_result_status').text("");
											$('#json_result_error').text("");
											$('#json_result_status').text(data.status);

											if (data.status == "SUCCESS") {

												var templateForTable = {
													"DT_RowId" : data.result.DT_RowId,
													"templateName" : data.result.templateName,
													"more" : data.result.more,
													"moreDo" : data.result.moreDo,
													"terminalServer" : {
														"DT_RowId" : data.result.terminalServer.DT_RowId,
														"deviceName" : data.result.terminalServer.deviceName,
														"deviceIp" : data.result.terminalServer.deviceIp
													}

												}

												console.log(templateForTable);
												table.row.add(templateForTable).draw();
												save_atable(data.result.DT_RowId);
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
		/*
		 * $(".tdEdit").click(function () {
		 * $(currentEditCell).html($(".tdEdit").val().trim()); });
		 */
	}

	function update_template() {

		var index;
		$(".tdEdit").each(function() {
			$(this).parent('td').html($(this).val());
		});
		for (index = 0; index < array_for_delete_command.length; ++index) {
			console.log(array_for_delete_command[index]);
			$.ajax({
				type : "DELETE",
				url : "api/command/delete/" + array_for_delete_command[index],
				success : function(value) {
				}
			});
		}
		array_for_delete_command = [];

		for (index = 0; index < array_for_delete_more.length; ++index) {
			console.log(array_for_delete_more[index]);
			$.ajax({
				type : "DELETE",
				url : "api/more/delete/" + array_for_delete_more[index],
				success : function(value) {
				}
			});
		}
		array_for_delete_more = [];

		$("#utable_command_start > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : $("#utemplate_id").val()
			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/command",
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
					url : "api/command",
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

		$("#utable_command_end > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : $("#utemplate_id").val()
			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/command",
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
					url : "api/command",
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

		$("#utable_command_body > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : $("#utemplate_id").val()
			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/command",
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
					url : "api/command",
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

		$("#utable_more > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				idTemplate : $("#utemplate_id").val(),
				more : $(this).find("td").eq(0).html(),
				moreDo : $(this).find("td").eq(1).html(),
				isdelete : $(this).find("td").eq(2).find("input:checkbox").is(':checked'),
				moreDelete : $(this).find("td").eq(3).html()

			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/more",
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
					url : "api/more",
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

	function clone_template(idTemplate) {
		// console.log(idTemplate);

		var index;
		$(".tdEdit").each(function() {
			$(this).parent('td').html($(this).val());
		});
		$("#utable_command_start > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : idTemplate
			});
			console.log(data);
			$.ajax({
				type : "POST",
				url : "api/command",
				data : data,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					// console.log(data);

				}
			});
		});

		$("#utable_command_end > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : idTemplate
			});
			console.log(data);
			$.ajax({
				type : "POST",
				url : "api/command",
				data : data,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
				}
			});
		});

		$("#utable_command_body > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : idTemplate
			});

			console.log(data);
			$.ajax({
				type : "POST",
				url : "api/command",
				data : data,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					// console.log(data);
				}
			});
		});

		$("#utable_more > tbody > tr:not(:first)").each(function() {
			var data = JSON.stringify({
				id : $(this).attr('id'),
				idTemplate : idTemplate,
				more : $(this).find("td").eq(0).html(),
				moreDo : $(this).find("td").eq(1).html(),
				isdelete : $(this).find("td").eq(2).find("input:checkbox").is(':checked'),
				moreDelete : $(this).find("td").eq(3).html()
			});

			console.log(data);

			$.ajax({
				type : "POST",
				url : "api/more",
				data : data,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					// console.log(data);
				}
			});
		});
	}

	function save_atable(atemplate_id) {

		$(".tdEdit").each(function() {
			$(this).parent('td').html($(this).val());
		});

		$("#atable_command_start > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : atemplate_id
			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/command",
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
					url : "api/command",
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

		$("#atable_command_end > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : atemplate_id
			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/command",
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
					url : "api/command",
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

		$("#atable_command_body > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				order : $(this).find("td").eq(0).html(),
				command : $(this).find("td").eq(1).html(),
				waitFor : $(this).find("td").eq(2).html(),
				waitTime : $(this).find("td").eq(3).html(),
				save : $(this).find("td").eq(4).find("input:checkbox").is(':checked'),
				idTemplate : atemplate_id
			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/command",
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
					url : "api/command",
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

		$("#atable_more > tbody > tr:not(:first)").each(function() {

			var data = JSON.stringify({
				id : $(this).attr('id'),
				idTemplate : atemplate_id,
				more : $(this).find("td").eq(0).html(),
				moreDo : $(this).find("td").eq(1).html(),
				isdelete : $(this).find("td").eq(2).find("input:checkbox").is(':checked'),
				moreDelete : $(this).find("td").eq(3).html()

			});

			console.log(data);

			if (typeof ($(this).attr('id')) === "undefined") {
				$.ajax({
					type : "POST",
					url : "api/more",
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
					url : "api/more",
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
}();