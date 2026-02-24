package com.example.aurythmedelaproduction;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ImprovementsFragment extends Fragment {

    private LinearLayout container;
    private final Map<String, CheckBox> checkBoxMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_improvements, parent, false);

        container = v.findViewById(R.id.improvementsContainer);
        Log.d("IMPROVEMENTS_DEBUG", "Container = " + container);
        Button btnNext = v.findViewById(R.id.btnNextIteration);
        btnNext.setTextAppearance(R.style.ParticipantButton);
        ImageButton btnReturn = v.findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(view ->
                getParentFragmentManager().popBackStack());

        btnNext.setOnClickListener(view -> sendImprovements());

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);

        requestImprovements();

        updateSubtitle();

        return v;
    }

    private void updateSubtitle() {
        if (getActivity() == null) return;

        ((LogIn) getActivity())
                .updateSubtitle("Améliorations");
    }

    private void requestImprovements() {

        JSONObject msg = new JSONObject();

        try {
            msg.put("type", "GET_IMPROVEMENTS");
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(msg.toString());
    }

    private void handleMessage(String message) {

        if (getActivity() == null) return;

        getActivity().runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                //String type = json.getString("type");
                String type = json.optString("type", "UNKNOWN");
                Log.d("IMPROVEMENTS_DEBUG", "Type reçu = " + type);

                switch (type) {

                    case "IMPROVEMENTS": {

                        JSONObject improvements =
                                json.optJSONObject("liste");

                        Log.d("IMPROVEMENTS_DEBUG",
                                "Improvements JSON = " + improvements);

                        if (improvements != null) {
                            buildUI(improvements);
                        } else {
                            Log.e("IMPROVEMENTS_DEBUG",
                                    "Champ 'liste' MANQUANT ");
                        }

                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("IMPROVEMENTS_DEBUG", "CRASH JSON", e);
            }
        });
    }

    private void buildUI(JSONObject improvements) {

        if (container == null) {
            Log.e("IMPROVEMENTS_DEBUG", "Container NULL");
            return;
        }
        container.removeAllViews();
        checkBoxMap.clear();

        Iterator<String> keys = improvements.keys();

        while (keys.hasNext()) {

            String key = keys.next();

            boolean value = improvements.optBoolean(key, false);

            CheckBox cb = new CheckBox(getContext());
            cb.setText(formatLabel(key));
            cb.setChecked(value);
            cb.setBackgroundResource(R.drawable.improvement_item_bg);
            cb.setTextSize(32);
            cb.setTypeface(null, Typeface.BOLD);
            Typeface bebas = ResourcesCompat.getFont(
                    requireContext(),
                    R.font.bebas_neue
            );

            cb.setTypeface(bebas);

            cb.setOnCheckedChangeListener((buttonView, isChecked) -> {

                int targetDrawable = isChecked
                        ? R.drawable.improvement_item_bg_checked
                        : R.drawable.improvement_item_bg;

                animateBackground(cb, targetDrawable);

            });

            if (value) {

                cb.setEnabled(false);
                cb.setAlpha(0.65f);
                cb.setBackgroundResource(
                        R.drawable.improvement_item_bg_disabled
                );

            }

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

            params.setMargins(0, 24, 0, 24);
            cb.setLayoutParams(params);
            cb.setGravity(Gravity.CENTER);

            container.addView(cb);
            checkBoxMap.put(key, cb);
        }
        Log.d("IMPROVEMENTS_DEBUG", "UI construite");
    }

    private String formatLabel(String key) {

        // Rendu plus lisible (optionnel mais conseillé)
        switch (key) {

            case "boutonAideSon": return "Bouton aide sonore";
            case "boutonAideEnvoi": return "Bouton aide chef d’équipe";
            case "boutonDemandePieces": return "Demande de pièces";
            case "controleQualiteUpgrade": return "Amélioration contrôle qualité";
            case "expeditionUpgrade": return "Amélioration expédition";
            case "mecanicienUpgrade": return "Amélioration mécanicien";
        }

        return key;
    }

    private void sendImprovements() {

        JSONObject updates = new JSONObject();

        try {

            for (Map.Entry<String, CheckBox> entry : checkBoxMap.entrySet()) {

                updates.put(entry.getKey(),
                        entry.getValue().isChecked());
            }

            JSONObject msg = new JSONObject();
            msg.put("type", "SET_IMPROVEMENTS");
            msg.put("improvements", updates);

            WebSocketManager.getInstance().send(msg.toString());

            requestNextIteration();

            Toast.makeText(getContext(),
                    "Itération suivante démarrée",
                    Toast.LENGTH_SHORT).show();

            getParentFragmentManager().popBackStack();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestNextIteration() {

        JSONObject msg = new JSONObject();

        try {
            msg.put("type", "SET_ITERATION");
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(msg.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }
    @Override
    public void onPause() {
        super.onPause();
        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }

    private void animateBackground(CheckBox cb, int drawableRes) {

        cb.animate()
                .alpha(0.5f)
                .setDuration(120)
                .withEndAction(() -> {

                    cb.setBackgroundResource(drawableRes);

                    cb.animate()
                            .alpha(1f)
                            .setDuration(120)
                            .start();

                })
                .start();
    }
}