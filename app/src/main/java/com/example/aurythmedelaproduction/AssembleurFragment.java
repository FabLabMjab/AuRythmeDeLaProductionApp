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
        //Button btnParts = view.findViewById(R.id.btnParts);

        btnHelp.setOnClickListener(v -> requestHelp());
        /*btnParts.setOnClickListener(v -> {

            if (!boutonDemandePieces)
                return;

            if (partsContainer.getVisibility() == View.VISIBLE) {

                partsContainer.setVisibility(View.GONE);

            } else {

                updatePartsUI();
            }
        });*/

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

        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.help_button_sound);

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
            btn.setPadding(0,0,0,0);
            //btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //btn.setScaleType(ImageView.ScaleType.CENTER_CROP);
            btn.setScaleType(ImageView.ScaleType.FIT_XY);
            btn.setCropToPadding(false);
            btn.setAdjustViewBounds(true);
            btn.setBackground(null);


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