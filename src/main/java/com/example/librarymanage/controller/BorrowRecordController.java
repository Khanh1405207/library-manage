package com.example.librarymanage.controller;

import com.example.librarymanage.DTO.BorrowRecordDTO;
import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BorrowRecordController {

    @Autowired
    BorrowRecordService borrowRecordService;

    @GetMapping("/borrow-records")
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
    public ResponseEntity<BorrowRecordDTO> getListRecordByRecord(
            @RequestParam("accountId") Integer accountid,
            @RequestParam("bookId") Integer bookid
    ){
        BorrowRecordDTO recordDTO= borrowRecordService.getByRecordId(accountid,bookid);
        return ResponseEntity.ok(recordDTO);
    }
}
