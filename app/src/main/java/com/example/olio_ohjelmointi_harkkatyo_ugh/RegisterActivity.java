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
            String pw = textInputPassword.getEditText().getText().toString();
            String securePassword = PasswordChecker.getSecurePassword(pw, salt);

            String username = textInputUsername.getEditText().getText().toString();
            String email = textInputEmail.getEditText().getText().toString();

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
        String emailInput = textInputEmail.getEditText().getText().toString();

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
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String passwordConfirmInput = textInputConfirmPassword.getEditText().getText().toString().trim();

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
        String usernameInput = textInputUsername.getEditText().getText().toString();
        boolean isValid = false;

        //Checks if the username is already taken
        if(databaseHelper.findUser(usernameInput)== null){
            //Checks if the username fulfills the requirements
            if (USERNAME_PATTERN.matcher(usernameInput).matches()) {
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