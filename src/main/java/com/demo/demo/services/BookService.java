package com.demo.demo.services;

import com.demo.demo.Object.BookDTO;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    public String save(BookDTO book);
    public List<Book> findAll();
    Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber);


}
