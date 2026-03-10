package com.example.aurythmedelaproduction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartsRepository {
    private static final Map<String, Part> partsById = new HashMap<>();
    private static final Map<String, List<Part>> partsByStation = new HashMap<>();

    static {

        // Ski-Doo
        register("Ski-Doo", "assembleur1",
                new Part("ZSMSC", R.drawable.pieceassembleur1_1),
                new Part("ZSMST", R.drawable.pieceassembleur1_2),
                new Part("ZT5L", R.drawable.pieceassembleur1_3));

        register("Ski-Doo", "assembleur1A",
                new Part("ZSMSC", R.drawable.pieceassembleur1_1),
                new Part("ZSMST", R.drawable.pieceassembleur1_2),
                new Part("ZT5L", R.drawable.pieceassembleur1_3));

        register("Ski-Doo", "assembleur1B",
                new Part("ZSMSC", R.drawable.pieceassembleur1_1),
                new Part("ZSMST", R.drawable.pieceassembleur1_2),
                new Part("ZT5L", R.drawable.pieceassembleur1_3));

        register("Ski-Doo", "assembleur2",
                new Part("ASMCA", R.drawable.pieceassembleur2_1),
                new Part("ZT3L", R.drawable.pieceassembleur2_2));

        register("Ski-Doo", "assembleur2A",
                new Part("ASMCA", R.drawable.pieceassembleur2_1),
                new Part("ZT3L", R.drawable.pieceassembleur2_2));

        register("Ski-Doo", "assembleur2B",
                new Part("ASMCA", R.drawable.pieceassembleur2_1b),
                new Part("ZT3L", R.drawable.pieceassembleur2_2));

        register("Ski-Doo", "assembleur3",
                new Part("ZSMV", R.drawable.pieceassembleur3_1),
                new Part("ZSMB", R.drawable.pieceassembleur3_2));

        register("Ski-Doo", "assembleur3A",
                new Part("ZSMV", R.drawable.pieceassembleur3_1),
                new Part("ZSMB", R.drawable.pieceassembleur3_2));

        register("Ski-Doo", "assembleur3B",
                new Part("ZSMV", R.drawable.pieceassembleur3_1),
                new Part("ZSMB", R.drawable.pieceassembleur3_2));

        register("Ski-Doo", "assembleur4",
                new Part("AT11P1P", R.drawable.pieceassembleur4_1),
                new Part("ZSMBC", R.drawable.pieceassembleur4_2));

        register("Ski-Doo", "assembleur4A",
                new Part("AT11P1P", R.drawable.pieceassembleur4_1),
                new Part("ZSMBC", R.drawable.pieceassembleur4_2));

        register("Ski-Doo", "assembleur4B",
                new Part("AT11P1P", R.drawable.pieceassembleur4_1b),
                new Part("ZSMBC", R.drawable.pieceassembleur4_2));

        register("Ski-Doo", "assembleur5",
                new Part("AT7P2P", R.drawable.pieceassembleur5_1),
                new Part("ZT5P", R.drawable.pieceassembleur5_2));

        register("Ski-Doo", "assembleur5A",
                new Part("AT7P2P", R.drawable.pieceassembleur5_1),
                new Part("ZT5P", R.drawable.pieceassembleur5_2));

        register("Ski-Doo", "assembleur5B",
                new Part("AT7P2P", R.drawable.pieceassembleur5_1b),
                new Part("ZT5P", R.drawable.pieceassembleur5_2));

        register("Ski-Doo", "assembleur6",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMS", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur6A",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMS", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur6B",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMS", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur7",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMS", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur7A",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMS", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur7B",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMS", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur8",
                new Part("ZT3LR", R.drawable.pieceassembleur8_1),
                new Part("ZSMCC", R.drawable.pieceassembleur8_2),
                new Part("ZT5PX3P2P", R.drawable.pieceassembleur8_3),
                new Part("ZT3LP", R.drawable.pieceassembleur8_4));

        register("Ski-Doo", "assembleur8A",
                new Part("ZT3LR", R.drawable.pieceassembleur8_1),
                new Part("ZSMCC", R.drawable.pieceassembleur8_2),
                new Part("ZT5PX3P2P", R.drawable.pieceassembleur8_3),
                new Part("ZT3LP", R.drawable.pieceassembleur8_4));

        register("Ski-Doo", "assembleur8B",
                new Part("ZT3LR", R.drawable.pieceassembleur8_1),
                new Part("ZSMCC", R.drawable.pieceassembleur8_2),
                new Part("ZT5PX3P2P", R.drawable.pieceassembleur8_3),
                new Part("ZT3LP", R.drawable.pieceassembleur8_4));

        register("Ski-Doo", "assembleur9",
                new Part("ZB", R.drawable.pieceassembleur9_1),
                new Part("ZSMC", R.drawable.pieceassembleur9_2));

        register("Ski-Doo", "assembleur9A",
                new Part("ZB", R.drawable.pieceassembleur9_1),
                new Part("ZSMC", R.drawable.pieceassembleur9_2));

        register("Ski-Doo", "assembleur9B",
                new Part("ZB", R.drawable.pieceassembleur9_1),
                new Part("ZSMC", R.drawable.pieceassembleur9_2));


        // Spyder
        register("Spyder", "assembleur1",
                new Part("ZSMSC", R.drawable.pieceassembleur1_1),
                new Part("ZSMST", R.drawable.pieceassembleur1_2),
                new Part("ZT5L", R.drawable.pieceassembleur1_3));

        register("Spyder", "assembleur1A",
                new Part("ZSMSC", R.drawable.pieceassembleur1_1),
                new Part("ZSMST", R.drawable.pieceassembleur1_2),
                new Part("ZT5L", R.drawable.pieceassembleur1_3));

        register("Spyder", "assembleur1B",
                new Part("ZSMSC", R.drawable.pieceassembleur1_1),
                new Part("ZSMST", R.drawable.pieceassembleur1_2),
                new Part("ZT5L", R.drawable.pieceassembleur1_3));

        register("Spyder", "assembleur2",
                new Part("ASMCA", R.drawable.pieceassembleur2_1),
                new Part("ZT3L", R.drawable.pieceassembleur2_2));

        register("Spyder", "assembleur2A",
                new Part("ASMCA", R.drawable.pieceassembleur2_1),
                new Part("ZT3L", R.drawable.pieceassembleur2_2));

        register("Spyder", "assembleur2B",
                new Part("ASMCA", R.drawable.pieceassembleur2_1b),
                new Part("ZT3L", R.drawable.pieceassembleur2_2));

        register("Spyder", "assembleur3",
                new Part("ZSMV", R.drawable.pieceassembleur3_1),
                new Part("ZSMB", R.drawable.pieceassembleur3_2));

        register("Spyder", "assembleur3A",
                new Part("ZSMV", R.drawable.pieceassembleur3_1),
                new Part("ZSMB", R.drawable.pieceassembleur3_2));

        register("Spyder", "assembleur3B",
                new Part("ZSMV", R.drawable.pieceassembleur3_1),
                new Part("ZSMB", R.drawable.pieceassembleur3_2));

        register("Spyder", "assembleur4",
                new Part("ZRM", R.drawable.spyder_pieceassembleur4_1),
                new Part("AT11P", R.drawable.spyder_pieceassembleur4_2),
                new Part("ZT5PX3P3P", R.drawable.spyder_pieceassembleur4_3));

        register("Spyder", "assembleur4A",
                new Part("ZRM", R.drawable.spyder_pieceassembleur4_1),
                new Part("AT11P", R.drawable.spyder_pieceassembleur4_2),
                new Part("ZT5PX3P3P", R.drawable.spyder_pieceassembleur4_3));

        register("Spyder", "assembleur4B",
                new Part("ZRM", R.drawable.spyder_pieceassembleur4_1),
                new Part("AT11P", R.drawable.spyder_pieceassembleur4_2b),
                new Part("ZT5PX3P3P", R.drawable.spyder_pieceassembleur4_3));

        register("Spyder", "assembleur5",
                new Part("AT5P2P", R.drawable.spyder_pieceassembleur5_1),
                new Part("ZT9PX2P", R.drawable.spyder_pieceassembleur5_2),
                new Part("ZRR", R.drawable.spyder_pieceassembleur5_3));

        register("Spyder", "assembleur5A",
                new Part("AT5P2P", R.drawable.spyder_pieceassembleur5_1),
                new Part("ZT9PX2P", R.drawable.spyder_pieceassembleur5_2),
                new Part("ZRR", R.drawable.spyder_pieceassembleur5_3));

        register("Spyder", "assembleur5B",
                new Part("AT5P2P", R.drawable.spyder_pieceassembleur5_1),
                new Part("ZT9PX2P", R.drawable.spyder_pieceassembleur5_2),
                new Part("ZRR", R.drawable.spyder_pieceassembleur5_3));
    }


    private static void register(String vehicle, String station, Part... parts) {

        String key = vehicle + "_" + station;

        List<Part> list = new ArrayList<>();

        for (Part p : parts) {

            list.add(p);
            partsById.put(p.getId(), p);
        }

        partsByStation.put(key, list);
    }


    public static List<Part> getPartsForAssembleur(String assembleurId, String vehicle) {

        String station = assembleurId.replace("A", "").replace("B", "");

        String key = vehicle + "_" + station;

        return partsByStation.getOrDefault(key, new ArrayList<>());
    }


    public static Part getPart(String partId) {

        return partsById.get(partId);
    }

}

