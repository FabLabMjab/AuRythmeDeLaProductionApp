package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// Mes imports

import android.media.MediaPlayer;
import android.widget.ImageButton;

public class BoiteDeSon extends AppCompatActivity {

    private MediaPlayer mediaPlayer; // Pour jouer les sons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_boitedeson);

        // Récupère les boutons définis dans activity_boitedeson.xml.xml
        ImageButton button1 = findViewById(R.id.button1);
        ImageButton button2 = findViewById(R.id.button2);
        ImageButton button3 = findViewById(R.id.button3);
        ImageButton button4 = findViewById(R.id.button4);
        ImageButton button5 = findViewById(R.id.button5);
        ImageButton button6 = findViewById(R.id.button6);
        ImageButton returnbutton = findViewById(R.id.returnbutton);

        // Associe chaque bouton à un son
        button1.setOnClickListener(v -> playSound(R.raw.son_outils_ogg));
        button2.setOnClickListener(v -> playSound(R.raw.son_nouveau_vehicule_ogg));
        button3.setOnClickListener(v -> playSound(R.raw.bell_ogg));
        button4.setOnClickListener(v -> playSound(R.raw.son_camion_ogg));
        button5.setOnClickListener(v -> playSound(R.raw.code_jaune_ogg));
        button6.setOnClickListener(v -> playSound(R.raw.code_rouge_ogg));
        returnbutton.setOnClickListener(v -> finish());
    }

    // Méthode pour jouer un son
    private void playSound(int soundResId) {
        // Libère l'ancien son si déjà en cours
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Crée un MediaPlayer avec la ressource donnée
        mediaPlayer = MediaPlayer.create(this, soundResId);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        // Nettoie le MediaPlayer quand l'app se ferme
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}