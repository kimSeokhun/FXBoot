<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body onload='document.f.username.focus();'>
	<div id="loginForm">
		<h3>Login With Username and Password</h3>
		<form:form name='f' action="<%= com.flexink.project.config.web.WebSecurityConfigureAdapter.LOGIN_PROCESSING_URL%>" method='POST'>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name="<%= com.flexink.project.config.web.WebSecurityConfigureAdapter.USERNAME_PARAMETER%>" value='' /></td>
					<td><form:errors path="<%= com.flexink.project.config.web.WebSecurityConfigureAdapter.USERNAME_PARAMETER%>" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name="<%= com.flexink.project.config.web.WebSecurityConfigureAdapter.PASSWORD_PARAMETER%>" /></td>
					<td><form:errors path="<%= com.flexink.project.config.web.WebSecurityConfigureAdapter.PASSWORD_PARAMETER%>" /></td>
				</tr>
				<tr>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
				</tr>
			</table>
		</form:form>
		<sec:authorize access="isAuthenticated()">
			안녕하세요. <a href="/security/logout">LogOut</a> 
		</sec:authorize>
	</div>
</body>
</html>