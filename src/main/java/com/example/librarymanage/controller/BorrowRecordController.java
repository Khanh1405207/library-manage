package com.example.librarymanage.controller;

import com.example.librarymanage.DTO.BorrowRecordDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Books;
import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.service.AccountService;
import com.example.librarymanage.service.BooksService;
import com.example.librarymanage.service.BorrowRecordService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BorrowRecordController {

    @Autowired
    BorrowRecordService borrowRecordService;
    @Autowired
    AccountService accountService;
    @Autowired
    BooksService booksService;

    @GetMapping("/records")
    public ResponseEntity<List<BorrowRecordDTO>> getListRecord(){
        List<BorrowRecordDTO> recordDTOS = borrowRecordService.getAll();
        return ResponseEntity.ok(recordDTOS);
    }

    @GetMapping("/records-by-account")
    public ResponseEntity<List<BorrowRecordDTO>> getListRecordByAccount(@RequestParam("accountId") Integer id){
        List<BorrowRecordDTO> recordDTOS= borrowRecordService.getByAccountId(id);
        return ResponseEntity.ok(recordDTOS);
    }

    @GetMapping("/records-by-record")
    public ResponseEntity<?> getListRecordByRecord(
            @RequestParam("accountId") Integer accountId,
            @RequestParam("bookId") Integer bookId
    ){
        try {
            BorrowRecordDTO recordDTO= borrowRecordService.getByRecordId(accountId,bookId);
            return ResponseEntity.ok(recordDTO);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }

    @PostMapping("/records")
    public ResponseEntity<?> startRecords(
            @RequestParam("accountId") Integer accountId,
            @RequestParam("bookId") Integer bookId)
    {
        try {
            Account account= accountService.getOne(accountId);
            Books book=booksService.getOne(bookId);
            BorrowRecord created=borrowRecordService.startBorrowRecord(account,book);
            return ResponseEntity.ok(new BorrowRecordDTO(created));
        }catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }catch(EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }

    @PutMapping("/records")
    public ResponseEntity<?> endRecords(
            @RequestParam("accountId") Integer accountId,
            @RequestParam("bookId") Integer bookId)
    {
        try {
            Account account= accountService.getOne(accountId);
            Books book=booksService.getOne(bookId);
            BorrowRecord created=borrowRecordService.endBorrowRecord(account,book);
            return ResponseEntity.ok(new BorrowRecordDTO(created));
        }catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }catch(EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }
}
