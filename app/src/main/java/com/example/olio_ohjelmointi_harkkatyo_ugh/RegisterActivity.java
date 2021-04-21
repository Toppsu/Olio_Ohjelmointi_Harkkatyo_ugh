package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText textInputEmail;
    EditText textInputUsername;
    EditText textInputPassword;
    EditText textInputConfirmPassword;
    PasswordChecker passwordChecker;

    DatabaseHelper databaseHelper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;

        textInputEmail = (EditText) findViewById(R.id.inputEmail);
        textInputUsername = (EditText) findViewById(R.id.inputUsername);
        textInputPassword = (EditText) findViewById(R.id.inputPassword);
        textInputConfirmPassword = (EditText) findViewById(R.id.inputConfirmPassword);

        passwordChecker = new PasswordChecker();
        databaseHelper = new DatabaseHelper(this);
    }

    public void register(View v){

        if (validateEmail() & validatePassword() & validateUsername()){

            //Generate salt for the user
            byte[] salt = new byte[0];
            try {
                salt = passwordChecker.newSalt();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }

            //Hash and salt the password
            String pw = textInputPassword.getText().toString();
            String securePassword = PasswordChecker.getSecurePassword(pw, salt);


            String username = textInputUsername.getText().toString();
            String email = textInputEmail.getText().toString();

            //Generate new user
            User newUser = new User (username, email, securePassword, salt);
            System.out.println(newUser.getEmail());
            System.out.println(Arrays.toString(newUser.getSalt()));
            System.out.println(newUser.getPassword());
            System.out.println(newUser.getUsername());

            //Save the new user
            boolean createUser = databaseHelper.addUser(newUser);

            if (createUser){
                System.out.println("User created");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT);}
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
        } else if (databaseHelper.findUser(emailInput) != null){                //Checks if the email address is already in use
            textInputEmail.setError("E-mail address already in use");
            return false;
        }else {                                                                //Valid email, remove errors
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
        //TODO Check if the username is suitable [a-z, A-Z, 0-9]

        return true;
    }

}