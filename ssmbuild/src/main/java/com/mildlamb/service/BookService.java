package com.mildlamb.service;

import com.mildlamb.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {
    //增加一本书
    int addBook(Books books);
    //删除一本书
    int delBook (Integer id);
    //修改一本书
    int updateBook(Books books);
    //查询书籍
    List<Books> selectBooks();
    //查询一本书
    Books selectBookById(Integer id);
    //搜索书籍
    List<Books> queryBook(String bookName);
}
