package com.example.aurythmedelaproduction;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;


public class AssembleurFragment extends Fragment {

    private String assembleurId;
    private ImageView imgAssembly;
    private TextView txtInstructions;
    private String vehicle;
    private boolean boutonAideEnvoi = false;
    private boolean boutonDemandePieces = false;
    private LinearLayout partsContainer;

    private boolean improvementsLoaded = false;
    private LinearLayout partsBox;

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

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);

        updateSubtitle();
        configureUI();
        requestImprovements();

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
        partsBox = view.findViewById(R.id.partsBox);
        partsContainer = view.findViewById(R.id.partsContainer);



        ImageButton btnHelp = view.findViewById(R.id.btnHelp);


        btnHelp.setOnClickListener(v -> requestHelp());


        return view;
    }

    private void configureUI() {
        AssemblyStep step =
                AssemblyRepository.getStep(assembleurId, vehicle);

        if (step == null) {
            txtInstructions.setText("Poste non configuré");
            return;
        }

        imgAssembly.setImageResource(step.getImageRes());
        txtInstructions.setText(step.getInstruction());

    }

    private void updateSubtitle() {

        if (getActivity() == null) return;

        ((LogIn) requireActivity())
                .updateSubtitle(assembleurId);
    }
    private void requestHelp() {
        if (!boutonAideEnvoi) {

            playHelpSound();

        } else {

            sendHelpRequest();
        }
    }

    private void requestPart(String partId) {

        try {

            JSONObject msg = new JSONObject();

            msg.put("type", "PART_REQUEST");
            msg.put("assembleur", assembleurId);
            msg.put("part", partId);
            msg.put("line", getLine());

            WebSocketManager
                    .getInstance()
                    .send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void onPause() {
        super.onPause();

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);
    }*/

    @Override
    public void onPause() {
        super.onPause();
        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }

    @Override
    public void onResume() {
        super.onResume();

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }

    private void handleMessage(String message) {

        if (getActivity() == null) return;

        getActivity().runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                String type = json.optString("type");

                if ("IMPROVEMENTS_UPDATE".equals(type) || "IMPROVEMENTS".equals(type)) {

                    JSONObject improvements = json.optJSONObject("improvements");

                    if (improvements == null)
                        improvements = json.optJSONObject("liste");

                    if (improvements != null) {
                        boutonAideEnvoi =
                                improvements.optBoolean("boutonAideEnvoi", false);
                        boutonDemandePieces =
                                improvements.optBoolean("boutonDemandePieces", false);

                        System.out.println("DEBUG boutonDemandePieces = " + boutonDemandePieces);
                        improvementsLoaded = true;
                        if (getView() != null) {
                            getActivity().runOnUiThread(() -> updatePartsUI());
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void playHelpSound() {

        if (getContext() == null) return;

        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.help_button_sound);

        if (mp == null) return;

        mp.setOnCompletionListener(player -> {
            player.release();
        });

        mp.start();
    }

    private String getLine() {

        if (assembleurId.endsWith("A"))
            return "A";

        if (assembleurId.endsWith("B"))
            return "B";

        return "";
    }

    private void sendHelpRequest() {

        try {

            JSONObject msg = new JSONObject();

            msg.put("type", "HELP_REQUEST");
            msg.put("assembleur", assembleurId);
            msg.put("line", getLine());

            WebSocketManager.getInstance()
                    .send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildPartsUI() {

        partsContainer.removeAllViews();

        List<Part> parts = PartsRepository.getPartsForAssembleur(assembleurId, vehicle);

        for (Part part : parts) {

            ImageButton btn = new ImageButton(getContext());

            btn.setImageResource(part.imageRes);

            //btn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            btn.setPadding(10,10,10,10);
            //btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //btn.setScaleType(ImageView.ScaleType.CENTER_CROP);
            btn.setScaleType(ImageView.ScaleType.FIT_XY);
            btn.setCropToPadding(false);
            btn.setAdjustViewBounds(true);
            btn.setBackgroundResource(R.drawable.part_button_selector);
            // btn.setBackground(null);


            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(200, 200);

            params.setMargins(20,20,20,20);

            btn.setLayoutParams(params);

            btn.setOnClickListener(v -> requestPart(part.id));

            partsContainer.addView(btn);
        }
    }

    private void updatePartsUI() {

        if (partsContainer == null || partsBox == null)
            return;

        if (!improvementsLoaded)
            return;

        if (!boutonDemandePieces) {

            partsBox.setVisibility(View.GONE);
            partsContainer.removeAllViews();
            return;
        }

        partsBox.setVisibility(View.VISIBLE);

        System.out.println("DEBUG updatePartsUI appelé");
        System.out.println("DEBUG container = " + partsContainer);

        buildPartsUI();
    }

    private void requestImprovements() {

        try {

            JSONObject msg = new JSONObject();
            msg.put("type", "GET_IMPROVEMENTS");

            WebSocketManager
                    .getInstance()
                    .send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}