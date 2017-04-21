<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>


<link rel="stylesheet" href="/webjars/ax5ui-grid/1.4.20/dist/ax5grid.css">

<script src="/webjars/ax5core/1.4.20/dist/ax5core.min.js"></script>
<script src="/webjars/ax5ui-formatter/1.4.20/dist/ax5formatter.js"></script>
<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5ui-picker/master/dist/ax5picker.min.js"></script>
<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5ui-select/master/dist/ax5select.min.js"></script>
<script src="/webjars/ax5ui-binder/1.4.20/dist/ax5binder.min.js"></script>
<script src="/webjars/ax5ui-grid/1.4.20/dist/ax5grid.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Code</title>


</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Code Management <small>CodeMng Page..</small>
			</h1>
			<ol class="breadcrumb">
				<li class="active"><a href="#"><i class="fa fa-dashboard"></i>
						CodeMng</a></li>
			</ol>
		</section>
		<section class="content">
			
			<div class="row">
	        	<div class="col-lg-6 col-xs-6">
	        		<form name="searchForm" id="searchForm" method="post" onsubmit="return ACTIONS.PAGE_SEARCH();" style="">
						<div data-ax-td="" id="" class="" style="width: 300px">
							<div data-ax-td-wrap="">
								<input type="text" id="filter" name="filter" class="form-control form-control" placeholder="Search..">
							</div>
						</div>
					</form>		
	        	</div>
	        	<div class="text-right col-lg-6 col-xs-6">
	        		<div class="button-warp">
			            <button type="button" class="btn btn-info" data-page-btn="search"><i class="axi axi-ion-android-search"></i> Inquery </button>
			            <button type="button" class="btn btn-info" data-page-btn="save"><i class="axi axi-save"></i> Save</button>
				    </div>
	        	</div>
        	</div>
			
			
			<div class="row">
				<div class="text-right col-lg-12 col-xs-12" style="padding: 10px;">
				    <button class="btn btn-default" data-grid-control="row-add">Add</button>
				    <button class="btn btn-default" data-grid-control="row-remove">Remove</button>
				</div>
			</div>
			
           	<div style="position: relative;height:400px;" id="grid-parent">
				<div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 100%;"></div>
			</div>
				
		</section>
	</div>
	
	<script type="text/javascript">
		var fnObj = {};
		
		fnObj.gridView = {
				initView: function() {
					//var _this = this;
					this.target = new ax5.ui.grid();
					this.target.setConfig({
				    	showLineNumber: true,
				    	showRowSelector: true,
				        frozenColumnIndex: 0,
				        sortable: true,
				        multipleSelect: true,
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
				        page: {
					        navigationItemCount: 9,
		                    //height: 30,
					        display: true,
		                    /* firstIcon: '<i class="fa fa-step-backward" aria-hidden="true"></i>',
		                    prevIcon: '<i class="fa fa-caret-left" aria-hidden="true"></i>',
		                    nextIcon: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
		                    lastIcon: '<i class="fa fa-step-forward" aria-hidden="true"></i>', */
					        onChange: function () {
		                        //gridView.setData(this.page.selectPage);
		                        console.log(this.page);
		                    }
					    },
				        columns: [            
				            {key: "groupCd", label: "Group Code", width: 250, align: "center", editor: {type: "text", disabled: function () {
				                return !this.item.__created__;
				            }}},
				            {key: "groupNm", label: "Group Name", width: 200, align: "center", editor: {type: "text"}},
				            {key: "code", label: "Code", width: 100, align: "center", editor: {type: "text", disabled: function () {
				                return !this.item.__created__;
				            }}},
				            {key: "name", label: "Value", width: 150, align: "center", editor: {type: "text"}},
				            {key: "sort", label: "Sort", align: "center", formatter: "money", editor: {type: "number"}},
				            {key: "useYn", label: "UseYN", align: "center", editor: {
				                type: "checkbox", config: {trueValue: "Y", falseValue: "N"}
				            }},
				            {key: "remark", label: "Remark", width: 200, align: "left", editor: {type: "text"}},
				            {key: "data1", label: "Data1", width: 70, align: "left", editor: {type: "text"}},
				            {key: "data2", label: "Data2", width: 70, align: "left", editor: {type: "text"}},
				            {key: "data3", label: "Data3", width: 70, align: "left", editor: {type: "text"}},
				            {key: "data4", label: "Data4", width: 70, align: "left", editor: {type: "text"}}
				        ]
				    });
					return this;
				},
				page: {
			        pageNumber: 0,
			        pageSize: 10,
			    },
			    setData: function setData(_data) {
			        this.target.setData(_data);
			    },
			    getData: function (_type) {
			        var list = [];
			        var _list = this.target.getList(_type);
		
			        if (_type == "modified" || _type == "deleted") {
			            list = ax5.util.filter(_list, function () {
			                return this.groupCd && this.code;
			            });
			        } else {
			            list = _list;
			        }
			        return list;
			    },
			    addRow: function () {
			        this.target.addRow({__created__: true, posUseYn: "N", useYn: "Y"}, "last");
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
			    },
			    setPageData: function setPageData(_page) {
			        this.page = $.extend(this.page, _page);
			    },
			    getPageData: function getPageData() {
			        return this.page;
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
		            pageNumber: fnObj.gridView.page.pageNumber,
		            pageSize: fnObj.gridView.page.pageSize,
		            filter: this.filter.val()
		        }
		    }
		};
		
		/* fnObj.pageButtonView = {
		    initView: function () {
		        axboot.buttonClick(this, "data-page-btn", {
		            "search": function () {
		                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
		            },
		            "save": function () {
		                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
		            }
		        });
		    }
		}; */
		
		var API_SERVER = "http://localhost:8080";
		
		var ACTIONS = {
		   		PAGE_SEARCH: function (caller, act, data) {
		   	        $.ajax({
		   	        	method: "GET",
		   	            url: API_SERVER + "/api/v1/commonCodes",
		   	            data: fnObj.searchView.getData(),
		   	            success: function(res) {
		   	            	console.log(res);
		   	            	fnObj.gridView.setData(res);
		   	            }
		   	        });
		   	        return false;
		   	    },
		   	    PAGE_SAVE: function (caller, act, data) {
		   	        var saveList = [].concat(fnObj.gridView.getData("modified"));
		   	        saveList = saveList.concat(fnObj.gridView.getData("deleted"));
					console.log(saveList);
		   	        $.ajax({
		   	        	method: "PUT",
		   	            url: API_SERVER + "/api/v1/commonCodes",
		   	         	contentType: "application/json;charset=UTF-8",
		   	            data: JSON.stringify(saveList),
		   	         	success: function (res) {
		   	         		ACTIONS.PAGE_SEARCH();
		   	            }
		   	        });
		   	    },
		   	    ITEM_ADD: function (caller, act, data) {
		   	    	fnObj.gridView.addRow();
		   	    },
		   	    ITEM_DEL: function (caller, act, data) {
		   	     	fnObj.gridView.delRow("selected");
		   	    }
		}
		    
		    
		$(function() {
			fnObj.gridView.initView();
			fnObj.searchView.initView();
			//fnObj.pageButtonView.initView();
			ACTIONS.PAGE_SEARCH();
			
		    
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
	<script type="text/javascript">
	   
	</script>
</body>
</html>