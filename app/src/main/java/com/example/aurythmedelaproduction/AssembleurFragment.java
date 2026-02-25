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

    public AssembleurFragment() {

    }


    public static AssembleurFragment newInstance(String assembleurId) {

        AssembleurFragment fragment = new AssembleurFragment();

        Bundle args = new Bundle();
        args.putString("ASSEMBLEUR_ID", assembleurId);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            assembleurId = getArguments().getString("ASSEMBLEUR_ID");
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

        switch (assembleurId) {

            case "assembleur1":
                imgAssembly.setImageResource(R.drawable.assembleur1);
                txtInstructions.setText("Assembler les skis...");
                break;

            case "assembleur2":
                imgAssembly.setImageResource(R.drawable.assembleur2);
                txtInstructions.setText("Installer le moteur...");
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
}