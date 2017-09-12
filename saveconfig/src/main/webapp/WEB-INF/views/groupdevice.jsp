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
<script src="js/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="js/DataTables-1.10.13/media/css/jquery.dataTables.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="js/restgroupdevices.js"></script>
<script src="js/menubar.js">
	
</script>
<link rel="stylesheet" href="css/menubar.css">
</head>
<body ng-app="deviceApp">
	<%@ include file="menubar.jsp"%>
	Configure devices group
	<div>
		<table id="devicegroup_table" class="display">
			<thead>
				<tr>
					<th>Name of groups</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<br>
		<div id="statusofaction"></div>
		<br>
		<button id="button_add">Add group</button>
		<button id="button_edit">Edit group</button>
		<button id="button_del">Delete group</button>
		<br> <br>
		<div id="json_result_status"></div>
		<div id="json_result_error"></div>
		<form ng-controller="updatevalidateCtrl" name="updateForm" novalidate>
			<div id="divUpdate" style="display: inline-block;">
				<fieldset>
					<legend>Edit parametrs</legend>
					<div style="display: inline-block;">
						<table border="1">
							<tr>
								<td>Device Group Name<input id='udevicegroup_id'
									type="hidden" /></td>
								<td><input id='uname_devicegroup' type="text"
									name="uname_devicegroup" ng-model="uname_devicegroup" required />
									<span ng-show="addForm.uname_devicegroup.$error.required">The
										name is required.</span></td>
							</tr>
						</table>
					</div>
					<div>
						<button id="btn_update">Update</button>

					</div>
				</fieldset>
				<br>
			</div>
		</form>
		<form ng-controller="addvalidateCtrl" name="addForm" novalidate>
			<div id="divAdd" style="display: inline-block;">
				<fieldset>
					<legend>Add group</legend>
					<div style="display: inline-block;">
						<table border="1">
							<tr>
								<td>Device Group Name</td>
								<td><input id='cname_devicegroup' type="text"
									name="cname_devicegroup" ng-model="cname_devicegroup" required />
									<span ng-show="addForm.cname_devicegroup.$error.required">The
										name is required.</span></td>
							</tr>
						</table>
					</div>
					<div>
						<button id="btn_create">Create</button>
					</div>
				</fieldset>
				<br>
			</div>
		</form>
</body>
</html>