<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/inc_taglib.jspf"%>

<html>
<head>
<link rel="stylesheet" href="${contextPath}/webjars/ax5ui-uploader/1.4.20/dist/ax5uploader.css">
<link rel="stylesheet" href="${contextPath}/webjars/ax5ui-dialog/1.4.20/dist/ax5dialog.css">

<script src="${contextPath}/webjars/ax5core/1.4.20/dist/ax5core.min.js"></script>
<script src="${contextPath}/webjars/ax5ui-uploader/1.4.20/dist/ax5uploader.min.js"></script>
<script src="${contextPath}/webjars/ax5ui-dialog/1.4.20/dist/ax5dialog.min.js"></script>
<script src="https://cdn.rawgit.com/thomasJang/jquery-direct/master/dist/jquery-direct.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Files</title>
</head>
<body>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>
				File Upload <small>sample</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="active">files</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<!-- AX5 -->
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">AX5 upload</h3>
						</div>
						<div class="box-body">
							<div class="form-group">
							
							
								<div class="DH20"></div>
 
								<div data-ax5uploader="upload1">
								    <button data-ax5uploader-button="selector" class="btn btn-primary">Select File (*/*)</button>
								    (Upload Max fileSize 20MB)
								    <div data-uploaded-box="upload1" data-ax5uploader-uploaded-box="thumbnail"></div>
								</div>
								 
								<div style="padding: 0;" data-btn-wrap>
								    <h3>control</h3>
								    <button class="btn btn-default" data-upload-btn="getUploadedFiles">getUploadedFiles</button>
								    <button class="btn btn-default" data-upload-btn="removeFileAll">removeFileAll</button>
								</div>
								
								
							</div>

						</div>
					</div>
				</div>
				
				<!-- basic -->
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">File Example</h3>
						</div>
						<form role="form" action="/files/upload" method="post"
							enctype="multipart/form-data">
							<div class="box-body">
								<div class="form-group">
									<label for="exampleInputFile">File input</label> <input
										type="file" id="exampleInputFile" name="file"> <input
										type="hidden" name="thumbnail" value="true" /> <input
										type="hidden" name="targetType" value="0" /> <input
										type="hidden" name="targetId" value="1" />
									<p class="help-block">Example block-level help text here.</p>
								</div>

							</div>

							<div class="box-footer">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>

	<script type="text/javascript">
	    $(function () {
	        var API_SERVER = "${contextPath}"; //"http://localhost:8080";
	        var DIALOG = new ax5.ui.dialog({
	            title: "AX5UI"
	        });
	        var UPLOAD = new ax5.ui.uploader({
	            //debug: true,
	            target: $('[data-ax5uploader="upload1"]'),
	            form: {
	                action: API_SERVER + "/files/upload?thumbnail=true",
	                fileName: "file"
	            },
	            multiple: true,
	            manualUpload: false,
	            progressBox: true,
	            progressBoxDirection: "left",
	            dropZone: {
	                target: $('[data-uploaded-box="upload1"]')
	            },
	            uploadedBox: {
	                target: $('[data-uploaded-box="upload1"]'),
	                icon: {
	                    "download": '<i class="fa fa-download" aria-hidden="true"></i>',
	                    "delete": '<i class="fa fa-minus-circle" aria-hidden="true"></i>'
	                },
	                columnKeys: {
	                    apiServerUrl: API_SERVER,
	                    name: "fileNm",
	                    type: "extension",
	                    size: "fileSize",
	                    uploadedName: "saveNm",
	                    thumbnail: "thumbnail"
	                },
	                lang: {
	                    supportedHTML5_emptyListMsg: '<div class="text-center" style="padding-top: 30px;">Drop files here or click to upload.</div>',
	                    emptyListMsg: '<div class="text-center" style="padding-top: 30px;">Empty of List.</div>'
	                },
	                onchange: function () {
	 
	                },
	                onclick: function () {
	                    // console.log(this.cellType);
	                    var fileIndex = this.fileIndex;
	                    var file = this.uploadedFiles[fileIndex];
	                    console.log(file);
	                    switch (this.cellType) {
	                        case "delete":
	                            DIALOG.confirm({
	                                title: "AX5UI",
	                                msg: "Are you sure you want to delete it?"
	                            }, function () {
	                                if (this.key == "ok") {
	                                    $.ajax({
	                                        contentType: "application/json",
	                                        method: "DELETE",
	                                        url: API_SERVER + "/files",
	                                        data: JSON.stringify([{
	                                            id: file.id
	                                        }]),
	                                        success: function (res) {
	                                            if (res.error) {
	                                                alert(res.error.message);
	                                                return;
	                                            }
	                                            UPLOAD.removeFile(fileIndex);
	                                        }
	                                    });
	                                }
	                            });
	                            break;
	 
	                        case "download":
	                            if (file.download) {
	                                location.href = API_SERVER + file.download;
	                            }
	                            break;
	                    }
	                }
	            },
	            validateSelectedFiles: function () {
	                // 10개 이상 업로드 되지 않도록 제한.
	                if (this.uploadedFiles.length + this.selectedFiles.length > 10) {
	                    alert("You can not upload more than 10 files.");
	                    return false;
	                }
	 
	                return true;
	            },
	            onprogress: function () {
	 
	            },
	            onuploaderror: function () {
	                console.log(this.error);
	                DIALOG.alert(this.error.message);
	            },
	            onuploaded: function () {
	 
	            },
	            onuploadComplete: function () {
	 
	            }
	        });
	 
	        // 파일 목록 가져오기
	        $.ajax({
	            method: "GET",
	            url: API_SERVER + "/files",
	            contentType: "application/json",
	            success: function (res) {
	                console.log(res);
	                UPLOAD.setUploadedFiles(res.content);
	            }
	        });
	 
	        // 컨트롤 버튼 이벤트 제어
	        $('[data-btn-wrap]').clickAttr(this, "data-upload-btn", {
	            "getUploadedFiles": function () {
	                var files = ax5.util.deepCopy(UPLOAD.uploadedFiles);
	                console.log(files);
	                DIALOG.alert(JSON.stringify(files));
	            },
	            "removeFileAll": function () {
	 
	                DIALOG.confirm({
	                    title: "AX5UI",
	                    msg: "Are you sure you want to delete it?"
	                }, function () {
	 
	                    if (this.key == "ok") {
	 
	                        var deleteFiles = [];
	                        UPLOAD.uploadedFiles.forEach(function (f) {
	                            deleteFiles.push({id: f.id});
	                        });
	 
	                        $.ajax({
	                            contentType: "application/json",
	                            method: "DELETE",
	                            url: API_SERVER + "/files",
	                            data: JSON.stringify(deleteFiles),
	                            success: function (res) {
	                                if (res.error) {
	                                    alert(res.error.message);
	                                    return;
	                                }
	 
	                                UPLOAD.removeFileAll();
	                            }
	                        });
	 
	                    }
	                });
	            }
	        });
	    });
	</script>
</body>
</html>