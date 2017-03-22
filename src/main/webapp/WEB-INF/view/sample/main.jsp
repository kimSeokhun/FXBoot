<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Main</title>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Main <small>Main Page..</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
				<li class="active">Here</li>
			</ol>
		</section>
		<section class="content">

			Main

			<ul class="nav navbar-nav navbar-right">
				<!-- 로그인하지 않았을때 -->
				<sec:authorize access="isAnonymous()">
					<li><a href="#">회원가입</a></li>
					<li><a href="/security/login">로그인</a></li>
				</sec:authorize>

				<!-- 회원 권한이 있을 때 -->
				<sec:authorize access="isAuthenticated()">
					<li><a href="/security/logout">로그아웃</a></li>
				</sec:authorize>

				<!-- 권한체크 -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="#">관리자 페이지</a></li>
				</sec:authorize>
				<!-- 여러 권한 체크 -->
				<sec:authorize access="hasAnyRole('ROLE_USER, ROLE_ADMIN')">
					<li><a href="#">정보수정</a></li>
				</sec:authorize>

			</ul>
		</section>
	</div>
</body>
</html>