package com.demo.demo.dao.impl;

import com.demo.demo.dao.BookRepository;
import com.demo.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class BookRepositoryImpl implements BookRepository {


    /*@Autowired
    BaseRepositoryImpl baseRepository;*/
    @PersistenceContext
    EntityManager entityManager;


    public String save(Book book) {
        try {
            /*//book.setId(UUID.randomUUID().toString());*/
            entityManager.persist(book);
            return book.getId();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public List<Book> findAll() {

        TypedQuery<Book> tQuery = entityManager.createQuery(" from com.demo.demo.model.Book b", Book.class);
        return tQuery.getResultList();
    }


}
