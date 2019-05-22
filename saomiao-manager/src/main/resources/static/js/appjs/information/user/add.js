$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/information/user/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			uname : {
				required : true
			},
			ubirthday : {
				required : true
			},
			uorganization : {
				required : true
			},
			uage:{
				required : true
			}
		},
		messages : {
			uname : {
				required : icon + "请输入姓名"
			},
			ubirthday : {
				required : icon + "请输入出生日期"
			},
			uorganization : {
				required : icon + "请输入学校或单位"
			},
			uage:{
				required : icon + "请输入年龄"
			}
			
		}
	})
}