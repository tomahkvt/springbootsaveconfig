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
<script src="js/jquery-2.1.4.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.10.2.js"></script>-->
<script src="js/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
<!--<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
<link rel="stylesheet" type="text/css"
	href="js/DataTables-1.10.13/media/css/jquery.dataTables.min.css">
<script src="js/restschedule.js"></script>
<script src="js/menubar.js">
</script>
<link rel="stylesheet" href="css/menubar.css">
</head>
<body>
	<%@ include file="menubar.jsp"%>
	Configure Schedule
	<div>
		<table id="task_table" class="display">
			<thead>
				<tr>
					<th>Task Name</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<br>
		<div id="statusofaction"></div>
		<br>
		<button id="button_add">Add Task</button>
		<button id="button_edit">Edit Task</button>
		<button id="button_del">Delete Task</button>
		<br> <br>
		<div id="json_result_status"></div>
		<div id="json_result_error"></div>
		<div id="divUpdate" style="display: inline-block;">

			<fieldset>
				<legend>Edit Parameters</legend>
				<div style="display: inline-block;">
					<table border="1">
						<tr>
							<td>Task Name</td>
							<td><input id='u_task_name' /> <input id='u_task_id'
								type="hidden" /></td>
						</tr>
					</table>
					<div style="display: block; vertical-align: top">
						<div div="u_block_schedule"
							style="display: inline-block; vertical-align: top">
							<label>Schedule</label><br>
							<div style="display: inline-block; vertical-align: top">
								<table id="u_table_schedule" border="1">
									<tbody>
										<tr>
											<th>day_of_week</th>
											<th>hour</th>
											<th>minute</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="display: inline-block; vertical-align: top">
								<button id="u_btn_rowinsert_schedule">Insert row</button>
								<br>
								<button id="u_btn_rowdelete_schedule">Delete row</button>
								<br>
								<button id="u_btn_rowtest_schedule">test row</button>
								<br>
							</div>
						</div>
						<div style="display: inline-block; vertical-align: top">
							<table>
								<tbody>
									<tr>
										<td>Groups</td>
										<td>&nbsp;</td>
										<td>Other groups</td>
									</tr>
									<tr>
										<td><select class="input select" id="u_groups_left"
											name="u_groups_left" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
										<td><input class="input formlist" type="button"
											id="u_add_group" name="add" value="  «  "><br> <input
											class="input formlist" type="button" id="u_remove_group"
											name="remove" value="  »  "></td>
										<td><select class="input select" id="u_groups_right"
											name="u_groups_right" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
							</table>
							<table>
								<tbody>
									<tr>
										<td>Devices</td>
										<td>&nbsp;</td>
										<td>Other Devices</td>
									</tr>
									<tr>
										<td><select class="input select" id="u_devices_left"
											name="u_groups_left" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
										<td><input class="input formlist" type="button"
											id="u_add_device" name="add" value="  «  "><br>
											<input class="input formlist" type="button"
											id="u_remove_device" name="remove" value="  »  "></td>
										<td><select class="input select" id="u_devices_right"
											name="u_groups_right" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div>
							<button id="btn_update">Update</button>
						</div>
			</fieldset>
			<br>
		</div>
		<div id="divAdd" style="display: inline-block;">
			<fieldset>
				<legend>Edit parametrs</legend>
				<div style="display: inline-block;">
					<table border="1">
						<tr>
							<td>Task Name</td>
							<td><input id='a_task_name' /> <input id='a_task_id'
								type="hidden" /></td>
						</tr>
					</table>
					<div style="display: block; vertical-align: top">
						<div div="a_block_schedule"
							style="display: inline-block; vertical-align: top">
							<label>Schedule</label><br>
							<div style="display: inline-block; vertical-align: top">
								<table id="a_table_schedule" border="1">
									<tbody>
										<tr>
											<th>day_of_week</th>
											<th>hour</th>
											<th>minute</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="display: inline-block; vertical-align: top">
								<button id="a_btn_rowinsert_schedule">Insert row</button>
								<br>
								<button id="a_btn_rowdelete_schedule">Delete row</button>
								<br>
							</div>
						</div>
						<div style="display: inline-block; vertical-align: top">
							<table>
								<tbody>
									<tr>
										<td>Groups</td>
										<td>&nbsp;</td>
										<td>Other groups</td>
									</tr>
									<tr>
										<td><select class="input select" id="a_groups_left"
											name="a_groups_left" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
										<td><input class="input formlist" type="button"
											id="a_add_group" name="add" value="  «  "><br> <input
											class="input formlist" type="button" id="a_remove_group"
											name="remove" value="  »  "></td>
										<td><select class="input select" id="a_groups_right"
											name="a_groups_right" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
							</table>

							<table>
								<tbody>
									<tr>
										<td>Devices</td>
										<td>&nbsp;</td>
										<td>Other devices</td>
									</tr>
									<tr>
										<td><select class="input select" id="a_devices_left"
											name="a_groups_left" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
										<td><input class="input formlist" type="button"
											id="a_add_device" name="a_add" value="  «  "><br>
											<input class="input formlist" type="button"
											id="a_remove_device" name="a_remove" value="  »  "></td>
										<td><select class="input select" id="a_devices_right"
											name="a_groups_right" size="10" multiple="multiple"
											style="width: 280px;">
										</select></td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div>
							<button id="btn_create">Create</button>
						</div>
			</fieldset>
			<br>
		</div>
</body>
</html>