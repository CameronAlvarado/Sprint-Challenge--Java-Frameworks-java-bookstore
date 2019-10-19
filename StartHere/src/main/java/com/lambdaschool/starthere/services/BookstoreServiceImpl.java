package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceFoundException;
import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ArrayList<Book> findAllBooks(Pageable pageable)
    {
        ArrayList<Book> list = new ArrayList<>();
        bookrepo.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Author> findAllAuthors(Pageable pageable)
    {
        List<Author> list = new ArrayList<>();
        authrepo.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book id: " + Long.toString(id) + " not found."));

        if (book.getTitle() != null)
        {
            currentBook.setTitle(book.getTitle());
            currentBook.setCopy(book.getCopy());
            currentBook.setISBN(book.getISBN());
        }

        return bookrepo.save(currentBook);
    }

    @Override
    public void addBookToAuthor(long bookid, long authorid) throws ResourceFoundException
    {
        bookrepo.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book id " + bookid + " not found"));
        authrepo.findById(authorid).orElseThrow(() -> new ResourceNotFoundException("Author id " + authorid + " not found"));

        if (bookrepo.checkWroteCombo(bookid, authorid)
                .getCount() <= 0)
        {
            bookrepo.insertWrote(bookid, authorid);
        } else
        {
            throw new ResourceFoundException("Book and Author Combination Already Exists");
        }
    }

//    @Override
//    public void save(long bookid, long authorid)  throws ResourceNotFoundException
//    {
////        Book currentBook = bookrepo.findById(bookid);
////        Author currentAuthor = authrepo.findById(authorid);
//
//        if (bookrepo.findById(bookid).isPresent() && authrepo.findById(authorid).isPresent())
//        {
//            newStudent.setStudname(student.getStudname());
//
//            return studrepos.save(newStudent);
//        } else {
//
//            newStudent.setStudname(student.getStudname());
//
//            return studrepos.save(newStudent);
//        }
//    }

    @Transactional
    @Override
    public void deleteBook(long id)
    {
        bookrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " not found"));

        bookrepo.deleteById(id);
    }
}
