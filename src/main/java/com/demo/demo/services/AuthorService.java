package com.demo.demo.services;

import com.demo.demo.Object.AuthorDTO;
import com.demo.demo.Object.BookDTO;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;

import java.util.List;
import java.util.Set;

public interface AuthorService {

     List<Author> getAuthors();
     String save(AuthorDTO authorDTO);

}
