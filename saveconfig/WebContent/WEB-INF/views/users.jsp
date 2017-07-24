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
<script src="js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="js/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="js/DataTables-1.10.13/media/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="js/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="js/restuser.js"></script>
<script src="js/menubar.js">
	
</script>
<link rel="stylesheet" href="css/menubar.css">
</head>
<body>
	<%@ include file="menubar.jsp"%>
	Users page
	<div>
		<table id="user_table" class="display">
			<thead>
				<tr>
					<th>Username</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<br>
		<div id="statusofaction"></div>
		<br>
		<button id="button_add">Add user</button>
		<button id="button_edit">Set Password</button>
		<button id="button_del">Delete user</button>
		<br> <br>
		<div id="json_result_status"></div>
		<div id="json_result_error"></div>
		<div id="divUpdate" style="display: inline-block;">
			<fieldset>
				<legend>Edit Parametrs</legend>
				<div style="display: inline-block;">
					<table border="1">
						<tr>
							<td>Login</td>
							<td><input id='uiduser' type="hidden" /> <input
								id='uusername' readonly /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><span id='u_span_password'></span> <input id='upassword'
								type="password" /></td>
						</tr>
						<tr>
							<td>Password Comfirm</td>
							<td><input id='uconfirmpassword' type="password" /></td>
						</tr>
						<tr>
							<td>Status</td>
							<td><select id='uenable'>
									<option value=false>Disable</option>
									<option value=true>Enable</option>
							</select></td>
						</tr>
					</table>
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
								<td><select class="input select" id="uroles_left"
									name="roles_left" size="10" multiple="multiple"
									style="width: 280px;">
								</select></td>
								<td><input class="input formlist" type="button"
									id="uaddrole" name="uaddrole" value="  «  "><br> <input
									class="input formlist" type="button" id="uremoverole"
									name="uremoverole" value="  »  "></td>
								<td><select class="input select" id="uroles_right"
									name="groups_right" size="10" multiple="multiple"
									style="width: 280px;">
								</select></td>
							</tr>
							<tr>

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
				<legend>New user</legend>
				<div style="display: inline-block;">
					<table border="1">
						<tr>
							<td>Username</td>
							<td><input id='aiduser' type="hidden" /> <input
								id='ausername' /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input id='apassword' type="password" /></td>
						</tr>
						<tr>
							<td>Password Comfirm</td>
							<td><input id='aconfirmpassword' type="password" /></td>
						</tr>
						<tr>
							<td>Status</td>
							<td><select id='aenable'>
									<option value=false>Disable</option>
									<option value=true>Enable</option>

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
								<td><select class="input select" id="aroles_left"
									name="roles_left" size="10" multiple="multiple"
									style="width: 280px;">
								</select></td>
								<td><input class="input formlist" type="button"
									id="aaddrole" name="aaddrole" value="  «  "><br> <input
									class="input formlist" type="button" id="aremoverole"
									name="aremoverole" value="  »  "></td>
								<td><select class="input select" id="aroles_right"
									name="groups_right" size="10" multiple="multiple"
									style="width: 280px;">
								</select></td>
							</tr>
							<tr>

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