package com.demo.demo.Object;

import com.demo.demo.model.Book;

import java.util.List;
import java.util.Set;

public class AuthorDTO {

    private String name;

    private String bookId;

    private String paperId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }


    @Override
    public String toString() {
        return "AuthorDTO{" +
                "name='" + name + '\'' +
                ", bookId='" + bookId + '\'' +
                ", paperId='" + paperId + '\'' +
                '}';
    }
}
