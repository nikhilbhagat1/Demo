package com.demo.demo.controller;


import com.demo.demo.model.Book;
import com.demo.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    BookService bookService;


    /*@RequestMapping(value = "/save/book", method = RequestMethod.POST)
    public ResponseEntity <String> save(@RequestBody Book book){
            return ResponseEntity.ok(bookService.save(book));
    }*/

    @RequestMapping(value = "/get/books",method= RequestMethod.GET)
    public List<Book> greeting(Model model) {
      return bookService.findAll();
    }

}
