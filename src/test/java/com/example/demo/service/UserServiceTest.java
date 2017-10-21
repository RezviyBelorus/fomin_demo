package com.example.demo.service;

import com.example.demo.dao.BookDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.BookDTO;
import com.example.demo.entity.dto.UserDTO;
import org.junit.Assert;
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
public class UserServiceTest {

    @Mock
    UserDAO userDAO;

    @Mock
    BookDAO bookDAO;

    @InjectMocks
    private UserService userService;

    private static UserDTO userDTO;
    private static User user;
    private static Book book;

    @BeforeClass
    public static void init() {
        user = new User();
        user.setLogin("login");
        user.setEmail("email@cor.com");
        user.setId(4);

        book = new Book();
        book.setName("Alisa");
        book.setReleaseYear(2004);
        book.setId(1);
    }

    @Test
    public void shouldSaveUser() throws Exception {
        //given
        userDTO = new UserDTO(user);

        Mockito.when(userDAO.findUserByEmail(user.getEmail())).thenReturn(null);
        Mockito.when(userDAO.findUserByLogin(user.getLogin())).thenReturn(null);
        Mockito.when(userDAO.save(user)).thenReturn(user);

        //when
        UserDTO actual = userService.saveUser(UserServiceTest.userDTO);

        //then
        assertNotNull(actual);
    }

    @Test
    public void shouldFindByLogin() throws Exception {
        //given
        userDTO = new UserDTO(user);

        Mockito.when(userDAO.findUserByLogin(userDTO.getLogin())).thenReturn(user);

        //when
        UserDTO actual = userService.findByLogin(userDTO.getLogin());
        //then
        assertNotNull(actual);

    }

    @Test
    public void shouldFindByEmail() throws Exception {
        //given
        userDTO = new UserDTO(user);

        Mockito.when(userDAO.findUserByEmail(userDTO.getEmail())).thenReturn(user);

        //when
        UserDTO actual = userService.findByEmail(userDTO.getEmail());

        //then
        assertNotNull(actual);
    }

    @Test
    public void shouldDeleteByLogin() throws Exception {
        //given
        userDTO = new UserDTO(user);

        Mockito.when(userDAO.findUserByLogin(userDTO.getLogin())).thenReturn(user);

        //when
        UserDTO actual = userService.deleteByLogin(userDTO.getLogin());
        assertNotNull(actual);
    }

    @Test
    public void shouldAddBooksToFavorites() throws Exception {
        //given
        userDTO = new UserDTO(user);
        Set<BookDTO> booksDTO = new HashSet<>();
        booksDTO.add(new BookDTO(book));

        Mockito.when(userDAO.findUserByLogin(userDTO.getLogin())).thenReturn(user);

        //when
        UserDTO actual = userService.addBooksToFavorites(UserServiceTest.userDTO, booksDTO);

        assertNotNull(actual);
    }
}