package com.demo.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="paper")
public class Paper {
    @Id
    @GeneratedValue
    private String id;

    @Column(name="name")
    private String name;

    @NotEmpty
    @Column(name="periodical_id")
    private String periodicalId;

    @ManyToOne
    @JoinColumn(name = "periodical_id", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Periodical periodical;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Author> authors;

    public Paper() {
    }

    public Paper(String id, String name, Periodical periodical, Set<Author> authors) {
        this.id = id;
        this.name = name;
        this.periodical = periodical;
        this.authors = authors;
    }

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

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
