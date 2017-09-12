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
<script src="js/restlogging.js"></script>
<script src="js/menubar.js">
	
</script>
<link rel="stylesheet" href="css/menubar.css">
</head>
<body>
	<%@ include file="menubar.jsp"%>
	Log page
	<div>
		<div style="vertical-align: top;">
			<table id="task_table" class="display">
				<thead align="left">
					<tr>
						<th>Id</th>
						<th>Task Name</th>
						<th>Type Start</th>
						<th>Status</th>
						<th>Time Start</th>
						<th>Time Stop</th>
					</tr>
				</thead>
				<tfoot align="left">
					<tr>
						<th>Id</th>
						<th>Task Name</th>
						<th>Type Start</th>
						<th>Status</th>
						<th>Time Start</th>
						<th>Time Stop</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
		<div id="divcomplitedtask" style="vertical-align: top; display: none;">
			Detail Log
			<table id="complitedtasklogging_table" class="display">
				<thead>
					<tr>
						<th>Time of application</th>
						<th>Status</th>
						<th>Massage</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>

		<br>
		<div id="statusofaction"></div>
		<br>
		<div id="control">
			Status of active console: <label id="label_status"></label><br>
			<select id='task_list'></select>
			<button id="bt_start">Start</button>
			<button id="bt_pause">Pause</button>
			<button id="bt_stop">Stop</button>
			<br> Active console
			<div id="div_console_log">
				<textarea id="console_log" name="comment" cols="150" rows="40"></textarea>
			</div>

			<br>
			<div id="json_result_status"></div>
			<div id="json_result_error"></div>
</body>
</html>