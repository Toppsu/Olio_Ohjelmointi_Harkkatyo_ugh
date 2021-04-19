package com.example.olio_ohjelmointi_harkkatyo_ugh;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

public class PasswordChecker {


    // Password requirements
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{12,}" +               //at least 12 characters
                    "$");


    //Checks "password" and "confirm password" are identical
    public boolean checkPassword(String password, String confirmPassword){
        if(password.equals(confirmPassword)){
            return true;
        }else{
            return false;
        }
    }


    //Checks if password fulfills the requirements
    public boolean validatePassword(String password){
        if(PASSWORD_PATTERN.matcher(password).matches()){
            return true;
        } else {
            return false;
        }
    }

    public static String getSecurePassword(String password, byte[] salt){
        String securePassword = null;

        //TODO Hash the password

        return securePassword;
    }

    //Add salt
    public byte[] newSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
