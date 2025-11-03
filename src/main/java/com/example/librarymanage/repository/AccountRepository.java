package com.example.librarymanage.repository;

import com.example.librarymanage.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
    public Boolean existsByUsernameAndIdNot(String username, Integer id);
    public Boolean existsByEmailAndIdNot(String email,Integer id);
}
