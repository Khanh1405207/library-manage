package com.example.librarymanage.controller;

import com.example.librarymanage.DTO.AccountDTO;
import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Role;
import com.example.librarymanage.service.AccountService;
import com.example.librarymanage.utility.AuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    AccountService accountService;
    @Autowired
    AuthService authService;
    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        try{
            String username= account.getUsername();
            String password= account.getPassword();
            Account user= accountService.login(username,password);
            if (user != null){
                session.setAttribute("userId",user.getId());
                session.setAttribute("username",user.getUsername());
                session.setAttribute("role",user.getRole());
                return ResponseEntity.ok("Dang nhap thanh cong");
            }else {
                return ResponseEntity.badRequest().body("Tai khoan/mat khau khong chinh xac");
            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }catch (ValidationException e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }

    @GetMapping("/get-info")
    public ResponseEntity<?> getInfo(HttpSession ss){
        if (ss.getAttribute("username") == null){
            return ResponseEntity.badRequest().body("Chua dang nhap");
        }
        Map<String,Object> response= new HashMap<>();
        response.put("id",ss.getAttribute("id"));
        response.put("username",ss.getAttribute("username"));
        response.put("role",ss.getAttribute("role"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registry")
    public ResponseEntity<?> addAccount(@RequestBody Account account){
        try {
            account.setRole(Role.USER);
            account.setCreateDate(LocalDateTime.now());
            Account created= accountService.addAccount(account);
            return ResponseEntity.ok(new AccountDTO(created));
        }catch (DuplicateKeyException e){
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }catch (ValidationException e){
            return ResponseEntity.badRequest().body("Error :"+ e.getMessage());
        }
    }
}
