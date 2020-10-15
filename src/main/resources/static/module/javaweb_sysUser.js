layui.use(['func'], function () {

    //声明变量
    var func = layui.func
        , $ = layui.$;

    if (A == 'index') {
        //【TABLE列数组】
        var cols = [
            {type: 'checkbox', fixed: 'left'}
            , {field: 'userId', width: 100, title: '员工ID', align: 'center', sort: true, fixed: 'left'}
            , {field: 'name', width: 100, title: '姓名', align: 'center'}
            , {field: 'username', width: 100, title: '用户名', align: 'center'}
            , {field: 'gender', width: 60, title: '性别', align: 'center', templet(d) {
                var cls = "";
                var genderName;
                if (d.gender == 1) {
                    // 男
                    genderName = "男";
                    cls = "layui-btn-normal";
                } else if (d.gender == 0) {
                    // 女
                    genderName = "女";
                    cls = "layui-btn-danger";
                } else if (d.gender == 3) {
                    // 保密
                    cls = "layui-btn-warm";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+genderName+'</span>';
            }}
            , {field: 'avatar', width: 80, title: '头像', align: 'center', templet: function (d) {
                var avatarStr = "";
                if (d.avatar) {
                    avatarStr = '<a href="' + d.avatar + '" target="_blank"><img src="' + d.avatar + '" height="26" /></a>';
                }
                return avatarStr;
              }
            }
            , {field: 'phone', width: 130, title: '手机号码', align: 'center'}
            , {field: 'email', width: 200, title: '邮箱地址', align: 'center'}
            , {field: 'birthDay', width: 120, title: '出生日期', align: 'center'}
            , {field: 'loginTime', width: 180, title: '最近登录时间', align: 'center'}
            , {field: 'loginIp', width: 100, title: '最近登录IP', align: 'center'}
            , {field: 'ctime', width: 180, title: '创建时间', align: 'center'}
            , {field: 'operator', width: 100, title: '创建人', align: 'center'}
            , {field: 'utime', width: 180, title: '更新时间', align: 'center'}
            , {field: 'updateOperator', width: 100, title: '更新人', align: 'center'}
            , {field: 'remark', width: 180, title: '备注', align: 'center'}
            , {field: 'status', width: 100, title: '状态', align: 'center', templet: '#statusTpl'}
            , {fixed: 'right', width: 150, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置弹框】
        func.setWin("系统人员");

        //【设置状态】
        func.formSwitch('status', null, function (data, res) {
            console.log("开关回调成功");
        });
    }
});
