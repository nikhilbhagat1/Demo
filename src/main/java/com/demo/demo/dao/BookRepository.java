package com.demo.demo.dao;

import com.demo.demo.model.Book;

import java.util.List;



public interface BookRepository  {

    String save(Book book);
    List<Book> findAll();
}
