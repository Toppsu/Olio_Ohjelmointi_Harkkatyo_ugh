package com.example.olio_ohjelmointi_harkkatyo_ugh;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MealActivity implements Serializable {
    String pvm = null;
    String Food = null;
    float Kalorit = 0;
    float Fat = 0;
    float Carbohydrates = 0;
    float Protein = 0;
    float FoodAmount = 0;

    public MealActivity(float carbo, float Kcal, float protein, float fat, String food, float amount) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("E dd-MM-yyyy HH:mm:ss");
        pvm = formatter.format(date);
        Carbohydrates = carbo; //TODO LISÄÄ RUOAN YKSIKKO (gramma/litra)
        Kalorit = Kcal;
        Protein = protein;
        Fat = fat;
        Food = food;
        FoodAmount = amount;
    }
}