package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfo extends AppCompatActivity {

    private TextView email;
    private EditText heigth;
    private EditText weigth;
    private EditText yearOfBirth;
    DataHolder dataHolder = DataHolder.getInstance();
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private Button exitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        email = (TextView) findViewById(R.id.emailShow);
        heigth = (EditText) findViewById(R.id.editHeigth);
        weigth = (EditText) findViewById(R.id.editWeigth);
        yearOfBirth = (EditText) findViewById(R.id.editYearOfBirth);
        exitButton = (Button) findViewById(R.id.exitButton);

        email.setText(dataHolder.currentUser.getEmail());
        int h = dataHolder.currentUser.getHeigth();
        int b = dataHolder.currentUser.getYearOfBirth();
        int w = dataHolder.currentUser.getWeigth();

        if(h != 0){heigth.setHint(Integer.toString(h));}
        if(w != 0){weigth.setHint(Integer.toString(w));}
        if(b != 0){yearOfBirth.setHint(Integer.toString(b));}

    }


    public void resetPassword(View v){
        //TODO pw reset
    }


    //Save changes to the user info (height etc.)
    public void saveChanges(View v){
        String h = heigth.getText().toString();
        String w = weigth.getText().toString();
        String y = yearOfBirth.getText().toString();

        if (!h.isEmpty()) {dataHolder.currentUser.setHeigth(Integer.parseInt(h));}

        if (!w.isEmpty()) {dataHolder.currentUser.setWeigth(Integer.parseInt(w));}

        if (!y.isEmpty()) {dataHolder.currentUser.setYearOfBirth(Integer.parseInt(y));}

        if(databaseHelper.updateUser(dataHolder.currentUser)) {
            Toast.makeText(this, "Save successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Save FAILED", Toast.LENGTH_LONG).show();
        }

    }

    //Return back to MainScreenView
    public void exit(View v){
        Intent intent = new Intent(this, MainScreenView.class);
        startActivity(intent);
    }

}