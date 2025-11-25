package com.example.aurythmedelaproduction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Posteanimateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_posteanimateur);

        // Récupère les boutons définis dans le fichier xml
        Button buttonverifierconnexions = findViewById(R.id.verifierconnexion);
        Button buttonafficherstatistiques = findViewById(R.id.afficherstatistiques);
        Button buttonsuggestionsameliorations = findViewById(R.id.suggestionsamelioration);
        Button buttonboitedeson = findViewById(R.id.boitedeson);
        Button buttonstatistiquesparposte = findViewById(R.id.statistiquesparposte);
        ImageButton returnbutton = findViewById(R.id.returnbutton);

        // Associe le démarrage de l'activité à chaque bouton
        buttonboitedeson.setOnClickListener(v -> {
            Intent intent = new Intent(Posteanimateur.this, BoiteDeSon.class);
            startActivity(intent);
        });
        returnbutton.setOnClickListener(v -> finish());
    }
}