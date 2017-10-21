package com.example.demo.service;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import com.example.demo.entity.dto.BookDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    BookDAO bookDAO;

    @InjectMocks
    BookService bookService;

    private static Book book;

    @BeforeClass
    public static void ini() {
        book = new Book();
        book.setName("Alisa");
        book.setReleaseYear(1766);
    }
    @Test
    public void saveBook() throws Exception {
        //given
        BookDTO bookDTO = new BookDTO(book);

        Mockito.when(bookDAO.findBookByNameAndReleaseYear(bookDTO.getName(), bookDTO.getReleaseYear())).thenReturn(null);
        Mockito.when(bookDAO.save(book)).thenReturn(book);

        //when
        BookDTO actual = bookService.saveBook(bookDTO);

        //then
        assertNotNull(actual);
    }

    @Test
    public void findByName() throws Exception {
        //given
        BookDTO bookDTO = new BookDTO(book);
        Set<Book> booksInDB = new HashSet<>();

        Mockito.when(bookDAO.findBookByName(bookDTO.getName())).thenReturn(booksInDB);

        //when
        Set<BookDTO> actual = bookService.findByName(bookDTO.getName());

        //then
        assertNotNull(actual);
    }

    @Test
    public void findBooksByReleaseYear() throws Exception {
        //given
        BookDTO bookDTO = new BookDTO(book);
        Set<Book> booksInDB = new HashSet<>();

        Mockito.when(bookDAO.findBookByReleaseYear(bookDTO.getReleaseYear())).thenReturn(booksInDB);

        //when
        Set<BookDTO> actual = bookService.findBooksByReleaseYear(bookDTO.getReleaseYear());

        //then
        assertNotNull(actual);
    }

    @Test
    public void findBookByNameAndReleaseYear() throws Exception {
        //given
        BookDTO bookDTO = new BookDTO(book);

        Mockito.when(bookDAO.findBookByNameAndReleaseYear(bookDTO.getName(), bookDTO.getReleaseYear())).thenReturn(book);

        //when
        BookDTO actual = bookService.findBookByNameAndReleaseYear(bookDTO.getName(), bookDTO.getReleaseYear());

        //then
        assertNotNull(actual);
    }
}