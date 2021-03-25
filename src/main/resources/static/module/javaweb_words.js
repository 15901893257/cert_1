layui.use(['func'], function () {

    //声明变量
    var func = layui.func
        , $ = layui.$;

    if (A == 'index') {
        //【TABLE列数组】
        var cols = [
            {type: 'checkbox', fixed: 'left'}
            , {field: 'keyword', width: 300, title: '关键字/正则表达式', align: 'center', sort: true, fixed: 'left'}
            , {field: 'keyType', width: 100, title: '搜索类型', align: 'center', templet(d) {
                    var cls = "";
                    var keyType;
                    if (d.keyType == 1) {
                        keyType = "正则表达式";
                        cls = "layui-btn-normal";
                    } else if (d.keyType == 0) {
                        keyType = "模糊匹配";
                        cls = "layui-btn-danger";
                    }
                    return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+keyType+'</span>';
                }}
            , {field: 'indexType', width: 100, title: '索引', align: 'center', templet(d) {
                    var cls = "";
                    var indexType;
                    if (d.indexType == -1) {
                        indexType = "全部";
                        cls = "layui-btn-disabled";
                    } else if (d.indexType == 0) {
                        indexType = "C";
                        cls = "layui-btn-primary";
                    } else if (d.indexType == 1) {
                        indexType = "C++";
                        cls = "layui-btn-danger";
                    } else if (d.indexType == 2) {
                        indexType = "C#";
                        cls = "layui-btn-normal";
                    } else if (d.indexType == 3) {
                        indexType = "Java";
                        cls = "layui-btn-warm";
                    } else if (d.indexType == 4) {
                        indexType = "JavaScript";
                        cls = "layui-btn-warm";
                    } else if (d.indexType == 5) {
                        indexType = "Python";
                        cls = "layui-btn-warm";
                    } else if (d.indexType == 6) {
                        indexType = "Ruby";
                        cls = "layui-btn-warm";
                    }
                    return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+indexType+'</span>';
                }}
            , {field: 'operator', width: 100, title: '创建人', align: 'center'}
            , {field: 'ctime', width: 180, title: '创建时间', align: 'center'}
            , {field: 'utime', width: 180, title: '更新时间', align: 'center'}
            , {field: 'description', width: 200, title: '描述', align: 'center'}
            , {fixed: 'right', width: 150, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置弹框】
        func.setWin("敏感词");

        //【设置状态】
        func.formSwitch('status', null, function (data, res) {
            console.log("开关回调成功");
        });
    }
});
