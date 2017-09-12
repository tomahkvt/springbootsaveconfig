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

<script src="js/menubar.js">
	
</script>
<link rel="stylesheet" href="css/menubar.css">


</head>
<body>
	<%@ include file="menubar.jsp"%>
	System paramets page
	<sf:form method="POST" commandName="dtoSystemParam">
	<div>
	<table border = "1">
	<tr>
	<td>Curent system </td>
	<td><sf:input readonly="true" path="detectSystem" /></td>
	</tr>
	<tr>
	<td>Path to configuration files Linux</td>
	<td><sf:input readonly="true" path="linuxSavePath" /></td>
	</tr>
	
	<tr>
	<td>Path to configuration files Windows</td>
	<td><sf:input readonly="true" path="winSavePath" /></td>
	</tr>
	
	<tr>
	<td>Number of templates</td>
	<td><input type="text" readonly value="${templatecount}" /></td>
	</tr>
	<tr>
	<td>Number of devices</td>
	<td><input type="text" readonly value="${devicecount}" /></td>
	</tr>
	
	</table>
	${resultupdate}
	<br>
	</div>
	</sf:form>

</body>
</html>