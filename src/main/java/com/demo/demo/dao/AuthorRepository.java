package com.demo.demo.dao;

import com.demo.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface AuthorRepository { /*JpaRepository<Author, Serializable> {*/
    @Query("select a from Author a")
    List<Author> getAuthors();

    String save(Author author);

    Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber);
    Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber, String sortBy,String direction);

}
