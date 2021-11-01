<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>


    <form action="/book/updateBook?id=${thisBook.bookId}" method="post">
        <div class="form-group">
            <label for="bookName">图书名称</label>
            <input type="text" class="form-control" id="bookName" name="bookName" value="${thisBook.bookName}">
        </div>
        <div class="form-group">
            <label for="bookCounts">图书数量</label>
            <input type="text" class="form-control" id="bookCounts" name="bookCounts" value="${thisBook.bookCounts}">
        </div>
        <div class="form-group">
            <label for="bookDetail">图书描述</label>
            <input type="text" class="form-control" id="bookDetail" name="detail" value="${thisBook.detail}">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

</div>
</body>
</html>
