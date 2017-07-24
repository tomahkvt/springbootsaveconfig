<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Welcome</title>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
<script src="js/jstree/dist/jstree.js"></script>

<script src="js/menubar.js"></script>
<script src="js/restfiletree.js"></script>

<link rel="stylesheet"
	href="js/DataTables-1.10.13/media/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="js/jstree/dist/themes/default/style.min.css" />

<link rel="stylesheet" href="css/menubar.css">
<link rel="stylesheet" href="css/filetree.css">
<meta name="viewport" content="width=device-width" />
</head>
<body>
	<%@ include file="menubar.jsp"%>
	<div>view Page</div>
	<div id="tree"></div>
	<div id="data">
				<div class="content code" style="display:none;"><textarea id="code" readonly="readonly"></textarea></div>
				<div class="content folder" style="display:none;"></div>
				<div class="content image" style="display:none; position:relative;"><img src="" alt="" style="display:block; position:absolute; left:50%; top:50%; padding:0; max-height:90%; max-width:90%;" /></div>
				<div class="content default" style="text-align:center;">Select a file from the tree.</div>
			</div>
	
	<br>
	<div id="json_result_status"></div>
	<div id="json_result_error"></div>
</body>
<script>
	
</script>
</html>