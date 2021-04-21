package com.example.olio_ohjelmointi_harkkatyo_ugh;

import java.io.Serializable;

public class User implements Serializable {
    private String username, email, password;
    private byte[] salt;

    public User(String username, String email, String password, byte[] salt){
        this.username = username;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getUsername(){
        return username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public byte[] getSalt(){
        return salt;
    }
}
