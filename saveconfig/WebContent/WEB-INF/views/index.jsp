<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Welcome</title> Вы авторизировались как
<security:authentication property="principal.username" />
!
<a href="logout" style="float: right;">logout</a>
<br>
<body>
	<table>
		<thead>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<tr>
					<th><a href="device">Натсройка устройств</a></th>
				</tr>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<tr>
					<th><a href="template">Натсройка шаблонов</a></th>
				</tr>
			</security:authorize>

			<security:authorize access="hasRole('ROLE_ADMIN')">
				<tr>
					<th><a href="terminalserver">Натсройка терминальных
							серверов</a></th>
				</tr>
			</security:authorize>
			<tr>
				<th><a href="mactable">Страница мактаблицы</a></th>
			</tr>
			<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')">
				<tr>
					<th><a href="uploadpage">Загрузка DHCP</a></th>
				</tr>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<tr>
					<th><a href="console">Сбор данных с устройств</a></th>
				</tr>
			</security:authorize>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>