<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公告通知管理</title>
    <!--相对路径-->
    <#include "../common/link.ftl" >
    <script>
        $(function () {
            //新增/编辑按钮点击事件
            $(".btn-input").click(function () {
                //清除模态框的数据
                $("#editForm input","#content").val('');
                var json = $(this).data("json");
                console.log(json);
                if (json) { //json有数据代表是编辑
                    $("#editForm input[name=id]").val(json.id);
                    $("#editForm input[name=title]").val(json.title);
                    $("#content").val(json.content);
                }
                //打开模态框
                $("#myModal").modal('show');
            })

            //保存
            $(".btn-submit").click(function () {
                //使用jquery-form插件来提交异步表单(有表单时会使用该插件,写的代码比较少)
                $("#editForm").ajaxSubmit(handlerMessage)
            })


            $(".btn-delete").click(function () {
                //获取勾选的数据
                //判断是否有选中,如果没有需要先提示选中
                //如果有,提示确认框是否删除
                var id = $(this).data("id");
                $.messager.confirm("警告", "请确认是否进行删除?", function () {
                    $.post('/notice/delete', {id: id}, handlerMessage)
                })
            })

        })






    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
     <#include "../common/navbar.ftl" >
    <!--声明变量 用来做菜单回显-->
    <#assign currentMenu="notice"/>
    <#include "../common/menu.ftl" >
        <div class="content-wrapper">
            <section class="content-header">
                <h1>公告通知管理</h1>
            </section>
            <section class="content">
                <div class="box">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/notice/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                    </form>
                    <!--编写内容-->
                    <div class="box-body table-responsive no-padding ">

                        <table class="table table-hover table-bordered">
                            <tr>
                                <th>编号</th>
                                <th>公告标题</th>
                                <th>发布人</th>
                                <th>发布时间</th>
                                <th>是否已读</th>
                                <th>操作</th>
                            </tr>
                            <#list result.list as notice>
                                <tr>
                                    <!--freemarker 如果取值时是空值 会报错-->
                                    <td>${notice_index+1}</td>
                                    <td>${(notice.title)!}</td>
                                    <td>${(notice.issuer.name)!}</td>
                                    <td>${(notice.pubdate)! ?datetime}</td>
                                    <td>
                                        <#switch notice.read>
                                            <#case 1>
                                               <span style="color: #4cae4c;font-family: Arial">已读</span>
                                                <#break>
                                            <#case 0>
                                            <span style="color: #c12e2a;font-family: Arial">未读</span>
                                                <#break>
                                        </#switch></td>
                                    <td>
                                        <!-- 使用data-*绑定自定义数据-->
                                        <a href="/notice/content?id=${notice.id}&read=${notice.read}"
                                            class="btn btn-xs btn-success"
                                          >
                                            <span class="glyphicon glyphicon-pencil"></span> 查看
                                        </a>

                                        <a href="#"
                                           class="btn btn-info btn-xs btn-input"
                                           data-json='${notice.json}'>
                                            <span class="glyphicon glyphicon-pencil"></span> 编辑
                                        </a>

                                        <a href="#"
                                           class="btn btn-danger btn-xs btn-delete"
                                           data-id='${notice.id}'>
                                            <span class="glyphicon glyphicon-trash"></span> 删除
                                        </a>

                                    </td>
                                </tr>
                            </#list>
                        </table>
                        <!--分页-->
                        <#include "../common/page.ftl">


                    </div>
                </div>
            </section>
        </div>
        <#include "../common/footer.ftl">
    </div>


    <!-- Modal模态框 -->

<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增/编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/notice/saveOrUpdate" method="post" id="editForm" style="margin: -3px 118px">
                    <div class="form-group" >
                        <label for="name" class="col-sm-4 control-label">公告标题：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="title" >
                            <input type="hidden" class="form-control"  name="id"  >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">公告内容：</label>
                        <div class="col-sm-8">
<#--                        <textarea type="text" class="form-control" id="content" name="content" cols="50" ></textarea>-->
                            <textarea class="form-control" id="content" name="content" rows="16" style="min-width:
                                90%"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-submit" >保存</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
