package com.demo.demo.controller;

import com.demo.demo.Object.AuthorDTO;
import com.demo.demo.Object.BookDTO;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import com.demo.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @RequestMapping(value = "/save/atuthor",method = RequestMethod.PUT)
    public ResponseEntity<String> save(@RequestBody AuthorDTO authorDTO){
        return  ResponseEntity.ok(authorService.save(authorDTO));
    }

    @RequestMapping(value = "/get/authors",method = RequestMethod.GET)
    public ResponseEntity<List<Author>> getAuthors(){
        return  ResponseEntity.ok(authorService.getAuthors());
    }


}
