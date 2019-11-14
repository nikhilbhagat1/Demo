package com.demo.demo.services.impl;

import com.demo.demo.Object.AuthorDTO;
import com.demo.demo.dao.AuthorRepository;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import com.demo.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors() {
        return authorRepository.getAuthors();
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public String save(AuthorDTO authorDTO){
        Author author =new Author();
        getAuthorEntity(authorDTO,author);
        return authorRepository.save(author);
    }

    public void getAuthorEntity(AuthorDTO authorDTO,Author authorEntity){
        authorEntity.setName(authorDTO.getName());
        authorEntity.setBookId(authorDTO.getBookId());
        authorEntity.setPaperId(authorDTO.getPaperId());
    }


}
