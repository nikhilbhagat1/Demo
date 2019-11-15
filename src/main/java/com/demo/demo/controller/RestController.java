package com.demo.demo.controller;



import com.demo.demo.model.Book;
import com.demo.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class RestController {
    @Autowired
    BookService bookService;


    @GetMapping("/demo")
    public String greeting( Model model) {
        List<Book> book = bookService.findAll();
        model.addAttribute("listBook", book);
        return "greeting";
    }






}
