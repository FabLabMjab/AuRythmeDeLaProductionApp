package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CaristeFragment extends Fragment {

    private LinearLayout partsBox;
    private LinearLayout partsContainer;

    private List<PartRequest> partRequests = new ArrayList<>();

    private boolean boutonDemandePieces = false;

    public CaristeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cariste, container, false);

        partsBox = view.findViewById(R.id.partsBox);
        partsContainer = view.findViewById(R.id.partsContainer);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ((LogIn) requireActivity())
                    .updateSubtitle("Cariste");
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

                    updatePartsUI();
                }

                if ("IMPROVEMENTS_UPDATE".equals(type) || "IMPROVEMENTS".equals(type)) {

                    JSONObject improvements = json.optJSONObject("improvements");

                    if (improvements == null)
                        improvements = json.optJSONObject("liste");

                    if (improvements != null) {

                        boutonDemandePieces =
                                improvements.optBoolean("boutonDemandePieces", false);

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

        partsContainer.removeAllViews();

        for (PartRequest req : partRequests) {

            LinearLayout line = new LinearLayout(getContext());
            line.setOrientation(LinearLayout.HORIZONTAL);
            line.setPadding(20,20,20,20);

            line.setBackgroundResource(R.drawable.part_button_selector);

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
            txt.setPadding(20,0,0,0);

            line.addView(img);
            line.addView(txt);

            line.setOnClickListener(v -> {

                partRequests.remove(req);

                updatePartsUI();
            });

            partsContainer.addView(line);
        }
    }
}