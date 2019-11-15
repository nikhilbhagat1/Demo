package com.demo.demo.controller;


import com.demo.demo.Object.BookDTO;
import com.demo.demo.Util.Constants;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import com.demo.demo.services.BookService;
import com.google.gson.Gson;
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
    private Gson gson;

    @Autowired
    BookService bookService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity <String> save(@RequestBody String book){
        BookDTO bookDTO;
        bookDTO = gson.fromJson(book,BookDTO.class);
        return ResponseEntity.ok(bookService.save(bookDTO));
    }

    /*@RequestMapping(value = "/save/book/Author", method = RequestMethod.POST)
    public ResponseEntity <String> save(@RequestBody String bookAuthors){
        BookDTO bookDTO;
        bookDTO = gson.fromJson(book,BookDTO.class);
        return ResponseEntity.ok(bookService.save(bookDTO));
    }
    */

    @RequestMapping(value = "/get/books",method= RequestMethod.GET)
    public ResponseEntity<List<Book>> greeting(Model model) {
      return ResponseEntity.ok(bookService.findAll());
    }


    @RequestMapping(value = "/{bookId}/authors",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAuthorsByBoook(@PathVariable String bookId, @RequestParam(name = "pageSize")Integer pageSize, @RequestParam(name="pageNumber")Integer pageNumber){
          return ResponseEntity.ok(bookService.getAuthorsByBook(bookId,pageSize,pageNumber));
    }

    @RequestMapping(value = "/{bookId}/authors/sortby",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getSortedAuthorsByBoook(@PathVariable String bookId, @RequestParam(name = "pageSize")Integer pageSize, @RequestParam(name="pageNumber")Integer pageNumber,@RequestParam(name="sortBy")String sortBy,@RequestParam(name="direction")String direction){
        return ResponseEntity.ok(bookService.getAuthorsByBook(bookId,pageSize,pageNumber,sortBy,direction));
    }



    @RequestMapping(value = "/{bookId}/fifty/authors",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getFiftyAuthorsByBook(@PathVariable String bookId,@RequestParam(name="pageNumber")Integer pageNumber){

        return ResponseEntity.ok(bookService.getFiftyAuthorsByBook(bookId, Constants.APIPAGESIZE  ,pageNumber));
    }


}
