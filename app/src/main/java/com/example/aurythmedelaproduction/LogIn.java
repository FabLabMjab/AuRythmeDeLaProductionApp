package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        // Récupère les boutons définis dans activity_log_in.xml
        Button buttonposte1a = findViewById(R.id.poste1a);
        Button buttonposteanimateur = findViewById(R.id.animateur);
        Button buttonposte1b = findViewById(R.id.poste1b);
        Button buttonposte2a = findViewById(R.id.poste2a);
        Button buttonposte2b = findViewById(R.id.poste2b);
        Button buttonposte3a = findViewById(R.id.poste3a);
        Button buttonposte3b = findViewById(R.id.poste3b);
        Button buttonposte4a = findViewById(R.id.poste4a);
        Button buttonposte4b = findViewById(R.id.poste4b);
        Button buttonposte5a = findViewById(R.id.poste5a);
        Button buttonposte5b = findViewById(R.id.poste5b);
        Button buttonposte6a = findViewById(R.id.poste6a);
        Button buttonposte6b = findViewById(R.id.poste6b);
        Button buttonposte7a = findViewById(R.id.poste7a);
        Button buttonposte7b = findViewById(R.id.poste7b);
        Button buttonposte8a = findViewById(R.id.poste8a);
        Button buttonposte8b = findViewById(R.id.poste8b);
        Button buttonposte9a = findViewById(R.id.poste9a);
        Button buttonposte9b = findViewById(R.id.poste9b);
        Button buttonposte10a = findViewById(R.id.poste10a);
        Button buttonposte10b = findViewById(R.id.poste10b);

        // Associe le démarrage de l'activité à chaque bouton
        buttonposteanimateur.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Posteanimateur.class);
            startActivity(intent);
        });

        buttonposte1a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste1A.class);
            startActivity(intent);
        });

        buttonposte1b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste1B.class);
            startActivity(intent);
        });

        buttonposte2a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste2A.class);
            startActivity(intent);
        });

        buttonposte2b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste2B.class);
            startActivity(intent);
        });

        buttonposte3a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste3A.class);
            startActivity(intent);
        });

        buttonposte3b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste3B.class);
            startActivity(intent);
        });

        buttonposte4a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste4A.class);
            startActivity(intent);
        });

        buttonposte4b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste4B.class);
            startActivity(intent);
        });

        buttonposte5a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste5A.class);
            startActivity(intent);
        });

        buttonposte5b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste5B.class);
            startActivity(intent);
        });

        buttonposte6a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste6A.class);
            startActivity(intent);
        });

        buttonposte6b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste6B.class);
            startActivity(intent);
        });

        buttonposte7a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste7A.class);
            startActivity(intent);
        });

        buttonposte7b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste7B.class);
            startActivity(intent);
        });

        buttonposte8a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste8A.class);
            startActivity(intent);
        });

        buttonposte8b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste8B.class);
            startActivity(intent);
        });

        buttonposte9a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste9A.class);
            startActivity(intent);
        });

        buttonposte9b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste9B.class);
            startActivity(intent);
        });

        buttonposte10a.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste10A.class);
            startActivity(intent);
        });

        buttonposte10b.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, Poste10B.class);
            startActivity(intent);
        });
    }
}