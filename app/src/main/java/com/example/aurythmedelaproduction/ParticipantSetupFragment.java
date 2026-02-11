package com.example.aurythmedelaproduction;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.*;
import android.widget.Button;
import android.widget.GridLayout;

import org.json.JSONObject;

public class ParticipantSetupFragment extends Fragment {

    private int selectedValue = -1;
    private Button submitButton;
    private Button lastSelectedButton;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View v = inflater.inflate(
                R.layout.fragment_participant_setup,
                container,
                false
        );

        // Changer sous-titre activité
        ((LogIn) requireActivity())
                .findViewById(R.id.headerText2)
                .post(() ->
                        ((android.widget.TextView)
                                requireActivity().findViewById(R.id.headerText2))
                                .setText("Choix du nombre de participants")
                );

        GridLayout grid = v.findViewById(R.id.gridNumbers);
        submitButton = v.findViewById(R.id.btnSubmit);

        createNumberButtons(grid);

        submitButton.setOnClickListener(view -> sendSelection());

        return v;
    }
    private void createNumberButtons(GridLayout grid) {

        for (int i = 10; i <= 30; i++) {

            Button btn = new Button(getContext());
            btn.setText(String.valueOf(i));

            GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();
            params.width = 0;
            params.columnSpec =
                    GridLayout.spec(GridLayout.UNDEFINED, 1f);

            btn.setLayoutParams(params);

            int value = i;

            btn.setOnClickListener(v -> selectButton(btn, value));

            grid.addView(btn);
        }
    }

    private void selectButton(Button btn, int value) {

        // reset ancien bouton
        if (lastSelectedButton != null)
            lastSelectedButton.setBackgroundColor(Color.LTGRAY);

        // nouveau bouton
        btn.setBackgroundColor(Color.GRAY);

        lastSelectedButton = btn;
        selectedValue = value;

        submitButton.setEnabled(true);
    }

    private void sendSelection() {

        JSONObject msg = new JSONObject();

        try {
            msg.put("type", "SET_PARTICIPANTS");
            msg.put("count", selectedValue);
        } catch (Exception ignored) {}

        WebSocketManager.getInstance().send(msg.toString());
    }
}