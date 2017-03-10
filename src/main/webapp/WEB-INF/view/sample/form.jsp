<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="#" action="/" method="post" commandName="personForm">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" value="${personForm.name}"/></td>
				<c:if test="${personForm.name == null}">
					<td th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Name Error</td>
					<td>Name Error</td>
				</c:if>
				
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type="text" name="name" value="${personForm.age}" /></td>
				<c:if test="">
					<td th:if="${#fields.hasErrors('age')}" th:errors="*{age}">Age Error</td>
					<td th:if="${#fields.hasErrors('age')}" th:errors="*{age}">Age Error</td>
				</c:if>
			</tr>
			<tr>
				<td><button type="submit">Submit</button></td>
			</tr>
		</table>
	</form>

</body>
</html> --%>