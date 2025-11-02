package com.example.librarymanage.utility;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public static String hashPass(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // 12 = do kho
    }

    public static boolean check(String plainPass, String hashPass){
        return BCrypt.checkpw(plainPass,hashPass);
    }
}
