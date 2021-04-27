package com.example.olio_ohjelmointi_harkkatyo_ugh;

import java.util.ArrayList;
import java.util.List;

//This holds the userlist and other

public class DataHolder {
    List<User> userlist = new ArrayList<>();
    User currentUser;

    private DataHolder(){}

    static DataHolder getInstance(){
        if(instance == null){
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}
