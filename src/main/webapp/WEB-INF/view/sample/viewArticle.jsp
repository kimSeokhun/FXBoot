<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/inc_taglib.jspf" %>

<html>
<head>

<script src="/resources/lib/ckeditor/ckeditor.js"></script>

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
			            <form method="get" action="${contextPath}/sample/article?id=${article.id}" class="form-horizontal">
			            	<input type="hidden" name="id" value="${article.id}" />
			            	<div class="box-body">
				                <div class="form-group">
				                  <label for="title" class="col-sm-2 control-label">Title</label>
				                  <div class="col-sm-10">
				                    <input type="text" class="form-control" name="title" placeholder="Title" value="${article.title}" disabled="disabled"/>
				                  </div>
				                </div>
				                
				                <div class="form-group">
				                  <label for="inputContent" class="col-sm-2 control-label">Content</label>
				                  <div class="col-sm-10">
				                    <!-- <input type="text" class="form-control" id="inputContent" placeholder=""> -->
				                    
				                    <!-- <textarea name="content" id="editor" rows="10" cols="80" > -->
				                    	${article.content}
				                    <!-- </textarea> -->
				                  </div>
				                </div>
		              		</div>
			              
			              <!-- /.box-body -->
			              <div class="box-footer">
			                <button type="button" class="btn btn-default" onclick="history.back();">Cancel</button>
			                <button type="submit" class="btn btn-info pull-right">Update</button>
			              </div>
			              
		              	</form>
			              <!-- /.box-footer -->
			          </div>
		        ${board} <br>
		        ${paramsVo}
		        </div>
		    </div>
		</section>
	</div>
	
</body>

</html>