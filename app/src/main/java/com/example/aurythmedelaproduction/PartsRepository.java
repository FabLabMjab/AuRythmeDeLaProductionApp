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
                new Part("BT7P2P", R.drawable.pieceassembleur5_1b),
                new Part("ZT5P", R.drawable.pieceassembleur5_2));

        register("Ski-Doo", "assembleur6",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMSAG", R.drawable.pieceassembleur6_2), // Ski avant, à continuer à partir d'ici
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur6A",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMSAG", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur6B",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMSAG", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur7",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMSAD", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur7A",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMSAD", R.drawable.pieceassembleur6_2),
                new Part("ZT2LR", R.drawable.pieceassembleur6_3),
                new Part("ZT4PX2P", R.drawable.pieceassembleur6_4));

        register("Ski-Doo", "assembleur7B",
                new Part("ZP", R.drawable.pieceassembleur6_1),
                new Part("ZSMSAD", R.drawable.pieceassembleur6_2),
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
                new Part("AT5P2P", R.drawable.spyder_pieceassembleur5_1b),
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