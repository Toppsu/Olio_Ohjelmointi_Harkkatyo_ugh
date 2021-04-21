package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    PasswordChecker passwordChecker = new PasswordChecker();

    public void login(String username, String password){

        User user = databaseHelper.findUser(username);

        if (user != null){
            byte[] salt = user.getSalt();
            String securePassword = passwordChecker.getSecurePassword(password, salt);
            if (securePassword.matches(user.getPassword())){
                System.out.println("Logged in as "+user.getUsername());
                Intent intent = new Intent(this, MainScreenView.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, R.string.invalid_credentials, Toast.LENGTH_SHORT);
        }



    }
}