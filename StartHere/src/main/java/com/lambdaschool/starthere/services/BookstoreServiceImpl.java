package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookstoreService")
public class BookstoreServiceImpl implements BookstoreService
{

    @Autowired
    private BookRepository bookrepo;

    @Autowired
    private AuthorRepository authrepo;

    @Override
    public List<Book> findAllBooks()
    {
        List<Book> list = new ArrayList<>();
        bookrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Author> findAllAuthors()
    {
        List<Author> list = new ArrayList<>();
        authrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book id: " + Long.toString(id) + " not found."));

        if (book.getTitle() != null)
        {
            currentBook.setTitle(book.getTitle());
            currentBook.setCopy(book.getCopy());
            currentBook.setISBN(book.getISBN());
        }

        return bookrepo.save(currentBook);
    }

//    @Override
//    public void save(long bookid, long authorid)
//    {
//        Book currentBook = bookrepo.findById(bookid);
//        Author currentAuthor = authrepo.findById(authorid);
//
//        if (bookrepo.findByTitle(currentBook.getTitle()) != null && authrepo.findByFname(currentAuthor.getFname()) != null)
//        {
//            throw new ResourceFoundException(student.getStudname() + " is alreay taken! ");
//        } else {
//
//            newStudent.setStudname(student.getStudname());
//
//            return studrepos.save(newStudent);
//        }
//    }

    @Override
    public void deleteBook(long id)
    {

    }
}
