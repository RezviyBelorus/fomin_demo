package com.example.demo.utils;

import com.example.demo.entity.Book;
import com.example.demo.entity.dto.BookDTO;

import java.util.HashSet;
import java.util.Set;

public class Transformator {
    public static Set<Book> transformToBook(Set<BookDTO> booksDTO) {
        Set<Book> books = new HashSet<>();
        booksDTO.forEach(bookDTO -> {
            Book book = new Book();
            book.setName(bookDTO.getName());
            book.setReleaseYear(bookDTO.getReleaseYear());
            books.add(book);
        });
        return books;
    }

    public static Set<BookDTO> transformToBookDTO(Set<Book> books) {
        Set<BookDTO> booksDTO = new HashSet<>();
        books.forEach(book -> booksDTO.add(new BookDTO(book)));
        return booksDTO;
    }
}
