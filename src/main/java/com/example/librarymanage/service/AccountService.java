package com.example.librarymanage.service;

import com.example.librarymanage.model.Account;
import com.example.librarymanage.repository.AccountRepository;
import com.example.librarymanage.utility.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AuthService authService;

    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    public Account getOne(Integer id){
        return accountRepository.findById(id).get();
    }

    public void addAccount(Account account){
        account.setPassword(authService.hashPass(account.getPassword()));
        accountRepository.save(account);
    }

    public void updateAccount(Account account){
        accountRepository.save(account);
    }
}
