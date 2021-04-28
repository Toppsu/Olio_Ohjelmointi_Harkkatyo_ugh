package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Meal_mainscreen extends AppCompatActivity {


    private String foodnumber1 = null;
    private String foodnumber2 = null;
    private String foodnumber3 = null;

    TextView textView1;
    TextView textView2;
    TextView textView3;

    Context context;

    DatabaseHelper dbhelp = new DatabaseHelper(Meal_mainscreen.this);
    /* Mealhistory is used to parse the data to right type for the mealhistory */
    MealHistory mealh = new MealHistory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_mainscreen);
        context = Meal_mainscreen.this;

    }

    /* When adding food (pressing the button) we then go to mealconstructor. There are 3 buttons and 3 foodslots
    * We need to transfer data both ways, buttonID tells which slot to add the food*/
    public void Button1press(View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int buttonID = 1;
        intent.putExtra("food_item", buttonID);
        startActivityForResult(intent, 1);
    }

    public void Button2press(View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int ButtonID = 2;
        intent.putExtra("food_item", ButtonID);
        startActivityForResult(intent, 12);
    }

    public void Button3press(View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int ButtonID = 3;
        intent.putExtra("food_item", ButtonID);
        startActivityForResult(intent, 123);
    }

    /* These take the Food that is selected and append it to the foodbox.
    * String that they get contains firstly the food id the the name, we need the id
    * to get the correct info from fineli's API. That's why the split.*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                foodnumber1 = (String) data.getSerializableExtra("1");
                String[] foodnumber1_name = foodnumber1.split("\\t");
                textView1 = (TextView) findViewById(R.id.food1text);
                textView1.setText(foodnumber1_name[1]);
            }
        }
        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                foodnumber2 = (String) data.getSerializableExtra("12");
                String[] foodnumber2_name = foodnumber2.split("\\t");
                textView2 = (TextView) findViewById(R.id.food2text);
                textView2.setText(foodnumber2_name[1]);
            }
        }
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                foodnumber3 = (String) data.getSerializableExtra("123");
                String[] foodnumber3_name = foodnumber3.split("\\t");
                textView3 = (TextView) findViewById(R.id.food3text);
                textView3.setText(foodnumber3_name[1]);

            }
        }
    }
        /* Here we check all the 3 possible fooditems and make an arraylist out of them and their nutrition vlaues.
        * This arraylist then gets saved as json with Gson*/
    public void MealActivityReady(View v) {

        ArrayList<Object> ruokailu = new ArrayList<>();

        if (foodnumber1 != null) {
            String[] parts = foodnumber1.split("\\t");
            String food1_id = parts[0]; // ID part
            String food1_name = parts[1]; // Name part, see foodnames_en.txt to see the list where these come from
            EditText food1grams = (EditText) findViewById(R.id.ruoka1maara); // Taking the food gram amount fron edittext
            String isempty = food1grams.getText().toString();
            if (isempty.matches("")) {
                Toast.makeText(this, "Add food amount!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                int grammat1 = Integer.parseInt(food1grams.getText().toString());
                ArrayList<String> foodnumber1arraylist = FineliAPI.readJSON(Integer.parseInt(food1_id));
                for (int i = 0; i < foodnumber1arraylist.size(); i++) {
                    System.out.println(foodnumber1arraylist.get(i));
                }
                /* Makes a MealActivity object out of the food parameters. Parameters are in known order so we use indexes*/
                MealActivity foodnumber1object
                        = new MealActivity(Float.parseFloat(foodnumber1arraylist.get(0))
                        , Float.parseFloat(foodnumber1arraylist.get(1))
                        , Float.parseFloat(foodnumber1arraylist.get(2))
                        , Float.parseFloat(foodnumber1arraylist.get(3))
                        , food1_name
                        , grammat1);
                ruokailu.add(foodnumber1object);
            }
        }
        if (foodnumber2 != null) {
            String[] parts = foodnumber2.split("\\t");
            String food2_id = parts[0];
            String food2_name = parts[1];
            EditText food2grams = (EditText) findViewById(R.id.ruoka2maara);
            String isempty = food2grams.getText().toString();
            if (isempty.matches("")) {
                Toast.makeText(this, "Add food amount!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                int grammat2 = Integer.parseInt(food2grams.getText().toString());
                ArrayList<String> foodnumber2arraylist = FineliAPI.readJSON(Integer.parseInt(food2_id));
                for (int i = 0; i < foodnumber2arraylist.size(); i++) {
                    System.out.println(foodnumber2arraylist.get(i));
                }
                /* Makes a MealActivity object out of the food parameters.*/
                MealActivity foodnumber2object
                        = new MealActivity(Float.parseFloat(foodnumber2arraylist.get(0))
                        , Float.parseFloat(foodnumber2arraylist.get(1))
                        , Float.parseFloat(foodnumber2arraylist.get(2))
                        , Float.parseFloat(foodnumber2arraylist.get(3))
                        , food2_name
                        , grammat2);
                ruokailu.add(foodnumber2object);
            }
        }
        if (foodnumber3 != null) {
            String[] parts = foodnumber3.split("\\t");
            String food3_id = parts[0];
            String food3_name = parts[1];
            EditText food3grams = (EditText) findViewById(R.id.ruoka3maara);
            String isempty = food3grams.getText().toString();
            if (isempty.matches("")) {
                Toast.makeText(this, "Add food amount!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                int grammat3 = Integer.parseInt(food3grams.getText().toString());
                ArrayList<String> foodnumber3arraylist = FineliAPI.readJSON(Integer.parseInt(food3_id));
                for (int i = 0; i < foodnumber3arraylist.size(); i++) {
                    System.out.println(foodnumber3arraylist.get(i));
                }
                /* Makes a MealActivity object out of the food parameters.*/
                MealActivity foodnumber3object
                        = new MealActivity(Float.parseFloat(foodnumber3arraylist.get(0))
                        , Float.parseFloat(foodnumber3arraylist.get(1))
                        , Float.parseFloat(foodnumber3arraylist.get(2))
                        , Float.parseFloat(foodnumber3arraylist.get(3))
                        , food3_name
                        , grammat3);
                ruokailu.add(foodnumber3object);
            }
        }
        SaveMeal(ruokailu);
    }

    public void SaveMeal(ArrayList Meal) {
        if (Meal != null) {

            Gson gson = new Gson(); // Using Gson to make the Meals.json
            String s = gson.toJson(Meal);
            System.out.println(context.getFilesDir());
            try {
                /* Basic opening the current users folder and appending the file to there */
                File path = context.getFilesDir();
                File ruokafilu = new File(path + "/"+ DataHolder.getInstance().currentUser.getUsername()+"/Meals.json");
                FileWriter filewriter = new FileWriter(ruokafilu,true);
                filewriter.write(s);
                filewriter.close();
                DataHolder.getInstance().mealjson = DataHolder.getInstance().mealjson + s;
                DataHolder.getInstance().MealHistoryArray.addAll(mealh.SetArrayListforAdapter(s)); //Also appending to the DataHolder so we don't have to read the json again to there
                System.out.println(getFilesDir());
                Intent intent = new Intent(this,MainScreenView.class);
                startActivity(intent);
            } catch (FileNotFoundException e) {
                Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));

            } catch (IOException e) {
                Log.e("IOException", String.valueOf(R.string.IOException));
            }
        }
    }
}



