package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

        ImageButton btnReturn = view.findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(v ->
                getParentFragmentManager().popBackStack()
        );

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
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien\n"
                        + "• Le contrôleur qualité occupe également le rôle de l'expédition";
                break;

            case 11:
                resId = R.drawable.plan_salle_10_11_12;
                notes = "Notes\n\n"
                        + "• Le contrôleur qualité occupe également le rôle de l'expédition";
                break;

            case 12:
                resId = R.drawable.plan_salle_10_11_12;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            case 13:
                resId = R.drawable.plan_salle_13;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            case 14:
                resId = R.drawable.plan_salle_14;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            case 15:
                resId = R.drawable.plan_salle_15;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            case 16:
                resId = R.drawable.plan_salle_16_17_18_19;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour les deux lignes\n"
                        + "• Le contrôleur qualité occupe également le rôle de l'expédition pour les deux lignes";
                break;

            case 17:
                resId = R.drawable.plan_salle_16_17_18_19;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour les deux lignes\n"
                        + "• Le contrôleur qualité occupe également le rôle de l'expédition pour la ligne B";
                break;

            case 18:
                resId = R.drawable.plan_salle_16_17_18_19;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour les deux lignes";
                break;

            case 19:
                resId = R.drawable.plan_salle_16_17_18_19;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour la ligne B";
                break;

            case 20:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour les deux lignes\n"
                        + "• Le contrôleur qualité occupe également le rôle de l'expédition pour les deux lignes";
                break;

            case 21:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour les deux lignes\n"
                        + "• Le contrôleur qualité occupe également le rôle de l'expédition pour la ligne B";
                break;

            case 22:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour les deux lignes";
                break;

            case 23:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                notes = "Notes\n\n"
                        + "• Le chef d'équipe occupe également le rôle de mécanicien pour la ligne B";
                break;

            case 24:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            case 25:
                resId = R.drawable.plan_salle_25;
                notes = "Notes\n\n"
                        + "• Cariste supplémentaire pour la ligne A";
                break;

            case 26:
                resId = R.drawable.plan_salle_26;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            case 27:
                resId = R.drawable.plan_salle_27;
                notes = "Notes\n\n"
                        + "• Un sous-assembleur supplémentaire pour la ligne A";
                break;

            case 28:
                resId = R.drawable.plan_salle_28;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            case 29:
                resId = R.drawable.plan_salle_29;
                notes = "Notes\n\n"
                        + "• Un sous-assembleur supplémentaire pour la ligne A";
                break;

            case 30:
                resId = R.drawable.plan_salle_30;
                notes = "Notes\n\n"
                        + "• Tous les postes sont occupés";
                break;

            default:
                resId = R.drawable.plan_salle_30;
                notes = "Configuration non standard";
                break;
        }

        imgPlan.setImageResource(resId);
        txtNotes.setText(notes);
    }
}