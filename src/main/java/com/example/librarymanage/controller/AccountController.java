package com.example.librarymanage.controller;

import com.example.librarymanage.DTO.AccountDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Role;
import com.example.librarymanage.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<?> getAccount(@PathVariable("id") Integer id){
        try {
            Account account=accountService.getOne(id);
            return ResponseEntity.ok(new AccountDTO(account));
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }
    }

    @PutMapping("/accounts")
    public ResponseEntity<?> updateAccount(@RequestBody Account account){
        try {
            Account acc=accountService.getOne(account.getId());
            account.setRole(acc.getRole());
            account.setCreateDate(acc.getCreateDate());
            Account updated=accountService.updateAccount(account);
            return ResponseEntity.ok(new AccountDTO(updated));
        }catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }catch(EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }catch (ValidationException e){
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }catch (InvalidDataAccessApiUsageException e){
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }
    }
}
