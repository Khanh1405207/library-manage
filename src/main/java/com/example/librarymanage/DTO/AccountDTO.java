package com.example.librarymanage.DTO;

import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    public Integer id;
    public String username;
    public Role role;
    public String email;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.role = account.getRole();
        this.email = account.getEmail();
    }
}
