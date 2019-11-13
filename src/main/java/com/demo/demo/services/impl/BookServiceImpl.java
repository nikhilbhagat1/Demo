package com.demo.demo.services.impl;

import com.demo.demo.dao.BookRepository;
import com.demo.demo.model.Book;
import com.demo.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;


    public String save(Book book){
       return bookRepository.save(book);
    }

    public List<Book> findAll(){
     return bookRepository.findAll();
    }

}
