package com.example.aurythmedelaproduction;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ControleQualiteFragment extends Fragment {

    private String vehicule;
    private String controleQualiteID;
    private boolean controleQualiteUpgrade = false;
    private boolean boutonAideEnvoi = false;
    private List<CheckBox> checklistItems = new ArrayList<>();

    public ControleQualiteFragment() {
        // Required empty public constructor
    }


    public static ControleQualiteFragment newInstance(String vehicle, String profileId) {
        ControleQualiteFragment fragment = new ControleQualiteFragment();
        Bundle args = new Bundle();
        args.putString("PROFILE_ID", profileId);
        args.putString("VEHICULE", vehicle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vehicule = getArguments().getString("VEHICULE");
            controleQualiteID = getArguments().getString("PROFILE_ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_controle_qualite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);

        ImageView img = view.findViewById(R.id.imgVehicle);
        String line = getLine();
        if (line.isEmpty() || "A".equals(line)){
            if ("spyder".equalsIgnoreCase(vehicule)) {
                img.setImageResource(R.drawable.modele_complet_spyder);
            } else {
                img.setImageResource(R.drawable.modele_complet_skidoo);
            }
        }

        if ("B".equals(line)){
            if ("spyder".equalsIgnoreCase(vehicule)) {
                img.setImageResource(R.drawable.modele_complet_spyder_b);
            } else {
                img.setImageResource(R.drawable.modele_complet_skidoo_b);
            }
        }

        updateSubtitle();
        requestImprovements();
        view.findViewById(R.id.btnHelp).setOnClickListener(v -> requestHelp());
    }

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
                        controleQualiteUpgrade =
                                improvements.optBoolean("controleQualiteUpgrade", false);

                        updateUI();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void sendHelpRequest() {

        try {

            JSONObject msg = new JSONObject();

            msg.put("type", "HELP_REQUEST");
            msg.put("assembleur", controleQualiteID);
            msg.put("line", getLine());

            WebSocketManager.getInstance()
                    .send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestHelp() {
        if (!boutonAideEnvoi) {
            playHelpSound();
        }

        sendHelpRequest();
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

    private void updateSubtitle() {

        if (getActivity() == null) return;

        ((LogIn) requireActivity())
                .updateSubtitle(controleQualiteID);
    }

    private String getLine() {

        if (controleQualiteID == null)
            return "";

        if (controleQualiteID.endsWith("A"))
            return "A";

        if (controleQualiteID.endsWith("B"))
            return "B";

        return "";
    }

    private void updateUI() {

        View root = getView();
        if (root == null) return;

        View checklistColumn = root.findViewById(R.id.checklistColumn);
        View title = root.findViewById(R.id.txtChecklistTitle);
        View container = root.findViewById(R.id.checklistContainer);

        if (controleQualiteUpgrade) {

            checklistColumn.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            container.setVisibility(View.VISIBLE);

            setupChecklist();

        } else {
            checklistColumn.setVisibility(View.GONE);

        }
    }

    private void setupChecklist() {

        LinearLayout container = getView().findViewById(R.id.checklistContainer);
        container.removeAllViews();
        checklistItems.clear();

        Typeface font = ResourcesCompat.getFont(getContext(), R.font.bebas_neue);

        List<String> items = getChecklistItems();

        for (String item : items) {

            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(item);
            checkBox.setTypeface(null, Typeface.BOLD);
            checkBox.setTypeface(font);
            checkBox.setTextSize(20);
            checkBox.setAllCaps(false);
            checkBox.setTextColor(Color.BLACK);


            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

                if (isChecked) {
                    checkBox.setAlpha(0.5f); // effet grisé
                } else {
                    checkBox.setAlpha(1f);
                }

            });

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

            params.setMargins(16,16,16,16);

            checkBox.setLayoutParams(params);

            container.addView(checkBox);
            checklistItems.add(checkBox);
        }

        Button resetBtn = getView().findViewById(R.id.btnResetChecklist);

        resetBtn.setOnClickListener(v -> {

            for (CheckBox cb : checklistItems) {
                cb.setChecked(false);
                cb.setAlpha(1f);
            }

        });
    }

    private List<String> getChecklistItems() {

        if ("spyder".equalsIgnoreCase(vehicule)) {

            return Arrays.asList(
                    getString(R.string.controle_qualite_liste_verification_bouton_general_un),
                    getString(R.string.controle_qualite_liste_verification_bouton_general_deux),
                    getString(R.string.controle_qualite_liste_verification_bouton_spyder)

            );
        }

        return Arrays.asList(
                getString(R.string.controle_qualite_liste_verification_bouton_general_un),
                getString(R.string.controle_qualite_liste_verification_bouton_general_deux),
                getString(R.string.controle_qualite_liste_verification_bouton_skidoo)
        );
    }
}