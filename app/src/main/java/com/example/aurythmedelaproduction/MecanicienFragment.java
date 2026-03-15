package com.example.aurythmedelaproduction;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MecanicienFragment extends Fragment {

    private boolean boutonAideEnvoi = false;
    private boolean mecanicienUpgrade = false;
    private String mecanicienID;
    private int participantCount;
    private String vehicule;
    private ViewPager2 pager;
    private List<Button> stepButtonList = new ArrayList<>();

    public MecanicienFragment() {
        // Required empty public constructor
    }


    public static MecanicienFragment newInstance(String profileId, int currentParticipants, String vehicle) {

        MecanicienFragment fragment = new MecanicienFragment();

        Bundle args = new Bundle();
        args.putString("PROFILE_ID", profileId);
        args.putInt("PARTICIPANTS", currentParticipants);
        args.putString("VEHICULE", vehicle);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mecanicienID = getArguments().getString("PROFILE_ID");
            participantCount = getArguments().getInt("PARTICIPANTS");
            vehicule = getArguments().getString("VEHICULE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_mecanicien,
                container,
                false
        );

        pager = view.findViewById(R.id.stepPager);

        List<Integer> images = getImagesForConfig();

        pager.setAdapter(new StepAdapter(images));

        view.findViewById(R.id.btnPrev).setOnClickListener(v -> {

            int current = pager.getCurrentItem();

            if (current > 0) {
                pager.setCurrentItem(current - 1, true);
            }
        });

        view.findViewById(R.id.btnNext).setOnClickListener(v -> {

            int current = pager.getCurrentItem();
            int last = pager.getAdapter().getItemCount() - 1;

            if (current < last) {
                pager.setCurrentItem(current + 1, true);
            }
        });

        view.findViewById(R.id.btnHelp).setOnClickListener(v ->
                requestHelp()
        );

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);

        updateSubtitle();
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
                        mecanicienUpgrade =
                                improvements.optBoolean("mecanicienUpgrade", false);

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
            msg.put("assembleur", mecanicienID);
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
                .updateSubtitle(mecanicienID);
    }

    private String getLine() {

        if (mecanicienID == null)
            return "";

        if (mecanicienID.endsWith("A"))
            return "A";

        if (mecanicienID.endsWith("B"))
            return "B";

        return "";
    }

    private List<Integer> getImagesForConfig() {

        String line = getLine();

        if ("spyder".equalsIgnoreCase(vehicule)) {
            return getSpyderImages(line);
        }

        if ("ski-doo".equalsIgnoreCase(vehicule)) {

            if (participantCount >= 10 && participantCount <= 13)
                return getImagesSet1(line);
                // Les deux tablettes des sous-assembleurs doivent être présentes
            if (participantCount == 14 || participantCount == 15)
                return getImagesSet2(line);


            if (participantCount >= 20 && participantCount <= 26)
                return getImagesSet1(line);

            if (participantCount >= 27 && participantCount <= 30)
                return getImagesSet2(line);

        }

        return getImagesSet1(line);
    }

    private List<Integer> getImagesSet1(String line) {

        if ("B".equals(line)) {

            return Arrays.asList(
                    R.drawable.assembleur1,
                    R.drawable.assembleur2b,
                    R.drawable.assembleur3b,
                    R.drawable.assembleur4b,
                    R.drawable.assembleur5b,
                    R.drawable.assembleur6b,
                    R.drawable.assembleur7b
            );

        }

        return Arrays.asList(
                R.drawable.assembleur1,
                R.drawable.assembleur2,
                R.drawable.assembleur3,
                R.drawable.assembleur4,
                R.drawable.assembleur5,
                R.drawable.assembleur6,
                R.drawable.assembleur7
        );
    }

    private List<Integer> getImagesSet2(String line) {

        if ("B".equals(line)) {

            return Arrays.asList(
                    R.drawable.assembleur1,
                    R.drawable.assembleur2b,
                    R.drawable.assembleur3b,
                    R.drawable.assembleur4b,
                    R.drawable.assembleur5b,
                    R.drawable.assembleur6b,
                    R.drawable.assembleur7b,
                    R.drawable.assembleur8,
                    R.drawable.assembleur9
            );

        }

        return Arrays.asList(
                R.drawable.assembleur1,
                R.drawable.assembleur2,
                R.drawable.assembleur3,
                R.drawable.assembleur4,
                R.drawable.assembleur5,
                R.drawable.assembleur6,
                R.drawable.assembleur7,
                R.drawable.assembleur8,
                R.drawable.assembleur9
        );
    }

    private List<Integer> getSpyderImages(String line) {

        if ("A".equals(line)) {

            return Arrays.asList(
                    R.drawable.assembleur1,
                    R.drawable.assembleur2,
                    R.drawable.assembleur3,
                    R.drawable.spyder_assembleur4,
                    R.drawable.spyder_assembleur5
            );
        }

        return Arrays.asList(
                R.drawable.assembleur1,
                R.drawable.assembleur2b,
                R.drawable.assembleur3b,
                R.drawable.spyder_assembleur4b,
                R.drawable.spyder_assembleur5b
        );
    }

    private void updateUI() {

        View carousel = getView().findViewById(R.id.layoutCarousel);
        View upgrade = getView().findViewById(R.id.layoutUpgrade);

        if (mecanicienUpgrade) {

            carousel.setVisibility(View.GONE);
            upgrade.setVisibility(View.VISIBLE);

            setupUpgradeLayout();

        } else {

            carousel.setVisibility(View.VISIBLE);
            upgrade.setVisibility(View.GONE);
        }
    }

    private void setupUpgradeLayout() {

        LinearLayout stepButtons = getView().findViewById(R.id.stepButtons);
        ImageView stepImage = getView().findViewById(R.id.stepImage);

        stepButtons.removeAllViews();
        stepButtonList.clear();

        List<Integer> images = getImagesForConfig();

        for (int i = 0; i < images.size(); i++) {

            int stepIndex = i;

            Button btn = new Button(getContext());
            btn.setText("Assembleur " + (i + 1));

            btn.setBackgroundResource(R.drawable.rounded_button);
            btn.setTextColor(Color.WHITE);
            btn.setAllCaps(false);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

            params.setMargins(16,16,16,16);

            btn.setLayoutParams(params);

            btn.setOnClickListener(v -> {
                stepImage.setImageResource(images.get(stepIndex));
                updateActiveButton(stepIndex);
            });

            stepButtons.addView(btn);
            stepButtonList.add(btn);
        }

        if (!images.isEmpty()) {
            stepImage.setImageResource(images.get(0));
            updateActiveButton(0);
        }
    }

    private void updateActiveButton(int activeIndex) {

        for (int i = 0; i < stepButtonList.size(); i++) {

            Button btn = stepButtonList.get(i);

            if (i == activeIndex) {

                btn.setEnabled(false);
                btn.setAlpha(0.5f); // effet grisé

            } else {

                btn.setEnabled(true);
                btn.setAlpha(1f);
            }
        }
    }
}