package com.example.librarymanage.service;

import com.example.librarymanage.model.Books;
import com.example.librarymanage.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    BooksRepository booksRepository;

    public List<Books> getAll(){
        return booksRepository.findAll();
    }

    public Books getOne(Integer id){
        return booksRepository.findById(id).get();
    }

    public void addBook(Books book){
        booksRepository.save(book);
    }

    public void updateBook(Books book){
        booksRepository.save(book);
    }

    public void deleteBook(Integer id){
        booksRepository.deleteById(id);
    }
}
