package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MealHistory {

    ArrayList<String> pastmeals = new ArrayList<>();
    String[] pastmealnames;

    Context context;

    DatabaseHelper dbhelp = new DatabaseHelper(this.context);

    DataHolder dataHolder = DataHolder.getInstance();

    public MealHistory() {

    }

    /* This fetches the Meals.json from the current logged users folder and modifies it to a Arraylist
     * This arraylist is in the form that it can be shown in the meal history activity. */
    public ArrayList<String> GetMealHistory(String userpath) {

        String path = userpath;
        System.out.println(path);
        String userdir = path + "/" + DataHolder.getInstance().currentUser.getUsername() + "/Meals.json";
        System.out.println(userdir);
        String json = null;
        try {
            json = dbhelp.getJSON_wFileInputStreamer(userdir);
            System.out.println(json);
            dataHolder.mealjson = json; // Appending the pure json form to be used in other applications.
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

                    /* For loop to chech all instances, then it is just parsing a string together*/
                    JSONObject jobject = jarray.getJSONObject(i);

                    Double multiplier = (jobject.getDouble("FoodAmount") / 100);

                    Double Kcal = jobject.getDouble("Kalorit") * multiplier;
                    Double Carbo = jobject.getDouble("Carbohydrates") * multiplier;
                    Double Fat = jobject.getDouble("Fat") * multiplier;
                    Double Prot = jobject.getDouble("Protein") * multiplier;

                    totKcal = totKcal + jobject.getDouble("Kalorit") * multiplier;
                    totCarbo = totCarbo + jobject.getDouble("Carbohydrates") * multiplier;
                    totFat = totFat + jobject.getDouble("Fat") * multiplier;
                    totProt = totProt + jobject.getDouble("Protein") * multiplier;


                    String k = "Food: " + jobject.getString("Food") + "\t\t" + jobject.getInt("FoodAmount") + "g\n"
                            + "Kcal: " + Math.round(Kcal) + "\n"
                            + "Carbohydrates: " + Math.round(Carbo) + "g\t\t"
                            + "Protein: " + Math.round(Fat) + "g\t\t"
                            + "Fat: " + Math.round(Prot) + "g\n\n";
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

                Double multiplier = (jobject.getDouble("FoodAmount") / 100);

                totKcal = totKcal + jobject.getDouble("Kalorit") * multiplier;
                totCarbo = totCarbo + jobject.getDouble("Carbohydrates") * multiplier;
                totFat = totFat + jobject.getDouble("Fat") * multiplier;
                totProt = totProt + jobject.getDouble("Protein") * multiplier;


                String k = "Food: " + jobject.getString("Food") + "\t\t" + jobject.getInt("FoodAmount") + "g\n"
                        + "Kcal: " + Math.round(jobject.getDouble("Kalorit")) * multiplier + "\n"
                        + "Carbohydrates: " + Math.round(jobject.getDouble("Carbohydrates")) * multiplier + "g\t\t"
                        + "Protein: " + Math.round(jobject.getDouble("Protein")) * multiplier + "g\t\t"
                        + "Fat: " + Math.round(jobject.getDouble("Fat")) * multiplier + "g\n\n";
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
