package com.demo.demo.services;

import com.demo.demo.model.Book;

import java.util.List;

public interface BookService {

    public String save(Book book);
    public List<Book> findAll();

}
