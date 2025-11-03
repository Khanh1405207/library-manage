package com.example.librarymanage.service;

import com.example.librarymanage.model.Books;
import com.example.librarymanage.repository.BooksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        return booksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Khong tim thay sach"));
    }

    public Books addBook(Books book){
        if (booksRepository.existsByBookCode(book.getBookCode())){
            throw new DuplicateKeyException("Ma sach trung lap: "+ book.getBookCode());
        }
        return booksRepository.save(book);
    }

    public Books updateBook(Books book){
        booksRepository.findById(book.getId())
                .orElseThrow(() -> new EntityNotFoundException("Khong tim thay sach can sua"));
        if (booksRepository.existsByBookCodeAndIdNot(book.getBookCode(),book.getId())){
            throw new DuplicateKeyException("Ma sach da ton tai");
        }
        return booksRepository.save(book);
    }

    public void deleteBook(Integer id){
        booksRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Khong tim thay sach can xoa"));
        booksRepository.deleteById(id);
    }
}
