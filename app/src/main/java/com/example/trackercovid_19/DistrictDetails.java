package com.example.trackercovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DistrictDetails extends AppCompatActivity {

    TextView dName;
    TextView activeInDistrict;
    TextView recoveredInDist;
    TextView confirmedInDistrict;
    TextView deathsInDistrict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_details);

        dName = findViewById(R.id.districtName);
        activeInDistrict = findViewById(R.id.activeDistrict);
        recoveredInDist = findViewById(R.id.recoveredDistrict);
        confirmedInDistrict = findViewById(R.id.confirmedDistrict);
        deathsInDistrict = findViewById(R.id.deceasedDistrict);

        Intent intent = getIntent();

        String districtNameD = intent.getStringExtra("districtName");
        String activeRec = intent.getStringExtra("activeDistrict");
        String confirmedRec = intent.getStringExtra("confirmedDistrict");
        String recoveredRec = intent.getStringExtra("recoveredDistrict");
        String deathsRec = intent.getStringExtra("deathsDistrict");

        dName.setText(districtNameD);
        activeInDistrict.setText(activeRec);
        recoveredInDist.setText(recoveredRec);
        confirmedInDistrict.setText(confirmedRec);
        deathsInDistrict.setText(deathsRec);

        double activeShow = Double.parseDouble(activeRec);
        double recoveredShow = Double.parseDouble(recoveredRec);
        double confirmedShow = Double.parseDouble(confirmedRec);
        double deathsShow = Double.parseDouble(deathsRec);

        PieChart pieChartViewInStart = (PieChart) findViewById(R.id.piechartDistrict);
        pieChartViewInStart.clearChart();

        pieChartViewInStart.addPieSlice(new PieModel("Active", (float) activeShow, Color.parseColor("#FE6DA8")));
        pieChartViewInStart.addPieSlice(new PieModel("Confirmed", (float) confirmedShow, Color.parseColor("#56B7F1")));
        pieChartViewInStart.addPieSlice(new PieModel("Recovered", (float) recoveredShow, Color.parseColor("#CDA67F")));
        pieChartViewInStart.addPieSlice(new PieModel("Deceased", (float)deathsShow, Color.parseColor("#FED70E")));

        pieChartViewInStart.startAnimation();
    }
}