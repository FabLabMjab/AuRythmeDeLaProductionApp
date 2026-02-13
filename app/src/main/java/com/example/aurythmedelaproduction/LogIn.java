package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import androidx.fragment.app.Fragment;

public class LogIn extends AppCompatActivity {

    private WebSocketManager wsm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);

        wsm = WebSocketManager.getInstance();

        retryConnection();
    }

    private void requestParticipants() {

        JSONObject req = new JSONObject();
        try {
            req.put("type", "REQUEST_PARTICIPANTS");
        } catch (Exception ignored) {}

        wsm.send(req.toString());
    }

    private void handleServerMessage(String message) {

        runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                String type = json.getString("type");

                if (type.equals("PARTICIPANTS_STATUS")) {

                    int count = json.getInt("count");

                    if (count == 0) {

                        loadFragment(new ParticipantSetupFragment());

                    } else {

                        requestProfiles();
                        loadFragment(new ProfileSelectionFragment());
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void requestProfiles() {

        JSONObject req = new JSONObject();

        try {
            req.put("type", "REQUEST_PROFILES");
        } catch (Exception ignored) {}

        wsm.send(req.toString());
    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    public void retryConnection() {

        wsm.connect(
                "ws://10.0.2.2:8887",

                // succès
                () -> runOnUiThread(() -> {

                    wsm.setMessageListener(this::handleServerMessage);

                    requestParticipants();
                }),

                // erreur
                error -> runOnUiThread(() -> {

                    loadFragment(new ConnectionErrorFragment());
                })
        );
    }

    public void updateSubtitle(String text) {

        TextView subtitle = findViewById(R.id.headerText2);
        subtitle.setText(text);
    }

}
// Ancienne activité
/*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        // Connexion au serveur
        WebSocketManager wsm = new WebSocketManager();*/
/* Insérer ici l'adresse locale du serveur
 * --> ws://10.0.2.2:8887 est l'adresse qui permet à l'émulateur d'AndroidStudio d'avoir accès au serveur s'il tourne sur la machine hôte
 * --> ws://192.168.137.1:8887 si l'adresse est sur Windows*/
        /* wsm.connect(
                "ws://10.0.2.2:8887",
                () -> runOnUiThread(() ->
                        Toast.makeText(this, "Connecté au serveur!", Toast.LENGTH_SHORT).show()
                ),
                error -> runOnUiThread(() ->
                        Toast.makeText(this, "Erreur de connexion: " + error, Toast.LENGTH_LONG).show()
                ),
                message -> runOnUiThread(() ->
                        Toast.makeText(this, "Message reçu: " + message, Toast.LENGTH_SHORT).show()
                )
        );

        wsm.send("HELLO SERVER");


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
    } */