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
    private int lastParticipants = 0;
    private String lastVehicle = "";
    private int lastLines = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);

        wsm = WebSocketManager.getInstance();
        WebSocketManager.getInstance()
                .setGlobalListener(this::handleServerMessage);

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

        android.util.Log.d("SERVER_MSG", message);

        runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                String type = json.getString("type");

                if (type.equals("ACTIVITY_CONFIG")){
                    lastVehicle = json.getString("vehicle");
                    lastLines = json.getInt("assemblyLines");
                    lastParticipants = json.getInt("participants");
                }

                if (type.equals("RECONNECT_RESULT")) {

                    boolean hasProfile = json.getBoolean("hasProfile");

                    if (hasProfile) {

                        String profileName = json.getString("profileName");

                        openAssignedProfile(profileName);

                    } else {

                        requestParticipants();
                    }

                    return;
                }

                if (type.equals("PARTICIPANTS_STATUS")) {

                    int count = json.getInt("count");

                    if (count == 0) {

                        loadFragment(new ParticipantSetupFragment());

                    } else {

                        requestProfiles();
                        loadFragment(new ProfileSelectionFragment());
                    }
                }

                if (type.equals("RESET")) {

                    lastParticipants = 0;
                    lastVehicle = "";
                    lastLines = 1;

                    loadFragment(new ParticipantSetupFragment());
                    updateSubtitle("Nouvelle activité");

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


                    attemptReconnect();
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

    private void attemptReconnect() {

        String id = DeviceIdManager.getId(this);

        android.util.Log.d("RECONNECT_DEBUG", "Tentative RECONNECT → ID = " + id);

        JSONObject msg = new JSONObject();

        try {
            msg.put("type", "RECONNECT");
            msg.put("clientId", DeviceIdManager.getId(this));
        } catch (Exception ignored) {}

        wsm.send(msg.toString());
    }

    private void openAssignedProfile(String profileName) {

        if (profileName.equals("animateur")) {

            loadFragment(
                    AnimateurProfileFragment.newInstance(
                            lastParticipants,
                            lastVehicle,
                            lastLines
                    )
            );

        } else {

            // plus tard → fragments assembleur / cariste / etc
            Toast.makeText(this,
                    "Profil restauré : " + profileName,
                    Toast.LENGTH_SHORT).show();
        }
    }
}