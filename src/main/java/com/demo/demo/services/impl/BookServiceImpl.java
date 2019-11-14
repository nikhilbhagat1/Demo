package com.demo.demo.services.impl;

import com.demo.demo.Object.BookDTO;
import com.demo.demo.dao.AuthorRepository;
import com.demo.demo.dao.BookRepository;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import com.demo.demo.services.AuthorService;
import com.demo.demo.services.BookService;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public Book mapBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setYear(bookDTO.getYear());
        book.setIsbn(bookDTO.getIsbn());
        book.setPages(bookDTO.getPages());
        return book;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public String save(BookDTO bookDTO) {
        if (bookDTO != null) {
            Book book = mapBookEntity(bookDTO);
            return bookRepository.save(book);
        }
        return null;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber) {
        if (bookId != null){
            return authorRepository.getAuthorsByBook(bookId, pageSize, pageNumber);
        }
        return null;
    }



}
