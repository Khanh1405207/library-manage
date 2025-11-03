package com.example.librarymanage.controller;

import com.example.librarymanage.model.Books;
import com.example.librarymanage.service.BooksService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BookController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getListBook(){
        List<Books> books = booksService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") Integer id){
        try {
            Books books= booksService.getOne(id);
            return ResponseEntity.ok(books);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }

    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Books book){
        try {
            Books created = booksService.addBook(book);
            return ResponseEntity.status(201).body(created);
        }catch (DuplicateKeyException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }catch (ValidationException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }

    @PutMapping("/books")
    public ResponseEntity<?> updateBook(@RequestBody Books book){
        try {
            Books updated= booksService.updateBook(book);
            return ResponseEntity.ok(updated);
        }catch (DuplicateKeyException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Integer id){
        try{
            booksService.deleteBook(id);
            return ResponseEntity.ok("Delete successfully");
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }
    }
}
