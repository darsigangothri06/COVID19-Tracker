package com.example.trackercovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String websiteFetch;
    TextView tv2, tv3, tv4, tv5;
    Button onClickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2 = findViewById(R.id.active);
        tv3 = findViewById(R.id.recovered);
        tv4 = findViewById(R.id.confirmed);
        tv5 = findViewById(R.id.deceased);
        onClickButton =  findViewById(R.id.button);

        Intent intent = getIntent();

        double activeCases = intent.getDoubleExtra("activeI", 0);
        Log.d("Active",""+activeCases);
        double confirmedCases = intent.getDoubleExtra("confirmedI", 0);
        double recoveredCases = intent.getDoubleExtra("recoveredI", 0);
        double deathCases = intent.getDoubleExtra("deathsI", 0);
        ArrayList stateData = intent.getStringArrayListExtra("state");

        String activeCasesString = String.format ("%.0f", activeCases);
        String recoveredCasesString = String.format ("%.0f", recoveredCases);
        String confirmedCasesString = String.format ("%.0f", confirmedCases);
        String deathCasesString = String.format ("%.0f", deathCases);

        tv2.setText(activeCasesString);
        tv3.setText(recoveredCasesString);
        tv4.setText(confirmedCasesString);
        tv5.setText(deathCasesString);

        PieChart picturePieChart = (PieChart) findViewById(R.id.piechart);
        picturePieChart.clearChart();

        picturePieChart.addPieSlice(new PieModel("Active", (float) activeCases, Color.parseColor("#e52c1d")));
        picturePieChart.addPieSlice(new PieModel("Confirmed", (float) confirmedCases, Color.parseColor("#e9a816")));
        picturePieChart.addPieSlice(new PieModel("Recovered", (float) recoveredCases, Color.parseColor("#CDA67F")));
        picturePieChart.addPieSlice(new PieModel("Deceased", (float)deathCases, Color.parseColor("#FED70E")));

        picturePieChart.startAnimation();

        onClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, StateWiseData.class);
                intent1.putStringArrayListExtra("state", stateData);
                startActivity(intent1);

            }
        });




    }

}