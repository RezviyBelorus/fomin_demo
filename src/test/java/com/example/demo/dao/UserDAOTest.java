package com.example.demo.dao;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;



import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDAOTest {
    @Autowired
    UserDAO userDAO;

    static User user;

    @BeforeClass
    public static void init() {
        Book book = new Book();
        book.setName("Harry Potter");
        book.setReleaseYear(2099);
        Set<Book> books = new HashSet<>();
        books.add(book);

        user = new User();
        user.setLogin("testLogin");
        user.setEmail("testEmail@cor.com");
        user.setPassword("123123");
        user.setRole("user");
        user.setBooks(books);

    }
    @Test
    public void shouldSave() {
        User actual = userDAO.save(user);
        assertEquals(user.getLogin(), actual.getLogin());
    }

    @Test
    public void shouldFindUserByLogin() throws Exception {
        String login = "login";
        User actual = userDAO.findUserByLogin(login);
        assertNotNull(actual);
    }

    @Test
    public void shouldFindUserByEmail() throws Exception {
        String email = "email@cor.com";
        User actual = userDAO.findUserByEmail(email);
        assertNotNull(actual);
    }
}