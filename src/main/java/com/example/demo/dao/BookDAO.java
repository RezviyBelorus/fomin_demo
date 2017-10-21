package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BookDAO extends CrudRepository<Book, Integer> {
    Book findBookByNameAndReleaseYear(String name, int releaseYear);

    Set<Book> findBookByName(String name);

    Set<Book> findBookByReleaseYear(int releaseYear);
}
