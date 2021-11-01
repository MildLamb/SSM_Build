<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style>
      a{
        text-decoration: none;
        color: black;
        font-size: 18px;
      }
      h3{
        width:180px;
        height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background-color: aqua;
        border-radius: 5px;
      }
    </style>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  </head>
  <body>
    <h3><a href="${pageContext.request.contextPath}/book/list">查询所有书籍</a></h3>
    <h3><a href="${pageContext.request.contextPath}/book/add">添加书籍</a></h3>
  </body>
</html>
