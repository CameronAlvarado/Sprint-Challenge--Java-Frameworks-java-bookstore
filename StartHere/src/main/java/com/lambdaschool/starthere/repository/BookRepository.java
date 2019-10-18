package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository  extends PagingAndSortingRepository<Book, Long>
{
    Book findByTitle(String title); // <-- added functionality

    List<Book> findByTitleContainingIgnoreCase(String title);
}
