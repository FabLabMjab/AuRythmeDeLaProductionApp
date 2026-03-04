package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class AssembleurFragment extends Fragment {

    private String assembleurId;
    private ImageView imgAssembly;
    private TextView txtInstructions;
    private String vehicle;

    public AssembleurFragment() {

    }


    public static AssembleurFragment newInstance(String assembleurId, String vehicle) {

        AssembleurFragment fragment = new AssembleurFragment();

        Bundle args = new Bundle();
        args.putString("ASSEMBLEUR_ID", assembleurId);
        args.putString("VEHICLE", vehicle);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            assembleurId = getArguments().getString("ASSEMBLEUR_ID");
            vehicle = getArguments().getString("VEHICLE");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateSubtitle();
        configureUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_assembleur,
                container,
                false
        );

        txtInstructions = view.findViewById(R.id.txtTask);
        imgAssembly = view.findViewById(R.id.imgAssembly);

        Button btnHelp = view.findViewById(R.id.btnHelp);
        Button btnParts = view.findViewById(R.id.btnParts);

        btnHelp.setOnClickListener(v -> requestHelp());
        btnParts.setOnClickListener(v -> requestParts());

        return view;
    }

    private void configureUI() {
        switch (vehicle) {

            case "Ski-Doo":
                configureSkiDoo();
                break;

            case "Spyder":
                configureSpyder();
                break;
        }

    }

    private void updateSubtitle() {

        if (getActivity() == null) return;

        ((LogIn) requireActivity())
                .updateSubtitle(assembleurId);
    }
    private void requestHelp() {
        // websocket → HELP_REQUEST
    }

    private void requestParts() {
        // websocket → PARTS_REQUEST
    }

    private void configureSkiDoo() {
        switch (assembleurId) {
            /******* Les assembleur# et assembleur#A ont le même affichage (jaune)********/
            case "assembleur1":
            case "assembleur1A":
                imgAssembly.setImageResource(R.drawable.assembleur1);
                txtInstructions.setText("Installation de la transmission");
                break;

            case "assembleur2":
            case "assembleur2A":
                imgAssembly.setImageResource(R.drawable.assembleur2);
                txtInstructions.setText("Installation du chassis avant");
                break;

            case "assembleur3":
            case "assembleur3A":
                imgAssembly.setImageResource(R.drawable.assembleur3);
                txtInstructions.setText("Pose du volant et du banc");
                break;

            case "assembleur4":
            case "assembleur4A":
                imgAssembly.setImageResource(R.drawable.assembleur4);
                txtInstructions.setText("Installation du chassis inférieur et de la chenille");
                break;

            case "assembleur5":
            case "assembleur5A":
                imgAssembly.setImageResource(R.drawable.assembleur5);
                txtInstructions.setText("Installation latérale du chassis");
                break;

            case "assembleur6":
            case "assembleur6A":
                imgAssembly.setImageResource(R.drawable.assembleur6);
                txtInstructions.setText("Installation du patin gauche");
                break;

            case "assembleur7":
            case "assembleur7A":
                imgAssembly.setImageResource(R.drawable.assembleur7);
                txtInstructions.setText("Installation du patin droit");
                break;

            case "assembleur8":
            case "assembleur8A":
                imgAssembly.setImageResource(R.drawable.assembleur8);
                txtInstructions.setText("Assemblage de la structure de la chenille");
                break;

            case "assembleur9":
            case "assembleur9A":
                imgAssembly.setImageResource(R.drawable.assembleur9);
                txtInstructions.setText("Pose de la chenille");
                break;

            /******* Les assembleur#B ont un affichage différent (rouge)********/
            case "assembleur1B":
                imgAssembly.setImageResource(R.drawable.assembleur1);
                txtInstructions.setText("Installation de la transmission");
                break;

            case "assembleur2B":
                imgAssembly.setImageResource(R.drawable.assembleur2b);
                txtInstructions.setText("Installation du chassis avant");
                break;

            case "assembleur3B":
                imgAssembly.setImageResource(R.drawable.assembleur3b);
                txtInstructions.setText("Pose du volant et du banc");
                break;

            case "assembleur4B":
                imgAssembly.setImageResource(R.drawable.assembleur4b);
                txtInstructions.setText("Installation du chassis inférieur et de la chenille");
                break;

            case "assembleur5B":
                imgAssembly.setImageResource(R.drawable.assembleur5b);
                txtInstructions.setText("Installation latérale du chassis");
                break;

            case "assembleur6B":
                imgAssembly.setImageResource(R.drawable.assembleur6b);
                txtInstructions.setText("Installation du patin gauche");
                break;

            case "assembleur7B":
                imgAssembly.setImageResource(R.drawable.assembleur7b);
                txtInstructions.setText("Installation du patin droit");
                break;

            case "assembleur8B":
                imgAssembly.setImageResource(R.drawable.assembleur8);
                txtInstructions.setText("Assemblage de la structure de la chenille");
                break;

            case "assembleur9B":
                imgAssembly.setImageResource(R.drawable.assembleur9);
                txtInstructions.setText("Pose de la chenille");
                break;

            default:
                txtInstructions.setText("Poste non configuré");
                break;
        }
    }
    private void configureSpyder() {

        switch (assembleurId) {

            case "assembleur1A":
                imgAssembly.setImageResource(R.drawable.assembleur1);
                txtInstructions.setText("Installation de la transmission");
                break;

            case "assembleur2A":
                imgAssembly.setImageResource(R.drawable.assembleur2);
                txtInstructions.setText("Installation du chassis avant");
                break;

            case "assembleur3A":
                imgAssembly.setImageResource(R.drawable.assembleur3);
                txtInstructions.setText("Pose du volant et du banc");
                break;

            case "assembleur4A":
                imgAssembly.setImageResource(R.drawable.spyder_assembleur4);
                txtInstructions.setText("Installation du chassis arrière (première partie)");
                break;

            case "assembleur5A":
                imgAssembly.setImageResource(R.drawable.spyder_assembleur5);
                txtInstructions.setText("Installation du chassis arrière (deuxième partie)");
                break;

            case "assembleur1B":
                imgAssembly.setImageResource(R.drawable.assembleur1);
                txtInstructions.setText("Installation de la transmission");
                break;

            case "assembleur2B":
                imgAssembly.setImageResource(R.drawable.assembleur2b);
                txtInstructions.setText("Installation du chassis avant");
                break;

            case "assembleur3B":
                imgAssembly.setImageResource(R.drawable.assembleur3b);
                txtInstructions.setText("Pose du volant et du banc");
                break;

            case "assembleur4B":
                imgAssembly.setImageResource(R.drawable.spyder_assembleur4b);
                txtInstructions.setText("Installation du chassis arrière (première partie)");
                break;

            case "assembleur5B":
                imgAssembly.setImageResource(R.drawable.spyder_assembleur5b);
                txtInstructions.setText("Installation du chassis arrière (deuxième partie)");
                break;

            default:
                txtInstructions.setText("Poste non configuré");
                break;
        }
    }
}