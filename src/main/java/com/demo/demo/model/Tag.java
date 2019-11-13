package com.demo.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tag")
public class Tag {

    @Id
    @GeneratedValue
    private  String id;

    @Column(name="value")
    private Integer value;

    /*@ManyToMany(targetEntity =Book.class, mappedBy = "tags", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Book> bookSet = new HashSet<>();*/

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tagSet")
    private Set<Periodical> periodicalSet = new HashSet<>();

    public Tag(String id, Integer value, Set<Book> bookSet, Set<Periodical> periodicalSet) {
        this.id = id;
        this.value = value;
        /*this.bookSet = bookSet;
        this.periodicalSet = periodicalSet;*/
    }

    public Tag() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

   /* public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public Set<Periodical> getPeriodicalSet() {
        return periodicalSet;
    }

    public void setPeriodicalSet(Set<Periodical> periodicalSet) {
        this.periodicalSet = periodicalSet;
    }*/

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", value=" + value +
                /*", bookSet=" + bookSet +
                ", periodicalSet=" + periodicalSet +*/
                '}';
    }
}
