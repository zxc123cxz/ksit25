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
<div class="container">
    <h3>商品名称：${kaola.productName}</h3>
    <ul class="list-group">
        <li class="list-group-item">
            价格：${kaola.price}
        </li>
        <li class="list-group-item">
            市场价格：${kaola.marketPrice}
        </li>
        <li class="list-group-item">
            产地：${kaola.place}
        </li>
    </ul>
</div>
</body>
</html>