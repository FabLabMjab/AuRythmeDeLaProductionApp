package com.example.aurythmedelaproduction;

import android.widget.CheckBox;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ImprovementsColumn {
    private final String line;
    private final LinearLayout container;
    private final Map<String, CheckBox> checkBoxes = new HashMap<>();

    public ImprovementsColumn(String line, LinearLayout container) {
        this.line = line;
        this.container = container;
    }

    public String getLine() {
        return line;
    }

    public LinearLayout getContainer() {
        return container;
    }

    public void addCheckBox(String key, CheckBox cb) {
        checkBoxes.put(key, cb);
        container.addView(cb);
    }

    public CheckBox getCheckBox(String key) {
        return checkBoxes.get(key);
    }

    public void clear() {
        checkBoxes.clear();
        while (container.getChildCount() > 1) {
            container.removeViewAt(1);
        }
    }

    public JSONObject toJson() {

        JSONObject json = new JSONObject();

        try {

            for (Map.Entry<String, CheckBox> entry : checkBoxes.entrySet()) {

                json.put(
                        entry.getKey(),
                        entry.getValue().isChecked()
                );
            }

        } catch (Exception ignored){}

        return json;
    }
}
