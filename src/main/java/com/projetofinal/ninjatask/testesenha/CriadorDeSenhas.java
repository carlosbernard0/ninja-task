package com.projetofinal.ninjatask.testesenha;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriadorDeSenhas {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

//        String senhaCriptografada = bCryptPasswordEncoder.encode("senha123");
//        System.out.println(senhaCriptografada);
        //$2a$10$z2vQCWrdJkwRwk9Shz0O7eatjMdw.PxsSRrgE9ONMVfJTW8XrSDum SENHA DO CARLOS


        boolean senhaCorreta =bCryptPasswordEncoder.matches("senha123", "$2a$10$z2vQCWrdJkwRwk9Shz0O7eatjMdw.PxsSRrgE9ONMVfJTW8XrSDum");
            System.out.println(senhaCorreta);
    }

}   
