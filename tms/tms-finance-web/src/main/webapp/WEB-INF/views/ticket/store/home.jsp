<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票入库</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="ticket_storage"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票入库
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <c:if test="${not empty message}">
                <div class="alert alert-success">${message}</div>
            </c:if>
            <div class="box">
                <div class="box-body">
                    <form method="get" class="form-inline">
                        <input type="text" name="accountName" class="form-control" placeholder="入库人" value="${param.accountName}">
                        <input type="text" name="createName" class="form-control" placeholder="入库时间" value="${param.createName}">
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
                <div class="box-header">
                    <h3 class="box-title">入库列表</h3>
                    <div class="box-tools">
                        <a href="/ticket/store/new"  class="btn btn-sm btn-success"><i class="fa fa-plus"></i> 新增入库</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>入库时间</th>
                            <th>内容</th>
                            <th>起始票号</th>
                            <th>截至票号</th>
                            <th>数量</th>
                            <th>入库人</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ticketInRecordPageInfo.list}" var="ticketInRecord">
                            <tr>
                                <td>${today}</td>
                                <td>${ticketInRecord.content}</td>
                                <td>${ticketInRecord.beginTicketNum}</td>
                                <td>${ticketInRecord.endTicketNum}</td>
                                <td>${ticketInRecord.totalNum}</td>
                                <td>${ticketInRecord.accountName}</td>
                                <td>
                                    <a class="btn btn-primary btn-xs updateLink" href="/ticket/store/${ticketInRecord.id}/edit" title="编辑"><i class="fa fa-pencil"></i></a>
                                    <a class="btn btn-danger btn-xs delLink" rel="${ticketInRecord.id}" href="javascript:;" title="删除"><i class="fa fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                    <%--分页需要--%>
                    <ul id="pagination-demo" class="pagination pull-right"></ul>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<%--分页插件--%>
<script src="/static/plugins/page/jquery.twbsPagination.js"></script>
<script>
    $(function () {
        $('.tree').treegrid();

        //分页
        $('#pagination-demo').twbsPagination({
            /*分页请注意*/
            totalPages: ${ticketInRecordPageInfo.pages},
            visiblePages: 4,
            first:'首页',
            last:'末页',
            prev:'←',
            next:'→',
            //分页请注意
            href:"?p={{number}}"
        });




        $(".delLink").click(function(){
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗",function (index) {
                layer.close(index);
                $.get("/ticket/store/"+id+"/del").done(function (result) {
                    if(result.status == 'success') {
                        history.go(0);
                    } else {
                        layer.msg(result.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙");
                });
            })
        });



        /*   $(".updateLink").click(function(){
               var id = $(this).attr("rel");
               layer.confirm("确定要删除吗",function (index) {
                   layer.close(index);
                   $.get("/manage/permission/"+id+"/edit").done(function (result) {
                       if(result.status == 'success') {
                           history.go(0);
                       } else {
                           layer.msg(result.message);
                       }
                   }).error(function () {
                       layer.msg("服务器忙");
                   });
               })
           });*/
    });
</script>
</body>
</html>