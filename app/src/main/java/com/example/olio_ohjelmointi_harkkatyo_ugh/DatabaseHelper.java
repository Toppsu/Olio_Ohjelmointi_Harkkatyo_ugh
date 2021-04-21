package com.example.olio_ohjelmointi_harkkatyo_ugh;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class DatabaseHelper extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    DatabaseHelper(Context context){
        this.context = context;
    }

    public void readJSON(String file){
        String json = getJSON(file);
        System.out.println("JSON: "+json);
    }


    public String getJSON(String file){
        String response = null;
        try{
            InputStream ins = context.openFileInput(file);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line=br.readLine()) != null){
                sb.append(line).append("\n");
            }
            response = sb.toString();
            ins.close();

        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));

        } catch (IOException e) {
            Log.e("IOException", String.valueOf(R.string.IOException));
        }
        return response;
    }

    public boolean testi(){
        return true;
    }


    public boolean addUser(User user){
        System.out.println(super.toString());
        System.out.println("HELLO");

        String filename = "users.json";
        Gson gson = new Gson();
        String s = gson.toJson(user);
        FileOutputStream fos = null;
        try{
            fos = context.openFileOutput(filename, Context.MODE_APPEND);
            fos.write(s.getBytes());
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));
            return false;

        } catch (IOException e) {
            Log.e("IOException", String.valueOf(R.string.IOException));
            return false;
        }

    }

    public User findUser(String username){
        User user = null;

        Gson gson = new Gson();
        String json = getJSON("users.json");
        String name = "";

        if (json != null){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i =0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    name = jsonObject.getString("username");
                    System.out.println(username);
                    if (name.equals(username)){
                        user = gson.fromJson(json, User.class);

                        System.out.println(user);
                        //TODO create new User
                    }
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
        }


        return user;
    }

}
