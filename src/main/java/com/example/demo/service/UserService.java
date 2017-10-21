package com.example.demo.service;

import com.example.demo.dao.BookDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.entity.UserSession;
import com.example.demo.entity.dto.BookDTO;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.exception.AlreadyExistException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.utils.Transformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    AuthenticationService authenticationService;

    public UserDTO login(String login, String password) {
        User user = userDAO.findUserByLogin(login);

        if (user == null) {
            return null;
        }
        boolean isLogined = user.getPassword().equals(password);
        if (isLogined) {
            UserDTO userDTO = new UserDTO(user);
            UserSession session = authenticationService.getSession(userDTO.getId());
            if (session == null) {
                userDTO.setSessionId(AuthenticationService.generateId());
                authenticationService.createSession(userDTO);
            }
            return userDTO;
        }
        return null;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User userByEmail = userDAO.findUserByEmail(userDTO.getEmail());
        User userByLogin = userDAO.findUserByLogin(userDTO.getLogin());

        if (userByLogin == null && userByEmail == null) {
            User user = new User();
            user.setLogin(userDTO.getLogin());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            Set<Book> books = Transformator.transformToBook(userDTO.getBooks());
            Iterable<Book> allBooksFromDB = bookDAO.findAll();
            Set<Book> allBooks = new HashSet<>();
            allBooksFromDB.forEach(book -> allBooks.add(book));
            allBooks.retainAll(books);
            allBooks.addAll(books);
            user.setBooks(allBooks);
            if (user.getRole() == null) {
                user.setRole("user");
            }
            User savedUser = userDAO.save(user);
            return new UserDTO(savedUser);
        }
        throw new AlreadyExistException("User already exist: " + userDTO.getLogin());
    }

    public UserDTO findByLogin(String login) {
        User userByLogin;
        userByLogin = userDAO.findUserByLogin(login);
        if (userByLogin == null) {
            throw new NotFoundException("User not found: " + login);
        }
        return new UserDTO(userByLogin);
    }

    public UserDTO findByEmail(String email) {
        User userByEmail;

        userByEmail = userDAO.findUserByEmail(email);

        if (userByEmail == null) {
            throw new NotFoundException("User not found: " + email);
        }

        return new UserDTO(userByEmail);
    }

    public UserDTO deleteByLogin(String login) {
        User userByLogin;
        userByLogin = userDAO.findUserByLogin(login);
        if (userByLogin == null) {
            throw new NotFoundException("User not found: " + login);
        }
        UserDTO userDTO = new UserDTO(userByLogin);
        userDAO.delete(userByLogin);
        return userDTO;
    }

    public UserDTO deleteByEmail(String email) {
        User userByEmail;
        userByEmail = userDAO.findUserByEmail(email);
        if (userByEmail == null) {
            throw new NotFoundException("User not found: " + email);
        }
        UserDTO userDTO = new UserDTO(userByEmail);
        userDAO.delete(userByEmail);
        return userDTO;
    }

    public UserDTO addBooksToFavorites(UserDTO userDTO, Set<BookDTO> booksDTO) {
        User userInDB;
        try {
            userInDB = userDAO.findUserByLogin(userDTO.getLogin());
        } catch (Exception e) {
            throw new NotFoundException("User not found: " + userDTO.getLogin());
        }
        Set<Book> books = userInDB.getBooks();
        booksDTO.forEach(bookDTO -> {
            Book book = new Book();
            book.setName(bookDTO.getName());
            book.setReleaseYear(bookDTO.getReleaseYear());
            books.add(book);
        });
        return new UserDTO(userInDB);
    }

    public UserDTO findById(int userId) {
        User user = userDAO.findOne(userId);
        if (user == null) {
            throw new NotFoundException("User not found: " + userId);
        }
        user.getBooks().size();
        return new UserDTO(user);
    }
}
