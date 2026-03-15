package com.example.aurythmedelaproduction;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;


public class ExpeditionFragment extends Fragment {

    private String vehicule;
    private String expeditionID;
    private boolean expeditionUpgrade = false;
    private boolean boutonAideEnvoi = false;

    private TextView txtRole;
    private ImageView imgInstruction;
    private ImageButton btnHelp;

    public ExpeditionFragment() {
        // Required empty public constructor
    }


    public static ExpeditionFragment newInstance(String vehicle, String profileId) {
        ExpeditionFragment fragment = new ExpeditionFragment();
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
            if (getArguments() != null) {
                vehicule = getArguments().getString("VEHICULE");
                expeditionID = getArguments().getString("PROFILE_ID");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expedition, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getActivity() != null) {
            ((LogIn) requireActivity()).updateSubtitle("Expédition");
        }

        txtRole = view.findViewById(R.id.txtRole);
        imgInstruction = view.findViewById(R.id.imgInstruction);
        btnHelp = view.findViewById(R.id.btnHelp);

        btnHelp.setOnClickListener(v -> requestHelp());

        requestImprovements();

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
                        expeditionUpgrade =
                                improvements.optBoolean("expeditionUpgrade", false);

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
            msg.put("assembleur", expeditionID);
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
                .updateSubtitle(expeditionID);
    }

    private String getLine() {

        if (expeditionID == null)
            return "";

        if (expeditionID.endsWith("A"))
            return "A";

        if (expeditionID.endsWith("B"))
            return "B";

        return "";
    }

    private void updateUI() {
        if (txtRole == null || imgInstruction == null)
            return;

        if (expeditionUpgrade) {

            txtRole.setText("La boîte est déjà assemblée, déposer le véhicule à l'intérieur");

            imgInstruction.setImageResource(R.drawable.assemblage_boite_expedition_complete);

        } else {

            txtRole.setText("Vous devez assembler la boîte de livraison et déposer le véhicule à l'intérieur");

            imgInstruction.setImageResource(R.drawable.assemblage_boite_expedition);
        }
    }
}