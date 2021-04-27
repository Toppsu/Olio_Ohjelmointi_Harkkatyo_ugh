package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        email = (TextView) findViewById(R.id.emailShow);
        heigth = (EditText) findViewById(R.id.editHeigth);
        weigth = (EditText) findViewById(R.id.editWeigth);
        yearOfBirth = (EditText) findViewById(R.id.editYearOfBirth);

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



    public void saveChanges(View v){
        //Read input data
        dataHolder.currentUser.setEmail(email.getText().toString());
        //TODO check the validity of the email adress
        dataHolder.currentUser.setHeigth(Integer.parseInt(heigth.getText().toString()));
        dataHolder.currentUser.setWeigth(Integer.parseInt(weigth.getText().toString()));
        dataHolder.currentUser.setYearOfBirth(Integer.parseInt(yearOfBirth.getText().toString()));

        if(databaseHelper.updateUser(dataHolder.currentUser)) {
            Toast.makeText(this, "Save successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Save FAILED", Toast.LENGTH_LONG).show();
        }

    }

}