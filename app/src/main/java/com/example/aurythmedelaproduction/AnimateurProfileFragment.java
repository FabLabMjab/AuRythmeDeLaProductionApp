package com.example.aurythmedelaproduction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private int lastIteration = -1;


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

        String[] actions = requireContext()
                .getResources()
                .getStringArray(R.array.animateur_actions);

        for (int i = 0; i < actions.length; i++) {

            String action = actions[i];
            int index = i;

            Button btn = createActionButton(action);

            btn.setOnClickListener(v -> handleAction(index));

            actionsContainer.addView(btn);
        }

        // Conteneur horizontal pour les boutons de production
        LinearLayout vehicleRow = new LinearLayout(getContext());
        vehicleRow.setOrientation(LinearLayout.HORIZONTAL);
        vehicleRow.setGravity(android.view.Gravity.CENTER);

        // marge au-dessus
        LinearLayout.LayoutParams rowParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        rowParams.topMargin = 60;

        vehicleRow.setLayoutParams(rowParams);

        // Boutons de production de véhicules selon les lignes

        if (lines >= 1) {
            vehicleRow.addView(createVehicleButton("A"));
        }

        if (lines >= 2) {
            vehicleRow.addView(createVehicleButton("B"));
        }

        actionsContainer.addView(vehicleRow);

        // Changer sous-titre activité
        ((LogIn) requireActivity())
                .findViewById(R.id.headerText2)
                .post(() ->
                        ((android.widget.TextView)
                                requireActivity().findViewById(R.id.headerText2))
                                .setText(getString(R.string.animateur_sous_titre))
                );

        // Références aux textes infos activité
        txtParticipants = view.findViewById(R.id.txtParticipants);
        txtVehicle = view.findViewById(R.id.txtVehicle);
        txtLines = view.findViewById(R.id.txtLines);
        txtIteration = view.findViewById(R.id.txtIteration);

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);
        requestIteration();


        txtParticipants.setText(
                getString(R.string.animateur_ligne_participant) + participants);

        txtVehicle.setText(
                getString(R.string.animateur_ligne_vehicule) + vehicle);

        txtLines.setText(
                getString(R.string.animateur_ligne_ligne) + lines);




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

                        lastIteration = json.getInt("iteration");

                        if (txtIteration != null) {
                            txtIteration.setText(getString(R.string.animateur_ligne_iteration) + lastIteration);
                        }

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
    private void handleAction(int position) {

        switch (position) {

            case 0: // Plan de la salle
                openPlanSalle();
                break;

            case 1: // Améliorations
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new ImprovementsFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case 2: // Afficher statistiques
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(
                                R.id.fragmentContainer,
                                StatisticsFragment.newInstance(lines)
                        )
                        .addToBackStack(null)
                        .commit();
                break;

            case 3: // Réinitialisation
                new AlertDialog.Builder(getContext())
                        .setTitle(getString(R.string.animateur_alerte_titre))
                        .setMessage(getString(R.string.animateur_alerte_message))
                        .setPositiveButton(getString(R.string.animateur_alerte_positif), (d, w) -> requestReset())
                        .setNegativeButton(getString(R.string.animateur_alerte_negatif), null)
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

    @Override
    public void onResume() {
        super.onResume();

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);

        requestIteration();
    }

    private void sendVehicleProduced(String line) {

        try {

            JSONObject msg = new JSONObject();
            msg.put("type", "VEHICLE_PRODUCED");
            msg.put("line", line);

            WebSocketManager
                    .getInstance()
                    .send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Button createVehicleButton(String line) {

        Button btn = createActionButton(getString(R.string.animateur_bouton_vehicule_produit) + line);

        if ("A".equals(line)) {
            btn.setBackgroundColor(getResources().getColor(R.color.lineA_yellow));
        } else if ("B".equals(line)) {
            btn.setBackgroundColor(getResources().getColor(R.color.lineB_red));
        }

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                );

        params.setMargins(10,0,10,0);

        btn.setLayoutParams(params);

        btn.setOnClickListener(v -> {

            sendVehicleProduced(line);

            Toast.makeText(
                    getContext(),
                    getString(R.string.animateur_notification_vehicule_produit) + line,
                    Toast.LENGTH_SHORT
            ).show();

        });

        return btn;
    }
}