<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="begin" value="${param.number+1 -((param.pageViewCount / 2) - ((param.pageViewCount / 2) % 1)) < 1 ? 1 : param.number+1 -((param.pageViewCount / 2) - ((param.pageViewCount / 2) % 1))}" />
<c:set var="end" value="${begin + param.pageViewCount-1 > param.totalPages ? param.totalPages : begin + param.pageViewCount-1}" />

<ul class="pagination no-margin pull-right">
	<c:if test="${!param.first}">
		<li><a href="javascript:movePage(0);"><i class="fa fa-angle-double-left"></i>&nbsp;</a></li>
		<li><a href="javascript:movePage(${param.number-1});"><i class="fa fa-angle-left"></i>&nbsp;</a></li>
	</c:if>
	<c:forEach var="page" begin="${begin}" end="${end}" varStatus="index">
		<li class="${index.index eq param.number+1 ? 'active' : '' }">
			<a href="javascript:movePage(${index.index-1});">${index.index}</a>
		</li>
	</c:forEach>
	<c:if test="${!param.last}">
		<li><a href="javascript:movePage(${param.number+1});"><i class="fa fa-angle-right"></i>&nbsp;</a></li>
		<li><a href="javascript:movePage(${param.totalPages-1});"><i class="fa fa-angle-double-right"></i>&nbsp;</a></li>
	</c:if>
</ul>

<script src="/webjars/uri.js/1.17.1/src/URI.min.js"></script>
<script>
function movePage(page){
	var uri = new URI(window.location);
	uri.setSearch("pageNumber", page);
	location.href = uri;
}
</script>