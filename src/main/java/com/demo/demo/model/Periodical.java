package com.demo.demo.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Periodical {


    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="title")
    private String title;

    @Column(name="year")
    private String year;

    @Column(name="volume")
    private String volume;

    @Column(name="isbn")
    private String isbn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodical", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Paper> paperSet;

    @ManyToMany( fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "periodical_tag",
            joinColumns = {@JoinColumn(name = "periodical_id")},
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Paper> getPaperSet() {
        return paperSet;
    }

    public void setPaperSet(Set<Paper> paperSet) {
        this.paperSet = paperSet;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Periodical(String id, String title, String year, String volume, String isbn, Set<Paper> paperSet, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.volume = volume;
        this.isbn = isbn;
        this.paperSet = paperSet;
        this.tags = tags;
    }

    public Periodical() {
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", volume='" + volume + '\'' +
                ", isbn='" + isbn + '\'' +
                ", paperSet=" + paperSet +
                ", tags=" + tags +
                '}';
    }
}
