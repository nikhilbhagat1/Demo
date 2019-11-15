package com.demo.demo.controller;

import com.demo.demo.Object.AuthorDTO;
import com.demo.demo.Object.BookDTO;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import com.demo.demo.services.AuthorService;
import com.google.gson.Gson;
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

    @Autowired
    Gson gson;


    @RequestMapping(value = "/save/author",method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody String author){
        AuthorDTO authorDTO;
        authorDTO = gson.fromJson(author,AuthorDTO.class);
        return  ResponseEntity.ok(authorService.save(authorDTO));
    }

    @RequestMapping(value = "/get/authors",method = RequestMethod.GET)
    public ResponseEntity<List<Author>> getAuthors(){
        return  ResponseEntity.ok(authorService.getAuthors());
    }


}
