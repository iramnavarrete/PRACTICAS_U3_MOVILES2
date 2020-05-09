package com.example.eva3_1_conexion_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ListView listViewActor;
    ArrayList<JSONObject> miListaJSON = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewActor = findViewById(R.id.listVwActor);
        //new MiConexion().execute("http://192.168.0.19:3001/actors");
        //new MiConexionPOST().execute("http://192.168.0.19:3001/actors");
        //new MiConexionPUT().execute("http://192.168.0.19:3001/actors/202");
        new MiConexionDELETE().execute("http://192.168.0.19:3001/actors/202");
    }

    class MiConexion extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {//Aquí vamos a mandar la url
            String sRuta = strings[0];
            String sResu = null;
            try {
                URL ruta = new URL(sRuta);
                HttpURLConnection http = (HttpURLConnection)ruta.openConnection();

                if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader inputStreamReader = new InputStreamReader(http.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    sResu = bufferedReader.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {//Aquí recibimos la url
            super.onPostExecute(s);
            if(s != null){
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i=0; i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Log.wtf("JSON",jsonArray.getJSONObject(i).toString());
                        miListaJSON.add(jsonObject);
                    }

                    listViewActor.setAdapter(new MiAdaptador(MainActivity.this,R.layout.layout_actor,miListaJSON));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    class MiConexionPOST extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {//Aquí vamos a mandar la url
            String sRuta = strings[0];
            String sResu = null;
            try {
                URL ruta = new URL(sRuta);
                HttpURLConnection http = (HttpURLConnection)ruta.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type","application/json; charset=utf-8");
                http.connect();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("first_name","Pedro");
                jsonObject.put("last_name","Macario");

                DataOutputStream dataOutputStream = new DataOutputStream(http.getOutputStream());
                dataOutputStream.write(jsonObject.toString().getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();

                    InputStreamReader inputStreamReader = new InputStreamReader(http.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    sResu = bufferedReader.readLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {//Aquí recibimos la url
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    class MiConexionPUT extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {//Aquí vamos a mandar la url
            String sRuta = strings[0];
            String sResu = null;
            try {
                URL ruta = new URL(sRuta);
                HttpURLConnection http = (HttpURLConnection)ruta.openConnection();
                http.setRequestMethod("PUT");
                http.setDoInput(true);
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type","application/json; charset=utf-8");
                http.connect();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("first_name","Juanito");
                jsonObject.put("last_name","Camelas");

                DataOutputStream dataOutputStream = new DataOutputStream(http.getOutputStream());
                dataOutputStream.write(jsonObject.toString().getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();

                InputStreamReader inputStreamReader = new InputStreamReader(http.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                sResu = bufferedReader.readLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {//Aquí recibimos la url
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    class MiConexionDELETE extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {//Aquí vamos a mandar la url
            String sRuta = strings[0];
            String sResu = null;
            try {
                URL ruta = new URL(sRuta);
                HttpURLConnection http = (HttpURLConnection)ruta.openConnection();
                http.setRequestMethod("DELETE");
                http.setDoInput(true);
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type","application/json; charset=utf-8");
                http.connect();

                InputStreamReader inputStreamReader = new InputStreamReader(http.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                sResu = bufferedReader.readLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {//Aquí recibimos la url
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}

