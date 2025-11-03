package com.example.librarymanage.service;

import com.example.librarymanage.DTO.AccountDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.repository.AccountRepository;
import com.example.librarymanage.utility.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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

    public AccountDTO getOne(Integer id){
        Account account=accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account khong ton tai: "+ id));
        return new AccountDTO(account);
    }

    public void addAccount(Account account){
        account.setPassword(authService.hashPass(account.getPassword()));
        accountRepository.save(account);
    }

    public void updateAccount(Account account){
        accountRepository.save(account);
    }
}
