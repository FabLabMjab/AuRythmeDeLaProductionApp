package com.example.aurythmedelaproduction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnimateurProfileFragment extends Fragment {

    private TextView txtParticipants;
    private TextView txtVehicle;
    private TextView txtLines;

    private static final String ARG_PARTICIPANTS = "participants";
    private static final String ARG_VEHICLE = "vehicle";
    private static final String ARG_LINES = "lines";

    private int participants;
    private String vehicle;
    private int lines;
    private TextView txtIteration;

    public AnimateurProfileFragment() {
        // constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_animateur_profile,
                container,
                false
        );

        LinearLayout actionsContainer =
                view.findViewById(R.id.actionsContainer);

        String[] actions = {
                "Plan de la salle",
                "Améliorations",
                "Afficher statistiques",
                "Réinitialisation de l'activité"
        };

        for (String action : actions) {

            Button btn = createActionButton(action);

            btn.setOnClickListener(v -> handleAction(action));

            actionsContainer.addView(btn);
        }

        // Changer sous-titre activité
        ((LogIn) requireActivity())
                .findViewById(R.id.headerText2)
                .post(() ->
                        ((android.widget.TextView)
                                requireActivity().findViewById(R.id.headerText2))
                                .setText("Profil animateur")
                );

        // Références aux textes infos activité
        txtParticipants = view.findViewById(R.id.txtParticipants);
        txtVehicle = view.findViewById(R.id.txtVehicle);
        txtLines = view.findViewById(R.id.txtLines);
        txtIteration = view.findViewById(R.id.txtIteration);
        /*WebSocketManager.getInstance()
                .setMessageListener(this::handleMessage);*/
        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);
        requestIteration();


        txtParticipants.setText(
                "Participants : " + participants);

        txtVehicle.setText(
                "Véhicule : " + vehicle);

        txtLines.setText(
                "Lignes : " + lines);




        return view;
    }

    public static AnimateurProfileFragment newInstance(
            int participants,
            String vehicle,
            int lines
    ) {

        AnimateurProfileFragment fragment =
                new AnimateurProfileFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_PARTICIPANTS, participants);
        args.putString(ARG_VEHICLE, vehicle);
        args.putInt(ARG_LINES, lines);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            participants = getArguments()
                    .getInt(ARG_PARTICIPANTS);

            vehicle = getArguments()
                    .getString(ARG_VEHICLE);

            lines = getArguments()
                    .getInt(ARG_LINES);
        }
    }

    private Button createActionButton(String text) {

        Button btn = new Button(
                new android.view.ContextThemeWrapper(
                        getContext(),
                        R.style.ParticipantButton
                )
        );

        btn.setText(text);
        btn.setBackgroundResource(R.drawable.rounded_button);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        params.width = getResources().getDisplayMetrics().widthPixels / 2;

        // centre horizontal
        params.gravity = android.view.Gravity.CENTER_HORIZONTAL;

        int margin = 8;
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (margin * scale + 0.5f);

        params.setMargins(0, px, 0, px);
        btn.setLayoutParams(params);

        return btn;
    }

    private void requestIteration() {

        JSONObject req = new JSONObject();

        try {
            req.put("type", "REQUEST_ITERATION");
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(req.toString());
    }
    private void handleMessage(String message) {

        if (getActivity() == null) return;
        getActivity().runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                String type = json.getString("type");
                switch(type) {
                    case "ITERATION_STATUS": {

                        int iteration = json.getInt("iteration");

                        txtIteration.setText("Itération : " + iteration);

                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }
    private void openPlanSalle() {

        PlanSalleFragment fragment =
                PlanSalleFragment.newInstance(participants, vehicle, lines);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)   // important pour bouton retour
                .commit();
    }
    private void handleAction(String action) {

        switch (action) {

            case "Plan de la salle":
                openPlanSalle();
                break;

            case "Améliorations":
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new ImprovementsFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case "Afficher statistiques":
                break;


            case "Réinitialisation de l'activité":
                new AlertDialog.Builder(getContext())
                        .setTitle("Réinitialisation")
                        .setMessage("Voulez-vous vraiment réinitialiser l'activité ?")
                        .setPositiveButton("Oui", (d, w) -> requestReset())
                        .setNegativeButton("Annuler", null)
                        .show();
                break;
        }
    }
    private void requestReset() {

        JSONObject msg = new JSONObject();

        try {
            msg.put("type", "RESET_SERVER");
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(msg.toString());
    }
}