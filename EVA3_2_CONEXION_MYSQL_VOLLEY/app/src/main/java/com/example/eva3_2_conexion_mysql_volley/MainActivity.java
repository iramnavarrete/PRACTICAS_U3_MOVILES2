package com.example.eva3_2_conexion_mysql_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    ListView listViewActores;
    ArrayList<JSONObject> milistaJSON = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewActores = findViewById(R.id.listVwActores);
/*
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://192.168.0.19:3001/actors",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i < response.length();i++){
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = response.getJSONObject(i);
                                Log.wtf("JSON",response.getJSONObject(i).toString());
                                milistaJSON.add(jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        listViewActores.setAdapter(new MiAdaptador(MainActivity.this,R.layout.layout_actor,milistaJSON));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(this).add(jsonArrayRequest);*/

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("first_name","Pedro");
            jsonObject.put("last_name","Macario");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.POST,
                "http://192.168.0.19:3001/actors",
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
