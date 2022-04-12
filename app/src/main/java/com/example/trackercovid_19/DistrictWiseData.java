package com.example.trackercovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trackercovid_19.Adapter.DistrictListAdapter;
import com.example.trackercovid_19.Model.District;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DistrictWiseData extends AppCompatActivity {
    ListView districtsLV;
    public static List<District> distsListData = new ArrayList<>();
    District districtName;
    ArrayList cityName;
    Adapter adapterToFetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_wise_data_list);

        districtsLV = findViewById(R.id.listViewDist);
        cityName = new ArrayList();

        distsListData.clear();
        fetchData();
    }

    public  void fetchData(){
        String websiteAPI = "https://data.covid19india.org/state_district_wise.json";
        Intent intent = getIntent();
        String stateName = intent.getStringExtra("stateName");


        StringRequest reqStringOne = new StringRequest(Request.Method.GET, websiteAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jObj = new JSONObject(response);
                        JSONObject objJson1 = jObj.getJSONObject(stateName);
                        JSONObject objJson2 = objJson1.getJSONObject("districtData");

                        Iterator keyData = objJson2.keys();
                        while (keyData.hasNext()){
                            String getCity = (String) keyData.next();
                            cityName.add(getCity);
                            Log.d("City", getCity);

                        }
                        for(int j = 0; j< cityName.size(); j++){

                            JSONObject jsonObject3 = objJson2.getJSONObject((String) cityName.get(j));
                            double activeReceived = jsonObject3.getDouble("active");
                            double recoveredReceived = jsonObject3.getDouble("recovered");
                            double confirmedReceived = jsonObject3.getDouble("confirmed");
                            double deathsReceived = jsonObject3.getDouble("deceased");

                            String activeString = String.format ("%.0f", activeReceived);
                            String recoveredString = String.format ("%.0f", recoveredReceived);
                            String confirmedString = String.format ("%.0f", confirmedReceived);
                            String deathsString = String.format ("%.0f", deathsReceived);

                            districtName = new District((String) cityName.get(j), activeString, recoveredString, confirmedString, deathsString);
                            distsListData.add(districtName);
                            Log.d("DList", distsListData.toString());
                        }
                    Log.d("TAG", "size"+ distsListData.size());
                    adapterToFetch = new DistrictListAdapter(DistrictWiseData.this, distsListData);
                    districtsLV.setAdapter((ListAdapter) adapterToFetch);
                    cityName.clear();
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

        RequestQueue requestQueue1 = Volley.newRequestQueue(DistrictWiseData.this);
        requestQueue1.add(reqStringOne);
        Log.d("DistrictSize",""+ distsListData.size());
    }

}