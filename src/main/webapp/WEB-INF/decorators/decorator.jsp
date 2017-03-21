<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/webjars/jquery/3.2.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>#Decorator#</title>
	<sitemesh:write property='head' />
</head>
<body>
	<div>
		<!-- Security TagLib http://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html 참조 -->
		<sec:authorize access="isAuthenticated()">
			<p>
				안녕하세요. <sec:authentication property="principal.username"/>
				<sec:authorize access="hasRole('ADMIN')">
					(ADMIN)
				</sec:authorize>
				님 <a href="/security/logout">LogOut</a>
			</p>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<a href="/security/login">Login Page..</a>
		</sec:authorize>
	</div>
	<div>====================== Top ======================</div>
	<br/><br/>
	
	<sitemesh:write property='body' />
	
	<br/><br/>
	<div>===================== Bottom =====================</div>
</body>
</html>