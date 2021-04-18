package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText textInputEmail, textInputUsername, textInputPassword, textInputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEmail = (EditText)findViewById(R.id.inputEmail);
        textInputUsername = (EditText)findViewById(R.id.inputUsername);
        textInputPassword = (EditText)findViewById(R.id.inputPassword);
        textInputConfirmPassword = (EditText)findViewById(R.id.inputConfirmPassword);
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
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {     //Checks if the email address follows the guidelines / e-mail pattern
            textInputEmail.setError("Enter a valid email address");
            return false;
        } else {                                                                //Valid email, remove errors
            textInputEmail.setError(null);
            return true;
        }

        //THIS SHOULD CHECK (FROM A DATABASE) IF THE EMAIL ADDRESS IS ALREADY USED FOR SOME OTHER ACCOUNT

    }

    private boolean validatePassword(){                                         //Checks that password fulfills the requirements
        String passwordInput = textInputPassword.getText().toString().trim();
        String passwordConfirmInput = textInputConfirmPassword.getText().toString().trim();

        if (passwordInput.isEmpty()){
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password doesn't fulfill the requirements");    //There could be more specific info on what is missing
            return false;
        } else if (passwordInput != passwordConfirmInput){
            textInputConfirmPassword.setError("Passwords don't match");
            return false;
        } else {
            textInputConfirmPassword.setError(null);
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v){
        if (!validateEmail() | validatePassword()){
            return;
        }

    }

}