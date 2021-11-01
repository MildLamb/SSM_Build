package com.mildlamb.controller;

import com.mildlamb.pojo.Books;
import com.mildlamb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //Controller调用Service层
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    //查询所有的书籍
    @GetMapping("/list")
    public String book_list(Model model){
        List<Books> books = bookService.selectBooks();
        model.addAttribute("allBooks",books);
        return "booklist";
    }

    @GetMapping("/add")
    public String b_add(){
        return "addBook";
    }

    @PostMapping("/addBook")
    public String book_add(Books books){
        System.out.println("添加图书: " + books);
        bookService.addBook(books);
        return "redirect:/book/list";
    }

    @GetMapping("/toUpdateBook")
    public String to_updateBook(@RequestParam("id") Integer bookId,Model model){
        Books book = bookService.selectBookById(bookId);
        model.addAttribute("thisBook",book);
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBook(Books books,@RequestParam("id") Integer bookId){
        System.out.println("修改图书: " + books);
        books.setBookId(bookId);
        bookService.updateBook(books);
        return "redirect:/book/list";
    }

    @GetMapping("/delBook")
    public String delBook(@RequestParam("id") Integer bookId){
        bookService.delBook(bookId);
        return "redirect:/book/list";
    }


    //搜索书籍
    @PostMapping("/queryBook")
    public String queryBook(@RequestParam("queryName") String bookName,Model model){

        List<Books> books = bookService.queryBook("%" + bookName + "%");

        model.addAttribute("allBooks",books);
        return "booklist";
    }
}
