<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
					<td><input type='text' name='username' value='' /></td>
					<td><form:errors path="username" /> <spring:message code="error.login.fail"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
					<td><form:errors path="password" /></td>
				</tr>
				<tr>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<td colspan='2'><input name="submit" type="submit"
						value="Login" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>