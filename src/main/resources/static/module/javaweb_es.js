layui.use(['func'], function () {

    //声明变量
    var func = layui.func
        , $ = layui.$;

    if (A == 'index') {
        //【TABLE列数组】
        var cols = [
            {type: 'checkbox', fixed: 'left'}
            , {field: 'project', width: 100, title: '项目名', align: 'center', sort: true, fixed: 'left'}
            , {field: 'filename', width: 100, title: '文件名', align: 'center'}
            , {field: 'highlight', width: 100, title: '内容', align: 'center'}
            , {field: 'language', width: 60, title: '语言', align: 'center'}
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
