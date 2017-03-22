<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Validate</title>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			Validate <small>Validate Page..</small>
		</h1>
		<ol class="breadcrumb">
			<li class="active"><a href="#"><i class="fa fa-dashboard"></i> Validate</a></li>
		</ol>
		</section>
		<section class="content">
			${person} ${model}
			<form:form action="valid" method="post" commandName="person">
				<table>
					<tr>
						<td>Name:</td>
						<td><form:input path="name" />
							<form:errors path="name" /></td>
					</tr>
					<tr>
						<td>Age:</td>
						<td><form:input path="age" />
							<form:errors path="age" /></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><form:input path="email" />
							<form:errors path="email" /></td>
					</tr>
					<tr>
						<td>Range:</td>
						<td><form:input path="range" />
							<form:errors path="range" /></td>
					</tr>
					<tr>
						<td><button type="submit">Submit</button></td>
					</tr>
				</table>
			</form:form>
		</section>
	</div>
</body>
</html>