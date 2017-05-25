/**
 * 
 */

var gridView = {
		initView: function() {
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
                    height: 30,
			        display: true,
                    firstIcon: '<i class="fa fa-step-backward" aria-hidden="true"></i>',
                    prevIcon: '<i class="fa fa-caret-left" aria-hidden="true"></i>',
                    nextIcon: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
                    lastIcon: '<i class="fa fa-step-forward" aria-hidden="true"></i>',
			        onChange: function () {
			        	fnObj.gridView.setPageData(this.page);
			        	ACTIONS.PAGE_SEARCH();
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
	        this.target.setData({
	        	list: _data.content,
	        	page: {
                    currentPage: _data.number || 0,
                    pageSize: _data.size,
                    totalElements: _data.totalElements,
                    totalPages: _data.totalPages
                }
	        });
	        
	        return this;
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
	    setPageData: function (_page) {
	    	_page.pageNumber = _page.selectPage
	        this.page = $.extend(this.page, _page);
	    },
	    getPageData: function () {
	        return this.page;
	    }
};

var searchView = {
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
   	        $.ajax({
   	        	method: "GET",
   	            url: API_SERVER + "/system/commonCodes",
   	         	data: $.extend({}, fnObj.searchView.getData(), fnObj.gridView.getPageData()),
   	            success: function(res) {
   	            	console.log(res);
   	            	console.log(res.first);
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
   	            url: API_SERVER + "/system/commonCodes",
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
};

function gridInit() {
	fnObj.gridView.initView();
	fnObj.searchView.initView();
	ACTIONS.PAGE_SEARCH();
}