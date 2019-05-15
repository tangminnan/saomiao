
var prefix = "/information/user"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
																{
									field : 'uid', 
									title : 'id' 
								},
																{
									field : 'uname', 
									title : '姓名' 
								},
																{
									field : 'uorganization', 
									title : '单位或学校' 
								},
																{
									field : 'ugender', 
									title : '性别',
									formatter: function(value,row,index ){
										if(value == 0) return "男";
										if(value == 1) return "女";
									}
								},
																{
									field : 'ugrand', 
									title : '年级或职业' 
								},
																{
									field : 'uage', 
									title : '年龄' 
								},
																{
									field : 'ubirthday', 
									title : '出生日期' 
								},
																{
									field : 'uidcard', 
									title : '身份证号' 
								},
																{
									field : 'uphone', 
									title : '联系电话' 
								},
																{
									field : 'uheight', 
									title : '身高 单位cm' 
								},
																{
									field : 'uweight', 
									title : '体重 单位kg' 
								},
																{
									field : 'ufolder', 
									title : '3D扫描数据存放目录' 
								},								{
									field : 'uimg', 
									title : '用户头像',
									formatter : function(value ,row , index){
										if(value!=null){
											var e = '<div class="image"><img width="90" height="100" alt="image" class="img-responsive" src="' + value + '"></div>'
											return e;
										}
										else
											return "";
									}
								},
																{
									field : 'mname', 
									title : '负责人（医生）' 
								},
																{
									field : 'uupdatedate', 
									title : '创建时间' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-xs '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.uid
												+ '\')"><i class="fa fa-edit"></i></a> ';
										
										var f = '<a class="btn btn-primary btn-xs" href="#" title="指定"  mce_href="#" onclick="resetfile(\''
												+ row.uid
												+ '\')"><i class="fa fa-key"></i></a> ';
										var a = '<a class="btn btn-primary btn-xs dowmL"   title="下载"  onclick="downfile(\''
											   + `${row.uid},${index}`
											   + '\')" ><i class="fa fa-download"></i></ a> ';
										
										if(row.ufolder === null || row.uimg === null || row.mname === null){
											return f + a;
										}else{
											return e + a;
										}
										
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function downfile(val){
		var arr = val.split(',')
		var id = arr[0]
		var index = arr[1]
		
		$.ajax({
			url : "/information/user/download", 
			type : "get",
			data : {
				'uid' : id
			},
			success : function(data) {
				console.log(downloads(https:\\facescan190509.oss-cn-beijing.aliyuncs.com/42_2019.05.14.21.10.36))
				$.each(data,function(i,item){
					$('.dowmL').each(function(index1){
						if(index1 == index){
							var url = downloads(item.ufolder,item.unmme)
							$(this).attr('href',url)
							window.open(item.ufolder,'_blank')
						}
					})
				})
			}
		});
		
		
}
/** * 
 * 获取 blob * 
 * @param {String} url 目标文件地址 *
 * @return {Promise} */
function getBlob(url){
	return new Promise(resolve =>{
		const xhr = new XMLHttpRequest();
		
		xhr.open('GET',url,true);
		xhr.responseType = 'blob';
		xhr.onload = () => {
			if(xhr.status === 200){
				resolve(xhr.response);
			}
		};
		xhr.send();
	})
}
/** * 
 * 保存 * 
 * @param {Blob} blob 
 * * @param {String} filename 想要保存的文件名称 */
function saveAs(blob, filename) 
{ 
	if (window.navigator.msSaveOrOpenBlob) { 
		navigator.msSaveBlob(blob, filename); 
	}else{ 
		const link = document.createElement('a'); 
		const body = document.querySelector('body'); 
		link.href = window.URL.createObjectURL(blob); 
		link.download = filename; // fix Firefox link.style.display = 'none';
		body.appendChild(link); 
		link.click(); 
		body.removeChild(link); 
		window.URL.revokeObjectURL(link.href); 
	} 
}

/** * 
 * 下载 
 * @param {String} url 目标文件地址 
 * @param {String} filename 想要保存的文件名称 */
function downloads(url, filename) { 
	getBlob(url).then(blob => { 
		saveAs(blob, filename); 
	}); 
}

function add(type) {
	if(type == 1){
		layer.open({
			type : 2,
			title : '手动添加',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '800px', '520px' ],
			content : prefix + '/add' // iframe的url
		});
	}else if(type == 2){
		layer.open({
			type : 2,
			title : '批量添加',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '800px', '520px' ],
			content : prefix + '/batchAdd' // iframe的url
		});
	}else{
		alert("请输入type1或2")
	}
	
}

function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'uid' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetfile(id) {
	layer.open({
		type : 2,
		title : '指定',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :'/information/user/point/' + id // iframe的url
	});
	
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['uid'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}