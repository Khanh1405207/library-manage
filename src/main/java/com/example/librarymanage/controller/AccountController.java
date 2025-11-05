package com.example.librarymanage.controller;

import com.example.librarymanage.DTO.AccountDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Role;
import com.example.librarymanage.service.AccountService;
import com.example.librarymanage.utility.AuthService;
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
    @Autowired
    AuthService authService;

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
            acc.setUsername(account.getUsername());
            acc.setEmail(account.getEmail());
            Account updated=accountService.updateAccount(acc);
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

    @PutMapping("/account-change-pass")
    public ResponseEntity<?> changePass(@RequestParam("oldPass") String oldPass,@RequestBody Account account){
        try{
            Account acc=accountService.getOne(account.getId());
            String cp=acc.getPassword();
            if (authService.check(oldPass,cp)){
                String newPass=authService.hashPass(account.getPassword());
                acc.setPassword(newPass);
                accountService.updateAccount(acc);
                return ResponseEntity.ok(new AccountDTO(acc));
            }else {
                return ResponseEntity.badRequest().body("Mat khau khong chinh xac");
            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }
}
