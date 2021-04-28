package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MealHistory {

    ArrayList<String> pastmeals = new ArrayList<>();
    String[] pastmealnames;

    Context context;

    DatabaseHelper dbhelp = new DatabaseHelper(this.context);

    public MealHistory() {

    }

    public ArrayList<String> GetMealHistory(String userpath) {

        String path = userpath;
        System.out.println(path);
        String userdir = path + "/" + DataHolder.getInstance().currentUser.getUsername() + "/Meals.json";
        System.out.println(userdir);
        String json = null;
        try {
            json = dbhelp.getJSON_wFileInputStreamer(userdir);
            System.out.println(json);
            String[] jsonsplit = json.split("(?<=])");
            for (String s : jsonsplit) {
                String singlemeal = "";
                JSONArray jarray = new JSONArray(s);
                JSONObject jobjectgetime = jarray.getJSONObject(0);
                String pvm = jobjectgetime.getString("pvm"); // In form "E dd-MM-yyyy HH:mm:ss" e.g "Tue 27-04-2021 14:41:15"
                singlemeal = singlemeal + "Meal: " + pvm + "\n\n";
                Double totKcal = 0.0;
                Double totCarbo = 0.0;
                Double totFat = 0.0;
                Double totProt = 0.0;
                for (int i = 0; i < jarray.length(); i++) {

                    JSONObject jobject = jarray.getJSONObject(i);

                    int kerroin = (jobject.getInt("FoodAmount") / 100);

                    totKcal = totKcal + jobject.getDouble("Kalorit") * kerroin;
                    totCarbo = totCarbo + jobject.getDouble("Carbohydrates") * kerroin;
                    totFat = totFat + jobject.getDouble("Protein") * kerroin;
                    totProt = totProt + jobject.getDouble("Fat") * kerroin;


                    String k = "Food: " + jobject.getString("Food") + "\t\t" + jobject.getInt("FoodAmount") + "g\n"
                            + "Kcal: " + Math.round(jobject.getDouble("Kalorit") * kerroin) + "\n"
                            + "Carbohydrates: " + Math.round(jobject.getDouble("Carbohydrates") * kerroin) + "g\t\t"
                            + "Protein: " + Math.round(jobject.getDouble("Protein") * kerroin) + "g\t\t"
                            + "Fat: " + Math.round(jobject.getDouble("Fat") * kerroin) + "g\n\n";
                    singlemeal = singlemeal + k;

                }
                String total = "\nTotal:\nKcal: " + Math.round(totKcal) + "\tCarbohydrates: "
                        + Math.round(totCarbo) + "g\tProtein: " + Math.round(totProt) + "g\tFat: " + Math.round(totFat) + "g\n";
                singlemeal = singlemeal + total;
                pastmeals.add(singlemeal);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pastmeals;
    }



    /* New meals added to the meals collection are in wrong form and must go trough this. */
    public ArrayList<String> SetArrayListforAdapter(String json) {
        try {
            String singlemeal = "";
            JSONArray jarray = new JSONArray(json);
            JSONObject jobjectgetime = jarray.getJSONObject(0);
            String pvm = jobjectgetime.getString("pvm"); // In form "E dd-MM-yyyy HH:mm:ss" e.g "Tue 27-04-2021 14:41:15"
            singlemeal = singlemeal + "Meal: " + pvm + "\n\n";
            Double totKcal = 0.0;
            Double totCarbo = 0.0;
            Double totFat = 0.0;
            Double totProt = 0.0;
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jobject = jarray.getJSONObject(i);

                int kerroin = (jobject.getInt("FoodAmount") / 100);

                totKcal = totKcal + jobject.getDouble("Kalorit") * kerroin;
                totCarbo = totCarbo + jobject.getDouble("Carbohydrates") * kerroin;
                totFat = totFat + jobject.getDouble("Protein") * kerroin;
                totProt = totProt + jobject.getDouble("Fat") * kerroin;


                String k = "Food: " + jobject.getString("Food") + "\t\t" + jobject.getInt("FoodAmount") + "g\n"
                        + "Kcal: " + Math.round(jobject.getDouble("Kalorit") * kerroin) + "\n"
                        + "Carbohydrates: " + Math.round(jobject.getDouble("Carbohydrates") * kerroin) + "g\t\t"
                        + "Protein: " + Math.round(jobject.getDouble("Protein") * kerroin) + "g\t\t"
                        + "Fat: " + Math.round(jobject.getDouble("Fat") * kerroin) + "g\n\n";
                singlemeal = singlemeal + k;

            }
            String total = "\nTotal:\nKcal: " + Math.round(totKcal) + "\tCarbohydrates: "
                    + Math.round(totCarbo) + "g\tProtein: " + Math.round(totProt) + "g\tFat: " + Math.round(totFat) + "g\n";
            singlemeal = singlemeal + total;
            pastmeals.add(singlemeal);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pastmeals;
    }
}
