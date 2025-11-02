package com.example.librarymanage.controller;

import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BorrowRecordController {

    @Autowired
    BorrowRecordService borrowRecordService;

    @GetMapping("/borrow-records")
    public ResponseEntity<List<BorrowRecord>> getListRecord(){
        List<BorrowRecord> records = borrowRecordService.getAll();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/records-by-account")
    public ResponseEntity<List<BorrowRecord>> getListRecordByAccount(@RequestParam("accountId") Integer id){
        List<BorrowRecord> records= borrowRecordService.getByAccountId(id);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/records-by-record")
    public ResponseEntity<BorrowRecord> getListRecordByRecord(
            @RequestParam("accountId") Integer accountid,
            @RequestParam("bookId") Integer bookid
    ){
        BorrowRecord records= borrowRecordService.getByRecordId(accountid,bookid);
        return ResponseEntity.ok(records);
    }
}
