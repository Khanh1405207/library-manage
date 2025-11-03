package com.example.librarymanage.controller;

import com.example.librarymanage.DTO.AccountDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getListAccount(){
        List<AccountDTO> accountDTOS= accountService.getAll();
        return ResponseEntity.ok(accountDTOS);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Integer id){
        AccountDTO accountDTO=accountService.getOne(id);
        return ResponseEntity.ok(accountDTO);
    }
}
