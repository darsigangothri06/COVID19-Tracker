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

import com.example.trackercovid_19.DistrictWiseData;
import com.example.trackercovid_19.Model.District;
import com.example.trackercovid_19.R;

import java.util.List;

public class StateAdapter extends ArrayAdapter<District> {
    private Context cntxt;
    private List<District> allDistsData;


    public StateAdapter(Context cntxt, List<District> allDistsData) {
        super(cntxt, R.layout.district_model ,allDistsData);
        this.cntxt = cntxt;
        this.allDistsData = allDistsData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View viewV = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_model, null,true);

        TextView dName = viewV.findViewById(R.id.districtName);
        TextView dActive = viewV.findViewById(R.id.activeD);
        TextView dRecovered = viewV.findViewById(R.id.recoveredD);
        TextView dConfirmed = viewV.findViewById(R.id.confirmedD);
        TextView dDeathCases = viewV.findViewById(R.id.deathsD);

        dName.setText(allDistsData.get(position).getDistrictName());
        dActive.setText(allDistsData.get(position).getActive());
        dRecovered.setText(allDistsData.get(position).getRecovered());
        dConfirmed.setText(allDistsData.get(position).getConfirmed());
        dDeathCases.setText(allDistsData.get(position).getDeaths());
        viewV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cntxt, DistrictWiseData.class);
                intent.putExtra("stateName",dName.getText().toString());
                cntxt.startActivity(intent);
            }
        });
        return viewV;
    }
}

