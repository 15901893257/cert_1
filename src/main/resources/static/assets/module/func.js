!function (t) {
    var e = {};

    function n(i) {
        if (e[i]) return e[i].exports;
        var a = e[i] = {i: i, l: !1, exports: {}};
        return t[i].call(a.exports, a, a.exports, n), a.l = !0, a.exports
    }

    n.m = t, n.c = e, n.d = function (t, e, i) {
        n.o(t, e) || Object.defineProperty(t, e, {enumerable: !0, get: i})
    }, n.r = function (t) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(t, "__esModule", {value: !0})
    }, n.t = function (t, e) {
        if (1 & e && (t = n(t)), 8 & e) return t;
        if (4 & e && "object" == typeof t && t && t.__esModule) return t;
        var i = Object.create(null);
        if (n.r(i), Object.defineProperty(i, "default", {
            enumerable: !0,
            value: t
        }), 2 & e && "string" != typeof t) for (var a in t) n.d(i, a, function (e) {
            return t[e]
        }.bind(null, a));
        return i
    }, n.n = function (t) {
        var e = t && t.__esModule ? function () {
            return t.default
        } : function () {
            return t
        };
        return n.d(e, "a", e), e
    }, n.o = function (t, e) {
        return Object.prototype.hasOwnProperty.call(t, e)
    }, n.p = "", n(n.s = 2)
}([, , function (t, e, n) {
    n(3)
}, function (t, e) {
    layui.define(["form", "layer", "table", "common", "treeTable", "dropdown"], (function (t) {
        "use strict";
        var e, n, i, a, r, o = layui.form, l = layui.table, c = layui.layer, u = layui.common, d = layui.treeTable,
            f = (layui.dropdown, layui.$), s = 0, b = 0, p = {
                tableIns: function (t, o, d=null, p="", h=!1) {
                    n = o, i = d, p && "" != p || (p = cUrl + "/list");
                    var m = f("#param").val();
                    if (m && (m = JSON.parse(m), f.isArray(m))) for (var y in m) p.indexOf("?") >= 0 ? p += "&" + m[y] : p += "?" + m[y];
                    return e = l.render({
                        elem: "#" + n,
                        url: p,
                        method: "post",
                        cellMinWidth: 150,
                        page: {
                            layout: ["refresh", "prev", "page", "next", "skip", "count", "limit"],
                            curr: 1,
                            groups: 10,
                            first: "首页",
                            last: "尾页"
                        },
                        height: "full-100",
                        limit: 20,
                        limits: [20, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 1e3],
                        even: !0,
                        cols: [t],
                        loading: !0,
                        done: function (t, e, n) {
                            if (r) {
                                var i = f(".layui-table-body").find("table").find("tbody");
                                i.children("tr").on("dblclick", (function () {
                                    var e = i.find(".layui-table-hover").data("index"), n = t.data[e];
                                    u.edit(a, n.id, s, b)
                                }))
                            }
                        }
                    }), l.on("toolbar(" + n + ")", (function (t) {
                        var e = l.checkStatus(t.config.id);
                        switch (t.event) {
                            case"getCheckData":
                                var n = e.data;
                                c.alert(JSON.stringify(n));
                                break;
                            case"getCheckLength":
                                n = e.data;
                                c.msg("选中了：" + n.length + " 个");
                                break;
                            case"isAll":
                                c.msg(e.isAll ? "全选" : "未全选")
                        }
                    })), l.on("tool(" + n + ")", (function (t) {
                        var e = t.data, n = t.event;
                        "edit" === n ? u.edit(a, e.id, s, b) : "detail" === n ? u.detail(a, e.id, s, b) : "del" === n ? u.delete(e.id, (function (e, n) {
                            n && t.del()
                        })) : "cache" === n ? u.cache(e.id) : "copy" === n ? u.copy(a, e.id, s, b) : i && i(n, e)
                    })), l.on("checkbox(" + n + ")", (function (t) {
                    })), l.on("edit(" + n + ")", (function (t) {
                        var e = t.value, n = t.data, i = t.field, a = {};
                        a.id = n.id, a[i] = e;
                        var r = JSON.stringify(a), o = JSON.parse(r), l = cUrl + "/update";
                        u.ajaxPost(l, o, (function (t, e) {
                        }), "更新中...")
                    })), l.on("row(" + n + ")", (function (t) {
                        t.tr.addClass("layui-table-click").siblings().removeClass("layui-table-click");
                        t.data
                    })), h && l.on("sort(" + n + ")", (function (t) {
                        l.reload(n, {initSort: t, where: {field: t.field, order: t.type}})
                    })), this
                }, treetable: function (t=[], e, i=!0, r=0, o="", l=null, p="") {
                    n = e, p || (p = cUrl + "/list");
                    var h = d.render({
                        elem: "#" + e,
                        url: p,
                        method: "POST",
                        height: "full-50",
                        cellMinWidth: 80,
                        tree: {iconIndex: 1, idName: "id", pidName: o || "pid", isPidData: !0},
                        cols: [t],
                        done: function (t, e, n) {
                            c.closeAll("loading")
                        },
                        style: "margin-top:0;"
                    });
                    d.on("tool(" + e + ")", (function (t) {
                        var e = t.data, n = t.event, i = e.id;
                        "add" === n ? u.edit(a, 0, s, b, ["pid=" + i]) : "edit" === n ? u.edit(a, i, s, b) : "del" === n ? u.delete(i, (function (e, n) {
                            n && t.del()
                        })) : l && l(n, i, 0)
                    })), f("#collapse").on("click", (function () {
                        return h.foldAll(), !1
                    })), f("#expand").on("click", (function () {
                        return h.expandAll(), !1
                    })), f("#refresh").on("click", (function () {
                        return h.refresh(), !1
                    })), f("#search").click((function () {
                        var t = f("#keywords").val();
                        return t ? h.filterData(t) : h.clearFilter(), !1
                    }))
                }, setWin: function (t, e=0, n=0) {
                    return a = t, s = e, b = n, this
                }, setDbclick: function (t) {
                    return r = t || !0, this
                }, searchForm: function (t, e) {
                    o.on("submit(" + t + ")", (function (t) {
                        return u.searchForm(l, t, e), !1
                    }))
                }, getCheckData: function (t) {
                    return t || (t = n), l.checkStatus(t).data
                }, initDate: function (t, e=null) {
                    u.initDate(t, (function (t, n) {
                        e && e(t, n)
                    }))
                }, showWin: function (t, e, n=0, i=0, a=[], r=2, o=[], l=null) {
                    u.showWin(t, e, n, i, a, r, o, (function (t, e) {
                        l && l(t, e)
                    }))
                }, ajaxPost: function (t, e, n=null, i="处理中...") {
                    u.ajaxPost(t, e, n, i)
                }, ajaxGet: function (t, e, n=null, i="处理中...") {
                    u.ajaxGet(t, e, n, i)
                }, formSwitch: function (t, e="", n=null) {
                    u.formSwitch(t, e, (function (t, e) {
                        n && n(t, e)
                    }))
                }, uploadFile: function (t, e=null, n="", i="xls|xlsx", a=10240, r={}) {
                    u.uploadFile(t, (function (t, n) {
                        e && e(t, n)
                    }), n, i, a, r)
                }
            };
        u.verify(), o.on("submit(submitForm)", (function (t) {
            return u.submitForm(t.field, null, (function (t, e) {
                console.log("保存成功回调")
            })), !1
        })), o.on("submit(searchForm)", (function (t) {
            return u.searchForm(l, t), !1
        })), f(".btnOption").click((function () {
            var t = f(this).attr("data-param");
            null != t && (console.log(t), t = JSON.parse(t), console.log(t));
            var i = p.getCheckData(n), r = f(this).attr("lay-event");
            switch (r) {
                case"add":
                    u.edit(a, 0, s, b, t);
                    break;
                case"batchDrop":
                    (o = {title: "批量删除"}).url = cUrl + "/batchDelete", o.data = i, o.confirm = !0, o.type = "GET", u.batchFunc(o, (function () {
                        e.reload()
                    }));
                    break;
                case"batchCache":
                    (o = {title: "批量重置缓存"}).url = cUrl + "/batchCache", o.data = i, o.confirm = !0, o.type = "GET", u.batchFunc(o, (function () {
                        e.reload()
                    }));
                    break;
                case"batchEnable":
                    (o = {title: "批量启用状态"}).url = cUrl + "/batchStatus", o.param = t, o.data = i, o.form = "submitForm", o.confirm = !0, o.show_tips = "处理中...", o.type = "POST", u.batchFunc(o, (function () {
                        e.reload()
                    }));
                    break;
                case"batchDisable":
                    var o;
                    (o = {title: "批量禁用状态"}).url = cUrl + "/batchStatus", o.param = t, o.data = i, o.confirm = !0, o.show_tips = "处理中...", o.type = "POST", u.batchFunc(o, (function () {
                        e.reload()
                    }));
                    break;
                case"export":
                    c.msg("导出"), location.href = cUrl + "/btn" + r.substring(0, 1).toUpperCase() + r.substring(1);
                    break;
                case"import":
                    u.uploadFile("import", (function (t, e) {
                    }))
            }
        })), window.formClose = function () {
            var t = parent.layer.getFrameIndex(window.name);
            parent.layer.close(t)
        }, t("func", p)
    }))
}]);