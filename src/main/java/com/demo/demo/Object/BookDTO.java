package com.demo.demo.Object;

public class BookDTO {

    private String title;
    private String year;
    private Integer pages;
    private Integer isbn;


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


    public BookDTO() {
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", pages=" + pages +
                ", isbn=" + isbn +
                '}';
    }
}
