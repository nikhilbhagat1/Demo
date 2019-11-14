package com.demo.demo.controller;


import com.demo.demo.Object.BookDTO;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import com.demo.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    BookService bookService;


    @RequestMapping(value = "/save/book", method = RequestMethod.POST)
    public ResponseEntity <String> save(@RequestBody BookDTO book){
            return ResponseEntity.ok(bookService.save(book));
    }

    @RequestMapping(value = "/get/books",method= RequestMethod.GET)
    public ResponseEntity<List<Book>> greeting(Model model) {
      return ResponseEntity.ok(bookService.findAll());
    }



    @RequestMapping(value = "/book/{bookId}/authors",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAuthorsByBoook(@PathVariable String bookId, @RequestParam(name = "pageSize")Integer pageSize, @RequestParam(name="pageNumber")Integer pageNumber){
          return ResponseEntity.ok(bookService.getAuthorsByBook(bookId,pageSize,pageNumber));

    }


}
