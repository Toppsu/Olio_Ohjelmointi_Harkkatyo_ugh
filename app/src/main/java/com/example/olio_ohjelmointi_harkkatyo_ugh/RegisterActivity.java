package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText textInputEmail;
    EditText textInputUsername;
    EditText textInputPassword;
    EditText textInputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEmail = findViewById(R.id.inputEmail);
        textInputUsername = findViewById(R.id.inputUsername);
        textInputPassword = findViewById(R.id.inputPassword);
        textInputConfirmPassword = findViewById(R.id.inputConfirmPassword);
    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{12,}" +               //at least 12 characters
                    "$");

    private boolean validateEmail(){                                            //Checks the email address
        String emailInput = textInputEmail.getText().toString();

        if (emailInput.isEmpty()) {                                             //Checks if the field is empty
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {     //Checks if the email address follows the guidelines
            textInputEmail.setError("Enter a valid email address");
            return false;
        } else {                                                                //Valid email, remove errors
            textInputEmail.setError(null);
            return true;
        }
    }

}