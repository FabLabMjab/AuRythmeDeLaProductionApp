package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class PlanSalleFragment extends Fragment {
    private static final String ARG_PARTICIPANTS = "participants";
    private static final String ARG_VEHICLE = "vehicle";
    private static final String ARG_LINES = "lines";

    private int participants;
    private String vehicle;
    private int lines;

    private TextView txtParticipants;
    private TextView txtVehicle;
    private TextView txtLines;

    public PlanSalleFragment() {}

    public static PlanSalleFragment newInstance(
            int participants,
            String vehicle,
            int lines
    ) {
        PlanSalleFragment fragment = new PlanSalleFragment();

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
            participants = getArguments().getInt(ARG_PARTICIPANTS);
            vehicle = getArguments().getString(ARG_VEHICLE);
            lines = getArguments().getInt(ARG_LINES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_plan_salle,
                container,
                false
        );

        txtParticipants = view.findViewById(R.id.txtParticipants);
        txtVehicle = view.findViewById(R.id.txtVehicle);
        txtLines = view.findViewById(R.id.txtLines);

        txtParticipants.setText("Participants : " + participants);
        txtVehicle.setText("Véhicule : " + vehicle);
        txtLines.setText("Lignes : " + lines);

        updatePlanSalleContent(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LogIn) requireActivity()).updateSubtitle("Plan de la salle");
    }

    private void updatePlanSalleContent(View view) {

        ImageView imgPlan = view.findViewById(R.id.imgPlanSalle);
        TextView txtNotes = view.findViewById(R.id.txtPlanNotes);

        int resId;
        String notes;

        switch (participants) {

            case 10:
                resId = R.drawable.plan_salle_10_11_12;
                notes = "Configuration pour 10 participants\n\n"
                        + "• Tous les postes sont occupés\n"
                        + "• Une seule ligne d’assemblage";
                break;

            case 11:
                resId = R.drawable.plan_salle_10_11_12;
                notes = "Configuration pour 11 participants\n\n"
                        + "• Poste assembleur 6 vacant\n"
                        + "• Redistribution recommandée";
                break;

            case 12:
                resId = R.drawable.plan_salle_10_11_12;
                notes = "Configuration pour 12 participants\n\n"
                        + "• Équilibre optimal\n"
                        + "• Rotation possible";
                break;

            case 13:
                resId = R.drawable.plan_salle_13;
                notes = "Configuration pour 13 participants\n\n"
                        + "• Deux lignes activées\n"
                        + "• Nombre impair → un poste flottant";
                break;

            case 14:
                resId = R.drawable.plan_salle_14;
                notes = "Configuration pour 14 participants\n\n"
                        + "• Deux lignes équilibrées";
                break;

            case 15:
                resId = R.drawable.plan_salle_15;
                notes = "Configuration pour 15 participants\n\n"
                        + "• Deux lignes activées\n"
                        + "• Nombre impair → chef d’équipe mobile";
                break;

            default:
                resId = R.drawable.plan_salle_29_30;
                notes = "Configuration non standard";
                break;
        }

        imgPlan.setImageResource(resId);
        txtNotes.setText(notes);
    }
}