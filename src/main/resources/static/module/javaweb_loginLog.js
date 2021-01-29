/**
 * 登录日志
 * @auth 鲲鹏
 * @date 2020-05-04
 */
layui.use(['func'], function () {

    //声明变量
    var func = layui.func
        , $ = layui.$;

    if (A == 'index') {
        //【TABLE列数组】
        var cols = [
              {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 80, title: 'ID', align: 'center', sort: true, fixed: 'left'}
            , {field: 'userId', width: 100, title: '登陆账号', align: 'center'}
            , {field: 'username', width: 180, title: '登陆名', align: 'center'}
            , {field: 'loginType', width: 100, title: '操作类型', align: 'center', templet(d) {
                    var cls = "";
                    var loginType;
                    if (d.loginType == 1) {
                        loginType = "退出系统";
                        cls = "layui-btn-normal";
                    } else if (d.loginType == 0) {
                        loginType = "登陆系统";
                        cls = "layui-btn-danger";
                    }
                    return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+loginType+'</span>';
                }}
            , {field: 'loginIp', width: 100, title: '登录IP地址', align: 'center'}
            , {field: 'loginTime', width: 200, title: '操作时间', align: 'center'}
            , {field: 'result', width: 100, title: '操作结果', align: 'center', templet(d) {
                    var cls = "";
                    var result;
                    if (d.result == 1) {
                        result = "失败";
                        cls = "layui-btn-primary";
                    } else if (d.result == 0) {
                        result = "成功";
                        cls = "layui-btn-warm";
                    }
                    return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+result+'</span>';
                }}
            , {fixed: 'right', width: 100, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置状态】
        func.formSwitch('status', null, function (data, res) {
            console.log("开关回调成功");
        });
    }
});
