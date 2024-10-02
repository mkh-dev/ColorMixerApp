package com.example.colormixerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView textViewResult;
    Button buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.textViewResult);
        buttonQuit = findViewById(R.id.buttonQuit);

        // Récupérer les données de AnswerActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        boolean isCorrect = intent.getBooleanExtra("correct", false);

        // Afficher le résultat
        if (isCorrect) {
            textViewResult.setText("Congratulations \uD83C\uDFC6 \uD83C\uDF89 " + name + "! Your answer is correct.");
            textViewResult.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        } else {
            textViewResult.setText("Sorry " + name + "! Your answer is wrong.");
            textViewResult.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
}
