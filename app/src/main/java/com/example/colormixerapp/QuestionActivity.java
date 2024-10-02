package com.example.colormixerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    EditText editTextName;
    CheckBox checkBoxBlue, checkBoxRed, checkBoxYellow;
    Button buttonMix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Initialisation des vues
        editTextName = findViewById(R.id.editTextName);
        checkBoxBlue = findViewById(R.id.checkBoxBlue);
        checkBoxRed = findViewById(R.id.checkBoxRed);
        checkBoxYellow = findViewById(R.id.checkBoxYellow);
        buttonMix = findViewById(R.id.buttonMix);

        // Désactiver le bouton au départ
        buttonMix.setEnabled(false);

        // Gérer les cases à cocher
        View.OnClickListener checkBoxListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getSelectedCount();
                if (count == 2) {
                    buttonMix.setEnabled(true);
                    disableUnchecked();
                } else {
                    buttonMix.setEnabled(false);
                    enableAll();
                }
            }
        };

        checkBoxBlue.setOnClickListener(checkBoxListener);
        checkBoxRed.setOnClickListener(checkBoxListener);
        checkBoxYellow.setOnClickListener(checkBoxListener);

        // Action pour le bouton
        buttonMix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(QuestionActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Envoi des couleurs sélectionnées à AnswerActivity
                Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("blue", checkBoxBlue.isChecked());
                intent.putExtra("red", checkBoxRed.isChecked());
                intent.putExtra("yellow", checkBoxYellow.isChecked());
                startActivity(intent);
            }
        });
    }

    private int getSelectedCount() {
        int count = 0;
        if (checkBoxBlue.isChecked()) count++;
        if (checkBoxRed.isChecked()) count++;
        if (checkBoxYellow.isChecked()) count++;
        return count;
    }

    private void disableUnchecked() {
        if (!checkBoxBlue.isChecked()) checkBoxBlue.setEnabled(false);
        if (!checkBoxRed.isChecked()) checkBoxRed.setEnabled(false);
        if (!checkBoxYellow.isChecked()) checkBoxYellow.setEnabled(false);
    }

    private void enableAll() {
        checkBoxBlue.setEnabled(true);
        checkBoxRed.setEnabled(true);
        checkBoxYellow.setEnabled(true);
    }
}
