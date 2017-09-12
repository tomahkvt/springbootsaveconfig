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
<script src="js/restdevices.js"></script>
<script src="js/menubar.js">
	
</script>
<link rel="stylesheet" href="css/menubar.css">

</head>
<body ng-app="deviceApp">
	<%@ include file="menubar.jsp"%>
	Configure device
	<div>
		<table id="device_table" class="display">
			<thead>
				<tr>
					<th>Template</th>
					<th>IP</th>
					<th>Hostname</th>
					<th>State</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<br>
		<div id="statusofaction"></div>
		<br>
		<button id="button_add">Add device</button>
		<button id="button_edit">Edit device</button>
		<button id="button_del">Delete device</button>
		<button id="button_test">Test Device</button>
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
								<td>Template</td>
								<td><select id='utemplate' /></td>
							</tr>
							<tr>
								<td>Ip</td>
								<td><input id='uip_device' type="text" name='uip_device'
									ng-model="uip_device" ng-pattern="regex" required /> <span
									ng-show="updateForm.uip_device.$error.required">The IP
										is required.</span> <span ng-show="updateForm.uip_device.$invalid">The
										format is invalid.</span></td>
							</tr>
							<tr>
								<td>Hostname</td>
								<td><input id='uhostname_device' type="text"
									name="uhostname_device" ng-model="uhostname_device" required />
									<span ng-show="updateForm.uhostname_device.$error.required">The
										name is required.</span></td>
							</tr>
							<input id='udevice_id' type="hidden" />
							</td>
							</tr>
							<tr>
								<td>Username1</td>
								<td><input id='uusername1' type="text" /></td>
							</tr>
							<tr>
								<td>Password1</td>
								<td><span id='u_span_password1'></span> <input
									id='upassword1' type="password" /></td>
								<td><button class="btn_compare" id="btn_compare1" value="1">Check</button></td>
							</tr>
							<tr>
								<td>Promt1</td>
								<td><input id='upromt1' type="text" /></td>
							</tr>
							<tr>
								<td>Username2</td>
								<td><input id='uusername2' type="text" /></td>
							</tr>
							<tr>
								<td>Password2</td>
								<td><span id='u_span_password2'></span> <input
									id='upassword2' type="password" /></td>
								<td><button class="btn_compare" id="btn_compare2" value="2">Check</button></td>
							</tr>
							<tr>
								<td>Promt2</td>
								<td><input id='upromt2' type="text" /></td>
							</tr>
							<tr>
								<td>Username3</td>
								<td><input id='uusername3' type="text" /></td>
							</tr>
							<tr>
								<td>Password3</td>
								<td><span id='u_span_password3'></span> <input
									id='upassword3' type="password" /></td>
								<td><button class="btn_compare" id="btn_compare3" value="3">Check</button></td>
							</tr>
							<tr>
								<td>Promt3</td>
								<td><input id='upromt3' type="text" /></td>
							</tr>
							<tr>
								<td>Username4</td>
								<td><input id='uusername4' type="text" /></td>
							</tr>
							<tr>
								<td>Password4</td>
								<td><span id='u_span_password4'></span> <input
									id='upassword4' type="password" /></td>
								<td><button class="btn_compare" id="btn_compare4" value="4">Check</button></td>
							</tr>
							<tr>
								<td>Promt4</td>
								<td><input id='upromt4' type="text" /></td>
							</tr>
							<tr>
								<td>Enable</td>
								<td><select id='uenable_device'>
										<option value="true">Enable</option>
										<option value="false">Disable</option>
								</select></td>
							</tr>
						</table>
					</div>
					<div style="display: inline-block; vertical-align: top">
						<table>
							<tbody>
								<tr>
									<td>In groups</td>
									<td>&nbsp;</td>
									<td>Other groups</td>
								</tr>
								<tr>
									<td><select class="input select" id="ugroups_left"
										name="groups_left" size="10" multiple="multiple"
										style="width: 280px;">
									</select></td>
									<td><input class="input formlist" type="button"
										id="uaddgroup" name="uaddgroup" value="  «  "><br>
										<input class="input formlist" type="button" id="uremovegroup"
										name="uremovegroup" value="  »  "></td>
									<td><select class="input select" id="ugroups_right"
										name="groups_right" size="10" multiple="multiple"
										style="width: 280px;">
									</select></td>
								</tr>
								<tr>
									<td><label for="newgroup">New group</label> <br> <input
										class="input text" type="text" id="newgroup" name="newgroup"
										value="" size="25" maxlength="64"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div>
						<button id="btn_update">Update</button>
						<button id="btn_clone">Clone</button>
					</div>
				</fieldset>
				<br>
			</div>
		</form>
		<form ng-controller="addvalidateCtrl" name="addForm" novalidate>
			<div id="divAdd" style="display: inline-block;">
				<fieldset>
					<legend>Add device</legend>
					<div style="display: inline-block;">
						<table border="1">

							<tr>
								<td>Device Type</td>
								<td><select id='ctype_device' /></td>
							</tr>
							<tr>
								<td>Template</td>
								<td><select id='ctemplate' /></td>
							</tr>
							<tr>
								<td>Ip</td>
								<td><input id='cip_device' type="text" name='cip_device'
									ng-model="cip_device" ng-pattern="regex" required /> <span
									ng-show="addForm.cip_device.$error.required">The IP is
										required.</span> <span ng-show="addForm.cip_device.$invalid">The
										format is invalid.</span></td>
							</tr>
							<tr>
								<td>Hostname</td>
								<td><input id='chostname_device' type="text"
									name="chostname_device" ng-model="chostname_device" required />
									<span ng-show="addForm.chostname_device.$error.required">The
										name is required.</span></td>
							</tr>
							<tr>
								<td>Login</td>
								<td><input id='clogin_device' type="text" /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><input id='cpasswd_device' type="text" /></td>
							</tr>
							<tr>
								<td>Username1</td>
								<td><input id='cusername1' type="text" /></td>
							</tr>
							<tr>
								<td>Password1</td>
								<td><input id='cpassword1' type="text" /></td>
							</tr>
							<tr>
								<td>Username2</td>
								<td><input id='cusername2' type="text" /></td>
							</tr>
							<tr>
								<td>Password2</td>
								<td><input id='cpassword2' type="text" /></td>
							</tr>
							<tr>
								<td>Username3</td>
								<td><input id='cusername3' type="text" /></td>
							</tr>
							<tr>
								<td>Password3</td>
								<td><input id='cpassword3' type="text" /></td>
							</tr>
							<tr>
								<td>Username4</td>
								<td><input id='cusername4' type="text" /></td>
							</tr>
							<tr>
								<td>Password4</td>
								<td><input id='cpassword4' type="text" /></td>
							</tr>
							<tr>
								<td>Promt1</td>
								<td><input id='cpromt1' type="text" /></td>
							</tr>
							<tr>
								<td>Promt2</td>
								<td><input id='cpromt2' type="text" /></td>
							</tr>
							<tr>
								<td>Promt3</td>
								<td><input id='cpromt3' type="text" /></td>
							</tr>
							<tr>
								<td>Promt4</td>
								<td><input id='cpromt4' type="text" /></td>
							</tr>
							<tr>
								<td>Enable</td>
								<td><select id='cenable_device'>
										<option value="true">Enable</option>
										<option value="false">Disable</option>
								</select></td>
							</tr>
						</table>
					</div>
					<div style="display: inline-block; vertical-align: top">
						<table>
							<tbody>
								<tr>
									<td>In groups</td>
									<td>&nbsp;</td>
									<td>Other groups</td>
								</tr>
								<tr>
									<td><select class="input select" id="cgroups_left"
										name="cgroups_left" size="10" multiple="multiple"
										style="width: 280px;">
									</select></td>
									<td><input class="input formlist" type="button"
										id="caddgroup" name="caddgroup" value="  «  "><br>
										<input class="input formlist" type="button" id="cremovegroup"
										name="cremovegroup" value="  »  "></td>
									<td><select class="input select" id="cgroups_right"
										name="cgroups_right" size="10" multiple="multiple"
										style="width: 280px;">
									</select></td>
								</tr>
								<tr>
									<td><label for="cnewgroup">New group</label> <br>
										<input class="input text" type="text" id="cnewgroup"
										name="cnewgroup" value="" size="25" maxlength="64"></td>
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
		</form>
</body>
</html>