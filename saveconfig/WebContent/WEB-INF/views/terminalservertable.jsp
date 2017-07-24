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
<c:if test="${id != null}">
	<script>
		var idupdate = "${id}";
	</script>
</c:if>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="js/DataTables-1.10.13/media/css/jquery.dataTables.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>  
<script src="js/restterminalserver.js"></script>
<script src="js/menubar.js"></script>
<link rel="stylesheet" href="css/menubar.css"/>
</head>
<body ng-app="terminalServerApp">
	<%@ include file="menubar.jsp"%>
	Configure Terminal Server
	<div>
	<c:if test="${id == null}">
		<table id="terminalserver_table" class="display">
			<thead>
				<tr>
					<th>Название терминального сервера</th>
					<th>IP адрес</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		</c:if>
	<br>		
	<button id="button_add">Add Terminal Server</button>
	<button id="button_edit">Edit Terminal Server</button>
	<button id="button_del">Delete Terminal Server</button>
	<button id="button_test">Test Terminal Server</button>
	<br>
	<div id="json_result_status"></div>
	<div id="json_result_error"></div>
	<br>		
	<form ng-controller="updatevalidateCtrl" name="updateForm" novalidate>
	<div id="divUpdate" style = "display:inline-block;display: none;">
		<fieldset>
			<legend>Редактировать значение</legend>
			<div style = "display:inline-block;">
			<table border="1">
			<tr><td>Имя</td><td>
			<input type="text" id = 'uterminalserver_name' name = 'uterminalserver_name' ng-model="uterminalserver_name" required/>
			<span ng-show="updateForm.uterminalserver_name.$error.required">The name is required.</span>
			<input id = 'uterminalserver_id' type = "hidden"/>
			</td></tr>
			<tr><td>Ip адрес</td><td>
			<input id = 'uterminalserver_ip' type="text" name= 'uterminalserver_ip' ng-model="uterminalserver_ip" ng-pattern="regex" required/>
			<span ng-show="updateForm.uterminalserver_ip.$error.required">The IP is required.</span>
			<span ng-show="updateForm.uterminalserver_ip.$invalid">The format is invalid.</span>
			</td><td></td></tr> 
			<tr><td>Username1</td><td><input id = 'uterminalserver_username1' type="text" name = 'uterminalserver_username1' ng-model="uterminalserver_username1" required/>
			<span ng-show="updateForm.uterminalserver_username1.$error.required">The username1 is required.</span>
			</td><td></td></tr>
			<tr>
				<td>Password1</td>
				<td><span id = 'u_span_terminalserver_passw1'></span>
				<input type="password" id="u_input_terminalserver_passw1"/></td>
				<td><button id="btn_compare1">Сверить</button></td>
			</tr>

			<tr><td>Promt1</td><td><input id = 'uterminalserver_promt1'type="text" name = 'uterminalserver_promt1' ng-model="uterminalserver_promt1" required/>
			<span ng-show="updateForm.uterminalserver_promt1.$error.required">The promt1 is required.</span> 
			</td><td></td></tr>
			<tr><td>Username2</td><td><input id = 'uterminalserver_username2' type="text" /></td><td></td></tr>
			<tr>
				<td>Password2</td>
				<td><span id = 'u_span_terminalserver_passw2'></span>
				<input type="password" id="u_input_terminalserver_passw2"/></td>
				<td><button id="btn_compare2">Сверить</button></td>
			</tr>

			<tr><td>Promt2</td><td><input id = 'uterminalserver_promt2'type="text"/></td><td></td></tr>

			</table>
			</div>
			<div style = "display:inline-block; vertical-align:top">
			</div>
			<div>
			
			<a id = "hrefselectdevice" href ="terminalserver">Выбрать устройство</a>
			<br> 
			<button id="btn_update">Update</button>
			<button id="btn_clone">Clone</button>
			</div>
		</fieldset>
		<br>
	</div>
	</form>

	<form ng-controller="addvalidateCtrl" name="addForm" novalidate>
	<div id="divAdd" style = "display:inline-block;display: none;">
	
		<fieldset>
			<legend>Создать терминальный сервер</legend>
			<div style = "display:inline-block;">
			<table border="1">
			
			<tr><td>Имя</td><td>
			<input type="text" id = 'aterminalserver_name' name = 'aterminalserver_name' ng-model="aterminalserver_name" required/>
			<span ng-show="addForm.aterminalserver_name.$error.required">The name is required.</span>
			</td></tr>
 			
			<tr><td>Ip адрес</td><td>
			<input id = 'aterminalserver_ip' type="text" name= 'aterminalserver_ip' ng-model="aterminalserver_ip" ng-pattern="regex" required/>
			<span ng-show="addForm.aterminalserver_ip.$error.required">The IP is required.</span>
			<span ng-show="addForm.aterminalserver_ip.$invalid">The format is invalid.</span>
			</td></tr> 
			<tr><td>Username1</td><td><input id = 'aterminalserver_username1' type="text" name = 'aterminalserver_username1' ng-model="aterminalserver_username1" required/>
			<span ng-show="addForm.aterminalserver_username1.$error.required">The username1 is required.</span>
			</td></tr>
			<tr>
				<td>Password1</td>
				<td>
				<input type="password" id="a_input_terminalserver_passw1"/></td>
				
			</tr>

			<tr><td>Promt1</td><td><input id = 'aterminalserver_promt1'type="text" name = 'aterminalserver_promt1' ng-model="aterminalserver_promt1" required/>
			<span ng-show="addForm.aterminalserver_promt1.$error.required">The promt1 is required.</span> 
			</td></tr>
			<tr><td>Username2</td><td><input id = 'aterminalserver_username2' type="text" /></td></tr>
			<tr>
				<td>Password2</td>
				<td>
				<input type="password" id="a_input_terminalserver_passw2"/></td>
			</tr>
			<tr><td>Promt2</td><td><input id = 'aterminalserver_promt2'type="text"/></td></tr>
			</table>
			</div>
			<div style = "display:inline-block; vertical-align:top">
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