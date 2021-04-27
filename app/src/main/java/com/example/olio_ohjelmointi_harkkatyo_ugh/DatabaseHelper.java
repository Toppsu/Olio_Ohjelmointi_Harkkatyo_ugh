package com.example.olio_ohjelmointi_harkkatyo_ugh;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends AppCompatActivity {

    Context context;
    DataHolder dataHolder = DataHolder.getInstance();

    Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
    DatabaseHelper(Context context){
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void readJSON(String file){
        String json = getJSON(file);
        Gson gson = new Gson();

        if (json != null) {
            dataHolder.userlist = gson.fromJson(json, userListType);
        } else {

        }
    }


    //Returns a JSON file as a string
    public String getJSON(String file){
        String json = null;
        try{
            InputStream ins = context.openFileInput(file);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line=br.readLine()) != null){
                sb.append(line).append("\n");
            }
            json = sb.toString();
            ins.close();

        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));

        } catch (IOException e) {
            Log.e("IOException", String.valueOf(R.string.IOException));
        }
        return json;
    }

    //Returns a JSON file as a string using fileinputstreamer to get around a thing where you can't access some folders with inputstream. (Used in List_past_meals)
    public String getJSON_wFileInputStreamer(String file){
        String json = null;
        try{
            FileInputStream ins = new FileInputStream(new File(file));

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line=br.readLine()) != null){
                sb.append(line).append("\n");
            }
            json = sb.toString();
            ins.close();

        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));

        } catch (IOException e) {
            Log.e("IOException", String.valueOf(R.string.IOException));
        }
        return json;
    }


    public boolean addUser(User user){

        //Adds user to the userArray
        dataHolder.userlist.add(user);
        String filename = "userlist.json";

        //Adds user to the userlist.json
        Gson gson = new Gson();
        String s = gson.toJson(dataHolder.userlist);
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

    public User findUser(String username) {
        User u = null;

        for (User user : dataHolder.userlist){
            if (user.getUsername().matches(username)){
                u = user;
            }
        }
        return u;
    }

    //Check if username is already in use
    public boolean findUserName(String username) {
        boolean user_found = false;

        for (User user : dataHolder.userlist){
            if (user.getUsername().matches(username)){
                user_found = true;
            }
        }

        return user_found;
    }


    public boolean findUserEmail(String email) {
        boolean user_found = false;

        for (User user : dataHolder.userlist){
            if (user.getEmail().matches(email)){
                user_found = true;
            }
        }
        return user_found;
    }


    public boolean updateUser(User user){
        List<User> temp = new ArrayList<User>();

        //Copy current userlist and replace current user's data
        for (User u : dataHolder.userlist){
            if (u.getUsername().matches(user.getUsername())){
                //Skip
            }else{
                temp.add(u);
            }
        }
        temp.add(user);

        dataHolder.userlist = temp;

        //Rewrite userlist.json
        String filename = "userlist.json";
        Gson gson = new Gson();
        String s = gson.toJson(temp);
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
}
