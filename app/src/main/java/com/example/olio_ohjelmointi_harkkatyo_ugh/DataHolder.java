package com.example.olio_ohjelmointi_harkkatyo_ugh;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    List<User> userlist = new ArrayList<>();

    private DataHolder(){}

    static DataHolder getInstance(){
        if(instance == null){
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}
