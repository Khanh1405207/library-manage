package com.example.librarymanage.service;

import com.example.librarymanage.DTO.AccountDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.repository.AccountRepository;
import com.example.librarymanage.utility.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AuthService authService;

    public List<AccountDTO> getAll(){
        return accountRepository.findAll().stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    public Account getOne(Integer id){
        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account khong ton tai: "+ id));
        return account;
    }

    public Account addAccount(Account account){
        if (accountRepository.existsByUsername(account.getUsername())){
            throw new DuplicateKeyException("Username da ton tai");
        }
        if (accountRepository.existsByEmail(account.getEmail())){
            throw new DuplicateKeyException("Email da ton tai");
        }
        account.setPassword(authService.hashPass(account.getPassword()));
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account){
        accountRepository.findById(account.getId())
                .orElseThrow(() -> new EntityNotFoundException("Tai khoan khong ton tai"));
        if (accountRepository.existsByUsernameAndIdNot(account.getUsername(),account.getId())){
            throw new DuplicateKeyException("Username da ton tai");
        }
        if (accountRepository.existsByEmailAndIdNot(account.getEmail(),account.getId())){
            throw new DuplicateKeyException("Email da ton tai");
        }
        account.setPassword(authService.hashPass(account.getPassword()));
        return accountRepository.save(account);
    }
}
