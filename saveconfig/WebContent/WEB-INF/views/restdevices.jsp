<%@page pageEncoding="UTF-8"%>
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
<script src="js/restdevices.js"></script>
</head>

<body>
	<a href="/home17">Вернуться</a>
	<BR> Вы авторизировались как
	<security:authentication property="principal.username" />
	<a href="logout" style="float: right;">logout</a>
	<br>
	<button id="button_add">Добавить устройство</button>
	<button id="button_edit">Редактировать устройство</button>
	<button id="button_del">Удалить устройство</button>
	<br>
	<div id="divUpdate">
		<fieldset style="width: 420px">
			<legend>Редактировать значение</legend>
			<table border="1">
				<tr>
					<td>Ip</td>
					<td><input id='uip_device' type="text" /> <input
						id='udevice_id' type="hidden" />test</td>
				</tr>
				<tr>
					<td>Hostname</td>
					<td><input id='uhostname_device' type="text" /></td>
				</tr>
				<tr>
					<td>Login</td>
					<td><input id='ulogin_device' type="text" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input id='upasswd_device' type="text" /></td>
				</tr>
				<tr>
					<td>Username1</td>
					<td><input id='uusername1' type="text" /></td>
				</tr>
				<tr>
					<td>Password1</td>
					<td><input id='upassword1' type="text" /></td>
				</tr>
				<tr>
					<td>Username2</td>
					<td><input id='uusername2' type="text" /></td>
				</tr>
				<tr>
					<td>Password2</td>
					<td><input id='upassword2' type="text" /></td>
				</tr>
				<tr>
					<td>Username3</td>
					<td><input id='uusername3' type="text" /></td>
				</tr>
				<tr>
					<td>Password3</td>
					<td><input id='upassword3' type="text" /></td>
				</tr>
				<tr>
					<td>Username4</td>
					<td><input id='uusername4' type="text" /></td>
				</tr>
				<tr>
					<td>Password4</td>
					<td><input id='upassword4' type="text" /></td>
				</tr>
				<tr>
					<td>Promt1</td>
					<td><input id='upromt1' type="text" /></td>
				</tr>
				<tr>
					<td>Promt2</td>
					<td><input id='upromt2' type="text" /></td>
				</tr>
				<tr>
					<td>Promt3</td>
					<td><input id='upromt3' type="text" /></td>
				</tr>
				<tr>
					<td>Promt4</td>
					<td><input id='upromt4' type="text" /></td>
				</tr>
				<tr>
					<td>Template</td>
					<td><select id='utemplate' /></td>
				</tr>

				<tr>
					<td>Enable</td>
					<td><select id='uenable_device'>
							<option value="true">Enable</option>
							<option value="false">Disable</option>
					</select></td>
				</tr>
			</table>
			<button id="btn_update">Update</button>
		</fieldset>
		<br>
	</div>

	<div id="divAdd">
		<fieldset style="width: 420px">
			<legend>Добавить устройство</legend>
			<table border="1">

				<tr>
					<td>Тип устройства</td>
					<td><select id='ctype_device' /></td>
				</tr>
				<tr>
					<td>Ip</td>
					<td><input id='cip_device' type="text" /></td>
				</tr>
				<tr>
					<td>Hostname</td>
					<td><input id='chostname_device' type="text" /></td>
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
					<td>Enable</td>
					<td><select id='cenable_device'>
							<option value=true>Enable</option>
							<option value=false>Disable</option>
					</select></td>
				</tr>
			</table>
			<button id="btn_create">Create</button>
		</fieldset>
		<br>
	</div>


	<table id="device_table" class="display">
		<thead>
			<tr>

				<th>Тип устройства</th>
				<th>IP</th>
				<th>Hostname</th>
				<th>Enable</th>

			</tr>
		</thead>


		<tbody>

		</tbody>
	</table>


</body>
</html>