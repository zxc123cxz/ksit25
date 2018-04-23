<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票下发</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="ticket_out"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票下发
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">下发列表</h3>
                    <div class="box-tools">
                        <a href="/ticket/out/new" class="btn btn-sm btn-success"><i class="fa fa-plus"> 新增下发</i></a>
                    </div>
                </div>

                <c:if test="${not empty message}">
                    <div class="alert alert-success">${message}</div>
                </c:if>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>下发时间</th>
                            <th>下发网点</th>
                            <th>内容</th>
                            <th>起始票号</th>
                            <th>截至票号</th>
                            <th>数量</th>
                            <th>单价</th>
                            <th>总价</th>
                            <th>支付方式</th>
                            <th>下发人</th>
                            <th>状态</th>
                            <th>收款人</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty ticketOutRecordPageInfo.list}">
                            <tr>
                                <td colspan="13">暂无记录</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${ticketOutRecordPageInfo.list}" var="ticketOutRecords">
                            <tr>
                                <td><fmt:formatDate value="${ticketOutRecords.createTime}"/></td>
                                <td>${ticketOutRecords.storeAccountName}</td>
                                <td>${ticketOutRecords.content}</td>
                                <td>${ticketOutRecords.biginTicketNum}</td>
                                <td>${ticketOutRecords.endTicketNum}</td>
                                <td>${ticketOutRecords.totalNum}</td>
                                <td>${ticketOutRecords.price}</td>
                                <td>${ticketOutRecords.totalPrice}</td>
                                <td>${ticketOutRecords.payType}</td>
                                <td>${ticketOutRecords.outAccountName}</td>
                                <td>
                                    <span class="label ${ticketOutRecords.state == '未支付' ? 'label-danger' : 'label-success'}">${ticketOutRecords.state}</span>
                                </td>
                                <td>${ticketOutRecords.financeAccountName}</td>
                                <td>
                                    <c:if test="${ticketOutRecords.state == '未支付'}">
                                        <a href="javascript:;" rel="${ticketOutRecords.id}" class="del_link" title="取消"><i class="fa fa-trash text-danger"></i></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${ticketOutRecordPageInfo.pages > 1}">
                        <ul id="pagination-demo" class="pagination pull-right"></ul>
                    </c:if>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/bootstrap/js/jquery.twbsPaginationjs"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/page/jquery.twbsPagination.js"></script>
<script>
    $(function () {
        // $('.tree').treegrid();

        //分页
        $('#pagination-demo').twbsPagination({
            totalPages: ${ticketOutRecordPageInfo.pages},
            visiblePages: 4,
            first:'首页',
            last:'末页',
            prev:'←',
            next:'→',
            href:"?p={{number}}"
        });

        $(".del_link").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要取消吗",function (index) {
                layer.close(index);
                $.get("/ticket/out/"+id+"/del").done(function (result) {
                    if(result.status == 'success') {
                        layer.msg("删除成功");
                        window.history.go(0);
                    }
                }).error(function () {
                    layer.msg("服务器异常");
                });
            });
        });
    });
</script>
</body>
</html>