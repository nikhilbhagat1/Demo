package com.demo.demo.services.impl;

import com.demo.demo.Object.AuthorDTO;
import com.demo.demo.Object.BookDTO;
import com.demo.demo.Object.PaperDTO;
import com.demo.demo.Util.Constants;
import com.demo.demo.dao.AuthorRepository;
import com.demo.demo.dao.BookRepository;
import com.demo.demo.dao.PaperRepository;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import com.demo.demo.model.Paper;
import com.demo.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PaperRepository paperRepository;


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
            return bookRepository.saveBook(book);
        }
        return null;
    }

    public List<Book> findAll() {
        return bookRepository.findAllBooks();
    }


    //@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber) {
        if (bookId != null) {
            Map<String,Object> response = new HashMap<>();
            Map<String, Object> result;
            List<AuthorDTO> authorDTOS = new ArrayList<>();
            List<Author> authors;
            result= authorRepository.getAuthorsByBook(bookId, pageSize, pageNumber);
            authors=(List<Author>) result.get("authors");
            response.put("totalPages",result.get("totalPages"));
            Book book= bookRepository.findBookById(bookId);
            if(authors!=null){
                for (Author author: authors){
                    AuthorDTO authorDTO =new AuthorDTO();
                    mapAuthorDTO(author,authorDTO);
                    BookDTO bookDTO = new BookDTO();

                    if( book!=null ){
                        bookDTO.setIsbn(book.getIsbn());
                        bookDTO.setPages(book.getPages());
                        bookDTO.setTitle(book.getTitle());
                        bookDTO.setYear(book.getYear());
                        authorDTO.setBook(bookDTO);
                    }

                    PaperDTO paperDTO = new PaperDTO();
                    Paper paper= paperRepository.findById(author.getPaperId()).get();
                    if(paper!=null){
                        paperDTO.setId(paper.getId());
                        paperDTO.setName(paper.getName());

                        authorDTO.setPaper(paperDTO);
                    }
                    authorDTOS.add(authorDTO);
                }

            }
            response.put("authors",authorDTOS);
            return response;
        }
        return null;
    }


    public void mapAuthorDTO(Author authorEntity,AuthorDTO authorDTO){

        authorDTO.setPaperId(authorEntity.getPaperId());
        authorDTO.setName(authorEntity.getName());
        authorDTO.setBookId(authorEntity.getBookId());

    }



    public Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber, String sortBy,String direction) {
        if (bookId != null) {
            return authorRepository.getAuthorsByBook(bookId, pageSize, pageNumber,sortBy,direction);
        }
        return null;
    }



    public int getInitalPageNumber(int pageNumber){

        if(pageNumber == 1)
            return pageNumber-1;

        return (pageNumber-1)* Constants.APIITRATE;
    }

    @Async("threadPoolTaskExecutor")
    public Map<String, Object> getFiftyAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber) {
        try {

            Map<String, Object> map = new HashMap<>();
            List<AuthorDTO> authors = new ArrayList<>();
            // pageNumber From we will iterate to
            int initElem =getInitalPageNumber(pageNumber);

            for (int i = initElem; i <= initElem + Constants.COUNTOFDATA; i++) {
                Map<String, Object> responseMap = new HashMap<>();

                String url = "http://localhost:8081/v1/book/" + bookId + "/authors?pageSize=" + pageSize + "&pageNumber=" + i;
                RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
                RestTemplate restTemplate = restTemplateBuilder.build();
                responseMap=restTemplate.getForObject(url, Map.class);
                CompletableFuture.completedFuture(responseMap);

                int totalPages = (Integer) responseMap.get("totalPages");
                if(totalPages<i){
                    map.put("authors", authors);
                    return map;
                }
                authors.addAll ((List<AuthorDTO>) responseMap.get("authors"));
            }
            map.put("authors", authors);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
