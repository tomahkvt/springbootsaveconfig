<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:if test="${top_menu_active != null}">
	<script>
		var top_menu_active = "${top_menu_active}";
	</script>
</c:if>
<c:if test="${slave_menu_active != null}">
	<script>
		var slave_menu_active = "${slave_menu_active}";
	</script>
</c:if>
<c:if test="${slave_menu_show != null}">
	<script>
		var slave_menu_show = "${slave_menu_show}";
	</script>
</c:if>
<div class="logo">SaveALLConfig</div>
<div class="top-right">
<ul>
	<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
</ul>
</div>


<ul class="main-bar">
  <li id="systemgeneral"><a href="systemgeneral">General</a></li>
  <li id="menu_configure">Configure</li>
  <li id="menu_aministration">Administaration</li>
  <li id="menu_shedule">Schedule</li>
  <li id="viewing"><a href="viewing">View</a></li>
</ul>
	
<ul class="sub-menu configure">
  <li><a href="groupdevice">Groups</a></li>  	
  <li><a href="devicetable">Devices</a></li>	
  <li><a href="template">Templates</a></li>
  <li><a href="terminalserver">Terminal Servers</a></li>
</ul>
	
	
<ul class="sub-menu aministration">
  <li><a href="users">Users</a></li>	
  <li><a href="systemparam">System params</a></li>
</ul>
	
<ul class="sub-menu shedule">
  <li><a href="schedule">Schedule</a></li>	
  <li><a href="restlogging">Logs</a></li>
</ul>
