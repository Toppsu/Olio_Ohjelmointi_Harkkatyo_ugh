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
    Button button1;
    Button button2;
    Button button3;

    private String foodnumber1 = null;
    private String foodnumber2 = null;
    private String foodnumber3 = null;

    TextView textView1;
    TextView textView2;
    TextView textView3;

    Context context;

    DatabaseHelper dbhelp = new DatabaseHelper(Meal_mainscreen.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_mainscreen);
        context = Meal_mainscreen.this;

    }

    /* When adding food then we go to mealconstructor */
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

    public void MealActivityReady(View v) {

        ArrayList<Object> ruokailu = new ArrayList<>();

        if (foodnumber1 != null) {
            String[] parts = foodnumber1.split("\\t");
            String food1_id = parts[0];
            String food1_name = parts[1];
            EditText food1grams = (EditText) findViewById(R.id.ruoka1maara);
            String isempty = food1grams.getText().toString();
            if (isempty.matches("")) {
                Toast.makeText(this, "Add food amount!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                int grammat = Integer.parseInt(food1grams.getText().toString());
                ArrayList<String> foodnumber1arraylist = FineliAPI.readJSON(Integer.parseInt(food1_id));
                for (int i = 0; i < foodnumber1arraylist.size(); i++) {
                    System.out.println(foodnumber1arraylist.get(i));
                }
                /* Makes a MealActivity object out of the food parameters.*/
                MealActivity foodnumber1object
                        = new MealActivity(Float.parseFloat(foodnumber1arraylist.get(0))
                        , Float.parseFloat(foodnumber1arraylist.get(1))
                        , Float.parseFloat(foodnumber1arraylist.get(2))
                        , Float.parseFloat(foodnumber1arraylist.get(3))
                        , food1_name
                        , grammat);
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
                int grammat = Integer.parseInt(food2grams.getText().toString());
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
                        , grammat);
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
                int grammat = Integer.parseInt(food3grams.getText().toString());
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
                        , grammat);
                ruokailu.add(foodnumber3object);
            }
        }
        SaveMeal(ruokailu);
    }

    public String GetMealName() {
        EditText MealName = (EditText) findViewById(R.id.MealName);
        String Name = MealName.getText().toString();
        return Name;
    }

    public void SaveMeal(ArrayList Meal) {
        if (Meal != null) {
            String nimi = GetMealName();
            /*String filename = nimi + " " + Calendar.getInstance().getTime() + ".json";*/
            String filename = "Meals.json";
            Gson gson = new Gson();
            String s = gson.toJson(Meal);
            System.out.println(context.getFilesDir());
            try {

                File path = context.getFilesDir();
                File ruokafilu = new File(path + "/"+ DataHolder.getInstance().currentUser.getUsername()+"/Meals.json");
                FileWriter filewriter = new FileWriter(ruokafilu,true);
                filewriter.write(s);
                filewriter.close();
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

        /*ArrayList testi = FineliAPI.readJSON(123);
        paivamaara
        Ruokalista
        Kalorit
        Rasva
        Hiilarit
        Proteiini
        MealActivity mealactivity = new MealActivity()
        for (int i = 0; i < testi.size(); i++ ) {
            System.out.println(testi.get(i));
            */


