<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Locale Test</title>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			Locale <small>Locale Page..</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
			<li class="active">Here</li>
		</ol>
		</section>
		<section class="content"> Locale <spring:message
			code="test.test" /> Language : <a href="?language=en_US">English</a>
		| <a href="?language=ko_KR">Korea</a>
		<h3>
			Message :
			<spring:message code="test.test" text="default Text" />
			<spring:message code="test.xxxx" text="default Text" />
		</h3>
		Current Locale : ${pageContext.response.locale} </section>
	</div>
</body>
</html>