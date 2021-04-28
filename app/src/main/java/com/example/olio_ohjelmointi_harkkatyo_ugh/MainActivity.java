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
    private Button reeninappula;
    private Button paaruutuun;

    private TextInputLayout usernameInput;
    private TextInputLayout passwordInput;
    MealHistory mealhistory = new MealHistory();
    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
    PasswordChecker passwordChecker = new PasswordChecker();

    Context context = null;

    boolean isValid = false;
    DataHolder dataHolder = DataHolder.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        databaseHelper.readJSON("userlist.json");

        usernameInput = (TextInputLayout) findViewById(R.id.username);
        passwordInput = (TextInputLayout) findViewById(R.id.password);
        register = findViewById(R.id.signButton);
        login = findViewById(R.id.loginButton);
    }

    private void openMainScreenView() {
        Intent intent = new Intent(this,MainScreenView.class);
        startActivity(intent);
    }


    public void openWorkoutActivity(){
        Intent intent = new Intent(this,WorkoutActivity.class);
        startActivity(intent);
    }


    public void openRegisterActivity(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openMealActivity(View v){
        Intent intent = new Intent(this, Meal_mainscreen.class);
        startActivity(intent);
    }


    //Logs in the user. Checks if there is a corresponding user for the given password and username.
    //If there is, set user as currentUser and move them to MainScreenView
    public void logIn(View v){
        String username = usernameInput.getEditText().getText().toString();
        String password = passwordInput.getEditText().getText().toString();

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
            //Search if there is a user with given username
            User user = databaseHelper.findUser(username);

            //Check if the password is correct
            if (user != null){
                byte[] salt = user.getSalt();
                String securePassword = passwordChecker.getSecurePassword(password, salt);

                //Log in the user and load any files needed
                if (securePassword.matches(user.getPassword())){
                    System.out.println("Logged in as "+user.getUsername());
                    dataHolder.currentUser = user;
                    dataHolder.MealHistoryArray = mealhistory.GetMealHistory(getFilesDir().toString()); //Reading the Meals.json and putting it in to dataholder so we only read the json once per app opening
                    Intent intent = new Intent(this, MainScreenView.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, /*R.string.invalid_credentials */ "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, /*R.string.invalid_credentials */ "Invalid credentials", Toast.LENGTH_LONG).show();
            }
        }
    }
}