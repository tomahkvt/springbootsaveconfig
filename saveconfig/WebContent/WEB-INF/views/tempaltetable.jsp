<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="js/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="js/DataTables-1.10.13/media/css/jquery.dataTables.min.css">
<script src="js/resttemplates.js"></script>
<script src="js/menubar.js">
	
</script>
<link rel="stylesheet" href="css/menubar.css">
</head>
<body>
	<%@ include file="menubar.jsp"%>
	Configure Template
	<div>
		<table id="template_table" class="display">
			<thead>
				<tr>
					<th>Template Name</th>
					<th>Device Name</th>
					<th>Device IP</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<br>
		<div id="statusofaction"></div>
		<br>
		<button id="button_add">Add Template</button>
		<button id="button_edit">Edit Template</button>
		<button id="button_del">Delete Template</button>
		<br> <br>
		<div id="json_result_status"></div>
		<div id="json_result_error"></div>
		<div id="divUpdate" style="display: inline-block;">
			<fieldset>
				<legend>Edit paramter</legend>
				<div style="display: inline-block;">
					<table border="1">
						<tr>
							<td>Tempalte Name</td>
							<td><input id='utemplate_name' /> <input id='utemplate_id'
								type="hidden" /></td>
						</tr>
						<tr>
							<td>Termonal Server</td>
							<td><select id='uterminalserver' /></td>
						</tr>
					</table>
				</div>
				<div style="display: inline-block; vertical-align: top">
					<div div="ublock_more">
						<label>Block More</label><br>
						<div style="display: inline-block; vertical-align: top">
							<table id="utable_more" border="1">
								<tbody>
									<tr>
										<th>more</th>
										<th>more_Do</th>
										<th>isdelete</th>
										<th>delete</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="display: inline-block; vertical-align: top">
							<button id="ubtn_rowinsert_more">Insert row</button>
							<br>
							<button id="ubtn_rowdelete_more">Delete row</button>
							<br>
						</div>
					</div>
					<div div="ublock_body_command">
						<label>Connect block</label><br>
						<div style="display: inline-block; vertical-align: top">
							<table id="utable_command_start" border="1">
								<tbody>
									<tr>
										<th>order</th>
										<th>command</th>
										<th>waitFor</th>
										<th>waitTime, ms</th>
										<th>save</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="display: inline-block; vertical-align: top">
							<button id="ubtn_rowinsert_start">Insert row</button>
							<br>
							<button id="ubtn_rowdelete_start">Delete row</button>
							<br>
						</div>
						<div id="ublock_end_command">
							<label>Disconnect Block</label><br>
							<div style="display: inline-block; vertical-align: top">
								<table id="utable_command_end" border="1">
									<tbody>
										<tr>
											<th>order</th>
											<th>command</th>
											<th>waitFor</th>
											<th>waitTime, ms</th>
											<th>save</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="display: inline-block">
								<button id="ubtn_rowinsert_end">Insert row</button>
								<br>
								<button id="ubtn_rowdelete_end">Delete row</button>
								<br>
							</div>
						</div>
					</div>
					<div id="ublock_body_command">
						<label>Action Block</label><br>
						<div style="display: inline-block; vertical-align: top">
							<table id="utable_command_body" border="1">
								<tbody>
									<tr>
										<th>order</th>
										<th>command</th>
										<th>waitFor</th>
										<th>waitTime, ms</th>
										<th>save</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="display: inline-block">
							<button id="ubtn_rowinsert_body">Insert row</button>
							<br>
							<button id="ubtn_rowdelete_body">Delete row</button>
							<br>
						</div>
					</div>
				</div>
				<div>
					<button id="btn_update">Update</button>
					<button id="btn_clone">Clone</button>
				</div>
			</fieldset>
			<br>
		</div>


		<div id="divAdd" style="display: inline-block;">
			<fieldset>
				<legend>New Template</legend>
				<div style="display: inline-block;">
					<table border="1">
						<tr>
							<td>Template Name</td>
							<td><input id='atemplate_name' /> <input id='atemplate_id'
								type="hidden" /></td>
						</tr>
						<tr>
							<td>Terminal Server</td>
							<td><select id='aterminalserver' /></td>
						</tr>
					</table>
				</div>
				<div style="display: inline-block; vertical-align: top">
					<div div="ablock_more">
						<label>Block More</label><br>
						<div style="display: inline-block; vertical-align: top">
							<table id="atable_more" border="1">
								<tbody>
									<tr>
										<th>more</th>
										<th>more_Do</th>
										<th>isdelete</th>
										<th>delete</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="display: inline-block; vertical-align: top">
							<button id="abtn_rowinsert_more">Insert row</button>
							<br>
							<button id="abtn_rowdelete_more">Delete row</button>
							<br>

						</div>
					</div>
					<div div="ablock_body_command">
						<label>Connect Block</label><br>
						<div style="display: inline-block; vertical-align: top">
							<table id="atable_command_start" border="1">
								<tbody>
									<tr>
										<th>order</th>
										<th>command</th>
										<th>waitFor</th>
										<th>waitTime, ms</th>
										<th>save</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="display: inline-block; vertical-align: top">
							<button id="abtn_rowinsert_start">Insert row</button>
							<br>
							<button id="abtn_rowdelete_start">Delete row</button>
							<br>

						</div>
						<div id="ablock_end_command">
							<label>Disconnect Block</label><br>
							<div style="display: inline-block; vertical-align: top">
								<table id="atable_command_end" border="1">
									<tbody>
										<tr>
											<th>order</th>
											<th>command</th>
											<th>waitFor</th>
											<th>waitTime, ms</th>
											<th>save</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="display: inline-block">
								<button id="abtn_rowinsert_end">Insert row</button>
								<br>
								<button id="abtn_rowdelete_end">Delete row</button>
								<br>

							</div>
						</div>
					</div>
					<div id="ablock_body_command">
						<label>Action Block</label><br>
						<div style="display: inline-block; vertical-align: top">
							<table id="atable_command_body" border="1">
								<tbody>
									<tr>
										<th>order</th>
										<th>command</th>
										<th>waitFor</th>
										<th>waitTime, ms</th>
										<th>save</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="display: inline-block">
							<button id="abtn_rowinsert_body">Insert row</button>
							<br>
							<button id="abtn_rowdelete_body">Delete row</button>
							<br>
						</div>
					</div>
				</div>
				<div>
					<button id="btn_create">Create</button>
				</div>
			</fieldset>
			<br>
		</div>
</body>
</html>