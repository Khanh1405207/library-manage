package com.example.librarymanage.controller;

import com.example.librarymanage.model.Books;
import com.example.librarymanage.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book-api")
public class BookController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getListBook(){
        List<Books> books = booksService.getAll();
        return ResponseEntity.ok(books);
    }

}
