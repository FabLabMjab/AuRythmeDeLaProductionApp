package com.example.aurythmedelaproduction;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CaristeFragment extends Fragment {

    private LinearLayout partsBox;
    private LinearLayout partsContainer;

    private String caristeID;

    private List<PartRequest> partRequests = new ArrayList<>();

    private boolean boutonDemandePieces = false;
    private boolean boutonAideEnvoi = false;
    private ImageButton btnHelp;

    public CaristeFragment() {
        // Required empty public constructor
    }

    public static CaristeFragment newInstance(String profileId) {
        CaristeFragment fragment = new CaristeFragment();
        Bundle args = new Bundle();
        args.putString("PROFILE_ID", profileId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments() != null) {
                caristeID = getArguments().getString("PROFILE_ID");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cariste, container, false);

        partsBox = view.findViewById(R.id.partsBox);
        partsContainer = view.findViewById(R.id.partsContainer);

        btnHelp = view.findViewById(R.id.btnHelp);

        btnHelp.setOnClickListener(v -> requestHelp());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ((LogIn) requireActivity())
                    .updateSubtitle("Cariste");
        }
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

    private void handleMessage(String message) {

        if (getActivity() == null) return;

        getActivity().runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                String type = json.optString("type");

                if ("PART_REQUEST".equals(type)) {

                    String assembleur = json.optString("assembleur");
                    String part = json.optString("part");
                    String line = json.optString("line");

                    partRequests.add(new PartRequest(assembleur, part, line));

                    vibrateAlert();

                    updatePartsUI();
                }

                if ("IMPROVEMENTS_UPDATE".equals(type) || "IMPROVEMENTS".equals(type)) {

                    JSONObject improvements = json.optJSONObject("improvements");

                    if (improvements == null)
                        improvements = json.optJSONObject("liste");

                    if (improvements != null) {

                        boutonDemandePieces =
                                improvements.optBoolean("boutonDemandePieces", false);

                        boutonAideEnvoi =
                                improvements.optBoolean("boutonAideEnvoi", false);

                        updatePartsUI();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void updatePartsUI() {

        if (partsBox == null || partsContainer == null)
            return;

        partsBox.setVisibility(View.VISIBLE);

        if (!boutonDemandePieces) {

            partsBox.setVisibility(View.GONE);
            return;
        }

        partsContainer.removeAllViews();

        for (int i = 0; i < partRequests.size(); i++) {

            PartRequest req = partRequests.get(i);
            LinearLayout line = new LinearLayout(getContext());
            line.setOrientation(LinearLayout.HORIZONTAL);
            line.setPadding(20,20,20,20);

            line.setBackgroundResource(R.drawable.part_button_selector);
            if (i == partRequests.size() - 1) {
                startBlinkAnimation(line);
            }

            ImageView img = new ImageView(getContext());

            Part part = PartsRepository.getPart(req.getPartId());

            if (part != null) {
                img.setImageResource(part.getImageRes());
            }

            LinearLayout.LayoutParams imgParams =
                    new LinearLayout.LayoutParams(120,120);

            img.setLayoutParams(imgParams);

            TextView txt = new TextView(getContext());
            if (part != null) {
                txt.setText("Poste : " + req.getAssembleurId() +
                        "   Pièce : " + part.getId());
            }
            txt.setTextSize(22);
            txt.setTextColor(Color.BLACK);
            txt.setPadding(20,0,0,0);

            line.addView(img);
            line.addView(txt);

            line.setOnClickListener(v -> {

                v.clearAnimation();

                partRequests.remove(req);

                updatePartsUI();
            });

            partsContainer.addView(line);
        }
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

    private void vibrateAlert() {

        if (getContext() == null) return;

        Vibrator vibrator =
                (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator == null) return;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(
                    VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)
            );
        } else {
            vibrator.vibrate(150);
        }
    }

    private void startBlinkAnimation(View view) {

        AlphaAnimation blink = new AlphaAnimation(0.3f, 1.0f);
        blink.setDuration(600);
        blink.setRepeatMode(AlphaAnimation.REVERSE);
        blink.setRepeatCount(5);

        view.startAnimation(blink);
    }

    private void requestHelp() {
        if (!boutonAideEnvoi) {
            playHelpSound();
        }

        sendHelpRequest();
    }

    private void sendHelpRequest() {

        try {

            JSONObject msg = new JSONObject();

            msg.put("type", "HELP_REQUEST");
            msg.put("assembleur", caristeID);
            msg.put("line", getLine());

            WebSocketManager.getInstance()
                    .send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLine() {

        if (caristeID == null)
            return "";

        if (caristeID.endsWith("A"))
            return "A";

        if (caristeID.endsWith("B"))
            return "B";

        return "";
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
}