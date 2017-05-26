<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/inc_taglib.jspf" %>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="${contextPath}/webjars/ax5ui-grid/1.4.20/dist/ax5grid.css">
<script src="${contextPath}/webjars/ax5core/1.4.20/dist/ax5core.min.js"></script>
<script src="${contextPath}/webjars/ax5ui-grid/1.4.20/dist/ax5grid.js"></script>
<script src="${contextPath}/webjars/ax5ui-formatter/1.4.20/dist/ax5formatter.js"></script>

<%-- <link rel="stylesheet" href="${contextPath}/webjars/bootstrap-treeview/1.2.0/dist/bootstrap-treeview.min.css">
<script src="${contextPath}/webjars/bootstrap-treeview/1.2.0/dist/bootstrap-treeview.min.js"></script> --%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Menu Mng <small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
				<li class="active">Here</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-lg-12 col-xs-12">
					<div class="box box box-primary">
			            <div class="box-header with-border">
			              <i class="fa fa-fw fa-gears"></i>
			              <h3 class="box-title">Menu Management</h3>
			            </div>
			            <!-- /.box-header -->
			            <div class="box-body">
			            
			            	<div class="row" style="margin-bottom: 15px;">
			            		<div class="col-lg-6 col-xs-6">
			            			<form name="searchForm">
					            		<div class="input-group">
							                <input type="text" id="filter" name="filter" class="form-control" placeholder="Search..">
						                    <span class="input-group-btn">
						                      <button type="submit" class="btn btn-info btn-flat">Go!</button>
						                    </span>
						            	</div>
					            	</form>
				              	</div>
				              	<div class="text-right col-lg-6 col-xs-6">
				        			<div class="button-warp">
							            <!-- <button type="button" class="btn btn-info" data-page-btn="search"><i class="axi axi-ion-android-search"></i> Inquery </button> -->
							            <button type="button" class="btn btn-info " data-page-btn="save"><i class="axi axi-save"></i> Save</button>
								    </div>
					        	</div>
				        	</div>
				        	
					        <div class="row"  style="margin-bottom: 5px;">	
			            		<div class="text-right col-lg-12 col-xs-12" >
									<div class="btn-group ">
										<button type="button" class="btn btn-default" data-grid-control="row-add">
											<i class="fa fa-fw fa-plus"></i>
										</button>
										<button type="button" class="btn btn-default" data-grid-control="row-remove">
											<i class="fa fa-fw fa-minus"></i>
										</button>
									</div>
								</div>
			            	</div>
			            	
			            	<div class="row">
			            		<div class="col-lg-12 col-xs-12">
			            			<fx:common-code groupCd="MENU_GROUP"  defaultValue="SYSTEM"/>
			            		</div>
			            	</div>
			            	
			            	<div class="row">
		            			<div class="col-lg-12 col-xs-12" style="margin-bottom: 15px">
						           	<div style="position: relative;height:600px;" id="grid-parent">
										<div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 100%;"></div>
									</div>
								</div>
			            	</div>
			            	
			            </div>
			            <!-- /.box-body -->
	            	</div>
				</div>
			</div>
		</section>
	</div>
	<script type="text/javascript">
		var fnObj = {};
		
		fnObj.gridView = {
				initView: function() {
					this.target = new ax5.ui.grid();
					this.target.setConfig({
				    	showLineNumber: true,
				    	showRowSelector: true,
				        frozenColumnIndex: 0,
				        sortable: true,
				        multipleSelect: false,
				    	target: $('[data-ax5grid="first-grid"]'),
				    	header: {
		                    align: "center",
		                    columnHeight: 28
		                },
				    	body: {
				            onClick: function () {
				                this.self.select(this.dindex, {selectedClear: true});
				            }
				        },
					    tree: {
			                use: true,
			                indentWidth: 25,
			                arrowWidth: 15,
			                iconWidth: 18,
			                icons: {
			                    openedArrow: '<i class="fa fa-caret-down" aria-hidden="true"></i>',
			                    collapsedArrow: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
			                    groupIcon: '<i class="fa fa-folder-open" aria-hidden="true"></i>',
			                    collapsedGroupIcon: '<i class="fa fa-folder" aria-hidden="true"></i>',
			                    itemIcon: '<i class="fa fa-circle" aria-hidden="true"></i>'
			                },
			                columnKeys: {
			                    parentKey: "pid",
			                    selfKey: "id"
			                }
			            },
				        columns: [            
				            {key: "id", label: "Menu ID", width: 70, align: "center", editor: {type: "text", disabled: function () {
				                return true;
				            }}},
				            {key: "menuNm", label: "Menu Name", width: 300, treeControl: true, editor: {type: "text"}},
				            {key: "progUrl", label: "Program URL", width: 500, align: "left", editor: {type: "text"}},
				            {key: "sort", label: "Sort", width: 70, align: "center", formatter: "money", editor: {type: "number"}},
				            {key: "level", label: "Level", width: 70, align: "center", editor: {type: "text"}},
				            {key: "useYn", label: "UseYN", width: 70, align: "center", editor: {
				                type: "checkbox", config: {trueValue: "Y", falseValue: "N"}
				            }},
				            {key: "data1", label: "Data1", width: 70, align: "left", editor: {type: "text"}},
				            {key: "data2", label: "Data2", width: 70, align: "left", editor: {type: "text"}},
				            {key: "data3", label: "Data3", width: 70, align: "left", editor: {type: "text"}},
				            {key: "data4", label: "Data4", width: 70, align: "left", editor: {type: "text"}}
				        ]
				    });
					return this;
				},
			    setData: function setData(_data) {
			        this.target.setData({
			        	list: _data,
			        	page: null
			        });
			        
			        return this;
			    },
			    getData: function (_type) {
			    	console.log(this.target.getList());
			        var list = [];
			        var _list = this.target.getList(_type);
		
			        if (_type == "modified" || _type == "deleted") {
			            list = ax5.util.filter(_list, function () {
			                return this.menuNm;
			            });
			        } else {
			            list = _list;
			        }
			        return list;
			    },
			    addRow: function () {
			    	console.log(this.target.getList("selected"));
			    	var rowObj = {__created__: true, posUseYn: "N", useYn: "Y"}
			    	try {
			    		var pid = this.target.getList("selected")[0].id;
				    	var index = this.target.getList("selected")[0].__index;
				    	if(pid != null) {
				    		rowObj['parentId'] = pid
				    	}	
			    	} catch(e) {
			    	}
			    	console.log(this.target.addRow);
			        this.target.addRow(rowObj, "first");
			    },
			    delRow: function delRow(_type) {
			        this.target.deleteRow(_type);
			    },
			    align: function align() {
			        this.target.align();
			    },
			    clear: function clear() {
			        this.target.setData({
			            list: [],
			            page: {
			                currentPage: 0,
			                pageSize: 0,
			                totalElements: 0,
			                totalPages: 0
			            }
			        });
			    }
		}
		
		fnObj.searchView = {
		    initView: function () {
		        this.target = $(document["searchForm"]);
		        this.target.attr("onsubmit", "return ACTIONS.PAGE_SEARCH();");
		        this.filter = $("#filter");
		    },
		    getData: function () {
		        return {
		            filter: this.filter.val()
		        }
		    }
		};
		
		
		var API_SERVER = "${contextPath}";//"http://localhost:8080";
		
		var ACTIONS = {
		   		PAGE_SEARCH: function () {
		   			console.log("serach");
		   	        $.ajax({
		   	        	method: "GET",
		   	            url: API_SERVER + "/system/menus",
		   	         	contentType: "application/json;charset=UTF-8",
		   	         	data: $.extend({}, fnObj.searchView.getData()),
		   	            success: function(res) {
		   	            	console.log(res);
		   	            	fnObj.gridView.setData(res);
		   	            }
		   	        });
		   	        return false;
		   	    },
		   	    PAGE_SAVE: function () {
		   	        var saveList = [].concat(fnObj.gridView.getData("modified"));
		   	        saveList = saveList.concat(fnObj.gridView.getData("deleted"));
		   	        $.ajax({
		   	        	method: "PUT",
		   	            url: API_SERVER + "/system/menus",
		   	         	contentType: "application/json;charset=UTF-8",
		   	            data: JSON.stringify(saveList),
		   	         	success: function (res) {
		   	         		ACTIONS.PAGE_SEARCH();
		   	            }
		   	        });
		   	    },
		   	    ITEM_ADD: function () {
		   	    	fnObj.gridView.addRow();
		   	    },
		   	    ITEM_DEL: function () {
		   	     	fnObj.gridView.delRow("selected");
		   	    }
		}
		
		function gridInit() {
			fnObj.gridView.initView();
			fnObj.searchView.initView();
			ACTIONS.PAGE_SEARCH();
		}
		    
		    
		$(function() {
			// 그리드 시작
			gridInit();
			
		    // Grid Row 추가 / 삭제
		    $('[data-grid-control]').click(function () {
		        switch (this.getAttribute("data-grid-control")) {
		            case "row-add":
		                ACTIONS.ITEM_ADD();
		                break;
		            case "row-remove":
		                ACTIONS.ITEM_DEL();
		                break;
		        }
		    });
		    
		    // Grid search 및 저장
		    $('[data-page-btn]').click(function () {
		        switch (this.getAttribute("data-page-btn")) {
		            case "search":
		            	ACTIONS.PAGE_SEARCH();
		                break;
		            case "save":
		            	ACTIONS.PAGE_SAVE();
		                break;
		        }
		    });
		    
		    
		});
	</script>
</body>
</html>