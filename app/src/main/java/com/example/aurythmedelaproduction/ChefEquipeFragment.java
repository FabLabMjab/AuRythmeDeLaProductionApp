package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public ChefEquipeFragment() {
        // Required empty public constructor
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
            ((LogIn) requireActivity()).updateSubtitle("Chef d'équipe");
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

    private void updateHelpUI() {

        if (helpBox == null || helpContainer == null)
            return;

        if (!boutonAideEnvoi) {

            helpBox.setVisibility(View.GONE);
            return;
        }

        helpBox.setVisibility(View.VISIBLE);

        helpContainer.removeAllViews();

        for (HelpRequest req : helpRequests) {

            TextView line = new TextView(getContext());

            line.setText("Aide demandée : " + req.getPosteId());

            line.setTextSize(22);
            line.setPadding(20,20,20,20);

            line.setBackgroundResource(R.drawable.part_button_selector);

            line.setOnClickListener(v -> {

                helpRequests.remove(req);
                v.setVisibility(View.GONE);
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
}