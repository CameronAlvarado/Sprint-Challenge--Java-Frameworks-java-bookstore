package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.starthere.logging.Loggable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "authors")
public class Author extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @Column(nullable = false)
    private String lname;

    @Column(nullable = false)
    private String fname;

//    @ManyToMany
//    @JoinTable(name = "wrote",
//            joinColumns = {@JoinColumn(name = "authorid")},
//            inverseJoinColumns = {@JoinColumn(name = "bookid")})
//    @JsonIgnoreProperties("authors")
//    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "author",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("author")
    private List<Wrote> wrote = new ArrayList<>();

    public Author()
    {
    }

    public Author(String lname, String fname, List<Book> books)
    {
        this.lname = lname;
        this.fname = fname;
//        this.books = books;
    }

    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

//    public List<Book> getBooks()
//    {
//        return books;
//    }
//
//    public void setBooks(List<Book> books)
//    {
//        this.books = books;
//    }


    public List<Wrote> getWrote()
    {
        return wrote;
    }

    public void setWrote(List<Wrote> wrote)
    {
        this.wrote = wrote;
    }

    @Override
    public String toString()
    {
        return "Author{" +
                "authorid=" + authorid +
                ", lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
