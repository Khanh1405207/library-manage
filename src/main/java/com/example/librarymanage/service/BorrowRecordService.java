package com.example.librarymanage.service;

import com.example.librarymanage.DTO.BorrowRecordDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Books;
import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.model.BorrowRecordId;
import com.example.librarymanage.repository.BooksRepository;
import com.example.librarymanage.repository.BorrowRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {

    @Autowired
    BorrowRecordRepository borrowRecordRepository;
    @Autowired
    BooksRepository booksRepository;

    public List<BorrowRecordDTO> getAll(){
        return borrowRecordRepository.findAll().stream()
                .map(BorrowRecordDTO::new)
                .collect(Collectors.toList());
    }

    public BorrowRecordDTO getOne(BorrowRecordId id){
        BorrowRecord borrowRecord=borrowRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ban ghi muon khong ton tai"));
        return new BorrowRecordDTO(borrowRecord);
    }

    public List<BorrowRecordDTO> getByAccountId(Integer accountId){
        return borrowRecordRepository.findBorrowRecordsByAccount_Id(accountId).stream()
                .map(BorrowRecordDTO::new)
                .collect(Collectors.toList());
    }

    public BorrowRecordDTO getByRecordId(Integer accountId,Integer bookId){
        BorrowRecord borrowRecord=borrowRecordRepository.findBorrowRecordByAccount_IdAndBook_Id(accountId,bookId);
        if (borrowRecord == null){
            throw new EntityNotFoundException("Ban ghi muon khong ton tai");
        }
        return new BorrowRecordDTO(borrowRecord);
    }

    public Boolean existRecord(Integer accountId, Integer bookId){
        return borrowRecordRepository.existsByAccount_IdAndBook_IdAndEndDateIsNull(accountId,bookId);
    }

    public BorrowRecord startBorrowRecord(Account account,Books book){
        if (existRecord(account.getId(),book.getId())){
            throw new DuplicateKeyException("Ban ghi muon da ton tai");
        }
        LocalDateTime startTime= LocalDateTime.now();
        BorrowRecord record=new BorrowRecord(account,book,startTime,null);
        book.setRemaining(book.getRemaining()-1);
        booksRepository.save(book);
        return borrowRecordRepository.save(record);
    }

    public BorrowRecord endBorrowRecord(Account account,Books book){
        LocalDateTime endTime= LocalDateTime.now();
        BorrowRecordId id=new BorrowRecordId(account.getId(),book.getId());
        BorrowRecord record=borrowRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ban ghi muon khong ton tai"));
        record.setEndDate(endTime);
        book.setRemaining(book.getRemaining()+1);
        booksRepository.save(book);
        return borrowRecordRepository.save(record);
    }
}
