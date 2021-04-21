package com.example.olio_ohjelmointi_harkkatyo_ugh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class FineliAPI {



    public static ArrayList readJSON(int givenID) {
        int id = 4;
        id = givenID;
        String json = getJSON(id);
        ArrayList<String> ateria = new ArrayList<>();
        /*System.out.println("JSON: " + json);*/

        if (json != null) {
            try {
                JSONObject jobject = new JSONObject(json);
                JSONObject jobjectRuokanimi = new JSONObject(jobject.getString("name"));
                System.out.println("####### RUOKAINFO ########");
                System.out.println(jobject.getString("unit"));
                System.out.println(jobjectRuokanimi.getString("fi"));
                ateria.add(jobjectRuokanimi.getString("fi"));
                System.out.println(jobject.getDouble("energyKcal"));
                ateria.add(jobject.getString("energyKcal"));
                System.out.println(jobject.getDouble("fat"));
                ateria.add(jobject.getString("fat"));
                System.out.println(jobject.getDouble("protein"));
                ateria.add(jobject.getString("protein"));
                System.out.println(jobject.getDouble("carbohydrate"));
                ateria.add(jobject.getString("carbohydrate"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return ateria;
    }

    /* Fetches the JSON from fineli API*/
    public static String getJSON(int id) {
        String response = null;
        try {
            URL url = new URL("https://fineli.fi/fineli/api/v1/foods/" + id);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            in.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}



    /* Fethces the JSON from fineli API
    public String getJSON (int id) {
        String response = null;
        try {
            URL url = new URL("https://fineli.fi/fineli/api/v1/foods/" + id);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            in.close();
            br.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }*/