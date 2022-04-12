package com.example.trackercovid_19.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trackercovid_19.DistrictDetails;
import com.example.trackercovid_19.Model.District;
import com.example.trackercovid_19.R;

import java.util.List;

public class DistrictListAdapter extends ArrayAdapter<District> {
    private Context cntxt;
    private List<District> distListToFetch;

    public DistrictListAdapter(Context cntxt, List<District> districtList) {
        super(cntxt, R.layout.district_model, districtList);
        this.cntxt = cntxt;
        this.distListToFetch = districtList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View viewReturn = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_model, null,true);

        TextView districtName = viewReturn.findViewById(R.id.districtName);
        TextView activeData = viewReturn.findViewById(R.id.activeD);
        TextView recoveredData = viewReturn.findViewById(R.id.recoveredD);
        TextView confirmedData = viewReturn.findViewById(R.id.confirmedD);
        TextView deathsData = viewReturn.findViewById(R.id.deathsD);

        districtName.setText(distListToFetch.get(position).getDistrictName());
        activeData.setText(distListToFetch.get(position).getActive());
        recoveredData.setText(distListToFetch.get(position).getRecovered());
        confirmedData.setText(distListToFetch.get(position).getConfirmed());
        deathsData.setText(distListToFetch.get(position).getDeaths());

        viewReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cntxt, DistrictDetails.class);
                intent.putExtra("districtName",districtName.getText().toString());
                intent.putExtra("activeDistrict",activeData.getText().toString());
                intent.putExtra("confirmedDistrict", confirmedData.getText().toString());
                intent.putExtra("recoveredDistrict", recoveredData.getText().toString());
                intent.putExtra("deathsDistrict",deathsData.getText().toString());
                cntxt.startActivity(intent);
            }
        });
        return viewReturn;
    }
}