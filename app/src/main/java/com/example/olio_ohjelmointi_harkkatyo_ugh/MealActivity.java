package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.os.Build;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MealActivity implements Serializable {
    String pvm = null;
    String Ruoka = null;
    float Kalorit = 0;
    float Rasva = 0;
    float Hiilarit = 0;
    float Proteiini = 0;
    float Ruokamaara = 0;

    public MealActivity(float carbo, float Kcal, float protein, float fat, String ruoka, float maara) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("E dd-MM-yyyy HH:mm:ss");
        pvm = formatter.format(date);
        Hiilarit = carbo; //TODO LISÄÄ RUOAN YKSIKKO (gramma/litra)
        Kalorit = Kcal;
        Proteiini = protein;
        Rasva = fat;
        Ruoka = ruoka;
        Ruokamaara = maara;
    }
}