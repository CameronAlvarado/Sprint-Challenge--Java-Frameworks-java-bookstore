package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface BookstoreService
{
    ArrayList<Book> findAllBooks(Pageable pageable);

    List<Author> findAllAuthors(Pageable pageable);

    Book update(Book book, long bookid);

    void addBookToAuthor(long bookid, long authorid);

//    void save(long bookid, long authorid);

    void deleteBook(long id);
}
