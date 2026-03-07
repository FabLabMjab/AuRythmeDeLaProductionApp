package com.example.aurythmedelaproduction;

import java.util.ArrayList;
import java.util.List;

public class PartsRepository {

    public static List<Part> getPartsForAssembleur(String assembleurId, String vehicle) {

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
}
