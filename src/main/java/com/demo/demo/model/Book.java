package com.demo.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="book")
public class Book {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="title")
    private String title;

    @Column(name="year")
    private String year;

    @Column(name="pages")
    private Integer pages;

    @Column(name="isbn")
    private Integer isbn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Author> authors;

    public Book() {
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "book_tag",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags= new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {

        this.authors = authors;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", pages=" + pages +
                ", isbn=" + isbn +
                ", authors=" + authors +
                ", tags=" + tags +
                '}';
    }

    public Book(String title, String year, Integer pages) {
        this.title = title;
        this.year = year;
        this.pages = pages;
        this.authors = authors;
    }

    public Book(String id, String title, String year, Integer pages, Integer isbn, Set<Author> authors, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.pages = pages;
        this.isbn = isbn;
        this.authors = authors;
        this.tags = tags;
    }
}
