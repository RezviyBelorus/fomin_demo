package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookDAOTest {
    @Autowired
    BookDAO bookDAO;

    private static Book book;

    @BeforeClass
    public static void init() {
        book = new Book();
        book.setName("Chudo");
        book.setReleaseYear(2005);
    }

    @Test
    public void shouldSave() {
        Book actual = bookDAO.save(book);
        assertEquals(book.getName(), actual.getName());
    }

    @Test
    public void shouldFindBookByNameAndReleaseYear() throws Exception {
        Set<Book> actual = bookDAO.findBookByReleaseYear(book.getReleaseYear());
        assertNotNull(actual);
    }

    @Test
    public void shouldFindBookByName() throws Exception {
        Set<Book> actual = bookDAO.findBookByName(book.getName());
        assertNotNull(actual);
    }

    @Test
    public void shouldFindBookByReleaseYear() throws Exception {
        Set<Book> actual = bookDAO.findBookByReleaseYear(book.getReleaseYear());
        assertNotNull(actual);
    }

}