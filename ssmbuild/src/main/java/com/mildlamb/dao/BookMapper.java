package com.mildlamb.dao;

import com.mildlamb.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    //增加一本书
    int addBook(Books books);
    //删除一本书
    int delBook(@Param("bookid") Integer id);
    //修改一本书
    int updateBook(Books books);
    //查询书籍
    List<Books> selectBooks();
    //查询一本书
    Books selectBookById(@Param("bid") Integer id);
    //搜索书籍
    List<Books> queryBook(@Param("bName") String bookName);
}
