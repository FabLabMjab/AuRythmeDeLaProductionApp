package com.example.aurythmedelaproduction;

import android.content.Context;

import com.example.aurythmedelaproduction.R;

public class ProfileFormatter {
    public static String format(Context context, String profileId) {

        if (profileId == null) return "";

        String line = extractLine(profileId);
        String number = extractNumber(profileId);

        if (profileId.startsWith("assembleur")) {
            return context.getString(R.string.profil_assembleur, number + line);
        }

        if (profileId.startsWith("mecanicien")) {
            return context.getString(R.string.profil_mecanicien, line);
        }

        if (profileId.startsWith("cariste")) {
            return context.getString(R.string.profil_cariste, number + line);
        }

        if (profileId.startsWith("controleurQualite")) {
            return context.getString(R.string.profil_controle_qualite, line);
        }

        if (profileId.startsWith("expedition")) {
            return context.getString(R.string.profil_expedition, line);
        }

        if (profileId.startsWith("chefEquipe")) {
            return context.getString(R.string.profil_chef_equipe, line);
        }

        if (profileId.equals("animateur")) {
            return context.getString(R.string.profil_animateur);
        }

        return profileId;
    }

    private static String extractLine(String id) {
        if (id.endsWith("A")) return "A";
        if (id.endsWith("B")) return "B";
        return "";
    }

    private static String extractNumber(String id) {
        return id.replaceAll("\\D+", "");
    }
}
