package com.example.olio_ohjelmointi_harkkatyo_ugh;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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


    public boolean addUser(User user){
        String filename = user.getUsername() + ".json";
        System.out.println(filename);
        Gson gson = new Gson();
        String s = gson.toJson(user);
        try{
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            ows.write(s);
            ows.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));
            return false;

        } catch (IOException e) {
            Log.e("IOException", String.valueOf(R.string.IOException));
            return false;
        }

    }

    //TODO paremman tiedon tallennuksen kokeilua
    /*

    public boolean addUser2(User user){
        String filename = "users.json";
        System.out.println(filename);
        Gson gson = new Gson();
        String s = gson.toJson(user);
        String json = getJSON(filename);
        try{
            JSONArray jsonArray = new JSONArray(json);



            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
            ows.write(s);
            ows.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));
            return false;

        } catch (IOException e) {
            Log.e("IOException", String.valueOf(R.string.IOException));
            return false;
        }

    }

    public User findUser2(String username) {
        User user = null;
        System.out.println("Etsitään käyttäjää");

        Gson gson = new Gson();
        String json = getJSON("users.json");

        if (json != null) {
            try {
                String [] users = json.split("}");
                for(String u:users){
                    System.out.println(u);
                }


                JSONObject jsonObject = new JSONObject(json);
                user = gson.fromJson(json, User.class);
                if (user.getUsername().equals(username)) {
                    user = gson.fromJson(json, User.class);

                    System.out.println(user);
                    //TODO create new User
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(user);
        return user;
    }



     */

    public User findUser(String username) {
        User user = null;
        System.out.println("Etsitään käyttäjää");

        Gson gson = new Gson();
        String json = getJSON(username + ".json");

        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                user = gson.fromJson(json, User.class);
                if (user.getUsername().equals(username)) {
                    user = gson.fromJson(json, User.class);

                    System.out.println(user);
                    //TODO create new User
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(user);
        return user;
    }

}
