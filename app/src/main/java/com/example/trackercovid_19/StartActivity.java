package com.example.trackercovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class StartActivity extends AppCompatActivity {
    ImageView startButton;
    ArrayList citiesListArray;
    ArrayList statesListArray;
    double activeCases, recoveredCases, confirmedCases, deathCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startButton = findViewById(R.id.startButton);
        citiesListArray = new ArrayList();
        statesListArray = new ArrayList();

        String urlForAPI = "https://data.covid19india.org/state_district_wise.json";

        StringRequest strRequest = new StringRequest(Request.Method.GET, urlForAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jOb1 = new JSONObject(response);
                    Log.d("TAG", response);
                    Iterator keys = jOb1.keys();
                    while (keys.hasNext()){
                        String stateNew = (String) keys.next();
                        statesListArray.add(stateNew);
                        Log.d("Atr", stateNew);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(StartActivity.this);
        requestQueue.add(strRequest);
        fetchData();
        
         startButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(StartActivity.this, MainActivity.class);
                 intent.putExtra("activeI", activeCases);
                 intent.putExtra("recoveredI", recoveredCases);
                 intent.putExtra("confirmedI", confirmedCases);
                 intent.putExtra("deathsI", deathCases);
                 intent.putStringArrayListExtra("state", statesListArray);
                 Log.d("Value", activeCases + " r " + recoveredCases);
                 startActivity(intent);
             }
         });
     }

    public  void fetchData(){
        activeCases =0;
        recoveredCases =0;
        confirmedCases =0;
        deathCases =0;
        String url = "https://data.covid19india.org/state_district_wise.json";


        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    for(int i = 0; i< statesListArray.size(); i++){
                        JSONObject jsObj1 = jsonObject.getJSONObject((String) statesListArray.get(i));
                        JSONObject jsObj2 = jsObj1.getJSONObject("districtData");

                        Iterator nextKey = jsObj2.keys();
                        while (nextKey.hasNext()){
                            String cityN = (String) nextKey.next();
                            citiesListArray.add(cityN);
                            Log.d("City", cityN);

                        }
                        for(int j = 0; j< citiesListArray.size(); j++){

                            JSONObject jsonObject3 = jsObj2.getJSONObject((String) citiesListArray.get(j));
                            float activeReceived = jsonObject3.getInt("active");
                            float recoveredReceived = jsonObject3.getInt("recovered");
                            float confirmReceived = jsonObject3.getInt("confirmed");
                            float deathsReceived = jsonObject3.getInt("deceased");
                            activeCases = activeCases + activeReceived;
                            recoveredCases = recoveredCases + recoveredReceived;
                            deathCases = deathCases + deathsReceived;
                            confirmedCases = confirmedCases + confirmReceived;
                        }
                        citiesListArray.clear();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(StartActivity.this);
        rQueue.add(stringRequest1);
    }
}