package com.example.aurythmedelaproduction;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ChefEquipeFragment extends Fragment {

    private LinearLayout helpBox;
    private LinearLayout helpContainer;

    private boolean boutonAideEnvoi = false;

    private List<HelpRequest> helpRequests = new ArrayList<>();

    private String chefEquipeID;

    public ChefEquipeFragment() {
        // Required empty public constructor
    }

    public static ChefEquipeFragment newInstance(String profileId) {
        ChefEquipeFragment fragment = new ChefEquipeFragment();
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
                chefEquipeID = getArguments().getString("PROFILE_ID");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chef_equipe, container, false);

        helpBox = view.findViewById(R.id.helpBox);
        helpContainer = view.findViewById(R.id.helpContainer);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getActivity() != null) {
            ((LogIn) requireActivity()).updateSubtitle(ProfileFormatter.format(getContext(), chefEquipeID));
        }

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

        requestImprovements();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        WebSocketManager.getInstance()
                .setFragmentListener(null);
    }

    private void updateHelpUI() {

        if (helpBox == null || helpContainer == null)
            return;

        if (!boutonAideEnvoi) {

            helpBox.setVisibility(View.GONE);
            return;
        }

        helpBox.setVisibility(View.VISIBLE);

        helpContainer.removeAllViews();

        for (int i = 0; i < helpRequests.size(); i++) {

            HelpRequest req = helpRequests.get(i);

            TextView line = new TextView(getContext());
            Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.bebas_neue);
            line.setTypeface(typeface);

            line.setText(getString(R.string.chef_equipe_ligne_demande_aide) + req.getPosteId());

            line.setTextSize(22);
            line.setTextColor(Color.BLACK);
            line.setPadding(20,20,20,20);

            line.setBackgroundResource(R.drawable.part_button_selector);

            if (i == helpRequests.size() - 1) {
                startBlinkAnimation(line);
            }

            line.setOnClickListener(v -> {

                v.clearAnimation();
                helpRequests.remove(req);
                updateHelpUI();
            });

            helpContainer.addView(line);
        }
    }

    private void handleMessage(String message) {

        if (getActivity() == null) return;

        getActivity().runOnUiThread(() -> {

            try {

                JSONObject json = new JSONObject(message);
                String type = json.optString("type");

                if ("HELP_REQUEST".equals(type)) {

                    String assembleur = json.optString("from");
                    String line = json.optString("line");

                    helpRequests.add(new HelpRequest(assembleur, line));

                    vibrateAlert();
                    playHelpSound();
                    updateHelpUI();
                }

                if ("IMPROVEMENTS_UPDATE".equals(type) || "IMPROVEMENTS".equals(type)) {

                    JSONObject improvements = json.optJSONObject("improvements");

                    if (improvements == null)
                        improvements = json.optJSONObject("liste");

                    if (improvements != null) {

                        boutonAideEnvoi =
                                improvements.optBoolean("boutonAideEnvoi", false);

                        updateHelpUI();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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

        Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator == null) return;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
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