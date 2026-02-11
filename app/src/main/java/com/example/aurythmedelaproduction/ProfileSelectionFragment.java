package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ProfileSelectionFragment extends Fragment {

    private LinearLayout left;
    private LinearLayout center;
    private LinearLayout right;

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

        requestProfiles();

        return v;
    }

    private void requestProfiles() {

        JSONObject req = new JSONObject();

        try {
            req.put("type", "REQUEST_PROFILES");
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(req.toString());

        WebSocketManager.getInstance().setMessageListener(
                this::handleProfiles
        );
    }

    private void handleProfiles(String message) {

        requireActivity().runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);

                if (!json.getString("type")
                        .equals("PROFILES_LIST"))
                    return;

                JSONArray arr = json.getJSONArray("profiles");

                boolean twoLines = false;

                List<Profile> profiles = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject p = arr.getJSONObject(i);

                    Profile profile = new Profile(
                            p.getString("id"),
                            p.getString("role"),
                            p.optString("line", "")
                    );

                    if (profile.line.equals("B"))
                        twoLines = true;

                    profiles.add(profile);
                }

                populateUI(profiles, twoLines);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void populateUI(
            List<Profile> profiles,
            boolean twoLines
    ) {

        left.removeAllViews();
        center.removeAllViews();
        right.removeAllViews();

        for (Profile p : profiles) {

            Button btn = createButton(p);

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
    }

    private Button createButton(Profile p) {

        Button btn = new Button(getContext());
        btn.setText(p.id);

        btn.setOnClickListener(v -> openProfile(p));

        return btn;
    }

    private void openProfile(Profile p) {

        // placeholder pour prochaine étape
        Toast.makeText(
                getContext(),
                "Profil choisi: " + p.id,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        WebSocketManager.getInstance()
                .setMessageListener(this::handleProfiles);
    }

    @Override
    public void onPause() {
        super.onPause();

        WebSocketManager.getInstance()
                .setMessageListener(null);
    }
}