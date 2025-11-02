package com.example.librarymanage.service;

import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Books;
import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.model.BorrowRecordId;
import com.example.librarymanage.repository.BooksRepository;
import com.example.librarymanage.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<BorrowRecord> getByAccountId(Integer accountId){
        return borrowRecordRepository.findBorrowRecordsByAccount_Id(accountId);
    }

    public BorrowRecord getByRecordId(Integer accountId,Integer bookId){
        return borrowRecordRepository.findBorrowRecordByAccount_IdAndBook_Id(accountId,bookId);
    }

    public Boolean existRecord(Integer accountId, Integer bookId){
        return borrowRecordRepository.existsByAccount_IdAndBook_IdAndEndDateIsNull(accountId,bookId);
    }

    public void startBorrowRecord(Account account,Books book){
        LocalDateTime startTime= LocalDateTime.now();
        BorrowRecord record=new BorrowRecord(account,book,startTime,null);
        book.setRemaining(book.getRemaining()-1);
        booksRepository.save(book);
        borrowRecordRepository.save(record);
    }

    public void endBorrowRecord(Account account,Books book){
        LocalDateTime endTime= LocalDateTime.now();
        BorrowRecordId id=new BorrowRecordId(account.getId(),book.getId());
        BorrowRecord record=borrowRecordRepository.findById(id).get();
        record.setEndDate(endTime);
        book.setRemaining(book.getRemaining()+1);
        booksRepository.save(book);
        borrowRecordRepository.save(record);
    }
}
