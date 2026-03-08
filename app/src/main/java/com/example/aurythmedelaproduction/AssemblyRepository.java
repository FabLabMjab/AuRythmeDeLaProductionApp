package com.example.aurythmedelaproduction;

public class AssemblyRepository {
    public static AssemblyStep getStep(String assembleurId, String vehicle) {

        if (vehicle.equals("Ski-Doo")) {
            return getSkiDooStep(assembleurId);
        }

        if (vehicle.equals("Spyder")) {
            return getSpyderStep(assembleurId);
        }

        return null;
    }

    private static AssemblyStep getSkiDooStep(String id) {

        switch (id) {

            /******* Les assembleur# et assembleur#A ont le même affichage (jaune)********/
            case "assembleur1":
            case "assembleur1A":
                return new AssemblyStep(R.drawable.assembleur1, "Installation de la transmission");


            case "assembleur2":
            case "assembleur2A":
                return new AssemblyStep(R.drawable.assembleur2, "Installation du chassis avant");


            case "assembleur3":
            case "assembleur3A":
                return new AssemblyStep(R.drawable.assembleur3, "Pose du volant et du banc");


            case "assembleur4":
            case "assembleur4A":
                return new AssemblyStep(R.drawable.assembleur4, "Installation du chassis inférieur et de la chenille");


            case "assembleur5":
            case "assembleur5A":
                return new AssemblyStep(R.drawable.assembleur5, "Installation latérale du chassis");


            case "assembleur6":
            case "assembleur6A":
                return new AssemblyStep(R.drawable.assembleur6, "Installation du patin gauche");


            case "assembleur7":
            case "assembleur7A":
                return new AssemblyStep(R.drawable.assembleur7, "Installation du patin droit");


            case "assembleur8":
            case "assembleur8A":
                return new AssemblyStep(R.drawable.assembleur8, "Assemblage de la structure de la chenille");


            case "assembleur9":
            case "assembleur9A":
                return new AssemblyStep(R.drawable.assembleur9, "Pose de la chenille");


            /******* Les assembleur#B ont un affichage différent (rouge)********/
            case "assembleur1B":
                return new AssemblyStep(R.drawable.assembleur1, "Installation de la transmission");


            case "assembleur2B":
                return new AssemblyStep(R.drawable.assembleur2b, "Installation du chassis avant");


            case "assembleur3B":
                return new AssemblyStep(R.drawable.assembleur3b, "Pose du volant et du banc");


            case "assembleur4B":
                return new AssemblyStep(R.drawable.assembleur4b, "Installation du chassis inférieur et de la chenille");


            case "assembleur5B":
                return new AssemblyStep(R.drawable.assembleur5b, "Installation latérale du chassise");


            case "assembleur6B":
                return new AssemblyStep(R.drawable.assembleur6b, "Installation du patin gauche");


            case "assembleur7B":
                return new AssemblyStep(R.drawable.assembleur7b, "Installation du patin droit");


            case "assembleur8B":
                return new AssemblyStep(R.drawable.assembleur8, "Assemblage de la structure de la chenille");


            case "assembleur9B":
                return new AssemblyStep(R.drawable.assembleur9, "Pose de la chenille");


        }

        return null;
    }

    private static AssemblyStep getSpyderStep(String id) {

        switch (id) {

            case "assembleur1A":
                return new AssemblyStep(R.drawable.assembleur1,"Installation de la transmission");

            case "assembleur2A":
                return new AssemblyStep(R.drawable.assembleur2,"Installation du chassis avant");


            case "assembleur3A":
                return new AssemblyStep(R.drawable.assembleur3,"Pose du volant et du banc");


            case "assembleur4A":
                return new AssemblyStep(R.drawable.spyder_assembleur4,"Installation du chassis arrière (première partie)");


            case "assembleur5A":
                return new AssemblyStep(R.drawable.spyder_assembleur5,"Installation du chassis arrière (deuxième partie)");


            case "assembleur1B":
                return new AssemblyStep(R.drawable.assembleur1,"Installation de la transmission");


            case "assembleur2B":
                return new AssemblyStep(R.drawable.assembleur2b,"Installation du chassis avant");


            case "assembleur3B":
                return new AssemblyStep(R.drawable.assembleur3b,"Pose du volant et du banc");


            case "assembleur4B":
                return new AssemblyStep(R.drawable.spyder_assembleur4b,"Installation du chassis arrière (première partie)");


            case "assembleur5B":
                return new AssemblyStep(R.drawable.spyder_assembleur5b,"Installation du chassis arrière (deuxième partie)");


        }

        return null;
    }
}
