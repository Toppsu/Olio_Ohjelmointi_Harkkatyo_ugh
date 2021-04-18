package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MealActivity {

    private Date paivamaara = null;

    public MealActivity() {
        paivamaara = Calendar.getInstance().getTime();
        ArrayList<String> Ruokalista = new ArrayList<>();
        ArrayList<Float> Kalorit = new ArrayList<>();
        ArrayList<Float> Rasva = new ArrayList<>();
        ArrayList<Float> Hiilarit = new ArrayList<>();
        ArrayList<Float> Proteiini = new ArrayList<>();
    }
}