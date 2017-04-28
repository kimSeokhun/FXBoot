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
			            <form:form class="form-horizontal" commandName="board" method="post" action="article" onsubmit="return saveArticle();">
			              <div class="box-body">
			              
			                <div class="form-group">
			                  <label for="title" class="col-sm-2 control-label">Title</label>
			                  <div class="col-sm-10">
			                    <form:input type="text" class="form-control" path="title" placeholder="Title" value="${title}"/>
			                    <form:errors path="title" />
			                  </div>
			                </div>
			                
			                <div class="form-group">
			                  <label for="inputContent" class="col-sm-2 control-label">Content</label>
			                  <div class="col-sm-10">
			                    <!-- <input type="text" class="form-control" id="inputContent" placeholder=""> -->
			                    
			                    <form:textarea path="content" id="editor" rows="10" cols="80" />
			                    	<%-- ${content} --%>
			                    <%-- </form:textarea> --%>
			                    <!-- <textarea name="content" id="editor" rows="10" cols="80">
			                    	
					            </textarea> -->
					            
			                  </div>
			                </div>
			                
			                <div class="form-group">
			                  <div class="col-sm-offset-2 col-sm-10">
			                    <div class="checkbox">
			                      <label>
			                        <form:checkbox path="secret" value="true" /> 비밀 글
			                        <!-- <input type="checkbox" id="secret" name="secret" value="true" /> 비밀 글 -->
			                      </label>
			                    </div>
			                  </div>
			                </div>
			                
			              </div>
			              <!-- /.box-body -->
			              <div class="box-footer">
			                <button type="button" class="btn btn-default" onclick="history.back();">Cancel</button>
			                <button type="submit" class="btn btn-info pull-right">Save</button>
			              </div>
			              <!-- /.box-footer -->
			              <input type="hidden" name="type" value="${board.type}" />
			            </form:form>
			          </div>
		        
		        </div>
		    </div>
		</section>
	</div>
	<script>
		var editor = CKEDITOR.replace( 'editor', {
        	customConfig: '/resources/lib/ckeditor/config.js'
        });
        function save() {
        	var data = CKEDITOR.instances.editor.getData();
        	console.log(data);
        }
        
        function saveArticle() {
        	//var data = CKEDITOR.instances.editor.getData();
        	return true;
        }
        
        $(function() {
        	editor.on( 'change', function( evt ) {
        	    // getData() returns CKEditor's HTML content.
        	    console.log( 'Total bytes: ' + evt.editor.getData().length );
        	});
        });
    </script>
</body>

</html>