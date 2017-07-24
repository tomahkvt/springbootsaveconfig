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
	System paramters
	<sf:form method="POST" commandName="dtoSystemParam">
	<div>
	<table border = "1">
	<tr>
	<td>Curent system</td>
	<td><sf:input readonly="true" path="detectSystem" /></td>
	</tr>
	<tr>
	<td>Path to configuration files Linux</td>
	<td><sf:input path="linuxSavePath" /></td>
	</tr>
	
	<tr>
	<td>Path to configuration files Windows</td>
	<td><sf:input path="winSavePath" /></td>
	</tr>
	</table>
	${resultupdate}
	<br>
	<input type="submit" value="Update">
	
	</div>
	</sf:form>
	<br>
	<div id="json_result_status"></div>
	<div id="json_result_error"></div>
</body>
</html>