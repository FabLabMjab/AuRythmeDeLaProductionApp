package com.example.aurythmedelaproduction;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

public class DeviceIdManager {

    private static final String PREFS = "APP_PREFS";
    private static final String KEY_ID = "DEVICE_ID";

    public static String getId(Context context) {

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        String id = prefs.getString(KEY_ID, null);

        if (id == null) {

            id = UUID.randomUUID().toString();

            prefs.edit()
                    .putString(KEY_ID, id)
                    .apply();
        }

        return id;
    }
}
