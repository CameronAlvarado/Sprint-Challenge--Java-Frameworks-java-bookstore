package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController
{
    @Autowired
    private BookstoreService bookstoreServ;

    @GetMapping(value = "/books/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request)
    {
        List<Book> bookList = bookstoreServ.findAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping(value = "/authors/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(HttpServletRequest request)
    {
        List<Author> authorsList = bookstoreServ.findAllAuthors();
        return new ResponseEntity<>(authorsList, HttpStatus.OK);
    }

    @PutMapping(value = "/data/books/{bookid}")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book book,
            @PathVariable
                    long bookid)
    {
        bookstoreServ.update(book, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
