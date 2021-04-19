package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText textInputEmail;
    EditText textInputUsername;
    EditText textInputPassword;
    EditText textInputConfirmPassword;
    PasswordChecker passwordChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEmail = (EditText) findViewById(R.id.inputEmail);
        textInputUsername = (EditText) findViewById(R.id.inputUsername);
        textInputPassword = (EditText) findViewById(R.id.inputPassword);
        textInputConfirmPassword = (EditText) findViewById(R.id.inputConfirmPassword);

        passwordChecker = new PasswordChecker();
    }


    public void register(View v){


        if (validateEmail() & validatePassword() & validateUsername()){

            byte[] salt = new byte[0];
            try {
                salt = passwordChecker.newSalt();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
            String pw = textInputPassword.getText().toString();
            String securePassword = PasswordChecker.getSecurePassword(pw, salt);
            String username = textInputUsername.getText().toString();
            String email = textInputEmail.getText().toString();

            User newUser = new User (username, email, securePassword, salt);

            //TODO create new user
            //TODO message that registration was complete
            //TODO move to login page?

        }
    }

        //Checks if the e-mail address is OK
    private boolean validateEmail(){
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
        //TODO THIS SHOULD CHECK (FROM A DATABASE) IF THE EMAIL ADDRESS IS ALREADY USED FOR SOME OTHER ACCOUNT
    }


        //Checks if the password is OK
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();
        String passwordConfirmInput = textInputConfirmPassword.getText().toString().trim();

        //Password passes the check
        if (passwordChecker.validatePassword(passwordInput) & passwordChecker.checkPassword(passwordInput, passwordConfirmInput)) {
            textInputConfirmPassword.setError(null);
            textInputPassword.setError(null);
            return true;

        // Error messages for invalid passwords
        } else {
            if (passwordInput.isEmpty()) {
                textInputPassword.setError("Field can't be empty");
                return false;
            }

            if (!passwordChecker.checkPassword(passwordInput, passwordConfirmInput)) {
                textInputConfirmPassword.setError("Passwords don't match");
                return false;
            } else {
                textInputPassword.setError(getString(R.string.password_requirements));
                return false;
            }
        }
    }

    private boolean validateUsername(){
        String usernameInput = textInputUsername.getText().toString();

        //TODO Check if the username is already in use

        return true;
    }

}