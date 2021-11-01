package com.mildlamb.service;

import com.mildlamb.dao.BookMapper;
import com.mildlamb.pojo.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    //service调用dao层
    @Autowired
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    public int delBook(Integer id) {
        return bookMapper.delBook(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public List<Books> selectBooks() {
        return bookMapper.selectBooks();
    }

    public Books selectBookById(Integer id) {
        return bookMapper.selectBookById(id);
    }

    public List<Books> queryBook(String bookName) {
        return bookMapper.queryBook(bookName);
    }
}
