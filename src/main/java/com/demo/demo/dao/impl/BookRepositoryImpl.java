package com.demo.demo.dao.impl;

import com.demo.demo.dao.BookRepository;
import com.demo.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class BookRepositoryImpl implements  BookRepository{



    @Autowired
    BaseRepositoryImpl baseRepository;

    public String save(Book book){
        try {
            book.setId(UUID.randomUUID().toString());
            baseRepository.getEntityManager().persist(book);
            return book.getId();
        }catch (Exception e){
            return e.getMessage();
        }
    }


    public List<Book> findAll(){
      /* List<String> a = new ArrayList();
        a.add(new String("Hi"));
        return  a;*/
        return baseRepository.findList(" from com.demo.demo.model.Book b",Book.class);

    }
}
