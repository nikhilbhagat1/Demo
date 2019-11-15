package com.demo.demo.dao.impl;

import com.demo.demo.dao.BookRepository;
import com.demo.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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




    public String saveBook(Book book) {
        try {
            /*//book.setId(UUID.randomUUID().toString());*/
            entityManager.persist(book);
            return book.getId();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public List<Book> findAllBooks() {

        TypedQuery<Book> tQuery = entityManager.createQuery(" from com.demo.demo.model.Book b", Book.class);
        return tQuery.getResultList();
    }

    /*@Override
    public List<Book> findAll(Sort sort) {
        return null;
    }*/

/*    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }*/

    @Override
    public List<Book> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Book> S save(S s) {
        return null;
    }

    @Override
    public <S extends Book> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Book findBookById(String s) {
         TypedQuery<Book> tQuery = entityManager.createQuery(" from com.demo.demo.model.Book b where b.id =: id", Book.class);
        tQuery.setParameter("id",s);
        return tQuery.getSingleResult();
    }

    @Override
    public Optional<Book> findById(String s) {
        return Optional.empty();
    }

  /*  @Override
    public Optional<Book> findById(String s) {
        return Optional.empty();
    }*/

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

   /* @Override
    public void flush() {

    }

    @Override
    public <S extends Book> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Book> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Book getOne(String s) {
        return null;
    }

    @Override
    public <S extends Book> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Book> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Book> boolean exists(Example<S> example) {
        return false;
    }*/


}
