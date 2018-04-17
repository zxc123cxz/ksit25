<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <title></title>

</head>
<body>
<div class="container">
    <h3>商品列表</h3>
    <%--<c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>--%>
    <a href="/product/new" class="btn btn-primary pull-right">添加商品</a>
    <table class="table">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>价格</th>
            <th>市场价</th>
            <th>产地</th>
            <th>所属分类</th>
            <th>#</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${kaolaList}" var="kaola">
            <tr>
                <td><a href="/kaola/${kaola.id}">${kaola.productName}</a></td>
                <td>${kaola.price}</td>
                <td>${kaola.marketPrice}</td>
                <td>${kaola.place}</td>
                <td>${kaola.typeId}</td>
                <td>
                    <a href="/kaola/${kaola.id}/edit">编辑</a>
                    <a href="javascript:;" class="delLink" rel="${kaola.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>