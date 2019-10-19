package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookstoreService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController
{
    @Autowired
    private BookstoreService bookstoreServ;

    @ApiOperation(value = "return all Books",
            response = Book.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping(value = "/books/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 3) Pageable pageable, HttpServletRequest request)
    {
        ArrayList<Book> bookList = bookstoreServ.findAllBooks(pageable);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @ApiOperation(value = "return all Authors",
            response = Author.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping(value = "/authors/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 3) Pageable pageable, HttpServletRequest request)
    {
        List<Author> authorsList = bookstoreServ.findAllAuthors(pageable);
        return new ResponseEntity<>(authorsList, HttpStatus.OK);
    }

    @ApiOperation(value = "update book using book id")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Book Updated"), @ApiResponse(code = 404,
            message = "Book Not Found",
            response = ErrorDetail.class)})

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

    @ApiOperation(value = "add an existing book to existing author using ids")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Book Added to Author"), @ApiResponse(code = 404,
            message = "Book/Author Not Found",
            response = ErrorDetail.class)})

    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> addBookToAuthor(@PathVariable long bookid,
                                             @PathVariable long authorid)
    {
        bookstoreServ.addBookToAuthor(bookid, authorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "delete a book using book id")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Book Deleted"), @ApiResponse(code = 404,
            message = "Book Not Found",
            response = ErrorDetail.class)})

    @DeleteMapping("/data/books/{bookid}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long bookid)
    {
        bookstoreServ.deleteBook(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
