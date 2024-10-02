package com.example.colormixerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {

    TextView textViewChosenColors;
    RadioGroup radioGroupOptions;
    Button buttonSubmit;
    String correctMix = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        textViewChosenColors = findViewById(R.id.textViewChosenColors);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Récupérer les données de QuestionActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        boolean isBlueChecked = intent.getBooleanExtra("blue", false);
        boolean isRedChecked = intent.getBooleanExtra("red", false);
        boolean isYellowChecked = intent.getBooleanExtra("yellow", false);

        // Déterminer la couleur mélangée correcte
        if (isBlueChecked && isYellowChecked) correctMix = "Green";
        if (isRedChecked && isYellowChecked) correctMix = "Orange";
        if (isRedChecked && isBlueChecked) correctMix = "Purple";

        textViewChosenColors.setText("You chose: " + (isBlueChecked ? "Blue " : "") +
                (isRedChecked ? "Red " : "") + (isYellowChecked ? "Yellow" : ""));

        // Définir les options de couleur
        ((RadioButton) radioGroupOptions.getChildAt(0)).setText(correctMix);
        ((RadioButton) radioGroupOptions.getChildAt(1)).setText("Red");
        ((RadioButton) radioGroupOptions.getChildAt(2)).setText("Orange");

        // Action pour le bouton
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroupOptions.getCheckedRadioButtonId();
                RadioButton selectedRadio = findViewById(selectedId);
                String selectedColor = selectedRadio.getText().toString();

                Intent resultIntent = new Intent(AnswerActivity.this, ResultActivity.class);
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("correct", selectedColor.equals(correctMix));
                startActivity(resultIntent);
            }
        });
    }
}
