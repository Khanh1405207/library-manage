package com.example.librarymanage.service;

import com.example.librarymanage.model.Books;
import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.model.BorrowRecordId;
import com.example.librarymanage.model.Borrower;
import com.example.librarymanage.repository.BooksRepository;
import com.example.librarymanage.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BorrowRecordService {

    @Autowired
    BorrowRecordRepository borrowRecordRepository;
    @Autowired
    BooksRepository booksRepository;

    public List<BorrowRecord> getAll(){
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getOne(BorrowRecordId id){
        return borrowRecordRepository.findById(id).get();
    }

    public List<BorrowRecord> getByBorrower(Borrower borrower){
        return borrowRecordRepository.findBorrowRecordsByBorrower(borrower);
    }

    public Boolean existRecord(Borrower borrower, Books book){
        return borrowRecordRepository.existsByBorrowerAndBookAndEndDateIsNull(borrower,book);
    }

    public void startBorrowRecord(Borrower borrower,Books book){
        LocalDateTime startTime= LocalDateTime.now();
        BorrowRecord record=new BorrowRecord(borrower,book,startTime,null);
        book.setRemaining(book.getRemaining()-1);
        booksRepository.save(book);
        borrowRecordRepository.save(record);
    }

    public void endBorrowRecord(Borrower borrower,Books book){
        LocalDateTime endTime= LocalDateTime.now();
        BorrowRecordId id=new BorrowRecordId(borrower.getId(),book.getId());
        BorrowRecord record=borrowRecordRepository.findById(id).get();
        record.setEndDate(endTime);
        book.setRemaining(book.getRemaining()+1);
        booksRepository.save(book);
        borrowRecordRepository.save(record);
    }
}
