package com.demo.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="name")
    private String name;

    @NotEmpty
    @Column(name="book_id")
    private String bookId;

    @NotEmpty
    @Column(name="paper_id")
    private String paperId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn(name = "paper_id", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Paper paper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Book getBook() {
        return book;
    }

    private boolean sameAsFormer(Book newBook) {
        return bookId==null? newBook == null : bookId.equals(newBook.getId());
    }


    public void setBook(Book book) {
        //prevent endless loop
        if (sameAsFormer(book))
            return ;
        this.book = book;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Author() {
    }

    public Author(String id, String name, @NotEmpty String bookId, @NotEmpty String paperId, Book book, Paper paper) {
        this.id = id;
        this.name = name;
        this.bookId = bookId;
        this.paperId = paperId;
        this.book = book;
        this.paper = paper;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bookId='" + bookId + '\'' +
                ", paperId='" + paperId + '\'' +
              /*  ", book=" + book +
                ", paper=" + paper +*/
                '}';
    }
}
