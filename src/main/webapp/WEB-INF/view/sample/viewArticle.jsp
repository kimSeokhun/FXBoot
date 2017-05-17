<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/inc_taglib.jspf" %>

<html>
<head>

<script>
function addComment() {
	$.post("${contextPath}/sample/article/comment", $('#comment').serialize())
	.done(function(data) {
		location.reload();
	});
}

function removeComment(commentId) {
	$.ajax({
        contentType: "application/json",
        method: "DELETE",
        url: '${contextPath}/sample/article/comment',
        data: JSON.stringify({
            id: commentId
        }),
        success: function (res) {
            if (res.error) {
                alert(res.error.message);
                return;
            }
            location.reload();
        }
    });
	/* $.post("${contextPath}/sample/article/comment/delete", {id : commentId})
	.done(function(data) {
		location.reload();
		//$(".result").html(data);
	}); */
}

function editArticle() {
	location.href="${contextPath}/sample/article?id=${article.id}";
}

function removeArticle() {
	return true;
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
		        
		        	<div class="box box-info">
			            <div class="box-header with-border">
			              <h3 class="box-title">Horizontal Form</h3>
			            </div>
			            <!-- /.box-header -->
			            <!-- form start -->
			            <form:form method="delete" action="${contextPath}/sample/article" class="form-horizontal" onsubmit="return removeArticle();">
			            	<input type="hidden" name="type" value="${article.type}" />
			            	<div class="box-body">
				                <div class="form-group">
				                  <label for="title" class="col-sm-2 control-label">Title</label>
				                  <div class="col-sm-10">
				                  	<blockquote>
				                  		${article.title}
				                  	</blockquote>
				                  </div>
				                </div>
				                
				                <div class="form-group">
				                  <label for="inputContent" class="col-sm-2 control-label">Content</label>
				                  <div class="col-sm-10">
				                    <blockquote>
				                    	${article.content}
				                    </blockquote>
				                  </div>
				                </div>
				                <c:if test="${fn:length(article.files) > 0}">
				                	<div class="form-group">
					                	<label for="inputContent" class="col-sm-2 control-label">files</label>
					                	<c:forEach var="file" items="${article.files}">
						                	<div class="col-sm-10">
						                		<a href="${contextPath}/files/download/${file.id}"><i class="fa fa-fw fa-file"></i>${file.fileNm}</a>
						                	</div>
					                	</c:forEach>
					                </div>
				                </c:if>
		              		</div>
			              <!-- /.box-body -->
			              <div class="box-footer">
			                <button type="button" class="btn btn-default" onclick="history.back();">Back</button>
			                <button type="button" class="btn btn-info" onclick="editArticle();">Update</button>
			                <button type="submit" class="btn btn-danger pull-right">delete</button>
			                
			              </div>
			              
		              	</form:form>
			              <!-- /.box-footer -->
			          </div>
		        </div>
		    </div>


			<div class="row">
				<div class="col-md-12">
					<!-- The time line -->
					<ul class="timeline">
						<!-- timeline time label -->
						<li class="time-label"><span class="bg-red"> Comment </span>
						</li>

						<!-- timeline item -->
						<li>
							<i class="fa fa-comments bg-yellow"></i>

							<div class="timeline-item">
								<h3 class="timeline-header">
									Add Comment
								</h3>

								<div class="timeline-body">
									<form id="comment">
										<input type="hidden" name="boardId" value="${article.id}" />
										<input type="text" class="form-control" name="content" placeholder="comment" />
									</form>
								</div>
								<div class="timeline-footer">
									<a class="btn btn-primary btn-xs" onclick="addComment();">save</a>
								</div>
							</div>
						</li>
						<!-- end timeline item -->
						<c:forEach var="comment" items="${comments}">
							<!-- timeline item -->
							<li><i class="fa fa-comments bg-yellow"></i>
	
								<div class="timeline-item">
									<span class="time"><i class="fa fa-clock-o"></i> ${comment.createdAt}</span>
	
									<h3 class="timeline-header">
										<a href="#">${comment.createdBy}</a> commented on your post
									</h3>
	
									<div class="timeline-body">
										${comment.content}
									</div>
									<div class="timeline-footer">
										<a class="btn btn-warning btn-xs" onclick="">Update</a>
										<a class="btn btn-danger btn-xs" onclick="removeComment('${comment.id}');">Delete</a>
									</div>
								</div>
							</li>
							<!-- end timeline item -->
						</c:forEach>
					</ul>
				</div>
				<!-- /.col -->
			</div>


		</section>
	</div>
	
</body>

</html>