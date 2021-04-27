package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout textInputEmail;
    TextInputLayout textInputUsername;
    TextInputLayout textInputPassword;
    TextInputLayout textInputConfirmPassword;
    PasswordChecker passwordChecker;

    DatabaseHelper databaseHelper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;

        textInputEmail = (TextInputLayout) findViewById(R.id.text_input_email);

        textInputUsername = (TextInputLayout) findViewById(R.id.text_input_username);
        textInputPassword = (TextInputLayout) findViewById(R.id.text_input_password);
        textInputConfirmPassword = (TextInputLayout) findViewById(R.id.text_input_confirm_password);

        passwordChecker = new PasswordChecker();
        databaseHelper = new DatabaseHelper(this);
    }

    public void register(View v){

        String password = textInputPassword.getEditText().getText().toString().trim();
        String confirmPassword = textInputConfirmPassword.getEditText().getText().toString().trim();
        String username = textInputUsername.getEditText().getText().toString();
        String email = textInputEmail.getEditText().getText().toString();

        if (validateEmail(email) & validatePassword(password, confirmPassword) & validateUsername(username)){

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
            String securePassword = PasswordChecker.getSecurePassword(password, salt);

            //Generate and save new user
            User newUser = new User (username, email, securePassword, salt);
            boolean createUser = databaseHelper.addUser(newUser);

            File path = context.getFilesDir();
            File f1 = new File(path+"/"+username);


            if (createUser && f1.mkdir()){
                System.out.println("User created");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT);}
        }
    }

        //Checks if the e-mail address is OK
    private boolean validateEmail(String email){
        boolean isValid = false;

        if (email.isEmpty()) {                                             //Checks if the field is empty
            textInputEmail.setError("Field can't be empty");

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {     //Checks if the email address follows the guidelines / e-mail pattern
            textInputEmail.setError("Enter a valid email address");

        } else if (databaseHelper.findUserEmail(email) == true){           //Checks if the email address is already in use
            textInputEmail.setError("E-mail address already in use");

        }else {                                                            //Valid email, remove errors
            textInputEmail.setError(null);
            isValid = true;
        }

        return isValid;
    }


        //Checks if the password is OK
    private boolean validatePassword(String password, String confirmPassword) {

        //Password passes the check
        if (passwordChecker.validatePassword(password) & passwordChecker.checkPassword(password, confirmPassword)) {
            textInputConfirmPassword.setError(null);
            textInputPassword.setError(null);
            return true;

        // Error messages for invalid passwords
        } else {
            if (password.isEmpty()) {
                textInputPassword.setError("Field can't be empty");
                return false;
            }

            if (!passwordChecker.checkPassword(password, confirmPassword)) {
                textInputConfirmPassword.setError("Passwords don't match");
                return false;
            } else {
                textInputPassword.setError(getString(R.string.password_requirements));
                return false;
            }
        }
    }

    private boolean validateUsername(String username){
        boolean isValid = false;

        //Checks if the username is already taken
        if(databaseHelper.findUserName(username)== false){
            //Checks if the username fulfills the requirements
            if (USERNAME_PATTERN.matcher(username).matches()) {
                textInputUsername.setError(null);
                isValid = true;
            } else{
                Toast.makeText(this, R.string.username_requirements, Toast.LENGTH_LONG).show();
                textInputUsername.setError(getString(R.string.username_requirements));
            }
        } else {
            Toast.makeText(this, "That username is already taken", Toast.LENGTH_LONG).show();
            textInputUsername.setError("That username is already taken");
        }
        return isValid;
    }

    //Username requirements
    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^" +
                    "([0-9]*)" +             //may contain digits
                    "([a-z]*)" +             //may contain lower case letters
                    "([A-Z]*)" +             //may contain upper case letters
                    "([_-]*)" +              //may contain '_' or '-'
                    "(?=\\S+$)" +           //no white spaces
                    ".{3,}" +               //at least 3 characters
                    "$");

}