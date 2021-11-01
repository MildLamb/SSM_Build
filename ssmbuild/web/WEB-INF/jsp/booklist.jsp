<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12">
                <div class="page-header">
                    <h1>
                        <small>书籍列表 -- 显示所有书籍</small>
                    </h1>
                </div>
            </div>
        </div>

        <a class="btn btn-default" href="${pageContext.request.contextPath}/book/list">显示所有书籍</a>

        <div class="col-lg-4">
            <!-- 查询书籍 -->
            <form action="${pageContext.request.contextPath}/book/queryBook" method="post" class="form-inline">
                <input type="text" placeholder="请输入要查询的书籍名称" class="form-control" name="queryName">
                <input type="submit" value="提交" class="btn btn-primary">
                <span style="color: red;font-weight: bold">${errMsg}</span>
            </form>
        </div>


        <div class="row clearfix">
            <div class="col-md-12">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>书籍编号</th>
                            <th>书籍名称</th>
                            <th>书籍数量</th>
                            <th>书籍描述</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <!-- 书籍数据从数据库中查询出来 -->
                    <tbody>
                        <c:forEach var="book" items="${allBooks}">
                            <tr>
                                <td>${book.bookId}</td>
                                <td>${book.bookName}</td>
                                <td>${book.bookCounts}</td>
                                <td>${book.detail}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/book/toUpdateBook?id=${book.bookId}">修改</a> &nbsp; | &nbsp;
                                    <a href="${pageContext.request.contextPath}/book/delBook?id=${book.bookId}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</body>
</html>
