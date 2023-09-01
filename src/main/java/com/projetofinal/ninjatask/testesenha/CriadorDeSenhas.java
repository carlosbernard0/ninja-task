package com.projetofinal.ninjatask.testesenha;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriadorDeSenhas {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

//        String senhaCriptografada = bCryptPasswordEncoder.encode("senha123");
//        System.out.println(senhaCriptografada);
        //$2a$10$iPMYfy.92BUxSyZ3DaKgT.aN/i.ybPW7H7b89txrOaGeEvs0n4p9W

        boolean senhaCorreta =bCryptPasswordEncoder.matches("senha123", "$2a$10$iPMYfy.92BUxSyZ3DaKgT.aN/i.ybPW7H7b89txrOaGeEvs0n4p9W");
        System.out.println(senhaCorreta);
    }
}
