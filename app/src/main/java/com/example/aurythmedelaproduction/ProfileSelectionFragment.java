package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ProfileSelectionFragment extends Fragment {

    private LinearLayout left;
    private LinearLayout center;
    private LinearLayout right;

    private TextView txtVehicle;
    private TextView txtLines;
    private TextView txtParticipants;

    private String currentVehicle = "";
    private int currentLines = 0;
    private int currentParticipants = 0;

    private final Map<String, Button> buttonsMap = new HashMap<>();
    private Set<String> lastAvailableProfiles = new HashSet<>();

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View v = inflater.inflate(
                R.layout.fragment_profile_selection,
                container,
                false
        );

        left = v.findViewById(R.id.columnLeft);
        center = v.findViewById(R.id.columnCenter);
        right = v.findViewById(R.id.columnRight);

        txtVehicle = v.findViewById(R.id.txtVehicle);
        txtLines = v.findViewById(R.id.txtLines);
        txtParticipants = v.findViewById(R.id.txtParticipants);

        requestProfiles();
        requestActivityConfig();

        return v;
    }

    private void requestProfiles() {

        JSONObject req = new JSONObject();

        try {
            req.put("type", "REQUEST_PROFILES");
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(req.toString());

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);
    }

    private void requestActivityConfig() {

        JSONObject req = new JSONObject();

        try {
            req.put("type", "REQUEST_ACTIVITY_CONFIG");
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(req.toString());

    }

    private void populateUI(
            List<Profile> profiles,
            boolean twoLines
    ) {
        buttonsMap.clear();

        left.removeAllViews();
        center.removeAllViews();
        right.removeAllViews();

        for (Profile p : profiles) {

            Button btn = createButton(p);


            buttonsMap.put(p.id, btn);

            if (twoLines) {

                if (p.line.equals("A"))
                    left.addView(btn);

                else if (p.role.equals("animateur"))
                    center.addView(btn);

                else
                    right.addView(btn);

            } else {

                if (p.role.equals("assembleur"))
                    left.addView(btn);

                else if (p.role.equals("animateur"))
                    right.addView(btn);

                else
                    center.addView(btn);
            }
        }
        updateButtonsStateIfReady();
    }

    private Button createButton(Profile p) {

        Button btn = new Button(
                new android.view.ContextThemeWrapper(
                        getContext(),
                        R.style.ParticipantButton
                )
        );
        btn.setText(p.id);
        btn.setBackgroundResource(R.drawable.rounded_button);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        int margin = 8;
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (margin * scale + 0.5f);

        params.setMargins(0, px, 0, px);
        btn.setLayoutParams(params);

        buttonsMap.put(p.id, btn);

        applyProfileState(btn, p.assigned);

        if (!p.assigned) {
            btn.setOnClickListener(v -> openProfile(p));
        }

        return btn;
    }

    private void openProfile(Profile p) {

        try {

            JSONObject msg = new JSONObject();

            msg.put("type", "SELECT_PROFILE");
            msg.put("profileName", p.id);
            msg.put("clientId", DeviceIdManager.getId(getContext()));

            WebSocketManager.getInstance().send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        ((LogIn) requireActivity())
                .updateSubtitle("Choix du profil");
    }

    @Override
    public void onPause() {
        super.onPause();

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }

    public void updateActivityConfig(String vehicle, int lines, int participants) {

        currentVehicle = vehicle;
        currentLines = lines;
        currentParticipants = participants;

        if (getActivity() == null) return;

        getActivity().runOnUiThread(() -> {

            txtVehicle.setText("Véhicule : " + vehicle);
            txtLines.setText("Lignes : " + lines);
            txtParticipants.setText("Participants : " + participants);
        });
    }

    private void handleMessage(String message) {

        Log.d("WS_DEBUG", "Fragment reçu: " + message);

        if (getActivity() == null)
            return;

        getActivity().runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                String type = json.getString("type");

                switch (type) {

                    case "PROFILES_LIST": {

                        JSONArray arr = json.getJSONArray("profiles");

                        boolean twoLines = false;
                        List<Profile> profiles = new ArrayList<>();

                        for (int i = 0; i < arr.length(); i++) {

                            JSONObject p = arr.getJSONObject(i);

                            Profile profile = new Profile(
                                    p.getString("id"),
                                    p.getString("role"),
                                    p.optString("line", ""),
                                    p.optBoolean("assigned", false)
                            );

                            if (profile.line.equals("B"))
                                twoLines = true;

                            profiles.add(profile);
                        }

                        populateUI(profiles, twoLines);
                        updateButtonsStateIfReady();
                        break;
                    }

                    case "ACTIVITY_CONFIG": {

                        String vehicle = json.getString("vehicle");
                        int lines = json.getInt("assemblyLines");
                        int participants = json.getInt("participants");
                        updateActivityConfig(vehicle, lines, participants);
                        break;
                    }

                    case "PROFILE_SELECTED": {

                        boolean success = json.getBoolean("success");

                        if (!success) {
                            String reason = json.optString("reason", "");

                            if (reason.equals("CLIENT_ALREADY_ASSIGNED")) {
                                Toast.makeText(getContext(),
                                        "Un profil est déjà actif sur cette tablette",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(),
                                        "Profil déjà pris",
                                        Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }

                        String profileName = json.getString("profileName");

                        if (profileName.equals("animateur")) {

                            AnimateurProfileFragment fragment =
                                    AnimateurProfileFragment.newInstance(
                                            currentParticipants,
                                            currentVehicle,
                                            currentLines
                                    );

                            getParentFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragmentContainer, fragment)
                                    .commit();
                        }

                        break;
                    }
                    case "AVAILABLE_PROFILES": {

                        JSONArray available = json.getJSONArray("profiles");

                        lastAvailableProfiles.clear();

                        for (int i = 0; i < available.length(); i++) {
                            lastAvailableProfiles.add(available.getString(i));
                        }

                        updateButtonsStateIfReady();

                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void applyProfileState(Button btn, boolean assigned) {

        if (assigned) {
            btn.setEnabled(false);
            btn.setAlpha(0.35f);
        } else {
            btn.setEnabled(true);
            btn.setAlpha(1f);
        }
    }
    private void updateButtonsState(Set<String> availableProfiles) {

        for (Map.Entry<String, Button> entry : buttonsMap.entrySet()) {

            String profileId = entry.getKey();
            Button btn = entry.getValue();

            boolean isAvailable = availableProfiles.contains(profileId);

            applyProfileState(btn, !isAvailable);
        }
    }
    private void updateButtonsStateIfReady() {

        if (buttonsMap.isEmpty())
            return;

        if (lastAvailableProfiles.isEmpty())
            return;

        updateButtonsState(lastAvailableProfiles);
    }
}