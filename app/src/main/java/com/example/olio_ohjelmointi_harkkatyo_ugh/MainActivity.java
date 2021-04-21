package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button register;
    private Button login;
    private TextInputLayout usernameInput;
    private TextInputLayout passwordInput;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    PasswordChecker passwordChecker = new PasswordChecker();

    Context context = null;

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        System.out.println("HELLO "+context);

        usernameInput = (TextInputLayout) findViewById(R.id.username);
        passwordInput = (TextInputLayout) findViewById(R.id.password);
        register = findViewById(R.id.signButton);
        login = findViewById(R.id.loginButton);

        Context context = MainActivity.this;
        System.out.println(context.getFilesDir());
        System.out.println("HELLO");
    }

    public void openRegisterActivity(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openMealActivity(View v){
        Intent intent = new Intent(this, MealConstructor.class);
        startActivity(intent);
    }

    public void logIn(View v){
        String username = usernameInput.getEditText().getText().toString();
        String password = passwordInput.getEditText().getText().toString();

        //TODO tämä tulostaa konsoliin
        System.out.println("User: "+username+"Pw: "+password);

        //Check if username of password -fields are empty
        if(username.isEmpty() || password.isEmpty()) {

            if (username.isEmpty()) {
                usernameInput.setError("Field can't be empty");
            } else {
                usernameInput.setError(null);
            }

            if (password.isEmpty()) {
                passwordInput.setError("Field can't be empty");
            } else {
                passwordInput.setError(null);
            }



        } else {
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
            //findUser
            //Validate user inputs
        }

    }
}