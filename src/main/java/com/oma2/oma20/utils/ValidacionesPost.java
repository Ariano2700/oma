package com.oma2.oma20.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidacionesPost {
    public boolean isValidPassword(String password) {
        // Validar la contraseña: al menos 8 caracteres, al menos un número y un carácter especial
        String regex = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean isValidEmail(String email) {
        // Validar el correo electrónico
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isValidDNI (int dni){
        String dniString = String.valueOf(dni);
        String regex = "^\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dniString);
        return matcher.matches();
    }
}
