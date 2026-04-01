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

        txtParticipants.setText(getString(R.string.plan_salle_texte_participants) + participants);
        txtVehicle.setText(getString(R.string.plan_salle_texte_vehicule) + vehicle);
        txtLines.setText(getString(R.string.plan_salle_texte_ligne) + lines);

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
        ((LogIn) requireActivity()).updateSubtitle(getString(R.string.plan_salle_sous_titre));
    }

    private void updatePlanSalleContent(View view) {

        ImageView imgPlan = view.findViewById(R.id.imgPlanSalle);
        TextView txtNotes = view.findViewById(R.id.txtPlanNotes);

        int resId;
        String notes;

        switch (participants) {

            case 10:
                resId = R.drawable.plan_salle_10_11_12;
                setNotes(txtNotes, R.array.plan_notes_case10);
                break;

            case 11:
                resId = R.drawable.plan_salle_10_11_12;
                setNotes(txtNotes, R.array.plan_notes_case11);
                break;

            case 12:
                resId = R.drawable.plan_salle_10_11_12;
                setNotes(txtNotes, R.array.plan_notes_case12);
                break;

            case 13:
                resId = R.drawable.plan_salle_13;
                setNotes(txtNotes, R.array.plan_notes_case13);
                break;

            case 14:
                resId = R.drawable.plan_salle_14;
                setNotes(txtNotes, R.array.plan_notes_case14);
                break;

            case 15:
                resId = R.drawable.plan_salle_15;
                setNotes(txtNotes, R.array.plan_notes_case15);
                break;

            case 16:
                resId = R.drawable.plan_salle_16_17_18_19;
                setNotes(txtNotes, R.array.plan_notes_case16);
                break;

            case 17:
                resId = R.drawable.plan_salle_16_17_18_19;
                setNotes(txtNotes, R.array.plan_notes_case17);
                break;

            case 18:
                resId = R.drawable.plan_salle_16_17_18_19;
                setNotes(txtNotes, R.array.plan_notes_case18);
                break;

            case 19:
                resId = R.drawable.plan_salle_16_17_18_19;
                setNotes(txtNotes, R.array.plan_notes_case19);
                break;

            case 20:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                setNotes(txtNotes, R.array.plan_notes_case20);
                break;

            case 21:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                setNotes(txtNotes, R.array.plan_notes_case21);
                break;

            case 22:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                setNotes(txtNotes, R.array.plan_notes_case22);
                break;

            case 23:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                setNotes(txtNotes, R.array.plan_notes_case23);
                break;

            case 24:
                resId = R.drawable.plan_salle_20_21_22_23_24;
                setNotes(txtNotes, R.array.plan_notes_case24);
                break;

            case 25:
                resId = R.drawable.plan_salle_25;
                setNotes(txtNotes, R.array.plan_notes_case25);
                break;

            case 26:
                resId = R.drawable.plan_salle_26;
                setNotes(txtNotes, R.array.plan_notes_case26);
                break;

            case 27:
                resId = R.drawable.plan_salle_27;
                setNotes(txtNotes, R.array.plan_notes_case27);
                break;

            case 28:
                resId = R.drawable.plan_salle_28;
                setNotes(txtNotes, R.array.plan_notes_case28);

            case 29:
                resId = R.drawable.plan_salle_29;
                setNotes(txtNotes, R.array.plan_notes_case29);
                break;

            case 30:
                resId = R.drawable.plan_salle_30;
                setNotes(txtNotes, R.array.plan_notes_case30);
                break;

            default:
                resId = R.drawable.plan_salle_30;
                notes = "Configuration non standard";
                break;
        }

        imgPlan.setImageResource(resId);
    }

    private void setNotes(TextView txtNotes, int arrayResId) {

        String title = getString(R.string.plan_salle_notes_titre);
        String[] notesArray = getResources().getStringArray(arrayResId);

        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n\n");

        for (String note : notesArray) {
            sb.append(note).append("\n");
        }

        txtNotes.setText(sb.toString());
    }
}