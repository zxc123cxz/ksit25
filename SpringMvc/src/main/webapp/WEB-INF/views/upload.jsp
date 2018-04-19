<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
</head>
<body>
    <c:if test = "${not empty message}">
        <div style="color:#f44">${message}</div>
    </c:if>
<h1>无敌的寂寞</h1>
<form method = "post" enctype = "multipart/form-data">
    <input type="text" name = "name">
    <input type="file" name = "photo">
    <button>save</button>
</form>
</body>
</html>