/* public static List<Part> getPartsForAssembleur(String assembleurId, String vehicle) {

        List<Part> parts = new ArrayList<>();

        switch (vehicle) {

            case "Ski-Doo":
                configureSkiDoo(parts, assembleurId);
                break;

            case "Spyder":
                configureSpyder(parts, assembleurId);
                break;
        }

        return parts;
    }
    private static void configureSkiDoo(List<Part> parts, String assembleurId) {

        switch (assembleurId) {

            case "assembleur1":
            case "assembleur1A":
            case "assembleur1B":

                parts.add(new Part("ZSMSC", R.drawable.pieceassembleur1_1));
                parts.add(new Part("ZSMST", R.drawable.pieceassembleur1_2));
                parts.add(new Part("ZT5L", R.drawable.pieceassembleur1_3));
                break;

            case "assembleur2":
            case "assembleur2A":
                parts.add(new Part("ASMCA", R.drawable.pieceassembleur2_1));
                parts.add(new Part("ZT3L", R.drawable.pieceassembleur2_2));
                break;

                case "assembleur2B":

                parts.add(new Part("BSMCA", R.drawable.pieceassembleur2_1b));
                parts.add(new Part("ZT3L", R.drawable.pieceassembleur2_2));
                break;

            case "assembleur3":
            case "assembleur3A":
            case "assembleur3B":

                parts.add(new Part("ZSMV", R.drawable.pieceassembleur3_1));
                parts.add(new Part("ZSMB", R.drawable.pieceassembleur3_2));
                break;

            case "assembleur4":
            case "assembleur4A":
                parts.add(new Part("AT11P1P", R.drawable.pieceassembleur4_1));
                parts.add(new Part("ZSMBC", R.drawable.pieceassembleur4_2));
                break;

            case "assembleur4B":
                parts.add(new Part("BT11P1P", R.drawable.pieceassembleur4_1b));
                parts.add(new Part("ZSMBC", R.drawable.pieceassembleur4_2));
                break;

            case "assembleur5":
            case "assembleur5A":
                parts.add(new Part("AT7P2P", R.drawable.pieceassembleur5_1));
                parts.add(new Part("ZT5P", R.drawable.pieceassembleur5_2));
                break;

            case "assembleur5B":
                parts.add(new Part("BT7P2P", R.drawable.pieceassembleur5_1b));
                parts.add(new Part("ZT5P", R.drawable.pieceassembleur5_2));
                break;

            case "assembleur6":
            case "assembleur6A":
            case "assembleur6B":
            case "assembleur7":
            case "assembleur7A":
            case "assembleur7B":
                parts.add(new Part("ZP", R.drawable.pieceassembleur6_1));
                parts.add(new Part("ZSMS", R.drawable.pieceassembleur6_2));
                parts.add(new Part("ZT2LR", R.drawable.pieceassembleur6_3));
                parts.add(new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));
                break;

            case "assembleur8":
            case "assembleur8A":
            case "assembleur8B":
                parts.add(new Part("ZT3LR", R.drawable.pieceassembleur8_1));
                parts.add(new Part("ZSMCC", R.drawable.pieceassembleur8_2));
                parts.add(new Part("ZT5PX3P2P", R.drawable.pieceassembleur8_3));
                parts.add(new Part("ZT3LP", R.drawable.pieceassembleur8_4));
                break;

            case "assembleur9":
            case "assembleur9A":
            case "assembleur9B":
                parts.add(new Part("ZB", R.drawable.pieceassembleur9_1));
                parts.add(new Part("ZSMC", R.drawable.pieceassembleur9_2));
                break;
        }
    }
    private static void configureSpyder(List<Part> parts, String assembleurId) {

        switch (assembleurId) {

            case "assembleur1A":
            case "assembleur1B":

                parts.add(new Part("ZSMSC", R.drawable.pieceassembleur1_1));
                parts.add(new Part("ZSMST", R.drawable.pieceassembleur1_2));
                parts.add(new Part("ZT5L", R.drawable.pieceassembleur1_3));
                break;

            case "assembleur2A":
                parts.add(new Part("ASMCA", R.drawable.pieceassembleur2_1));
                parts.add(new Part("ZT3L", R.drawable.pieceassembleur2_2));
                break;

            case "assembleur2B":

                parts.add(new Part("BSMCA", R.drawable.pieceassembleur2_1b));
                parts.add(new Part("ZT3L", R.drawable.pieceassembleur2_2));
                break;

            case "assembleur3A":
            case "assembleur3B":

                parts.add(new Part("ZSMV", R.drawable.pieceassembleur3_1));
                parts.add(new Part("ZSMB", R.drawable.pieceassembleur3_2));
                break;

            case "assembleur4A":
                parts.add(new Part("ZRM", R.drawable.spyder_pieceassembleur4_1));
                parts.add(new Part("AT11P", R.drawable.spyder_pieceassembleur4_2));
                parts.add(new Part("ZT5PX3P3P", R.drawable.spyder_pieceassembleur4_3));
                break;

            case "assembleur4B":
                parts.add(new Part("ZRM", R.drawable.spyder_pieceassembleur4_1));
                parts.add(new Part("BT11P", R.drawable.spyder_pieceassembleur4_2b));
                parts.add(new Part("ZT5PX3P3P", R.drawable.spyder_pieceassembleur4_3));
                break;

            case "assembleur5A":
                parts.add(new Part("AT5P2P", R.drawable.spyder_pieceassembleur5_1));
                parts.add(new Part("ZT9PX2P", R.drawable.spyder_pieceassembleur5_2));
                parts.add(new Part("ZRR", R.drawable.spyder_pieceassembleur5_3));
                break;
            case "assembleur5B":
                parts.add(new Part("BT5P2P", R.drawable.spyder_pieceassembleur5_1));
                parts.add(new Part("ZT9PX2P", R.drawable.spyder_pieceassembleur5_2));
                parts.add(new Part("ZRR", R.drawable.spyder_pieceassembleur5_3));
                break;
        }
    }

    public static int getImageForPart(String partId) {

        List<Part> parts = new ArrayList<>();

        // on génère toutes les pièces possibles
        configureSkiDoo(parts, "assembleur1");
        configureSkiDoo(parts, "assembleur2");
        configureSkiDoo(parts, "assembleur2B");
        configureSkiDoo(parts, "assembleur3");
        configureSkiDoo(parts, "assembleur4");
        configureSkiDoo(parts, "assembleur4B");
        configureSkiDoo(parts, "assembleur5");
        configureSkiDoo(parts, "assembleur5B");
        configureSkiDoo(parts, "assembleur6");
        configureSkiDoo(parts, "assembleur7");
        configureSkiDoo(parts, "assembleur8");
        configureSkiDoo(parts, "assembleur9");

        configureSpyder(parts, "assembleur1A");
        configureSpyder(parts, "assembleur2A");
        configureSpyder(parts, "assembleur2B");
        configureSpyder(parts, "assembleur3A");
        configureSpyder(parts, "assembleur4A");
        configureSpyder(parts, "assembleur4B");
        configureSpyder(parts, "assembleur5A");
        configureSpyder(parts, "assembleur5B");

        for (Part p : parts) {

            if (p.getId().equals(partId)) {
                return p.getImageRes();
            }
        }

        return R.drawable.ic_launcher_foreground;
    }

    public static Part getPart(String partId) {

        List<Part> parts = new ArrayList<>();

        // Générer toutes les pièces Ski-Doo
        configureSkiDoo(parts, "assembleur1");
        configureSkiDoo(parts, "assembleur2");
        configureSkiDoo(parts, "assembleur2B");
        configureSkiDoo(parts, "assembleur3");
        configureSkiDoo(parts, "assembleur4");
        configureSkiDoo(parts, "assembleur4B");
        configureSkiDoo(parts, "assembleur5");
        configureSkiDoo(parts, "assembleur5B");
        configureSkiDoo(parts, "assembleur6");
        configureSkiDoo(parts, "assembleur7");
        configureSkiDoo(parts, "assembleur8");
        configureSkiDoo(parts, "assembleur9");

        // Générer toutes les pièces Spyder
        configureSpyder(parts, "assembleur1A");
        configureSpyder(parts, "assembleur2A");
        configureSpyder(parts, "assembleur2B");
        configureSpyder(parts, "assembleur3A");
        configureSpyder(parts, "assembleur4A");
        configureSpyder(parts, "assembleur4B");
        configureSpyder(parts, "assembleur5A");
        configureSpyder(parts, "assembleur5B");

        for (Part p : parts) {

            if (p.getId().equals(partId)) {
                return p;
            }
        }

        return null;
    }*/