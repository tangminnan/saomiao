
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
									title : 'id' ,
									width : 100
								},
																{
									field : 'uname', 
									title : '姓名' ,
									width : 100
									
								},
																{
									field : 'uorganization', 
									title : '单位或学校',
									width : 200 
								},
																{
									field : 'ugender', 
									title : '性别',
									formatter: function(value,row,index ){
										if(value == 0) return "男";
										if(value == 1) return "女";
									},
									width : 50
								},
																{
									field : 'ugrand', 
									title : '年级或职业',
									width : 100 
								},
																{
									field : 'uage', 
									title : '年龄' ,
									width : 50
								},
																{
									field : 'ubirthday', 
									title : '出生日期',
									width : 200 
								},
																{
									field : 'uidcard', 
									title : '身份证号' ,
									width : 200
								},
																{
									field : 'uphone', 
									title : '联系电话' ,
									width : 150
								},
																{
									field : 'uheight', 
									title : '身高(单位cm)',
									width : 150
										
								},
																{
									field : 'uweight', 
									title : '体重(单位kg)',
									width : 150 
								},
																{
									field : 'ufolder', 
									title : '3D扫描数据存放目录',
									width : 400 
								},								{
									field : 'uimg', 
									title : '用户头像',
									formatter : function(value ,row , index){
										if(value!=null && value !=""){
											var e = '<div class="image"><a class = ""  href="javascript:void(0)"><img width="90" height="100"  alt="image" class="img-responsive pimg view" src="' + value + '"></ a></div>'
											return e;
										}
										else
											return "";
									},
									events: 'operateEvents',
									width : 100
								},
																{
									field : 'mname', 
									title : '负责人(医生)',
									width : 150
								},
																{
									field : 'uupdatedate', 
									title : '创建时间',
									width : 200 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-block btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.uid
												+ '\')"><i class="fa fa-edit">修改</i></a> ';
										
										var f = '<a class="btn btn-primary btn-block btn-sm" href="#" title="指定"  mce_href="#" onclick="resetfile(\''
												+ row.uid
												+ '\')"><i class="fa fa-key">指定</i></a> ';
										
										var d = '<a class="btn btn-primary btn-block btn-sm" href="#" title="解绑"  mce_href="#" onclick="unbind(\''
											+ row.uid
											+ '\')"><i class="fa fa-key">解绑</i></a> ';
										
										var a = '<a class="btn btn-primary btn-block btn-sm dowmL"   title="下载"  onclick="downfile(\''
											   + `${row.uid},${index}`
											   + '\')" ><i class="fa fa-download">下载</i></ a> ';
										
									   var b = '<a class="btn btn-primary btn-block btn-sm dowmL" style="display:none"   title="下载"  onclick="downfile(\''
										   + `${row.uid},${index}`
										   + '\')" ><i class="fa fa-download">下载</i></ a> ';
											
										if((row.ufolder ===null || row.ufolder === "") && (row.uimg === "" || row.uimg === null)){
											return e + f + b ;
										}else if(row.uname != null && row.ufolder != null){
											return e + a + d;
										}else{
											return e + a ;
										}
										
									},
									width : 100
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

window.operateEvents = {
		    'click .view': function (e, value, row, index) {
			 console.log(row.uimg)
		        layer.open({
		          type: 1,
		          title: false,
		          closeBtn: 0,
				  area: 'auto',
		          skin: 'layui-layer-nobg', //没有背景色
		          shadeClose: true,
		          content: '<img width="200px" height="290px" src="'+row.uimg+'"/>'
		        });
		  },
};


/*function bigger(){  
	            var _this = $(this);//将当前的pimg元素作为_this传入函数  
	            imgShow("#outerdiv", "#innerdiv", "#bigimg", ".pimg");  
}*/
/*function imgShow(outerdiv, innerdiv, bigimg, pimg){  
	        var src = pimg[0].src;//获取当前点击的pimg元素中的src属性  
	        $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
	      
	            获取当前点击图片的真实大小，并显示弹出层及大图  
			var $img = $("<img/>").attr("src", src);
	
	         $("<img/>").attr("src", src).load(function(){  
	            var windowW = $(window).width();//获取当前窗口宽度  
	            var windowH = $(window).height();//获取当前窗口高度  
	            var realWidth = this.width;//获取图片真实宽度  
	            var realHeight = this.height;//获取图片真实高度  
	            var imgWidth, imgHeight;  
	            var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
	              
	            if(realHeight>windowH*scale) {//判断图片高度  
	                imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
	                imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
	                if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
	                    imgWidth = windowW*scale;//再对宽度进行缩放  
	                }  
	            } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
	                imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
	                            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
	            } else {//如果图片真实高度和宽度都符合要求，高宽不变  
	                imgWidth = realWidth;  
	                imgHeight = realHeight;  
	            }  
	            $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
	              
	            var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
	            var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
	            $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
	            $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
	        });  
	          
	        $(outerdiv).click(function(){//再次点击淡出消失弹出层  
	            $(this).fadeOut("fast");  
	        });  
	    }
	*/ 

function selectlist() {
	$('#exampleTable').bootstrapTable('destroy');	//清除原来界面
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url :prefix + "/selectlist", // 服务器数据的加载地址
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
								offset:params.offset,
								uname:$('#uname').val(),
								uorganization:$('#uorganization').val(),
								ugender: (function(){
									 if($('#ugender').val() === '男'){
									  return 0
									 }else if($('#ugender').val() === '女'){
									  return 1
									 }
								})(),
								uage:$('#uage').val(),
								mname:$('#mname').val()
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
										if(value!=null && value !=""){
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
										var e = '<a class="btn btn-primary btn-block btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.uid
												+ '\')"><i class="fa fa-edit">修改</i></a> ';
										
										var f = '<a class="btn btn-primary btn-block btn-sm" href="#" title="指定"  mce_href="#" onclick="resetfile(\''
												+ row.uid
												+ '\')"><i class="fa fa-key">指定</i></a> ';
										var d = '<a class="btn btn-primary btn-block btn-sm" href="#" title="解绑"  mce_href="#" onclick="unbind(\''
											+ row.uid
											+ '\')"><i class="fa fa-key">解绑</i></a> ';
										
										var a = '<a class="btn btn-primary btn-block btn-sm dowmL"   title="下载"  onclick="downfile(\''
											   + `${row.uid},${index}`
											   + '\')" ><i class="fa fa-download">下载</i></ a> ';
										
									   var b = '<a class="btn btn-primary btn-block btn-sm dowmL" style="display:none"   title="下载"  onclick="downfile(\''
										   + `${row.uid},${index}`
										   + '\')" ><i class="fa fa-download">下载</i></ a> ';
											
									   if((row.ufolder ===null || row.ufolder === "") && (row.uimg === "" || row.uimg === null)){
											return e + f + b ;
										}else{
											return e + a + d;
										}
									}
								} ]
					});
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
				$('.dowmL').each(function(index1){
					$(this).attr('href',data[0].ufolder);
					window.open(data[0].ufolder,'_blank')
				})
			}
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

function unbind(id){
	$.ajax({
		url : "/information/user/unbind", 
		type : "get",
		data : {
			'uid' : id  
		},
		success : function(data) {
			if (data.code==0) {
				layer.msg(data.msg);
				reLoad();
			}
		}
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