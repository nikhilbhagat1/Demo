package com.demo.demo.Object;

import com.demo.demo.model.Book;

import java.util.List;
import java.util.Set;

public class AuthorDTO {

    private String name;

    private String bookId;

    private String paperId;

    private BookDTO book;

    private PaperDTO paper;

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

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public PaperDTO getPaper() {
        return paper;
    }

    public void setPaper(PaperDTO paper) {
        this.paper = paper;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "name='" + name + '\'' +
                ", bookId='" + bookId + '\'' +
                ", paperId='" + paperId + '\'' +
                ", book=" + book +
                ", paper=" + paper +
                '}';
    }
}
