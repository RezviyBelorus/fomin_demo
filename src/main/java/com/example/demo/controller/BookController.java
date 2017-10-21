package com.example.demo.controller;

import com.example.demo.entity.dto.BookDTO;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Aleksandr Fomin
 */
@RestController
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     *
     * @param bookDTO potential to save book
     * @return ResponseEntity with Book in DTO form if already not exists in database
     */
    @PostMapping(path = "/saveBook")
    public ResponseEntity saveBook(BookDTO bookDTO) {
        BookDTO savedBookDTO = bookService.saveBook(bookDTO);
        return new ResponseEntity(savedBookDTO, HttpStatus.OK);
    }

    /**
     *
     * @param name Book's name, should be String
     * @return ResponseEntity with Set of books transformed to DTO format if found
     */
    @GetMapping(path = "/get/name/{name}")
    public ResponseEntity findByName(@PathVariable(value = "name") String name) {
        Set<BookDTO> bookDTOSet = bookService.findByName(name);
        return new ResponseEntity(bookDTOSet, HttpStatus.OK);
    }

    /**
     *
     * @param releaseYear Book's release year, should be int
     * @return ResponseEntity with Set of books transformed to DTO format if found
     */
    @GetMapping(path = "/get/releaseyear/{releaseYear}")
    public ResponseEntity findByReleaseYear(@PathVariable(value = "releaseYear") int releaseYear) {
        Set<BookDTO> bookDTOSet = bookService.findBooksByReleaseYear(releaseYear);
        return new ResponseEntity(bookDTOSet, HttpStatus.OK);
    }

    /**
     *
     * @param name Book's name, should be String
     * @param releaseYear Book's release year, should be int
     * @return ResponseEntity with Book in DTO form if exists in database
     */
    @GetMapping(path = "/get/name/{name}/releaseyear/{releaseYear}")
    public ResponseEntity findBookByNameAndReleaseYear(@PathVariable(value = "name") String name,
                                                @PathVariable(value = "releaseYear") int releaseYear) {
        BookDTO bookDTO = bookService.findBookByNameAndReleaseYear(name, releaseYear);
        return new ResponseEntity(bookDTO, HttpStatus.OK);
    }
}
