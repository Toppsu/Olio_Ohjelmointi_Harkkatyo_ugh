package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MealActivity implements Serializable {

    String Ruoka = null;
    float Kalorit = 0;
    float Rasva = 0;
    float Hiilarit = 0;
    float Proteiini = 0;
    float Ruokamaara = 0;

    public MealActivity(String ruoka, float Kcal, float fat, float carbo, float protein, float maara) {
        Ruoka = ruoka; //TODO LISÄÄ RUOAN YKSIKKO (gramma/litra)
        Kalorit = Kcal;
        Rasva = fat;
        Hiilarit = carbo;
        Proteiini = protein;
        Ruokamaara = maara;
    }
}