package com.demo.demo.dao;

import com.demo.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends CrudRepository<Book,String> {
    Book findBookById(String id);
    String saveBook(Book book);
    List<Book> findAllBooks();

}
