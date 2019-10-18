package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;

import java.util.List;

public interface BookstoreService
{
    List<Book> findAllBooks();

    List<Author> findAllAuthors();

    Book update(Book book, long bookid);

//    void save(long bookid, long authorid);

    void deleteBook(long id);
}
