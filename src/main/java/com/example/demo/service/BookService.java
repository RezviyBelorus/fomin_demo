package com.example.demo.service;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import com.example.demo.entity.dto.BookDTO;
import com.example.demo.exception.AlreadyExistException;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public BookDTO saveBook(BookDTO bookDTO) {
        Book bookInDB = bookDAO.findBookByNameAndReleaseYear(bookDTO.getName(), bookDTO.getReleaseYear());
        if (bookInDB == null) {
            Book book = new Book();
            book.setName(bookDTO.getName());
            book.setReleaseYear(bookDTO.getReleaseYear());
            Book savedBook = bookDAO.save(book);
            return new BookDTO(savedBook);
        }
        throw new AlreadyExistException("Book already exists" + bookDTO.getName() + " " + bookDTO.getReleaseYear());
    }

    public Set<BookDTO> findByName(String name) {
        Set<Book> bookByName = bookDAO.findBookByName(name);
        if (bookByName == null) {
            throw new NotFoundException("Books not found by name: " + name);
        }
        Set<BookDTO> booksDTO = new HashSet<>();
        bookByName.forEach(book -> {
            BookDTO bookDTO = new BookDTO(book);
            booksDTO.add(bookDTO);
        });
        return booksDTO;
    }

    public Set<BookDTO> findBooksByReleaseYear(int releaseYear) {

        Set<Book> bookByReleaseYear = bookDAO.findBookByReleaseYear(releaseYear);

        if (bookByReleaseYear == null) {
            throw new NotFoundException("Books not found by release year: " + releaseYear);
        }
        Set<BookDTO> booksDTO = new HashSet<>();
        bookByReleaseYear.forEach(book -> {
            BookDTO bookDTO = new BookDTO(book);
            booksDTO.add(bookDTO);
        });
        return booksDTO;
    }

    public BookDTO findBookByNameAndReleaseYear(String name, int releaseYear) {
        Book bookInDB = bookDAO.findBookByNameAndReleaseYear(name, releaseYear);
        if (bookInDB == null) {
            throw new NotFoundException("Book not found book: " + name + " " + releaseYear);
        }
        return new BookDTO(bookInDB);
    }

    public BookDTO deleteByNameandReleaseYear(String name, int releaseYear) {
        Book bookInDB = bookDAO.findBookByNameAndReleaseYear(name, releaseYear);
        if (bookInDB == null) {
            throw new NotFoundException("Book not found: " + name + " " + releaseYear);
        }
        bookDAO.delete(bookInDB);
        return new BookDTO(bookInDB);
    }
}
