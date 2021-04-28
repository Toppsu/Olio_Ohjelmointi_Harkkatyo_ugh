package com.example.olio_ohjelmointi_harkkatyo_ugh;

import java.util.ArrayList;
import java.util.List;

//This holds the userlist and other stuff

public class DataHolder {

    //Put items to hold here
    List<User> userlist = new ArrayList<>();
    User currentUser;

    ArrayList<String> MealHistoryArray = new ArrayList<>();
    String mealjson = null;


    private DataHolder(){}

    static DataHolder getInstance(){
        if(instance == null){
            instance = new DataHolder();
        }
        return instance;
    }
    private static DataHolder instance;
}
