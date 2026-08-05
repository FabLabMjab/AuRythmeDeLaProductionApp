package com.example.aurythmedelaproduction;

import androidx.appcompat.app.AlertDialog;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatisticsFragment extends Fragment {

    private LinearLayout statsContainer;
    private int lines;

    private final Map<Integer, Map<String, Integer>> helpDetailsA = new HashMap<>();
    private final Map<Integer, Map<String, Integer>> helpDetailsB = new HashMap<>();

    public StatisticsFragment() {
        // Required empty public constructor
    }


    public static StatisticsFragment newInstance(int lines) {

        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        args.putInt("LINES", lines);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_statistics,
                container,
                false
        );

        statsContainer = view.findViewById(R.id.statsContainer);

        if (getArguments() != null) {
            lines = getArguments().getInt("LINES");
        }



        ImageButton btnReturn = view.findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(v ->
                getParentFragmentManager().popBackStack()
        );

        return view;
    }

    private void requestStatistics() {

        try {

            JSONObject msg = new JSONObject();
            msg.put("type", "STATISTICS_REQUEST");

            WebSocketManager
                    .getInstance()
                    .send(msg.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        WebSocketManager.getInstance()
                .setFragmentListener(this::handleMessage);

        statsContainer.removeAllViews();

        requestStatistics();
    }

    @Override
    public void onPause() {
        super.onPause();
        WebSocketManager.getInstance()
                .setFragmentListener(null);
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

                if (!"STATISTICS".equals(json.optString("type")))
                    return;

                JSONArray iterations = json.getJSONArray("iterations");

                for (int i = 0; i < iterations.length(); i++) {

                    JSONObject it = iterations.getJSONObject(i);

                    int iteration = it.getInt("iteration");

                    int vehiclesA = it.optInt("vehiclesA");
                    int vehiclesB = it.optInt("vehiclesB");

                    int helpA = it.optInt("helpA");
                    int helpB = it.optInt("helpB");

                    JSONObject helpJsonA = it.optJSONObject("helpDetailsA");
                    JSONObject helpJsonB = it.optJSONObject("helpDetailsB");

                    Map<String, Integer> detailsA = new HashMap<>();
                    Map<String, Integer> detailsB = new HashMap<>();

                    if (helpJsonA != null) {

                        JSONArray names = helpJsonA.names();

                        if (names != null) {

                            for (int j = 0; j < names.length(); j++) {

                                String profile = names.getString(j);

                                detailsA.put(
                                        profile,
                                        helpJsonA.getInt(profile)
                                );
                            }
                        }
                    }

                    if (helpJsonB != null) {

                        JSONArray names = helpJsonB.names();

                        if (names != null) {

                            for (int j = 0; j < names.length(); j++) {

                                String profile = names.getString(j);

                                detailsB.put(
                                        profile,
                                        helpJsonB.getInt(profile)
                                );
                            }
                        }
                    }

                    helpDetailsA.put(iteration, detailsA);
                    helpDetailsB.put(iteration, detailsB);

                    int partsA = it.optInt("partsA");
                    int partsB = it.optInt("partsB");

                    addIterationView(
                            iteration,
                            vehiclesA, vehiclesB,
                            helpA, helpB,
                            partsA, partsB
                    );
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void addIterationView(
            int iteration,
            int vehiclesA, int vehiclesB,
            int helpA, int helpB,
            int partsA, int partsB
    ) {

        TextView header = new TextView(getContext());
        header.setText(getString(R.string.statistiques_iteration) + iteration);
        header.setTextSize(32);
        header.setTextColor(Color.BLACK);
        header.setTypeface(ResourcesCompat.getFont(getContext(), R.font.bebas_neue));
        header.setPadding(0,60,0,30);

        statsContainer.addView(header);

        LinearLayout row = new LinearLayout(getContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0,0,0,40);

        if (lines == 1) {

            row.addView(createLineStats(iteration,"A", vehiclesA, helpA, partsA));

        } else {

            row.addView(createLineStats(iteration, "B", vehiclesB, helpB, partsB));
            row.addView(createLineStats(iteration, "A", vehiclesA, helpA, partsA));

        }

        statsContainer.addView(row);
    }

    private LinearLayout createLineStats(
            int iteration,
            String line,
            int vehicles,
            int help,
            int parts
    ) {

        LinearLayout column = new LinearLayout(getContext());
        column.setOrientation(LinearLayout.VERTICAL);
        column.setPadding(24,24,24,24);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                );

        params.setMargins(16,16,16,16);

        column.setLayoutParams(params);

        if ("A".equals(line)) {
            column.setBackgroundResource(R.drawable.stats_line_a);
        } else {
            column.setBackgroundResource(R.drawable.stats_line_b);
        }

        TextView title = createStatsText(getString(R.string.statistiques_ligne) + line, 26);

        TextView vehiclesTxt =
                createStatsText(getString(R.string.statistiques_vehicules) + vehicles, 22);

        TextView helpTxt =
                createStatsText(getString(R.string.statistiques_aides) + help, 22);

        helpTxt.setClickable(true);

        helpTxt.setOnClickListener(v ->
                showHelpDetails(iteration, line));

        TextView partsTxt =
                createStatsText(getString(R.string.statistiques_pieces) + parts, 22);

        column.addView(title);
        column.addView(vehiclesTxt);
        column.addView(helpTxt);
        column.addView(partsTxt);

        return column;
    }

    private TextView createStatsText(String text, int size) {

        TextView tv = new TextView(getContext());

        tv.setText(text);
        tv.setTextSize(size);
        tv.setTextColor(Color.BLACK);

        tv.setTypeface(
                ResourcesCompat.getFont(getContext(), R.font.bebas_neue)
        );

        tv.setPadding(0,12,0,12);

        return tv;
    }

    private void showHelpDetails(int iteration, String line) {

        Map<String, Integer> details =
                "B".equals(line)
                        ? helpDetailsB.get(iteration)
                        : helpDetailsA.get(iteration);

        if (details == null || details.isEmpty()) {

            new AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.statistiques_aides))
                    .setMessage(getString(R.string.statistiques_aucune_demande))
                    .setPositiveButton("OK", null)
                    .show();

            return;
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(details.entrySet());

        list.sort((a, b) ->
                Integer.compare(b.getValue(), a.getValue()));

        StringBuilder message = new StringBuilder();

        for (Map.Entry<String, Integer> entry : list) {

            message.append(
                    ProfileFormatter.format(
                            getContext(),
                            entry.getKey()
                    )
            );

            message.append(" : ");
            message.append(entry.getValue());
            message.append("\n");
        }

        new AlertDialog.Builder(requireContext())
                .setTitle(
                        getString(R.string.statistiques_aides)
                                + " - "
                                + getString(R.string.statistiques_ligne)
                                + line
                )
                .setMessage(message.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}