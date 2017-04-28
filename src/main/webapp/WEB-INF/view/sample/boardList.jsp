<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/inc_taglib.jspf" %>

<html>
<head>
<script>
function search() {
	var uri = new URI(window.location);
	uri.setSearch("filter", $('#filter').val());
	uri.setSearch("pageNumber", 0);
	location.href = uri;
}
</script>
<title>Main</title>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Board <small>Board..</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
				<li class="active">Here</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
		        <div class="col-xs-12">
		        	<div class="box">
			            <div class="box-header with-border">
			              <h3 class="box-title">Board Sample</h3>
			              
			              <form>
				              <div class="input-group input-group-sm col-xs-4">
				                <input type="text" class="form-control" id="filter" name="filter" value="${filter}">
				                    <span class="input-group-btn">
				                      <button type="button" class="btn btn-info btn-flat" onclick="search();">Search!</button>
				                    </span>
				              </div>
			              </form>
			              
			            </div>
			            <div class="box-body">
			              <table class="table table-bordered">
			                <tbody>
				                <tr>
				                  <th style="width: 12px">#</th>
				                  <th>Title</th>
				                  <th>Content</th>
				                  <th>Writer</th>
				                  <th>View</th>
				                  <th>Date</th>
				                </tr>
				                <c:forEach var="row" items="${list.content}" varStatus="status">
				                	
			                		<tr>
					                  <td><fx:rowNumberDesc totalElements="${list.totalElements}" number="${list.number}" size="${list.size}" count="${status.count}"/></td>
					                  <%-- <td><fx:rowNumberAsc number="${data.number}" size="${data.size}" count="${status.count}"/></td> --%>
					                  <td>${row.title}</td>
					                  <td><a href="viewArticle?id=${row.id}">${row.content}</a></td>
					                  <td>${row.createdBy}</td>
					                  <td>${row.viewCount}</td>
					                  <td>${row.createdAt}</td>
					                </tr>
					                
			                	</c:forEach>
				          	</tbody>
			              </table>
			              <div class="box-footer">
								<button type="submit" class="btn btn-primary" onclick="location.href='article?type=${type}'">글쓰기</button>
							</div>
			            </div>
			            <!-- /.box-body -->
			            <div class="box-footer clearfix">
			            	<!-- 페이징 view -->
			            	<fx:pagination pageViewCount="9" totalPages="${list.totalPages}" number="${list.number}" />
			            </div>
			          </div>
		        </div>
		    </div>
		</section>
	</div>
</body>

</html>